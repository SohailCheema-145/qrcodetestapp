package com.example.qrcodetestapp.ui.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.qrcodetestapp.ui.compose.VideoPlayerWithQRDetection
import com.example.qrcodetestapp.ui.theme.QRCodeTestAppTheme
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.PlaybackException
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import java.net.UnknownHostException

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    // ViewModel instance for handling QR code detection
    private val qrCodeViewModel: QRCodeViewModel by viewModels()

    // ExoPlayer instance for video playback
    private lateinit var exoPlayer: SimpleExoPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initExoPlayer() // Initialize the ExoPlayer
        setContent {
            QRCodeTestAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Set up the composable with video player and QR code detection
                    VideoPlayerWithQRDetection(exoPlayer, qrCodeViewModel)
                }
            }
        }
    }

    private fun initExoPlayer() {
        // Initialize ExoPlayer
        exoPlayer = SimpleExoPlayer.Builder(this).build()

        // Create a MediaItem from the video URL provided by the ViewModel
        val mediaItem = MediaItem.fromUri(qrCodeViewModel.videoUrl)
        exoPlayer.setMediaItem(mediaItem)

        // Add a listener to handle playback state changes and errors
        exoPlayer.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(state: Int) {
                when (state) {
                    Player.STATE_READY -> {
                        // Video is ready to play
                        qrCodeViewModel.isLoading.value = false
                        if (exoPlayer.isPlaying) {
                            // Start QR code detection if the video is playing
                            qrCodeViewModel.startDetection()
                        }
                    }

                    Player.STATE_IDLE -> {
                        // Player is idle
                        qrCodeViewModel.isLoading.value = false
                        qrCodeViewModel.stopDetection() // Stop detection when idle
                    }

                    Player.STATE_ENDED -> {
                        // Video playback has ended
                        qrCodeViewModel.isLoading.value = false
                        qrCodeViewModel.isVideoEnded = true
                        qrCodeViewModel.stopDetection() // Stop detection when video ends
                    }

                    Player.STATE_BUFFERING -> {
                        // Video is buffering
                        qrCodeViewModel.isLoading.value = true
                    }
                }
            }

            override fun onPlayerError(error: PlaybackException) {
                super.onPlayerError(error)
                when (error.cause) {
                    is UnknownHostException -> {
                        // Handle network error due to no internet connection
                        showErrorToast("Network error: No internet connection.")
                    }

                    is IOException -> {
                        // Handle general network error
                        showErrorToast("Network error")
                    }

                    else -> {
                        // Handle other unknown errors
                        showErrorToast("An unknown error occurred.")
                    }
                }
            }

            private fun showErrorToast(message: String) {
                // Show a toast message on the main thread
                runOnUiThread {
                    Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
                }
            }
        })

        // Prepare the player and start playback
        exoPlayer.prepare()
        exoPlayer.playWhenReady = true
    }

    override fun onDestroy() {
        super.onDestroy()
        // Release the player resources when the activity is destroyed
        exoPlayer.release()
    }
}
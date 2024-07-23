package com.example.qrcodetestapp.ui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.qrcodetestapp.ui.main.QRCodeViewModel
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.PlayerView

/**
 * Composable function to display a video player with QR code detection.
 *
 * @param exoPlayer The ExoPlayer instance used for video playback.
 * @param qrCodeViewModel The ViewModel instance managing QR code detection and state.
 */
@Composable
fun VideoPlayerWithQRDetection(exoPlayer: SimpleExoPlayer, qrCodeViewModel: QRCodeViewModel) {
    // Observe the loading state and list of detected QR codes from the ViewModel
    val isLoading = qrCodeViewModel.isLoading.value
    val qrCodes by qrCodeViewModel.qrCodes.observeAsState(initial = emptyList())

    // Main layout container
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        // Container for video player and loader
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
        ) {
            // Video player view using AndroidView to integrate ExoPlayer's PlayerView
            AndroidView(
                factory = {
                    PlayerView(it).apply {
                        player = exoPlayer
                    }
                },
                modifier = Modifier
                    .align(Alignment.Center)
            )
            // Show a loading indicator while the video is buffering or processing
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(16.dp)
                )
            }
        }
        // Display detected QR codes in a scrollable column
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(qrCodes) { qrCode ->
                Text(
                    text = qrCode,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 1.dp)
                        .background(color = Color.LightGray)
                        .padding(horizontal = 16.dp, vertical = 4.dp),
                    color = Color.Black
                )
            }
        }
    }
}
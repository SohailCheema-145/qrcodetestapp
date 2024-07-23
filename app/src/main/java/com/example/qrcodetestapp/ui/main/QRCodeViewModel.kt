package com.example.qrcodetestapp.ui.main

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrcodetestapp.repositories.QRCodeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QRCodeViewModel @Inject constructor(private val repository: QRCodeRepository) : ViewModel() {

    // URL of the video to process
    val videoUrl =
//        "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
        "https://videocdn.cdnpk.net/videos/bee37727-87f6-47f2-a2ac-47d741c1ccbc/horizontal/previews/videvo_watermarked/large.mp4"
//        "https://videos.pexels.com/video-files/8830593/8830593-hd_1080_1920_30fps.mp4"
//        "https://videos.pexels.com/video-files/7287300/7287300-uhd_2560_1440_25fps.mp4"

    // MutableLiveData to hold and observe the list of detected QR codes
    private val _qrCodes = MutableLiveData<List<String>>()
    val qrCodes: LiveData<List<String>> = _qrCodes

    // State to indicate if loading is in progress
    var isLoading = mutableStateOf(true)

    // Flag to control whether QR code detection is active
    private var isDetecting = false

    // Flag to indicate if the video has ended
    var isVideoEnded = false

    /**
     * Start detecting QR codes from the video frames.
     */
    fun startDetection() {
        // If detection is already running, exit the function
        if (isDetecting) return

        // Set detection flag to true
        isDetecting = true

        // If the video has ended, reset the QR codes and the flag
        if (isVideoEnded) {
            _qrCodes.value = emptyList()
            isVideoEnded = false
        }

        // Launch a coroutine to process video frames
        viewModelScope.launch(Dispatchers.IO) {
            while (isDetecting) {
                // Extract a frame from the video URL
                val bitmap = repository.extractFrameFromVideoUrl(videoUrl)

                // If a frame was successfully extracted
                if (bitmap != null) {
                    // Detect QR codes from the frame
                    val detectedQRCodes = repository.detectQRCodes(bitmap)

                    // Combine newly detected QR codes with previously detected ones
                    val allQRCodes: MutableList<String> = if (_qrCodes.value.isNullOrEmpty()) {
                        mutableListOf()
                    } else _qrCodes.value!!.toMutableList()
                    allQRCodes.addAll(detectedQRCodes)

                    // Update the LiveData with the combined list of QR codes
                    _qrCodes.postValue(allQRCodes)
                }

                // Delay before processing the next frame
                delay(100)
            }
        }
    }

    /**
     * Stop QR code detection.
     */
    fun stopDetection() {
        // Launch a coroutine to handle stopping the detection
        viewModelScope.launch {
            // Add a short delay before stopping detection
            delay(100)
            // Set the detecting flag to false
            isDetecting = false
        }
    }
}
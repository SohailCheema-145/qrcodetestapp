package com.example.qrcodetestapp.repositories

import android.graphics.Bitmap
import android.media.MediaMetadataRetriever
import android.util.Log
import com.google.android.gms.tasks.Tasks
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.common.InputImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Repository class responsible for interacting with the QR code detection services.
 *
 * @param barcodeScanner The BarcodeScanner instance used to detect QR codes.
 */
class QRCodeRepository @Inject constructor(private val barcodeScanner: BarcodeScanner) {

    companion object {
        // Log tag for logging errors and information
        const val TAG = "QRCodeRepository"
    }

    /**
     * Extracts a frame from a video URL.
     *
     * This function retrieves a frame at the specified time (0 milliseconds) from the video
     * located at the provided URL. It uses MediaMetadataRetriever to extract the frame.
     *
     * @param videoUrl The URL of the video from which the frame is to be extracted.
     * @return A [Bitmap] of the extracted frame, or null if an error occurs.
     */
    suspend fun extractFrameFromVideoUrl(videoUrl: String): Bitmap? = withContext(Dispatchers.IO) {
        val retriever = MediaMetadataRetriever()
        return@withContext try {
            // Set the data source of the MediaMetadataRetriever
            retriever.setDataSource(videoUrl, HashMap())
            // Retrieve the frame at time 0
            retriever.getFrameAtTime(0)
        } catch (e: IllegalArgumentException) {
            // Log error if video URL is invalid
            Log.e(TAG, "Error accessing video from URL", e)
            null
        } finally {
            // Release resources
            retriever.release()
        }
    }

    /**
     * Detects QR codes in a given bitmap image.
     *
     * This function processes the provided bitmap image using ML Kit's BarcodeScanner to detect
     * QR codes and returns a list of detected QR codes' display values.
     *
     * @param bitmap The bitmap image in which QR codes are to be detected.
     * @return A list of strings representing detected QR codes' values.
     */
    suspend fun detectQRCodes(bitmap: Bitmap): List<String> = withContext(Dispatchers.IO) {
        val image = InputImage.fromBitmap(bitmap, 0)
        // Use ML Kit to process the image and detect QR codes
        val barcodes = Tasks.await(barcodeScanner.process(image))
        // Extract and return the display values of detected QR codes
        return@withContext barcodes.mapNotNull { it.displayValue }
    }
}
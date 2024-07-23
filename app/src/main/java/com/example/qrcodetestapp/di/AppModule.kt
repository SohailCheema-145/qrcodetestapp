package com.example.qrcodetestapp.di

import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScanning
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module for providing dependencies using Dagger-Hilt.
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides an instance of BarcodeScanner.
     * This method returns a BarcodeScanner client that can be used to scan barcodes.
     *
     * @return A [BarcodeScanner] instance for barcode detection.
     */
    @Provides
    @Singleton
    fun provideBarcodeScanner(): BarcodeScanner {
        // Return an instance of BarcodeScanner using ML Kit's BarcodeScanning API
        return BarcodeScanning.getClient()
    }
}
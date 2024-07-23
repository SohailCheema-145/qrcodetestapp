package com.example.qrcodetestapp.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * The Application class for the QR Code Test App.
 *
 * This class is annotated with @HiltAndroidApp to enable Hilt dependency injection in the application.
 * It is a base class for maintaining global application state and setting up dependency injection.
 */
@HiltAndroidApp
class QrCodeApplication : Application()

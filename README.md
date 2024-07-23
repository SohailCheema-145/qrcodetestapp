                                                                                    **QR Code Test App**
**Overview**
The QR Code Test App is an Android application designed to detect QR codes from video streams. It utilizes the ExoPlayer library for video playback and the Google Vision API for QR code detection. This app showcases the integration of modern Android architecture components and dependency injection using Hilt.

Features
Video Playback: Streams video content using ExoPlayer.
QR Code Detection: Detects QR codes in video frames in real-time using Google Vision API.
Loading and Error Handling: Displays loading indicators and handles network errors gracefully.
Architecture
The app follows the MVVM (Model-View-ViewModel) architecture pattern combined with Clean Architecture principles. Here’s a brief overview:

Model: Represents the data layer, including repository classes and data sources. The QRCodeRepository interacts with the Google Vision API to extract and detect QR codes from video frames.
View: Represents the UI components, implemented using Jetpack Compose. The VideoPlayerWithQRDetection composable displays the video player and detected QR codes.
ViewModel: Contains business logic and state management. The QRCodeViewModel handles video processing, QR code detection, and communicates with the UI.
Dependencies
The app uses several third-party libraries and tools:

Hilt: Dependency injection library for Android. It simplifies the process of providing dependencies across the app and managing their lifecycle.

Annotation Processor: dagger.hilt.android:hilt-android-compiler for generating code.
Hilt Android Components: dagger.hilt.android:hilt-android for integrating Hilt with Android components.
Google ML Kit - Barcode Scanning: Provides advanced barcode and QR code detection capabilities.

ML Kit Vision API: com.google.mlkit:barcode-scanning for QR code detection.
ExoPlayer: A powerful media player library for Android that supports a wide range of media formats and streaming protocols.

Core ExoPlayer: com.google.android.exoplayer:exoplayer-core
UI Components: com.google.android.exoplayer:exoplayer-ui
Jetpack Compose: A modern toolkit for building native UI with Kotlin.

Material3: androidx.compose.material3:material3
UI Toolkit: androidx.compose.ui:ui-tooling-preview
Architecture and Framework
MVVM Pattern
Model: Encapsulates the data layer. For this app, the QRCodeRepository class handles the extraction of video frames and QR code detection.
View: Composed of UI elements defined using Jetpack Compose. The VideoPlayerWithQRDetection composable manages video playback and displays detected QR codes.
ViewModel: Manages UI-related data and business logic. The QRCodeViewModel interacts with the repository to perform QR code detection and maintains UI state.
Clean Architecture
Separation of Concerns: Clear separation between data, domain, and presentation layers. This improves maintainability and testability.
Dependency Injection: Hilt is used to manage dependencies and provide them to the ViewModel and repository.
Getting Started
Prerequisites
Android Studio: Ensure you have the latest version of Android Studio.
Kotlin: The app is written in Kotlin.
Installation
Clone the Repository

bash
Copy code
git clone https://github.com/SohailCheema-145/qrcodetestapp.git
Open in Android Studio

Open the project in Android Studio.

Build the Project

Use the build tools provided by Android Studio to build the project.

Run the App

Connect an Android device or use an emulator to run the app.

Contributing
Feel free to submit issues, suggest improvements, or make pull requests. Please ensure that any contributions adhere to the project's coding standards and guidelines.

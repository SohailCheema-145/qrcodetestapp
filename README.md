# **QR Code Detection Android App**

## **Project Overview**

The QR Code Detection Android App is an application designed to detect QR codes from a video stream using the Google ML Kit's Barcode Scanning API. This project showcases the integration of various technologies and libraries to achieve real-time QR code detection.

## **Features**

- **Video Playback**: Uses ExoPlayer for smooth video playback.
- **QR Code Detection**: Real-time detection of QR codes in video frames.
- **UI Components**: Displays detected QR codes and provides feedback on video loading and playback status.

## **Technologies and Libraries**

### **1. Hilt**

- **Description**: A dependency injection library for Android that simplifies Dagger setup.
- **Usage**: Provides dependencies like `QRCodeRepository` and `BarcodeScanner`.

### **2. Google ML Kit**

- **Description**: Google's machine learning library for mobile apps.
- **Usage**: Utilized the Barcode Scanning API for QR code detection.

### **3. ExoPlayer**

- **Description**: A powerful media player library for Android.
- **Usage**: Handles video playback within the application.

### **4. Jetpack Compose**

- **Description**: Android’s modern toolkit for building native UI.
- **Usage**: Creates UI components such as video player and QR code display.

## **Architecture**

The application is structured using the **MVVM (Model-View-ViewModel)** architecture with the following components:

- **Model**: Manages data and business logic. In this app, it includes the `QRCodeRepository` which handles QR code detection and video frame extraction.
- **View**: The UI components created with Jetpack Compose, including the `VideoPlayerWithQRDetection` composable function.
- **ViewModel**: Acts as a bridge between the Model and View, providing data and handling business logic. The `QRCodeViewModel` manages video playback state and QR code detection logic.

### **Dependency Injection**

- **Hilt** is used to inject dependencies such as `QRCodeRepository` and `BarcodeScanner` into the ViewModel.

## **Setup and Installation**

1. **Clone the Repository**:

    ```bash
    git clone https://github.com/SohailCheema-145/qrcodetestapp.git
    ```

2. **Navigate to the Project Directory**:

    ```bash
    cd qrcodetestapp
    ```

3. **Open the Project in Android Studio**:
    - Open Android Studio and select "Open an existing project".
    - Navigate to the cloned project directory and open it.

4. **Build the Project**:
    - Sync the project with Gradle files.
    - Build and run the project on an Android device or emulator.

## **Usage**

- **Run the App**: Launch the app on a device or emulator.
- **View QR Codes**: The app will automatically start detecting QR codes from the provided video URL.

## **Contributing**

- **Fork** the repository and **create** a new branch for your changes.
- **Submit** a pull request with a description of your changes.

## **Acknowledgements**

- **Google ML Kit**: For the barcode scanning capabilities.
- **ExoPlayer**: For media playback.
- **Jetpack Compose**: For building the UI.

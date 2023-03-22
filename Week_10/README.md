 # Mobile Application Development
## FireBase
### Author: Viraj Ranaware

## Setting up Firebase Project

- Create FireBase Account using Gmail Id (not .edu or any other domain)
- Create FireBase Project
		https://console.firebase.google.com
- Create Android Project
- Follow steps on the Firebase console to connect Android project to the FireBase Project

<img width="600" alt="image" src="https://user-images.githubusercontent.com/112779376/226796094-d3773221-d102-4784-8628-7335c14a586f.png">

- Download google-services.json file and move it into your module (app-level) root directory.
- Update Root-level (project-level) Gradle file (<project>/build.gradle):
```
buildscript {
  repositories {
    // Make sure that you have the following two repositories
    google()  // Google's Maven repository
				mavenCentral()  // Maven Central repository
  }
  dependencies {
    // Add the dependency for the Google services Gradle plugin
    classpath 'com.google.gms:google-services:4.3.15'
  }
}

allprojects {
  repositories {
    // Make sure that you have the following two repositories
    google()  // Google's Maven repository
    mavenCentral()  // Maven Central repository
  }
}
```
- Update Module (app-level) Gradle file (<project>/<app-module>/build.gradle):
```
plugins {
  id 'com.android.application'

  // Add the Google services Gradle plugin
  id 'com.google.gms.google-services'
}

dependencies {
  // Import the Firebase BoM
  implementation platform('com.google.firebase:firebase-bom:31.2.3')
		
  // TODO: Add the dependencies for Firebase products you want to use
  // When using the BoM, don't specify versions in Firebase dependencies
  implementation 'com.google.firebase:firebase-analytics'
		
  // Add the dependencies for any other desired Firebase products
  // https://firebase.google.com/docs/android/setup#available-libraries
}
```
- After adding the plug-in and the desired SDKs, sync your Android project with the Gradle files.

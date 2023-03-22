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
	

## Email and Password Authentication

- To Enable Email and Password Authentication, Go to Authentication -> Sign-In method -> Sign-in providers -> Enable Email/Password
<img width="1336" alt="image" src="https://user-images.githubusercontent.com/112779376/226797124-8dffef5d-1a75-4dd9-b67c-eecd9837cde9.png">

- Go to Docs for steps to follow:
	https://firebase.google.com/docs/auth/android/start?hl=en&authuser=0
- Add Authentication to the App: In your module (app-level) Gradle file (usually <project>/<app-module>/build.gradle), add the dependency for the Firebase Authentication Android library.
```
dependencies {
    // Import the BoM for the Firebase platform
    implementation platform('com.google.firebase:firebase-bom:31.2.3')

    // Add the dependency for the Firebase Authentication library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation 'com.google.firebase:firebase-auth-ktx'
}
```
- Click Sync now to download dependencies.

## Login with Email and Password
- In your sign-up activity's onCreate method (onCreateView() of fragment), get the shared instance of the FirebaseAuth object:
```
    private FirebaseAuth mAuth;
    mAuth = FirebaseAuth.getInstance();
```
- When a user signs in to your app, pass the user's email address and password to signInWithEmailAndPassword:
	
```
    mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "onComplete: " + " Login Successful.");
                    	Log.d(TAG, "onComplete: " + " Login Successful using Email: " + mAuth.getCurrentUser().getEmail());
                    } else {
                        Log.d(TAG, "onFailure: " + task.getException().getMessage());
                    }
                }
            });
```

## Sign up new user

```	
    mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "onComplete: " + " Registration Successful.");
                        Log.d(TAG, "onComplete: " + " Registration Successful using Email: " + mAuth.getCurrentUser().getEmail());
                    } else {
                        Log.d(TAG, "onFailure: " + task.getException().getMessage());
                    }
                }
            });
```

## FireStore - Cloud DB
- Add below dependencies in app build.gradle file.
```
dependencies {
    // Import the BoM for the Firebase platform
    implementation platform('com.google.firebase:firebase-bom:31.2.3')

    // Declare the dependency for the Cloud Firestore library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation 'com.google.firebase:firebase-firestore'
}
```
- Initialize an instance of Cloud Firestore:
```
FirebaseFirestore db = FirebaseFirestore.getInstance();
```
- Retrieve data using FireStore DB instance (db)
```
    private void getData() {
        // [START get_all_contacts]
        db.collection("contacts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
        // [END get_all_contacts]
    }
```

- To avoid dex exception - Cannot fix requetsed classes in a single dex file, configure below in app.gradle
```
android {
    defaultConfig {
        ...
        multiDexEnabled true
    }
}

dependencies {
    ...
    implementation "androidx.multidex:multidex:2.0.1"
}
```

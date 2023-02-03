# Mobile Application Development
## View Binding and Fragments
### Author: Viraj Ranaware

Welcome to the itis-5180-mad wiki! This repository contains all basics of Android Application development using java along with samples. 
## View Binding
View binding is a feature that allows you to more easily write code that interacts with views. Once view binding is enabled in a module, it generates a binding class for each XML layout file present in that module. An instance of a binding class contains direct references to all views that have an ID in the corresponding layout.
In most cases, view binding replaces findViewById.

To enable View Binding add below snnipet in build.gradle file.

**Enabling View Binding**
```
android {
    ...
    buildFeatures {
        viewBinding true
    }
}
```

 
 

**Table Example:**

| col1 | col2 |
| ----------- | ----- |
|row1	      | row1 |
|row2	    | row1 |

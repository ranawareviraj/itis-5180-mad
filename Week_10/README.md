 # Mobile Application Development
## Making HTTP Connections and API Parsing
### Author: Viraj Ranaware

Welcome to the itis-5180-mad wiki! This repository contains all basics of Android Application development using java along with samples. 

## Giving internet persmissions

Code template
```
<uses-permission android:name="android.permission.INTERNET"></uses-permission>
```

**Making request**
```
 protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Request request = new Request.Builder()
                .url("https://publicobject.com/helloworld.txt")
                .build();
```


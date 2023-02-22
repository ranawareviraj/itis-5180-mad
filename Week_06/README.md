 # Mobile Application Development
## Making HTTP Connections and API Parsing
### Author: Viraj Ranaware

Welcome to the itis-5180-mad wiki! This repository contains all basics of Android Application development using java along with samples. 

## Giving internet persmissions
Update below configuration in AndroidManifest.xml.
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

**Create OkHttpClient**
```
private final OkHttpClient client = new OkHttpClient();
```

**Set Response callback listener for client**
```
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    ResponseBody responseBody = response.body();
                    String body = responseBody.string();
                    Log.d("API", "onResponse: " + body);
                }
            }
        });    
 ```

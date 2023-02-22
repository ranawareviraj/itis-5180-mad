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
If we dont enque response and use it in main thred directly, app throws android.os.NetworkOnMainThreadException.

To allow http turn on this flag in AndroidManifest.xml under application tag.
 ```
 <application
    android:usesCleartextTraffic="true"
 </application
 ```
 **Build complex URL String**
 ```
    HttpUrl url = HttpUrl.parse("https://www.theappsdr.com").newBuilder()
            .addPathSegment("contacts")
            .addPathSegment("json")
            .build();
 ```
  **Build complex URL String - using builder**
  ```
  HttpUrl.Builder builder = new HttpUrl.Builder();
  HttpUrl url = builder.scheme("https")
          .host("www.theappsdr.com")
          .addPathSegment("contacts")
          .addPathSegment("json")
          .build();
 ```

**Sending POST Request**
 ```
 private final OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createContact("Tango Charlie", "tc@tc.com", "111-222-3333", "CELL");
    }
    
    void createContact(String name, String email, String phone, String type) {
        HttpUrl url = HttpUrl.parse("https://www.theappsdr.com")
                .newBuilder()
                .addPathSegment("contact")
                .addPathSegment("json")
                .addPathSegment("create")
                .build();

        FormBody formBody = new FormBody.Builder()
                .add("name", name)
                .add("email", email)
                .add("phone", phone)
                .add("type", type)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
                
        Log.d("API", "createContact: "+ request);
        
        client.newCall(request).enqueue(new Callback() {
        
        public void onFailure(@NonNull Call call, @NonNull IOException e) {}
        
        public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {}
        }
    });
 ```

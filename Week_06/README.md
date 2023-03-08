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

**Updating UI after Async API Response**
```
 runOnUiThread(new Runnable() {
     @Override
     public void run() {
          System.out.println();
     }
 });
```

**Json Parsing**
```
ResponseBody responseBody = response.body();
String body = responseBody.string();

    try {
         JSONObject jsonObject = new JSONObject(body);
         Log.d("API", "onResponse: " + jsonObject);
         JSONArray personsArray  = jsonObject.getJSONArray("persons");
         // Heere we can use loop to get all person objects. We'll simply get first object
         JSONObject person = personsArray.getJSONObject(0);
         
         //Using getString(String key), we can retrieve bject properties
         String name = person.getString("name");
         } catch (JSONException e) {
               throw new RuntimeException(e);
         }
```
**Json Parsing - JSON**
```
==> Persons JSON:
{
    "persons": [
        {
            "name": "Bob Smith",
            "id": "80000001",
            "age": 25,
            "address": {
                "line1": "32 Newport Lane",
                "city": "Warren",
                "state": "MI",
                "zip": "48089"
            }
        }
    ]
}

==> Person JSON:
{
     "name": "Bob Smith",
     "id": "80000001",
     "age": 25,
     "address": {}
}
```
**Json Parsing - JAVA Objects**
```
public class Address{
    public String line1;
    public String city;
    public String state;
    public String zip;
}

public class Person{
    public String name;
    public String id;
    public int age;
    public Address address;
}

public class PersonsResponse{
    public ArrayList<Person> persons;
}
``` 

**Using GSON to parse JSON Object**
```  
Gson gson = new Gson();
Person person = gson.fromJson(responseBody.charStream(), Person.class);
```  

**Using GSON to parse JSON Array**
``` 
  Gson gson = new Gson();
  PersonsResponse persons = gson.fromJson(responseBody.charStream(), PersonsResponse.class);
```
Note: When using GSON, the java object properties should match with the json object properities.

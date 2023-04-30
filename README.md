# Mobile Application Development
## Views and Activities
### Author: Viraj Ranaware

Welcome to the itis-5180-mad wiki! This repository contains all basics of Android Application development using java along with samples. 


## Views, Logging and Exceptions
This module introduces Views and other basic concepts.

### Views:
  - TextView
  - EditText
  - Button
  - RadioGroup
  - Seekbar/Slider
  - ImageView


**TextView**
```java
  // Initialize TextView
  TextView textViewWeight = findViewById(R.id.textViewWeightValue);

  double weight = 100.00;
  textViewWeight.setText(String.valueOf(price));
```

**EditText**
```java
  // Initialize EditText
  EditText ticketPrice = findViewById(R.id.ticket_price_value);

  // Get the value from EditText
  String ticketPriceEntered = ticketPrice.getText().toString();

  // Set the value for EditText
  ticketPrice.setText("");
```

**Button**
```java
  // Handling button on click (inside onCreate())
  findViewById(R.id.AlertButton).setOnClickListener(new View.OnClickListener(){ @Override
    public void onClick(View v){
      // Actions to perform on click
    }
  });

  // Handling button on click - multiple buttons
  protected void onCreate(Bundle savedInstanceState) {
    Button button1 = findViewById(R.id.button1);
    Button button2 = findViewById(R.id.button2);

    button1.setOnClickListener(this);
    button2.setOnClickListener(this);
  }

    @Override
    public void onClick(View button) {
        if (button.getId() == R.id.button1) {
            Log.d(TAG, "onClick: User clicked Button 1 ..!");
        } else if (button.getId() == R.id.button2) {
            Log.d(TAG, "onClick: User clicked Button 2 ..!");
        }
    }
}
```

**RadioGroup**
```java
  RadioGroup radioGroupGender.getCheckedRadioButtonId() == R.id.radioButtonMale

  //Check which radio group is checked
  radioGroupGender.getCheckedRadioButtonId() == R.id.radioButtonMale
```

**ImageView**
```java
   public class MainActivity extends AppCompatActivity {
      ImageView imageView;

      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          imageView = findViewById(R.id.imageView);

          findViewById(R.id.buttonSad).setOnClickListener(view -> {
              imageView.setImageResource(R.drawable.sad)
            });

          findViewById(R.id.buttonSmile).setOnClickListener(view -> {
            imageView.setImageResource(R.drawable.smile)
            });
      }
  }
```

**SeekBar**
```java
  @Override
  protected void onCreate(Bundle savedInstanceState) {
      textViewSeekBar = findViewById(R.id.textViewSeekBar);
      SeekBar seekBar = findViewById(R.id.seekBar);

      seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
          @Override
          public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
              Log.d(TAG, "onProgressChanged: " + progress);
              textViewSeekBar.setText(String.valueOf(progress));
          }

          @Override
          public void onStartTrackingTouch(SeekBar seekBar) {
              Log.d(TAG, "onStartTrackingTouch: " + seekBar.getProgress());
          }

          @Override
          public void onStopTrackingTouch(SeekBar seekBar) {
              Log.d(TAG, "onStopTrackingTouch: " + seekBar.getProgress());
          }
      });
  }
```

### Logging
**LogD**
```java
  Log.d(TAG, "OnCreate: ");
```

**Toast**
```java
  import android.widget.Toast;

  Toast.makeText(MainActivity.this, "Enter a valid ticket price",Toast.LENGTH_SHORT).show();
```

**Set Title:**
```java
  {  
    setTitle("Main Activity");
  }
```

**Alert Dialog**
```java
AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

builder.setTitle("Error")
    .setMessage("Set Message")
    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        Log.d(TAG, "Positive");
      }
    })
    .setNegativeButton("cancel", new DialogInterface.OnClickListener() { @Override
      public void onClick(DialogInterface dialogInterface, int i) {
        Log.d(TAG, "Negative"); }
    });

    //Display Alert
    builder.create().show();
```

## Exceptions

**NumberFormatException**
```java
    try {
        double weight = Double.valueOf(editTextWeight.getText().toString());
        if (weight > 0) {
            Profile profile = new Profile(weight, gender);
            Intent intent = new Intent();
            intent.putExtra(PROFILE_KEY, profile);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Toast.makeText(SetProfileActivity.this, "Enter a valid weight", Toast.LENGTH_SHORT).show();
        }
    } catch (
        NumberFormatException ex) {
        Toast.makeText(SetProfileActivity.this, "Enter a valid weight", Toast.LENGTH_SHORT).show();
    }
```

## Activities

This module explains Activity Lifecycle and how to share data between multiple Activities.


## Activity Lifecycle
**Activity Lifecycle Methods**
```java
  @Override
  protected void onResume() {
    super.onResume();
    Log.d(TAG, "On resume");
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    Log.d(TAG, "On destory");
  }

  @Override
  protected void onPause() {
    super.onPause();
    Log.d(TAG, "On pause");
  }

  @Override
  protected void onStop() {
    super.onStop();
    Log.d(TAG, "On stop");
  }
```

## Intent

**Explicit Intent**
```java
// Go to Next page
  Intent intent = new Intent(MainActivity.this, SecondActivity.class);
  startActivity(intent);
```


**Implicit Intent**
  ```java
  // In Source Activivity class
  Intent intent = new Intent("com.example.practiceapp.intent.action.View");
  intent.addCategory(Intent.CATEGORY_DEFAULT);

  // Intent Filter: in android Manifest xml
    <intent-filter>
      <action android:name="com.example.practiceapp.intent.action.View">
      </action> <category android:name="android.intent.category.DEFAULT" />
    </intent-filter>
  ```

## Pass Data using Intent

### 1. Pass data in KEY-VALUE Format
**Data to pass**
  ```java
  final static public String NAME_KEY = "Name";
  ```

**Pass Data using Intent - in source activity**
  ```java
  Intent intent = new IntenMainActivity.this, SecondActivity.class);
  intent.putExtra(NAME_KEY, "Viraj Ranaware");
  String name = getIntent().getStringExtra(MainActivity.NAME_KEY);
  ```

**Recieve data - in target activity**
  ```java
  if(getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra("Name")){
    String name = getIntent().getStringExtra(MainActivity.NAME_KEY);
    nameText.setText(name);
  }
  ```

### 2. Pass serializable - object
**Data to pass - User**
  ```java
  class User implements Serializable{
    String name;
    int age;

    User(String name, int age){
      this.name = name;
      this.age = age;
    }
  }
  ```

**Pass Data using Intent - in source activity**
  ```java
  Intent intent = new IntenMainActivity.this, SecondActivity.class);
  User user = new User("Viraj Ranaware", 28)
  intent.putExtra(User_KEY, user);

  // Switch to Activity specified
  startActivity(intent);
  ```

**Recieve data - in target activity**
  ```java
  if (getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(NAME_KEY)){
    User user = (User) getIntent().getSerializableExtra(USER_KEY);
    String name = user.name;
    textViewGreeting.setText("Hello " + name + " !");
    Log.d(MainActivity.TAG, "onCreate: User retrieved");
  }
  ```

### 3. Pass serializable - List of Objects
  Object must be serializable. Use below code in the source activity to pass the data to the target activity


**Data to pass - List of Serializable Objects**
  ```java
  ArrayList<User> users = new ArrayList<>();
  users.add(new User("Alice", 22));
  users.add(new User("Bob", 24));
  ```

 **Pass Data using Intent - in source activity**
  ```java
  public void onClick(View v) {
    Intent intent = new IntenMainActivity.this, SecondActivity.class);
    //Pass List
      ArrayList<User> users = new ArrayList<>();
      users.add(new User("Alice", 22));
      users.add(new User("Bob", 24));
      intent.putExtra(USERS_KEY, users);

      // Switch to Activity specified
      startActivity(intent);
    }
  ```
**Recieve data - in target activity**
  ```java
  // Retrieving List<Object>
    if (getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra(USERS_KEY)){
      List<User> users = (List<User>) getIntent().getSerializableExtra(USERS_KEY);
      Collections.shuffle(users);
      User user = users.get(0);
      String name = user.name;
      textViewGreeting.setText("Hello " + name + " !");
      Log.d(MainActivity.TAG, "onCreate: User retrieved");
    }
  ```
## Recieve Data from launched activity

  ### 1. Start Activity for result (Deprecated)

  **startActivityForResult - In Original Activity**  
  ```java
    // Start Activity for result
      @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.buttonGoToSecond).setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
        });
    }

    // Capture Result:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null && data.hasExtra(SecondActivity.NAME)) {
                textViewProfile.setText("Hi " + data.getStringExtra(SecondActivity.NAME) + " !");
                Log.d(TAG, "onActivityResult: RESULT_OK");
            }
        } else if (resultCode == RESULT_CANCELED) {
            Log.d(TAG, "onActivityResult: RESULT_CANCELED");
        }
    }
  ```
  **startActivityForResult - In Second Activity**  
  ```java
  // Send data back
  buttonGoBack.setOnClickListener(view -> {
     Intent intent = new Intent();
     String name = editTextPersonName.getText().toString();
     intent.putExtra(NAME, name);
     setResult(RESULT_OK, intent);
     finish();
  });

   // HIT Cancel button  
   buttonCancel.setOnClickListener(view -> {
      setResult(RESULT_CANCELED);
      finish();
   });

  ```

  ### 2. Using ActivityResultContract

**ActivityResultContract - In Original Activity**  
  ```java
  ActivityResultLauncher<Intent> startForSetProfile = registerForActivityResult(
    new ActivityResultContracts.StartActivityForResult(),
    new ActivityResultCallback<ActivityResult>() {
      @Override
      public void onActivityResult(ActivityResult result) {
        if(result != null && result.getResultCode() == RESULT_OK){
          if(result.getData() != null && result.getData().getSerializableExtra(PROFILE_KEY) != null){
            Log.d("demo","in set Profile");
            profile = (Profile) result.getData().getSerializableExtra(MainActivity.PROFILE_KEY);
            textViewWeightValue.setText(profile.weight);     
          }
        }
      }
    });
  ```


## View Binding and Fragments

Welcome to the itis-5180-mad wiki! This repository contains all basics of Android Application development using java along with samples. 
## View Binding
View binding is a feature that allows you to more easily write code that interacts with views. Once view binding is enabled in a module, it generates a binding class for each XML layout file present in that module. An instance of a binding class contains direct references to all views that have an ID in the corresponding layout.
In most cases, view binding replaces findViewById.

To enable View Binding add below snnipet in build.gradle file.

**Enabling View Binding**
```java
    android {
        ...
        buildFeatures {
            viewBinding true
        }
    }
```

### Use view binding in activities
To set up an instance of the binding class for use with an activity, perform the following steps in the activity's onCreate() method:

    Step 1. Call the static inflate() method to create an instance of the binding class for the activity to use.
    Step 2. Get a reference to the root view by calling the getRoot() method
    Step 3. Pass it to setContentView() to make it the active view on the screen.
 
 ```java
    private MainActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainActivityBinding.inflate(getLayoutInflater());
        View rootView = binding.getRoot();
        setContentView(rootView);
    }
 ```
**Using Binding to reference any of the view**
 ```java
    // Using binding for a TextView
     binding.getName().setText(viewModel.getName());
     
     // Using binding for a Button
     binding.getButton().setOnClickListener(new View.OnClickListener() {
        viewModel.userClicked()
     });
 ```

Note: With binding we dont need to specify view type.

### Use view binding in fragments
To set up an instance of the binding class for use with a fragment, perform the following steps in the fragment's onCreateView() method:
 ```java
    Step 1. Call the static inflate() method of the binding class to create an instance of the binding class for the fragment to use.
    Step 2. Get a reference to the root view by calling the getRoot() method.
    Step 3. Return the root view from the onCreateView() method to make it the active view on the screen.
 ```
 
**Using View Binding in Fragments**
 ```java
    private ResultProfileBinding binding;

    @Override
    public View onCreateView (LayoutInflater inflater,
                              ViewGroup container,
                              Bundle savedInstanceState) {
        binding = ResultProfileBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
 ```
### Add a fragment to an activity

**1. Using FragmentContainerView**

Use FragmentContainerView in the Design and select appropriate Fragment class.

<img width="450" height="200" alt="image" src="https://user-images.githubusercontent.com/112779376/217070607-a7077ea0-c7c8-4cbd-b753-e83b7a0ac943.png"> <img width="450" height="200" alt="image" src="https://user-images.githubusercontent.com/112779376/217070729-1e8070ac-399d-4125-ad29-79b2545114f7.png">


**2. Adding programmatically**
 ```java    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
            getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container_view, new ExampleFragment())
                .commit();
    }
 ```
### Fragment Methods
**onCreate()** 
 ```java
   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
 ```
 
 **onCreateView() : initialize binding, return content root** 
 ```java
FragmentFirstBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
 ```
 
 **onViewCreated() : Logic to work with the fragment** 
 ```java
 @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("First Activity");

        binding.button.setOnClickListener(v -> {
            String name = binding.editTextUsername.getText().toString();
            Toast.makeText(getActivity(), "Hello " + name + " !", Toast.LENGTH_SHORT).show();
        });
    }
 ```
 **onAttach() - called when Fragment is attached to the Activity**
 ```java
  @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);                    // context = Activity
    }
 ```
 ### Sending Data from Fragment to Activity
This is done using an interface.
```java
    Step 1: Define interface(Listener) in the Fragment
    Step 2: Activity implemnts the Interface
    Step 3: In the fragment, cast context(activity) to listener.
    Step 4: User listner to send data back.
    Step 5: Capture data in the activity - Step 3 can be extended if needed
```

**Step 1: Define interface in the Fragment**
 ```java
     public interface FirstListener {
        void sendUsername(String username);
    }
 ```

**Step 2: Activity implemnts the Interface**
```java
     public class MainActivity extends AppCompatActivity implements FirstFragment.FirstListener {
         @Override
        public void sendUsername(String username) {
            Log.d(TAG, "sendUsername: " + username);
        }
     }
```
**Step 3: In the fragment, cast context(activity) to listener.**
```java
    FirstListener fListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);                // context = Activity
        fListener = (FirstListener) context;    // As Activity aka context implements listener, context can be casted back to listener
    }
```
**Step 4: User listner to send data back.**
```java
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("First Activity");

        binding.button.setOnClickListener(v -> {
            String name = binding.editTextUsername.getText().toString();
            fListener.sendUsername(name);
        });
    }
```
**Step 5: Capture data in the activity - Step 3 can be extended if needed**
```java
    @Override
    public void sendUsername(String username) {
        Log.d(TAG, "sendUsername: " + username);
    }
```
### Sending Data from Fragment to Activity - Objects
```java
//  Step 1: Define interface(Listener) in the Fragment
     public interface FirstListener {
            void sendProfile(Profile profile);
     }
 
//  Step 2: Activity implemnts the Interface
    public class MainActivity extends AppCompatActivity 
                              implements FirstFragment.FirstListener {
    }
    
//  Step 3: In the fragment, cast context(activity) to listener.
    FirstListener fListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);                // context = Activity
        fListener = (FirstListener) context;    // As Activity aka context implements listener, context can be casted back to listener
    }
    
//  Step 4: User listner to send data back.
    binding.buttonSubmit.setOnClickListener(v -> {
            String name = binding.editTextUsername.getText().toString();
            if (name.isEmpty()) {
                Toast.makeText(getActivity(), "Enter Valid usename", Toast.LENGTH_SHORT).show();
            } else {
                try {
                    double age = Double.parseDouble(binding.editTextAge.getText().toString());
                    Profile profile = new Profile(name, age);
                    fListener.sendProfile(profile);
                } catch (NumberFormatException exception) {
                    Toast.makeText(getActivity(), "Enter valid age", Toast.LENGTH_SHORT).show();
                }
            }
        });

//  Step 5: Capture data in the activity - Step 3 can be extended if needed
    @Override
    public void sendProfile(Profile profile) {
        Log.d(TAG, "sendProfile: " + profile);
    }
```

### We can use interface method to replace Fragment

**In First Fragment - Interface has goToSecondFragment() method**
```java
    public interface FirstListener {
        void sendUsername(String username);
        void goToSecondFragment();
    }
```
**In MainActivty - implement goToSecondFragment() method to replace Fragment with Second Fragment**
```java
    @Override
    public void goToSecondFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.contentView, new SecondFragment())   // Replace Activity view with SecondFragment
                .addToBackStack(null)                              // Go back will take to previous view
                .commit();
    }
```


## ListViews

## List View
Displays a vertically-scrollable collection of views, where each view is positioned immediatelybelow the previous view in the list.

### ListView of Strings:
**Creating simple ListView**
```java
  public class MainActivity extends AppCompatActivity {
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);

          ListView listView = findViewById(R.id.listView);
          String[] colors = {"Red", "Green", "Blue"};
          ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this,
                  android.R.layout.simple_list_item_1,
                  android.R.id.text1, colors);
          listView.setAdapter(stringArrayAdapter);
      }
  }
```

**Adding listener to ListView elements**
```java
  listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  Log.d("ListView_Demo", "onItemClick, position: " + position + " and color: " + colors[position]);
              }
          });
```

### ListView of Objects:

**User Object**
```java
  public class User {
      String name;
      int age;

      public User(String name, int age) {
          this.name = name;
          this.age = age;
      }
  }  
```
**Displaying ListView of Object - Simple ListView**
```java
  public class MainActivity extends AppCompatActivity {
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);

          ListView listView = findViewById(R.id.listView);
          ArrayList<User> users = new ArrayList<>();
          users.add(new User("Bob Smith", 35));
          users.add(new User("Alice Brown", 23));
          users.add(new User("Bill Jordon", 27));
          users.add(new User("Tom Hank", 32));

          ArrayAdapter<User> userArrayAdapter = new ArrayAdapter<>(this,
                  android.R.layout.simple_list_item_1,
                  android.R.id.text1, users);
          listView.setAdapter(userArrayAdapter);

          listView.setOnItemClickListener((parent, view, position, id) -> {
              Log.d("ListView_Demo", "onItemClick, position: " + position + " and user is: "
                      + userArrayAdapter.getItem(position).name);
          });
      }
  }  
```
**Using Binding with simple ListViews**
```java
    AppListItemBinding itemBinding;

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            itemBinding = AppListItemBinding.inflate(getLayoutInflater(), parent, false);
            convertView = itemBinding.getRoot();
            convertView.setTag(itemBinding);
        }
        itemBinding = (AppListItemBinding) convertView.getTag();
```

**Displaying ListView of Object - RecyclerView**
```java
   @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        appArrayList = DataServices.getAppsByCategory(mCategory);
        adapter = new AppsAdapter();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(adapter);
    }
    
    //Adapter With Holder pattern Integrated
    class AppsAdapter extends RecyclerView.Adapter<AppsAdapter.AppViewHolder> {

        @NonNull
        @Override
        public AppsAdapter.AppViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            AppListItemBinding itemBinding = AppListItemBinding.inflate(getLayoutInflater(), parent, false);
            AppViewHolder holder = new AppViewHolder(itemBinding);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull AppsAdapter.AppViewHolder holder, int position) {
            DataServices.App app = appArrayList.get(position);
            holder.setupUI(app);
        }

        @Override
        public int getItemCount() {
            return appArrayList.size();
        }
        
        // View Holder Class
        class AppViewHolder extends RecyclerView.ViewHolder {

            DataServices.App mApp;
            AppListItemBinding mBinding;

            public AppViewHolder(@NonNull AppListItemBinding binding) {
                super(binding.getRoot());
                mBinding = binding;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mListener.sendSelectedApp(mApp);
                    }
                });
            }

            void setupUI(DataServices.App app) {
                mApp = app;
                mBinding.textViewAppName.setText(mApp.getName());
                mBinding.textViewArtistName.setText(mApp.getArtistName());
                mBinding.textViewReleaseDate.setText(mApp.getReleaseDate());
            }
        }
    }
```


### Removing/updating items of Strings:
This can be done by updating list of objects and notifying adapter about uodates.
```java
   listView.setOnItemClickListener((parent, view, position, id) -> {
       User user = users.remove(position);
       userArrayAdapter.notifyDataSetChanged();
       Log.d("ListView", "Removed used " + users.get(position));
   });
```


## Making HTTP Connections and API Parsing

Welcome to the itis-5180-mad wiki! This repository contains all basics of Android Application development using java along with samples. 

## Giving internet persmissions
Update below configuration in AndroidManifest.xml.
```java
<uses-permission android:name="android.permission.INTERNET"></uses-permission>
```

**Making request**
```java
 protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Request request = new Request.Builder()
                .url("https://publicobject.com/helloworld.txt")
                .build();
```

**Create OkHttpClient**
```java
private final OkHttpClient client = new OkHttpClient();
```

**Set Response callback listener for client**
```java
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
 ```java
 <application
    android:usesCleartextTraffic="true"
 </application
 ```
 **Build complex URL String**
 ```java
    HttpUrl url = HttpUrl.parse("https://www.theappsdr.com").newBuilder()
            .addPathSegment("contacts")
            .addPathSegment("json")
            .build();
 ```
  **Build complex URL String - using builder**
  ```java
  HttpUrl.Builder builder = new HttpUrl.Builder();
  HttpUrl url = builder.scheme("https")
          .host("www.theappsdr.com")
          .addPathSegment("contacts")
          .addPathSegment("json")
          .build();
 ```

**Sending POST Request**
 ```java
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
```java
 runOnUiThread(new Runnable() {
     @Override
     public void run() {
          System.out.println();
     }
 });
```

**Json Parsing**
```java
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
```java
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
```java
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
```java
Gson gson = new Gson();
Person person = gson.fromJson(responseBody.charStream(), Person.class);
```  

**Using GSON to parse JSON Array**
```java 
  Gson gson = new Gson();
  PersonsResponse persons = gson.fromJson(responseBody.charStream(), PersonsResponse.class);
```
Note: When using GSON, the java object properties should match with the json object properities.


## FireBase

## Setting up Firebase Project

- Create FireBase Account using Gmail Id (not .edu or any other domain)
- Create FireBase Project
		https://console.firebase.google.com
- Create Android Project
- Follow steps on the Firebase console to connect Android project to the FireBase Project

<img width="600" alt="image" src="https://user-images.githubusercontent.com/112779376/226796094-d3773221-d102-4784-8628-7335c14a586f.png">

- Download google-services.json file and move it into your module (app-level) root directory.
- Update Root-level (project-level) Gradle file (<project>/build.gradle):
```java
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
```java
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
```java
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
```java
    private FirebaseAuth mAuth;
    mAuth = FirebaseAuth.getInstance();
```
- When a user signs in to your app, pass the user's email address and password to signInWithEmailAndPassword:
	
```java
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

```java	
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
```java
dependencies {
    // Import the BoM for the Firebase platform
    implementation platform('com.google.firebase:firebase-bom:31.2.3')

    // Declare the dependency for the Cloud Firestore library
    // When using the BoM, you don't specify versions in Firebase library dependencies
    implementation 'com.google.firebase:firebase-firestore'
}
```
- Initialize an instance of Cloud Firestore:
```java
FirebaseFirestore db = FirebaseFirestore.getInstance();
```
- **Retrieve data using FireStore DB instance (db)**
```java
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
```java
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
- **Add data -  generate AUTO ID**
```java
private void updateData() {
    db.collection("contacts")
            .add(new Contact("Test", "444-444-4444"))
            .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "onComplete: " + "SUCCESS");
                    } else {
			Log.d(TAG, "onComplete: " + "Failure");
                    }
                }
            });
}
```

- **Add data -  Create document with specified ID**
```java
    HashMap<String, Object> contact2 = new HashMap<>();
    contact2.put("name", "Test User 2");
    contact2.put("cell", "555-555-5555");
    db.collection("contacts")
            .document("SHDGGDYEW123sdwh")
            .set(contact2)
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "onComplete: " + "SUCCESS");
                    } else {
                        Log.d(TAG, "onComplete: " + "Failure" + task.getException().getMessage());
                    }
                }
            });
```

- **Add data -  Update spefific fields of the document provided**
```java
    HashMap<String, Object> contact2 = new HashMap<>();
    contact2.put("cell", "555-555-5558");
    db.collection("contacts")
            .document("SHDGGDYEW123sdwh")
            .update(contact2)
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Log.d(TAG, "onComplete: " + "SUCCESS");
                    } else {
                        Log.d(TAG, "onComplete: " + "Failure" + task.getException().getMessage());
                    }
                }
            });
```
	
- Message to dispay when task is not successful 
```java
Toast.makeText(getActivity(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
```


## Local Data Storage

## Important Classes:
### SQLite Classes:
**SQLiteOpenHelper:**
- A helper class to manage database creation and version management.
- This class contains a useful set of APIs for managing the database.
- **onCreate**(SQLiteDatabase db): Called when the database is created for the first time (i.e if database does not exist).This is where the creation of tables and the initial population of the tables should happen.
- **onUpgrade**(SQLiteDatabase db, int oldVersion, int newVersion): Called when the database needs to be upgraded.(DB version passed is heigher than the current version)
- SQLiteOpenHelper instance will be used to get database instance:
 - **getWritableDatabase()**: to perfrom read/write operations
 - **getReadableDatabase()**: to perfrom read only operations
- To use this class, create a DB Helper class which extends SQLiteOpenHelper, override onCreate() and onUpgrade() methods
<img width="722" alt="image" src="https://user-images.githubusercontent.com/112779376/231275733-40cb9cef-eb88-4653-a960-4eb28a45a601.png">

**SQLiteDatabase:**
- Exposes methods to manage a SQLite database.
- SQLiteDatabase has methods to create, delete, execute SQL commands, and perform other common database management tasks.

### Custom Classes:
**Table Classess:**
- This class corresponds to a table in the database.
- It is used to perform the SQL operations required to. create and update a specific table. Example below:

<img width="715" alt="image" src="https://user-images.githubusercontent.com/112779376/231276310-a17df4e5-31cc-49d2-8a77-d7fa09651216.png">

**Data Object class:**
- This class will be created to represent the data model.
- Its object will represent a row of the table.
- Class below represents a model for a Notes table.

<img width="535" alt="image" src="https://user-images.githubusercontent.com/112779376/231276767-4bb1e058-5509-4f7c-acd9-2e8f8e1b82a2.png">

**Data Access Object (DAO):**
- The DAO class is used to represent the data access layer.
- It is used to perform SQL operations required for accessig and manipulating data in each table.
- E.g DAO class for our Notes table:

<img width="325" alt="image" src="https://user-images.githubusercontent.com/112779376/231277988-6481448b-fc22-4dd9-858c-8dddeee231ec.png">
<img width="844" alt="image" src="https://user-images.githubusercontent.com/112779376/231277688-74712705-8b7e-4d2d-9030-e348a092d68c.png">
<img width="863" alt="image" src="https://user-images.githubusercontent.com/112779376/231277855-86109726-003e-4717-8b49-2615603829b1.png">

**Getting Object from a Cursor**

<img width="569" alt="image" src="https://user-images.githubusercontent.com/112779376/231280476-43ede0e1-79bf-4ec8-a4b4-0722c8af9e9a.png">

**Data Manager**
- This is a wrapper class that connects the designed classes.
- It is used to 
 - create or update the database.
 - maintain the database instance
 - call different access classes for the different tables.
 - close the DB connection.

<img width="504" alt="image" src="https://user-images.githubusercontent.com/112779376/231279177-c2ac3b35-1a40-46fd-a881-cbf4d6ac3a9e.png">

### Inspecting the SQLite DB:
- The database application is stored in a file in the emulator folder:  
  "/data/data/<package_name>/databases/<database_name>".  
  
  - Android Studio -> View -> Tool Window -> Device File Explorer
  
- This file can be downloaded and viewed in DB browser for SQLite: [SQLite Broswer](https://sqlitebrowser.org/)

## Using Room Database Library:
- The Room persistence library provides an abstraction layer over SQLite to allow fluent database access

### Setup:
- To use Room in your app, add the following dependencies to your app's build.gradle file:
```java
ext {
    roomVersion = '2.5.1'
}
dependencies {
    // room-library
    implementation("androidx.room:room-runtime:$roomVersion")
    annotationProcessor("androidx.room:room-compiler:$roomVersion")
}
```
### Components:
There are three major components in Room:
 - **Database**
 - **Entity**
 - **Dao**   

**Database**: 
 - It is a class that holds the database and serves as the main access point for the underlying connection to your app's persisted data.
 - The database class provides the app with instances of the DAOs associated with that database. 
```java
@Database(entities = {Note.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    //Methods to get instances of DAO's
    public abstract NoteDao noteDao();
}
```

**Entity**:
- These are classes that represent tables in your app's database.  
- Each entity corresponds to a table in the associated Room database, and 
- Each instance of an entity represents a row of data in the corresponding table.
- We define each Room entity as a class annotated with **@Entity**.
**Entity Class**
```java
@Entity(tableName = "note")
public class Note {
    @PrimaryKey(autoGenerate = true)
    public long _id;

    @ColumnInfo
    public String subject;

    @ColumnInfo
    public String note;
    
    public Note(long _id, String subject, String note) {...}

    public Note(String subject, String note) {...}
    
    // getters and setters
}
```
**Dao**:
- We interact with the stored data by defining data access objects, or DAOs.
- They provide methods that your app can use to query, update, insert, and delete data in the database.
- Each DAO includes methods that offer abstract access to your app's database. At compile time, Room automatically generates implementations of the DAOs that you define
- We can define each DAO as **either** an **interface** or an **abstract** class.
- We must always annotate our DAOs with @Dao

```java
@Dao
interface NoteDao {

    @Insert
    void insert(Note note);

    @Insert
    void insertAll(Note... notes);

    @Query("SELECT * FROM note")
    List<Note> getAllNotes();

    @Query("SELECT * FROM note WHERE _id = :id")
    Note findById(long id);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note")
    void deleteAll();
}
```
- There are two types of DAO methods that define database interactions:
   - **Convenience methods**: insert(@Insert), update(@Update), and delete(@Delete) rows
   - **Query methods**: Write your own SQL query to interact with the database. This can be SELECT or any other valid SQLite query.

**Pass simple parameters to a query**:
```java
    @Query("SELECT * FROM note WHERE _id = :id")
    Note findById(long id);
```
**Pass a collection of parameters to a query**
```java
    @Query("SELECT * FROM note WHERE _id IN = (:notes)")
    List<Note> findByIds(List<Note> notes);
```
### Using Room Database:
**Using Room Database in Activity**
```java
  // Create Instance of AppDatabase in onCreate() method
  AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "notes_db")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build();
        
 // Use AppDatabase instance (db) to get Dao objects and perform db operations
 db.noteDao().deleteAll();
 db.noteDao().insertAll(new Note("Note 1", "This is note 1"), new Note("Note 2", "This is note 2"));
```

**Using Room Database in Fragments**
```java
  // Create Instance of AppDatabase in onCreate() method
  AppDatabase db = Room.databaseBuilder(getContext(), AppDatabase.class, "notes_db")
        .allowMainThreadQueries()
        .fallbackToDestructiveMigration()
        .build();
        
 // Use AppDatabase instance (db) to get Dao objects and perform db operations
 db.noteDao().deleteAll();
 db.noteDao().insertAll(new Note("Note 1", "This is note 1"), new Note("Note 2", "This is note 2"));
```

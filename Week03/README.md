# Mobile Application Development
## Activities
### Author: Viraj Ranaware

This module explains Activity Lifecycle and how to share data between multiple Activities.


## Activity Lifecycle
**Activity Lifecycle Methods**
```
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
```
// Go to Next page
  Intent intent = new Intent(MainActivity.this, SecondActivity.class);
  startActivity(intent);
```


**Implicit Intent**
  ```
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
  ```
  final static public String NAME_KEY = "Name";
  ```

**Pass Data using Intent - in source activity**
  ```
  Intent intent = new IntenMainActivity.this, SecondActivity.class);
  intent.putExtra(NAME_KEY, "Viraj Ranaware");
  String name = getIntent().getStringExtra(MainActivity.NAME_KEY);
  ```

**Recieve data - in target activity**
  ```
  if(getIntent() != null && getIntent().getExtras() != null && getIntent().hasExtra("Name")){
    String name = getIntent().getStringExtra(MainActivity.NAME_KEY);
    nameText.setText(name);
  }
  ```

### 2. Pass serializable - object
**Data to pass - User**
  ```
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
  ```
  Intent intent = new IntenMainActivity.this, SecondActivity.class);
  User user = new User("Viraj Ranaware", 28)
  intent.putExtra(User_KEY, user);

  // Switch to Activity specified
  startActivity(intent);
  ```

**Recieve data - in target activity**
  ```
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
  ```
  ArrayList<User> users = new ArrayList<>();
  users.add(new User("Alice", 22));
  users.add(new User("Bob", 24));
  ```

 **Pass Data using Intent - in source activity**
  ```
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
  ```
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
  ```
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
  ```
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
  ```
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

# itis-5180-mad : Mobile Application Development
## Author: Viraj Ranaware

Welcome to the itis-5180-mad wiki! This repository contains all basics of Android Application development using java along with samples. 

**Text View**
```
TextView textViewWeightValue = findViewById(R.id.textViewWeightValue);
```

**Button**
```
findViewById(R.id.AlertButton).setOnClickListener(new View.OnClickListener(){ @Override
  public void onClick(View v){
    // Actions to perform on click
  }
});


```
**Radio Group**
```
RadioGroup radioGroupGender.getCheckedRadioButtonId() == R.id.radioButtonMale

//Check which radio group is checked
radioGroupGender.getCheckedRadioButtonId() == R.id.radioButtonMale
```

**Toast**
```
import android.widget.Toast;

Toast.makeText(MainActivity.this, "Enter a valid ticket price",Toast.LENGTH_SHORT).show();
```

**Title:**
```
{
  Code Snippet
}
```

**Alert Dialog**
```
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

**Activity Lifecycle**
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
**Set Title**
```
  setTitle("Main Activity");
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

## Pass serializable - object
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

  
## Exceptions
  
 

**Table Example:**

| Description | Score |
| ----------- | ----- |
|Street	      | 0.872 |
|Snapshot	    | 0.852 |

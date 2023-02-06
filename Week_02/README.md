# Mobile Application Development
## Views, Logging and Exceptions
### Author: Viraj Ranaware

This module introduces Views and other basic concepts.

### Views:
  - TextView
  - EditText
  - Button
  - RadioGroup
  - Seekbar/Slider
  - ImageView


**TextView**
```
  // Initialize TextView
  TextView textViewWeight = findViewById(R.id.textViewWeightValue);

  double weight = 100.00;
  textViewWeight.setText(String.valueOf(price));
```

**EditText**
```
  // Initialize EditText
  EditText ticketPrice = findViewById(R.id.ticket_price_value);

  // Get the value from EditText
  String ticketPriceEntered = ticketPrice.getText().toString();

  // Set the value for EditText
  ticketPrice.setText("");
```

**Button**
```
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
```
  RadioGroup radioGroupGender.getCheckedRadioButtonId() == R.id.radioButtonMale

  //Check which radio group is checked
  radioGroupGender.getCheckedRadioButtonId() == R.id.radioButtonMale
```

**ImageView**
```
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
```
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
```
  Log.d(TAG, "OnCreate: ");
```

**Toast**
```
  import android.widget.Toast;

  Toast.makeText(MainActivity.this, "Enter a valid ticket price",Toast.LENGTH_SHORT).show();
```

**Set Title:**
```
  {  
    setTitle("Main Activity");
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

## Exceptions

**NumberFormatException**
```
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

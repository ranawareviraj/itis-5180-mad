# Mobile Application Development
## ListViews
### Author: Viraj Ranaware

## List View
Displays a vertically-scrollable collection of views, where each view is positioned immediatelybelow the previous view in the list.

**Creating simple ListView**
```
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
```
  listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
              @Override
              public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  Log.d("ListView_Demo", "onItemClick, position: " + position + " and color: " + colors[position]);
              }
          });
```

###ListView of Objects:

**User Object**
```
  public class User {
      String name;
      int age;

      public User(String name, int age) {
          this.name = name;
          this.age = age;
      }
  }  
```
**Displaying ListView of Object**
```
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

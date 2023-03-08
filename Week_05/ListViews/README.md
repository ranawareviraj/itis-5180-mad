# Mobile Application Development
## ListViews
### Author: Viraj Ranaware

## List View
Displays a vertically-scrollable collection of views, where each view is positioned immediatelybelow the previous view in the list.

### ListView of Strings:
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

### ListView of Objects:

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
**Displaying ListView of Object - Simple ListView**
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
**Using Binding with simple ListViews**
```
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
```
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
```
   listView.setOnItemClickListener((parent, view, position, id) -> {
       User user = users.remove(position);
       userArrayAdapter.notifyDataSetChanged();
       Log.d("ListView", "Removed used " + users.get(position));
   });
```

# Mobile Application Development
## View Binding and Fragments
### Author: Viraj Ranaware

Welcome to the itis-5180-mad wiki! This repository contains all basics of Android Application development using java along with samples. 
## View Binding
View binding is a feature that allows you to more easily write code that interacts with views. Once view binding is enabled in a module, it generates a binding class for each XML layout file present in that module. An instance of a binding class contains direct references to all views that have an ID in the corresponding layout.
In most cases, view binding replaces findViewById.

To enable View Binding add below snnipet in build.gradle file.

**Enabling View Binding**
```
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
 
 ```
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
 ```
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
 ```
    Step 1. Call the static inflate() method of the binding class to create an instance of the binding class for the fragment to use.
    Step 2. Get a reference to the root view by calling the getRoot() method.
    Step 3. Return the root view from the onCreateView() method to make it the active view on the screen.
 ```
 
**Using View Binding in Fragments**
 ```
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
 ```    
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
 ```
   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
 ```
 
 **onCreateView() : initialize binding, return content root** 
 ```
FragmentFirstBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
 ```
 
 **onViewCreated() : Logic to work with the fragment** 
 ```
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
 ```
  @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);                    // context = Activity
    }
 ```
 ### Sending Data from Fragment to Activity
This is done using an interface.
```
    Step 1: Define interface(Listener) in the Fragment
    Step 2: Activity implemnts the Interface
    Step 3: In the fragment, cast context(activity) to listener.
    Step 4: User listner to send data back.
    Step 5: Capture data in the activity - Step 3 can be extended if needed
```



**Step 1: Define interface in the Fragment**
 ```
     public interface FirstListener {
        void sendUsername(String username);
    }
 ```

**Step 2: Activity implemnts the Interface**
```
     public class MainActivity extends AppCompatActivity implements FirstFragment.FirstListener {
         @Override
        public void sendUsername(String username) {
            Log.d(TAG, "sendUsername: " + username);
        }
     }
```
**Step 3: In the fragment, cast context(activity) to listener.**
```
    FirstListener fListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);                // context = Activity
        fListener = (FirstListener) context;    // As Activity aka context implements listener, context can be casted back to listener
    }
```
**Step 4: User listner to send data back.**
```
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
```
    @Override
    public void sendUsername(String username) {
        Log.d(TAG, "sendUsername: " + username);
    }
```

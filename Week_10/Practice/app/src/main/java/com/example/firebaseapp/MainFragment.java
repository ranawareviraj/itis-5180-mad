package com.example.firebaseapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.firebaseapp.databinding.FragmentMainBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;


public class MainFragment extends Fragment {
    private static final String TAG = "FireAuth";
    private FirebaseAuth mAuth;
    private FragmentMainBinding binding;
    FirebaseFirestore db;
    ArrayAdapter<Contact> adapter;
    ArrayList<Contact> contacts = new ArrayList<>();

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = MainActivity.getFirebaseAuthInstance();
        db = FirebaseFirestore.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMainBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Main Fragment");

        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1,
                android.R.id.text1, contacts);
        binding.listView.setAdapter(adapter);

        getData();

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                getFragmentManager().beginTransaction()
                        .replace(R.id.contentView, new LoginFragment())
                        .commit();
            }
        });
    }

    private void getData() {
/*
        db.collection("contacts")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots,
                                        @Nullable FirebaseFirestoreException error) {
                        contacts.clear();
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            contacts.add(new Contact(document.getString("name"),
                                    document.getString("cell")));
                            Log.d(TAG, "onEvent: " + "contact added");
                        }
                        Log.d(TAG, "onEvent: " + "Notifying adapter");
                        adapter.notifyDataSetChanged();
                        Log.d(TAG, "onEvent: " + "Adapter notified");
                    }
                });
*/

        // [START get_all_contacts]
        db.collection("contacts")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        contacts.clear();
                        for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                            contacts.add(new Contact(document.getString("name"), document.getString("cell")));
                        }
                        adapter.notifyDataSetChanged();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                }) ;
        // [END get_all_contacts]
    }

    private void updateData() {
        db.collection("contacts")
                .add(new Contact("Test User New", "444-444-4444"))
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "onComplete: " + "SUCCESS");
                        } else {
                        }
                    }
                });

    /*    // Adding document with custom ID
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
                });*/
    }
}
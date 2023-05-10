package edu.uncc.finalexam;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.uncc.finalexam.databinding.FragmentMyListsBinding;
import edu.uncc.finalexam.databinding.FragmentNewsBinding;
import edu.uncc.finalexam.models.News;
import edu.uncc.finalexam.models.NewsResponse;

public class MyListsFragment extends Fragment {
    public MyListsFragment() {
        // Required empty public constructor
    }

    ArrayList<NewsResponse> newsResponses = new ArrayList<>();
    FragmentMyListsBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentMyListsBinding.inflate(inflater,container,false);
        return  binding.getRoot();
    }

    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    ListenerRegistration listenerRegistration;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    public void getNewsList(){



    listenerRegistration = db.collection("News").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {
                    Log.d("TAG", "onEvent: on error "+error);
                    return;
                }

                newsResponses.clear();

                for(QueryDocumentSnapshot documentSnapshot: value){
                    NewsResponse newsResponse = documentSnapshot.toObject(NewsResponse.class);
                    newsResponses.add(newsResponse);
                }
              appsAdapter.notifyDataSetChanged();

            }
        });
    }

    public void addNewList(String title){
        DocumentReference docRef = db.collection("News").document();

        ArrayList<NewsResponse> newsList = new ArrayList<>();
        HashMap<String, Object> data = new HashMap<>();
        data.put("nameOfList", title);
        data.put("newsList", newsList);
        data.put("id", docRef.getId());

        docRef.set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                } else {

                }
            }
        });
    }
    AppsAdapter appsAdapter;
    class AppsAdapter extends ArrayAdapter<NewsResponse> {
        public AppsAdapter(@NonNull Context context, @NonNull List<NewsResponse> objects) {
            super(context,R.layout.mylist_row_item, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView == null){
                convertView = getLayoutInflater().inflate( R.layout.mylist_row_item, parent, false);
            }

            NewsResponse item = getItem(position);
            TextView textViewSourceName = convertView.findViewById(R.id.textViewSourceName);
            TextView textViewItemsCount = convertView.findViewById(R.id.textViewItemsCount);

            textViewSourceName.setText(item.nameOfList);
            if(item.newsList.size()> 0){
                textViewItemsCount.setText(String.valueOf(item.newsList.size()));
            }else{
                textViewItemsCount.setText("0");
            }


            return convertView;
        }
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("My Lists");

        getNewsList();
        appsAdapter = new AppsAdapter(getActivity(),newsResponses);
        binding.listViewMyLists.setAdapter(appsAdapter);

        binding.listViewMyLists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NewsResponse selected = newsResponses.get(i);
                myListInterface.goToListView();
            }
        });

        binding.buttonAddList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = binding.editTextListName.getText().toString();

                if(title.isEmpty()){
                    Toast.makeText(getActivity(), "enter valid title", Toast.LENGTH_SHORT).show();
                }else{
                    binding.editTextListName.setText("");
                    addNewList(title);
                }
            }
        });
    }
    MyListInterface myListInterface;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        myListInterface = (MyListInterface) context;
    }

    public interface MyListInterface{
        void goToListView();
    }

}
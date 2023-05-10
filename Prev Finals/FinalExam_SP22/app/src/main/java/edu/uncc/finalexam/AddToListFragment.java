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
import android.widget.TextView;

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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import edu.uncc.finalexam.databinding.FragmentAddToListBinding;
import edu.uncc.finalexam.databinding.FragmentMyListsBinding;
import edu.uncc.finalexam.models.News;
import edu.uncc.finalexam.models.NewsResponse;


public class AddToListFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_NEWS= "NEWS";


    private News news;


    public AddToListFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static AddToListFragment newInstance(News news) {
        AddToListFragment fragment = new AddToListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_NEWS, news);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            news = (News) getArguments().getSerializable(ARG_NEWS);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAddToListBinding.inflate(inflater,container,false);
        return  binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Add To List");

        getNewsList();
        appsAdapter = new AppsAdapter(getActivity(),newsResponses);
        binding.listViewMyLists.setAdapter(appsAdapter);

        binding.listViewMyLists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                addNewToList(newsResponses.get(i));
            }
        });
    }

    public void addNewToList(NewsResponse newsResponse){

        newsResponse.newsList.add(news);
        DocumentReference docRef = db.collection("News").document(newsResponse.id);


        HashMap<String, Object> data = new HashMap<>();
        data.put("newsList", newsResponse.newsList);

        docRef.update(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){

                    addToListInterface.back();
                } else {

                }
            }
        });
    }

    ArrayList<NewsResponse> newsResponses = new ArrayList<>();
    FragmentAddToListBinding binding;


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

    AddToListInterface addToListInterface;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        addToListInterface = (AddToListInterface) context;
    }

    public interface AddToListInterface{
        public void back();
    }

}
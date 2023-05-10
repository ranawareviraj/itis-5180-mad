package com.example.finalexam;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.Contacts;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.finalexam.databinding.FragmentSearchBinding;
import com.example.finalexam.databinding.ListItemPhotoSearchBinding;
import com.example.finalexam.models.CoverPhoto;
import com.example.finalexam.models.FavResponse;
import com.example.finalexam.models.Photos;
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
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import kotlin.reflect.KFunction;
import okhttp3.HttpUrl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchFragment extends Fragment {
    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentSearchBinding binding;
    SearchInterface searchInterface;

    SearchAdapter searchAdapter = new SearchAdapter();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        getFavList();
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        binding.recyclerView.setAdapter(searchAdapter);

        binding.buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String query = binding.editTextSearchKeyword.getText().toString();

                if(query.isEmpty()){
                    Toast.makeText(getActivity(), "Enter valid query!", Toast.LENGTH_SHORT).show();
                }else{
                    getSearchResults(query);
                }
            }
        });


    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.logout_menu_item){
            searchInterface.logout();
            return true;
        } else if(item.getItemId() == R.id.my_favorites_menu_item){
            searchInterface.gotoFav();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    ArrayList<CoverPhoto> coverPhotos = new ArrayList<>();
    private final OkHttpClient client = new OkHttpClient();

    public void getSearchResults(String query){
        //base url is https://api.unsplash.com/search/photos/?client_id=your-acess-key&query=user-entered-keywords&per_page=50&orientation=landscape&content_filter=high

        HttpUrl url = HttpUrl.parse("https://api.unsplash.com/search/photos/").newBuilder()
                .addQueryParameter("client_id", "gmoZUO0q5QUNcVDrlheM52DNBdbXD0DPQc4isNsTMis")
                .addQueryParameter("query", query)
                .addQueryParameter("per_page",String.valueOf(50))
                .addQueryParameter("orientation", "landscape")
                .addQueryParameter("content_filter", "high")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        coverPhotos.clear();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    Photos photosResponse= gson.fromJson(response.body().charStream(), Photos.class);
                    coverPhotos.addAll(photosResponse.results);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            searchAdapter.notifyDataSetChanged();
                        }
                    });


                }

            }
        });

    }

    FirebaseAuth mAuth = FirebaseAuth.getInstance();


    ListenerRegistration listenerRegistration;


HashSet<CoverPhoto> favPhotos = new HashSet<>();
    public void getFavList(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        listenerRegistration = db.collection("photos").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {
                    Log.d("TAG", "onEvent: on error "+error);
                    return;
                }

                favPhotos.clear();

                for(QueryDocumentSnapshot documentSnapshot: value){
                    FavResponse favResponse = documentSnapshot.toObject(FavResponse.class);
                    CoverPhoto coverPhoto =  favResponse.coverPhoto;
                    favPhotos.add(coverPhoto);
                }
                searchAdapter.notifyDataSetChanged();

            }
        });
    }


    public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {


        public SearchAdapter() {

        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ListItemPhotoSearchBinding listItemPhotoSearchBinding = ListItemPhotoSearchBinding.inflate(getLayoutInflater(),parent,false);
            // View view =
            ViewHolder viewHolder  = new ViewHolder(listItemPhotoSearchBinding);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.setUp(coverPhotos.get(position));
        }

        @Override
        public int getItemCount() {
            return coverPhotos.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            ListItemPhotoSearchBinding listItemPhotoSearchBinding;
            boolean isFav;
            public ViewHolder(ListItemPhotoSearchBinding listItemPhotoSearchBinding) {
                super(listItemPhotoSearchBinding.getRoot());
                this.listItemPhotoSearchBinding = listItemPhotoSearchBinding;
            }

            public void setUp(CoverPhoto coverPhoto) {

                listItemPhotoSearchBinding.textViewDescription.setText(coverPhoto.description);
                listItemPhotoSearchBinding.textViewUserFullName.setText(coverPhoto.user.name);


                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
                listItemPhotoSearchBinding.textViewCreatedAt.setText(simpleDateFormat.format(coverPhoto.created_at));


                Picasso.get().load(coverPhoto.urls.thumb).into(listItemPhotoSearchBinding.imageViewThumbnail);
                Picasso.get().load(coverPhoto.user.profile_image.small).into(listItemPhotoSearchBinding.imageViewUserThumbnail);

                if(favPhotos.contains(coverPhoto)){
                    Picasso.get().load(R.drawable.ic_heart_favorite).into(listItemPhotoSearchBinding.imageViewFavorite);
                    isFav= true;
                }else{
                    Picasso.get().load(R.drawable.ic_heart_not_favorite).into(listItemPhotoSearchBinding.imageViewFavorite);
                    isFav= false;

                }
                listItemPhotoSearchBinding.imageViewFavorite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        if(isFav){
                            removeFromFav(coverPhoto);
                        }else{
                            addToFav(coverPhoto);
                        }

                    }
                });

                listItemPhotoSearchBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        searchInterface.goToDetails(coverPhoto);
                    }
                });
            }
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            public void addToFav(CoverPhoto coverPhoto){


                DocumentReference docRef = db.collection("photos").document(coverPhoto.id);

                HashMap<String, Object> data = new HashMap<>();
                data.put("coverPhoto", coverPhoto);

                docRef.set(data).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                           // Picasso.get().load(R.drawable.ic_heart_favorite).into(listItemPhotoSearchBinding.imageViewFavorite);
                        } else {

                        }
                    }
                });


            }
            public void removeFromFav(CoverPhoto coverPhoto){

                //FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("photos").document(coverPhoto.id).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()) {
                          //  Picasso.get().load(R.drawable.ic_heart_not_favorite).into(listItemPhotoSearchBinding.imageViewFavorite);
                        }
                    }
                });

            }
        }
    }





    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        searchInterface = (SearchInterface) context;
    }

    public interface SearchInterface{
        void logout();
        void gotoFav();

        void goToDetails(CoverPhoto coverPhoto);
    }




}
package com.example.finalexam;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.finalexam.databinding.FragmentMyFavoritesBinding;
import com.example.finalexam.databinding.ListItemPhotoFavoriteBinding;
import com.example.finalexam.databinding.ListItemPhotoSearchBinding;
import com.example.finalexam.models.CoverPhoto;
import com.example.finalexam.models.FavResponse;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class MyFavoritesFragment extends Fragment {

    public MyFavoritesFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentMyFavoritesBinding binding;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

   FavInterface favInterface;

    FavAdapter favAdapter = new FavAdapter();

    ListenerRegistration listenerRegistration;
    ArrayList<CoverPhoto> coverPhotos = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMyFavoritesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        getFavList();
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        binding.recyclerView.setAdapter(favAdapter);

//        binding..setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                String query = binding.editTextSearchKeyword.getText().toString();
//
//                if(query.isEmpty()){
//                    Toast.makeText(getActivity(), "Enter valid query!", Toast.LENGTH_SHORT).show();
//                }else{
//                    getSearchResults(query);
//                }
//            }
//        });


        this.getActivity().setTitle("Favorites");
    }

    public void getFavList(){
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        listenerRegistration = db.collection("photos").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {

                if (error != null) {
                    Log.d("TAG", "onEvent: on error "+error);
                    return;
                }

                coverPhotos.clear();

                for(QueryDocumentSnapshot documentSnapshot: value){
                    FavResponse favResponse = documentSnapshot.toObject(FavResponse.class);
                    CoverPhoto coverPhoto =  favResponse.coverPhoto;
                    coverPhotos.add(coverPhoto);
                }
                favAdapter.notifyDataSetChanged();

            }
        });
    }



    public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {


        public FavAdapter() {

        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            ListItemPhotoFavoriteBinding listItemPhotoFavoriteBinding = ListItemPhotoFavoriteBinding.inflate(getLayoutInflater(),parent,false);
            // View view =
        ViewHolder viewHolder  = new ViewHolder(listItemPhotoFavoriteBinding);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull FavAdapter.ViewHolder holder, int position) {
            holder.setUp(coverPhotos.get(position));
        }

        @Override
        public int getItemCount() {
            return coverPhotos.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            ListItemPhotoFavoriteBinding listItemPhotoFavoriteBinding;
            public ViewHolder(ListItemPhotoFavoriteBinding listItemPhotoFavoriteBinding) {
                super(listItemPhotoFavoriteBinding.getRoot());
                this.listItemPhotoFavoriteBinding = listItemPhotoFavoriteBinding;
            }

            public void setUp(CoverPhoto coverPhoto) {

                listItemPhotoFavoriteBinding.textViewDescription.setText(coverPhoto.description);
                listItemPhotoFavoriteBinding.textViewUserFullName.setText(coverPhoto.user.name);


                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
                listItemPhotoFavoriteBinding.textViewCreatedAt.setText(simpleDateFormat.format(coverPhoto.created_at));


                Picasso.get().load(coverPhoto.urls.thumb).into(listItemPhotoFavoriteBinding.imageViewThumbnail);
                Picasso.get().load(coverPhoto.user.profile_image.small).into(listItemPhotoFavoriteBinding.imageViewUserThumbnail);

                listItemPhotoFavoriteBinding.imageViewTrash.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                            removeFromFav(coverPhoto);

                    }
                });
                listItemPhotoFavoriteBinding.getRoot().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        favInterface.goToDetails(coverPhoto);
                    }
                });
            }

            public void removeFromFav(CoverPhoto coverPhoto){

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("photos").document(coverPhoto.id).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // Picasso.get().load(R.drawable.ic_heart_not_favorite).into(listItemPhotoSearchBinding.imageViewFavorite);
                    }
                });

            }
        }
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        favInterface = (FavInterface) context;
    }

    public interface FavInterface{
        void goToDetails(CoverPhoto coverPhoto);

    }

}
package com.example.finalexam;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.finalexam.databinding.FragmentPhotoDetailsBinding;
import com.example.finalexam.models.CoverPhoto;
import com.squareup.picasso.Picasso;

import org.checkerframework.checker.units.qual.C;

import java.text.SimpleDateFormat;


public class PhotoDetailsFragment extends Fragment {


    private static final String ARG_PHOTO = "photo";



    private CoverPhoto coverPhoto;


    public PhotoDetailsFragment() {
        // Required empty public constructor
    }

    public static PhotoDetailsFragment newInstance(CoverPhoto coverPhoto) {
        PhotoDetailsFragment fragment = new PhotoDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PHOTO, coverPhoto);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            coverPhoto =(CoverPhoto) getArguments().getSerializable(ARG_PHOTO);

        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

            binding.textViewUserFullName.setText(coverPhoto.user.name);
        binding.textViewDescription.setText(coverPhoto.description);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
        binding.textViewCreatedAt.setText(simpleDateFormat.format(coverPhoto.created_at));

        Picasso.get().load(coverPhoto.urls.thumb).into(binding.imageViewThumbnail);
        Picasso.get().load(coverPhoto.user.profile_image.small).into(binding.imageViewUserThumbnail);
    }

    FragmentPhotoDetailsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPhotoDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


}
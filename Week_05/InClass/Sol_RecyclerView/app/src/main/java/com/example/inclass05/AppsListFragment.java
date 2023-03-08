package com.example.inclass05;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.inclass05.databinding.AppListItemBinding;
import com.example.inclass05.databinding.FragmentAppsListBinding;

import java.util.ArrayList;
import java.util.List;

public class AppsListFragment extends Fragment {
    private static final String ARG_PARAM_CATEGORY = "ARG_PARAM_CATEGORY";
    private String mCategory;
    ArrayList<DataServices.App> appArrayList;
    AppsAdapter adapter;

    public AppsListFragment() {
        // Required empty public constructor
    }

    public static AppsListFragment newInstance(String category) {
        AppsListFragment fragment = new AppsListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mCategory = getArguments().getString(ARG_PARAM_CATEGORY);
        }
    }

    FragmentAppsListBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAppsListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Top Paid Apps");

        appArrayList = DataServices.getAppsByCategory(mCategory);
        adapter = new AppsAdapter();

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(adapter);
    }

    AppsListListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (AppsListListener) context;

    }

    public interface AppsListListener {
        void sendSelectedApp(DataServices.App app);
    }


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
}
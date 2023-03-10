package com.example.inclass05;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.inclass05.databinding.AppListItemBinding;
import com.example.inclass05.databinding.FragmentAppsListBinding;

import java.util.ArrayList;
import java.util.List;

public class AppsListFragment extends Fragment {
    private static final String ARG_PARAM_CATEGORY = "ARG_PARAM_CATEGORY";
    private String mCategory;
    ArrayList<DataServices.App> appArrayList;
    ArrayAdapter<DataServices.App> adapter;

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
        adapter = new AppsAdapter(getActivity(), appArrayList);
        binding.listView.setAdapter(adapter);

        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataServices.App app = appArrayList.get(position);
                mListener.sendSelectedApp(app);
            }
        });
    }

    AppsListListener mListener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (AppsListListener) context;

    }

    private class AppsAdapter extends ArrayAdapter<DataServices.App> {

        public AppsAdapter(@NonNull Context context, @NonNull List<DataServices.App> objects) {
            super(context, R.layout.app_list_item, objects);
        }

        AppListItemBinding itemBinding;

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                itemBinding = AppListItemBinding.inflate(getLayoutInflater(), parent, false);
                convertView = itemBinding.getRoot();
                convertView.setTag(itemBinding);
            }
            itemBinding = (AppListItemBinding) convertView.getTag();

            DataServices.App app = getItem(position);

            itemBinding.textViewAppName.setText(app.getName());
            itemBinding.textViewArtistName.setText(app.getArtistName());
            itemBinding.textViewReleaseDate.setText(app.getReleaseDate());

            return convertView;
        }
    }

    public interface AppsListListener{
        void sendSelectedApp(DataServices.App app);
    }
}
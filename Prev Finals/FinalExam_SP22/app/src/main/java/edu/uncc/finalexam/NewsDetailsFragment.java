package edu.uncc.finalexam;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import edu.uncc.finalexam.databinding.FragmentNewsDetailsBinding;
import edu.uncc.finalexam.models.News;


public class NewsDetailsFragment extends Fragment {


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_NEWS = "news";

    // TODO: Rename and change types of parameters
    private News newsItem;

    FragmentNewsDetailsBinding binding;

    public NewsDetailsFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static NewsDetailsFragment newInstance(News newsItem) {
        NewsDetailsFragment fragment = new NewsDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_NEWS, newsItem);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            newsItem = (News) getArguments().getSerializable(ARG_NEWS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        binding = FragmentNewsDetailsBinding.inflate(inflater,container,false);
        return  binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("News Details");


        binding.textViewNewsAuthor.setText(newsItem.getAuthor());
        binding.textViewNewsTitle.setText(newsItem.getTitle());
        binding.textViewSourceName.setText(newsItem.getSourceName());
        Picasso.get().load(newsItem.getUrl()).into(binding.imageViewNewsIcon);

        binding.imageViewBrowser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewsUrl(newsItem.getWebUrl());
            }
        });

        binding.imageViewAddToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               detailsInterface.goToAddList(newsItem);
            }
        });


    }

    //this is the code required to open the news page news url
    public void openNewsUrl(String url){
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        customTabsIntent.launchUrl(getActivity(), Uri.parse(url));
    }

    DetailsInterface detailsInterface;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        detailsInterface = (DetailsInterface) context;
    }

    public interface DetailsInterface{
        void goToAddList(News item);
    }

}
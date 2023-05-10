package com.example.finalexam;

import android.app.appsearch.SearchResult;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.finalexam.databinding.FragmentSearchBinding;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class SearchFragment extends Fragment {
    public static final String TAG = "Final_Fall22";
    private final OkHttpClient client = new OkHttpClient();

    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentSearchBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    LinearLayoutManager linearLayoutManager;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        recyclerView = binding.recyclerView;
        linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);


        binding.buttonSearch.setOnClickListener(v -> {
            String query = binding.editTextSearchKeyword.getText().toString();
            if (query.isEmpty()) {
                Toast.makeText(requireContext(), "Please enter a search keyword", Toast.LENGTH_SHORT).show();
            } else {
                getSearchResults(query);
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
        if (item.getItemId() == R.id.logout_menu_item) {
            item.setOnMenuItemClickListener(item1 -> {
                mListener.logout();
                return true;
            });
            return true;
        } else if (item.getItemId() == R.id.my_favorites_menu_item) {
            item.setOnMenuItemClickListener(item1 -> {
                mListener.navigateToMyFavorites();
                return true;
            });
            //my favorites code goes here ...
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void getSearchResults(String query) {
        //base url is https://api.unsplash.com/search/photos/?client_id=your-acess-key&query=user-entered-keywords&per_page=50&orientation=landscape&content_filter=high

        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.unsplash.com/search/photos").newBuilder();
        HttpUrl url = urlBuilder.addQueryParameter("client_id", "wNvB79nPWCHCLoNvqQgcDNmsOGHONCAsTJ1A6HOevz4")
                .addQueryParameter("query", query)
                .addQueryParameter("per_page", "50")
                .addQueryParameter("orientation", "landscape")
                .addQueryParameter("content_filter", "high")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    Search person = gson.fromJson(response.body().charStream(), Search.class);
                    Log.d(TAG, "onResponse: " + person);
                } else {
                    Log.d(TAG, "onResponse: Failure " + response.body().string());
                }
            }
        });
    }

    SearchFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (SearchFragmentListener) context;
    }

    public interface SearchFragmentListener {
        void logout();

        void navigateToMyFavorites();
    }

    class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder> {

        ArrayList<ListItem> listItems;

        public SearchResultAdapter(ArrayList<ListItem> listItems) {
            this.listItems = listItems;
        }

        @NonNull
        @Override
        public SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_photo_search, parent, false);
            SearchResultViewHolder viewHolder = new SearchResultViewHolder(view);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull SearchResultViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return this.listItems.size();
        }

        class SearchResultViewHolder extends RecyclerView.ViewHolder {

            public SearchResultViewHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }
}
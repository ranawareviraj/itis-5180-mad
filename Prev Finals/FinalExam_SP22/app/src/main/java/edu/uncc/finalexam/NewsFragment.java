package edu.uncc.finalexam;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import edu.uncc.finalexam.databinding.FragmentNewsBinding;
import edu.uncc.finalexam.databinding.NewsRowItemBinding;
import edu.uncc.finalexam.models.News;

import kotlin.reflect.KFunction;
import okhttp3.HttpUrl;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class NewsFragment extends Fragment {

    FragmentNewsBinding binding;
    private final OkHttpClient client = new OkHttpClient();

    ArrayList<News> news = new ArrayList<>();
    public NewsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentNewsBinding.inflate(inflater,container,false);
        return  binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("News");

        appsAdapter = new AppsAdapter(getActivity(),news);
        binding.listViewNews.setAdapter(appsAdapter);

        binding.listViewNews.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                News selected = news.get(i);
                mListener.goToDetails(selected);
            }
        });

        getSearchResults();

        view.findViewById(R.id.buttonLogout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.logout();
            }
        });

        binding.buttonMyLists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               mListener.goToMyList();
            }
        });
    }
    public void getSearchResults(){
        //base url is https://api.unsplash.com/search/photos/?client_id=your-acess-key&query=user-entered-keywords&per_page=50&orientation=landscape&content_filter=high

        HttpUrl url = HttpUrl.parse("https://www.theappsdr.com/news_api.json").newBuilder()
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();


        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    String body = response.body().string();
                    try{
                        JSONObject jsonObject = new JSONObject(body);
                        JSONArray jsonList = jsonObject.getJSONArray("articles");
                        news.clear();

                        for(int i = 0;i<jsonList.length();i++){
                            JSONObject newsObject = jsonList.getJSONObject(i);

                            News newsItem = new News();
                            JSONObject sourceObject = newsObject.getJSONObject("source");
                            newsItem.setSourceName(sourceObject.getString("name"));
                            newsItem.setTitle(newsObject.getString("title"));
                            newsItem.setAuthor(newsObject.getString("author"));
                            newsItem.setUrl(newsObject.getString("urlToImage"));
                            newsItem.setWebUrl(newsObject.getString("url"));
                            newsItem.setPublishAt(newsObject.getString("publishedAt"));
                            news.add(newsItem);
                        }

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                appsAdapter.notifyDataSetChanged();
                            }
                        });


                    }catch(JSONException e){
                        throw new RuntimeException(e);
                    }


                }

            }
        });

    }


    AppsAdapter appsAdapter;
    class AppsAdapter extends ArrayAdapter<News> {
        public AppsAdapter(@NonNull Context context, @NonNull List<News> objects) {
            super(context,R.layout.news_row_item, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView == null){
                convertView = getLayoutInflater().inflate( R.layout.news_row_item, parent, false);
            }

            News item = getItem(position);
            TextView textViewNewsTitle = convertView.findViewById(R.id.textViewNewsTitle);
            TextView textViewNewsAuthor = convertView.findViewById(R.id.textViewNewsAuthor);
            TextView textViewPublishedAt = convertView.findViewById(R.id.textViewPublishedAt);
            TextView textViewSourceName = convertView.findViewById(R.id.textViewSourceName);
            ImageView imageViewNewsIcon = convertView.findViewById(R.id.imageViewNewsIcon);

            textViewNewsTitle.setText(item.getTitle());
            textViewNewsAuthor.setText(item.getAuthor());
            textViewPublishedAt.setText(item.getPublishAt());
            textViewSourceName.setText(item.getSourceName());
            Picasso.get().load(item.getUrl()).into(imageViewNewsIcon);


            return convertView;
        }
    }



    NewsFragmentListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (NewsFragmentListener) context;
    }

    public interface NewsFragmentListener{
        void logout();

        void goToDetails(News item);

        void goToMyList();
    }
}
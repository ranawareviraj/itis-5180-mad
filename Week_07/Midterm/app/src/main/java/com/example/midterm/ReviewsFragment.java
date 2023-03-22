package com.example.midterm;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.midterm.databinding.FragmentReviewsBinding;
import com.example.midterm.databinding.RowItemProductBinding;
import com.example.midterm.databinding.RowItemReviewBinding;
import com.example.midterm.models.Product;
import com.example.midterm.models.ProductsResponse;
import com.example.midterm.models.Review;
import com.example.midterm.models.ReviewsResponse;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReviewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReviewsFragment extends Fragment {

    private static final String ARG_PARAM_PRODUCT = "ARG_PARAM_PRODUCT";
    private Product mProduct;
    private final OkHttpClient client = new OkHttpClient();
    ArrayList<Review> reviews = new ArrayList<>();
    private static final String TAG = "MID_TERM";

    public ReviewsFragment() {
        // Required empty public constructor
    }


    public static ReviewsFragment newInstance(Product product) {
        ReviewsFragment fragment = new ReviewsFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_PRODUCT, product);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mProduct = (Product) getArguments().getSerializable(ARG_PARAM_PRODUCT);
        }
    }

    FragmentReviewsBinding binding;
    ArrayList<Review> mReviews = new ArrayList<>();
    ReviewsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentReviewsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String imageURL = mProduct.getImg_url();
        Picasso.get().load(imageURL).into(binding.imageViewProductIcon);
        binding.textViewProductName.setText(mProduct.getName());
        binding.textViewProductPrice.setText(mProduct.getPrice());
        String mProductUrl = mProduct.getImg_url();
        Picasso.get().load(mProductUrl).into(binding.imageViewProductIcon);
        getReviews(mProduct.getPid());
        adapter = new ReviewsAdapter(getActivity(), mReviews);
        binding.listView.setAdapter(adapter);

        binding.buttonCreateReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.createReview(mProduct);
            }
        });
    }

    ReviewsListListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (ReviewsListListener) context;
    }

    private void getReviews(String id) {
        Request request = new Request.Builder()
                .url("https://www.theappsdr.com/api/product/reviews/" + id)
                .build();
        Log.d(TAG, "createContact: " + request);

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    ResponseBody responseBody = response.body();
                    String body = responseBody.string();
                    Gson gson = new Gson();
                    ReviewsResponse reviewsResponse = gson.fromJson(body, ReviewsResponse.class);
                    mReviews.clear();
                    reviews = reviewsResponse.reviews;
                    Log.d(TAG, "Reviews Size: " + reviews.size());
                    for (Review review : reviews) {
                        System.out.println(review);
                    }
                    mReviews.addAll(reviews);
                    Log.d(TAG, "Reviews: " + reviews);

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });
    }

    private class ReviewsAdapter extends ArrayAdapter<Review> {
        public ReviewsAdapter(@NonNull Context context, @NonNull List<Review> objects) {
            super(context, R.layout.row_item_review, objects);
        }
        RowItemReviewBinding itemBinding;

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                itemBinding = RowItemReviewBinding.inflate(getLayoutInflater(), parent, false);
                convertView = itemBinding.getRoot();
                convertView.setTag(itemBinding);
            }
            itemBinding = (RowItemReviewBinding) convertView.getTag();
            Review review = getItem(position);

            itemBinding.textViewReview.setText(review.getReview());
            itemBinding.textViewReviewDate.setText(review.getCreated_at());

            String reviewRating = review.getRating();
            if (reviewRating.equals("1")) {
                itemBinding.imageViewReviewRating.setImageResource(R.drawable.stars_1);
            } else if (reviewRating.equals("2")) {
                itemBinding.imageViewReviewRating.setImageResource(R.drawable.stars_2);
            } else if (reviewRating.equals("3")) {
                itemBinding.imageViewReviewRating.setImageResource(R.drawable.stars_3);
            } else if (reviewRating.equals("4")) {
                itemBinding.imageViewReviewRating.setImageResource(R.drawable.stars_4);
            } else if (reviewRating.equals("5")) {
                itemBinding.imageViewReviewRating.setImageResource(R.drawable.stars_5);
            }
            return convertView;
        }
    }

    public interface ReviewsListListener {
        void createReview(Product product);
    }
}
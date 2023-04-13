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
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.midterm.databinding.FragmentCreateReviewBinding;
import com.example.midterm.models.Product;
import com.example.midterm.models.ProductsResponse;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateReviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateReviewFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private final OkHttpClient client = new OkHttpClient();
    private static final String ARG_PARAM_PRODUCT = "ARG_PARAM_PRODUCT";
    private static final String TAG = "MID-TERM";
    String reviewComment;
    String pId;
    String rating;

    private Product mProduct;

    public CreateReviewFragment() {
        // Required empty public constructor
    }

    public static CreateReviewFragment newInstance(Product product) {
        CreateReviewFragment fragment = new CreateReviewFragment();
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

    FragmentCreateReviewBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCreateReviewBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String imageURL = mProduct.getImg_url();
        Picasso.get().load(imageURL).into(binding.imageViewProductIcon);

//        binding.imageViewProductIcon
        binding.textViewProductName.setText(mProduct.getName());
        binding.textViewProductPrice.setText(mProduct.getPrice());
        binding.textViewProductName.setText(mProduct.getName());

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reviewComment = binding.editTextReview.getText().toString();
                pId = mProduct.getPid();
                rating = "5";
                if (reviewComment.isEmpty()) {
                    Toast.makeText(getContext(), "Enter review comment", Toast.LENGTH_SHORT).show();
                } else {
                    int checkedRadioButtonId = binding.radioGroup.getCheckedRadioButtonId();
                    if (checkedRadioButtonId == R.id.radioButton_level_4) {
                        rating = "4";
                    } else if (checkedRadioButtonId == R.id.radioButton_level_3) {
                        rating = "3";
                    } else if (checkedRadioButtonId == R.id.radioButton_level_2) {
                        rating = "2";
                    } else if (checkedRadioButtonId == R.id.radioButton_level_1) {
                        rating = "1";
                    }
                    createReview(reviewComment, pId, rating);
                }
            }
        });
    }

    void createReview(String reviewComment, String pId, String rating) {

        HttpUrl url = HttpUrl.parse("https://www.theappsdr.com/api/product/review")
                .newBuilder()
                .build();

        FormBody formBody = new FormBody.Builder()
                .add("pid", pId)
                .add("review", reviewComment)
                .add("rating", rating)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
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
                    Log.d(TAG, "onResponse: " + body);
                    Gson gson = new Gson();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mListener.reviewSubmitted();
                        }
                    });
                }else {
                    Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    CreateReviewFragmentListener mListener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (CreateReviewFragmentListener)context;

    }

    interface CreateReviewFragmentListener{
        void reviewSubmitted();
    }
}
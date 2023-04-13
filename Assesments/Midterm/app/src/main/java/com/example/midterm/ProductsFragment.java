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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.midterm.databinding.FragmentProductsBinding;
import com.example.midterm.databinding.RowItemProductBinding;
import com.example.midterm.models.Product;
import com.example.midterm.models.ProductsResponse;
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


public class ProductsFragment extends Fragment {
    private final OkHttpClient client = new OkHttpClient();
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String TAG = "MID_TERM";
    ArrayList<Product> products;
    ProductsAdapter adapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProductsFragment() {
        // Required empty public constructor
    }


    public static ProductsFragment newInstance(String param1, String param2) {
        ProductsFragment fragment = new ProductsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    FragmentProductsBinding binding;
    ArrayList<Product> mProducts = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProductsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Products");
        getProducts();
        adapter = new ProductsAdapter(getActivity(), mProducts);
        binding.listView.setAdapter(adapter);
        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product product = mProducts.get(position);
                mListener.sendProduct(product);
            }
        });
    }

    private void getProducts() {
        Request request = new Request.Builder()
                .url("https://www.theappsdr.com/api/products")
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
                    Log.d(TAG, "onResponse: " + body);
                    Gson gson = new Gson();
                    ProductsResponse productsResponse = gson.fromJson(body, ProductsResponse.class);
                    mProducts.clear();
                    products = productsResponse.products;
                    mProducts.addAll(products);
                    Log.d(TAG, "onResponse: " + products);

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

    ProductsListListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (ProductsListListener) context;
    }

    private class ProductsAdapter extends ArrayAdapter<Product> {

        public ProductsAdapter(@NonNull Context context, @NonNull List<Product> objects) {
            super(context, R.layout.row_item_product, objects);
        }

        RowItemProductBinding itemBinding;

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                itemBinding = RowItemProductBinding.inflate(getLayoutInflater(), parent, false);
                convertView = itemBinding.getRoot();
                convertView.setTag(itemBinding);
            }
            itemBinding = (RowItemProductBinding) convertView.getTag();
            Product product = getItem(position);

            itemBinding.textViewProductName.setText(product.getName());
            itemBinding.textViewProductDesc.setText(product.getDescription());
            itemBinding.textViewProductPrice.setText(product.getPrice());
            itemBinding.textViewProductReviews.setText(product.getReview_count() + " Reviews");
            String url = product.getImg_url();
            Picasso.get().load(url).into(itemBinding.imageViewProductIcon);
            return convertView;
        }
    }

    public interface ProductsListListener {
        void sendProduct(Product product);
    }
}

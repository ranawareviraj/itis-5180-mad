package edu.uncc.assessment06;

import android.content.ClipData;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import edu.uncc.assessment06.databinding.CartRowItemBinding;
import edu.uncc.assessment06.databinding.FragmentCartBinding;
import edu.uncc.assessment06.databinding.GradeRowItemBinding;

public class CartFragment extends Fragment {

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentCartBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCartBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("My Cart");
        adapter = new CartAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(adapter);
        getCart();
    }


    ArrayList<CartItem> mItems = new ArrayList<>();
    ListenerRegistration listenerRegistration;
    CartAdapter adapter;

    private void getCart() {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        listenerRegistration = db.collection("carts")
                .whereEqualTo("owner_id", FirebaseAuth.getInstance().getCurrentUser().getUid())
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {
                            mItems.clear();
                            for (QueryDocumentSnapshot doc : value) {
                                CartItem item = doc.toObject(CartItem.class);
                                mItems.add(item);
                            }
                            calculateTotal();
                            adapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void calculateTotal() {
        if (mItems.size() == 0) {
            binding.textViewTotal.setText("$0.00");
        } else {
            double price = 0.0;
            for (CartItem item : mItems) {
                price += Double.valueOf(item.getPrice());
            }
            binding.textViewTotal.setText(String.format("$%.2f", price) + "");
        }
    }


    class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
        @NonNull
        @Override
        public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            CartRowItemBinding rowBinding = CartRowItemBinding.inflate(getLayoutInflater(), parent, false);
            return new CartViewHolder(rowBinding);
        }

        @Override
        public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
            CartItem item = mItems.get(position);
            holder.setupUI(item);
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }

        class CartViewHolder extends RecyclerView.ViewHolder {
            CartRowItemBinding mBinding;
            CartItem mItem;

            public CartViewHolder(@NonNull CartRowItemBinding rowBinding) {
                super(rowBinding.getRoot());
                this.mBinding = rowBinding;
            }

            public void setupUI(CartItem item) {
                this.mItem = item;
                mBinding.textViewProductName.setText(mItem.getName());
                mBinding.textViewProductPrice.setText(mItem.getPrice());

                Picasso.get().load(mItem.getUrl()).into(mBinding.imageViewProductIcon);

                // To implement
                mBinding.imageViewDeleteFromCart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        FirebaseFirestore.getInstance().collection("carts")
                                .document(mItem.getDocId()).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                    }
                                });
                    }
                });
            }
        }
    }
}
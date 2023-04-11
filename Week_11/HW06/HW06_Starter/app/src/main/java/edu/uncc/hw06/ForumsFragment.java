package edu.uncc.hw06;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.uncc.hw06.databinding.FragmentForumsBinding;

public class ForumsFragment extends Fragment {

    public ForumsFragment() {
        // Required empty public constructor
    }

    FragmentForumsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentForumsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Forums");
        binding.buttonCreateForum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.createNewForum();
            }
        });

        binding.buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.logout();
            }
        });
    }


    ForumsListener mListener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mListener = (ForumsListener) context;
    }

    interface ForumsListener {
        void createNewForum();

        void logout();
    }

    class ForumsAdapter extends RecyclerView.Adapter<ForumsAdapter.ForumsViewHolder> {
        List<Forum> forums;
        Context context;

        public ForumsAdapter(List<Forum> forums, Context context) {
            this.forums = forums;
            this.context = context;
        }

        @NonNull
        @Override
        public ForumsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.forum_row_item, parent, false);
            return new ForumsViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ForumsViewHolder holder, int position) {
            Forum forum = forums.get(position);
            holder.bind(forum);
        }

        @Override
        public int getItemCount() {
            return forums.size();
        }

        class ForumsViewHolder extends RecyclerView.ViewHolder {
            TextView textViewTitle, textViewDescription, textViewCreatedBy, textViewCreatedAt;

            public ForumsViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewTitle = itemView.findViewById(R.id.textViewForumTitle);
                textViewDescription = itemView.findViewById(R.id.textViewForumText);
                textViewCreatedBy = itemView.findViewById(R.id.textViewForumCreatedBy);
                textViewCreatedAt = itemView.findViewById(R.id.textViewForumLikesDate);
            }

            public void bind(Forum forum) {
                textViewTitle.setText(forum.getTitle());
                textViewDescription.setText(forum.getDescription());
                textViewCreatedBy.setText(forum.getCreatedBy());
                textViewCreatedAt.setText(forum.getCreatedAt());
            }
        }
    }
}
package com.nexti.android.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.nexti.android.R;
import com.nexti.android.ui.discover.UserModel;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {
    public Context context;
    public List<PostModel> listPosts;

    FirebaseUser firebaseUser;

    public PostAdapter(Context context, List<PostModel> listPosts) {
        this.context = context;
        this.listPosts = listPosts;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item_post , parent ,false);
        return new PostAdapter.PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        PostModel post = listPosts.get(position);

        Glide.with(context)
                .load(post.getPostImage())
                .into(holder.image_post);

        if (post.getDescription().equals("")){
            holder.description.setVisibility(View.GONE);
        } else {
            holder.description.setVisibility(View.VISIBLE);
            holder.description.setText(post.getDescription());
        }

        publication((ImageButton) holder.image_profile,holder.username,holder.author, post.getAuthor());
    }

    @Override
    public int getItemCount() {
        return listPosts.size();
    }

    public class PostViewHolder extends RecyclerView.ViewHolder{
        public ImageView image_profile , image_post , like , comment , share;
        public TextView username , likes , author , description , comments;



        public PostViewHolder(@NonNull View itemView) {
            super(itemView);

            image_profile = itemView.findViewById(R.id.image_profile);
            image_post = itemView.findViewById(R.id.post_user_medias);
            like = itemView.findViewById(R.id.like);
            comment = itemView.findViewById(R.id.comment);
            username = itemView.findViewById(R.id.username_post);
            likes = itemView.findViewById(R.id.counter_stars);
            author = itemView.findViewById(R.id.author);
            description = itemView.findViewById(R.id.description);
            comments = itemView.findViewById(R.id.counter_comments);
        }
    }

    private void publication(final ImageButton image_profile , final TextView username , final TextView author , final String userId){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users").child("userId");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                UserModel user = dataSnapshot.getValue(UserModel.class);
                Glide.with(context).asBitmap()
                        .load(user.getImageUrl())
                        .into(image_profile);
                username.setText(user.getUsername());
                author.setText(user.getUsername());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}

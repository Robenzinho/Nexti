package com.nexti.android.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TestUserAdapter extends RecyclerView.Adapter<TestUserAdapter.TestUserHolder> {


    private Context context;
    private List<TestUser> listUsers;
    private FirebaseUser firebaseUser;

    public TestUserAdapter(Context context, List<TestUser> listUsers) {
        this.context = context;
        this.listUsers = listUsers;
    }


    @NonNull
    @Override
    public TestUserAdapter.TestUserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_user_discover , parent , false);
        return new TestUserAdapter.TestUserHolder(view);
    }


    public static class TestUserHolder extends RecyclerView.ViewHolder{
        public CircleImageView image_profile;
        public TextView username, country;
        public Button btn_follow;

        public TestUserHolder(@NonNull View itemView) {
            super(itemView);

            image_profile = itemView.findViewById(R.id.image_user_search);
            username = itemView.findViewById(R.id.username_search);
            country = itemView.findViewById(R.id.country_search);
            btn_follow = itemView.findViewById(R.id.action_follow);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull final TestUserAdapter.TestUserHolder holder, int position) {
       String userImage = listUsers.get(position).getImageUrl();
        String username = listUsers.get(position).getUsername();
        String country = listUsers.get(position).getCountry();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final TestUser user= listUsers.get(position);

        holder.username.setText(username);
        holder.country.setText(country);

        holder.btn_follow.setVisibility(View.VISIBLE);

        Glide.with(context).asBitmap()
                .load(userImage)
                .into(holder.image_profile);

        isFollowing(user.getId() , holder.btn_follow);

        if (user.getId().equals(firebaseUser.getUid())){
            holder.btn_follow.setVisibility(View.GONE);
        }

        holder.btn_follow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.btn_follow.getText().toString().equals("follow")){
                    FirebaseDatabase.getInstance().getReference()
                            .child("Follow").child(firebaseUser.getUid())
                            .child("following").child(user.getId()).setValue(true);

                    FirebaseDatabase.getInstance().getReference()
                            .child("Follow").child(user.getId())
                            .child("followers").child(firebaseUser.getUid()).setValue(true);

                }else {
                    FirebaseDatabase.getInstance().getReference()
                            .child("Follow").child(firebaseUser.getUid())
                            .child("following").child(user.getId()).removeValue();

                    FirebaseDatabase.getInstance().getReference()
                            .child("Follow").child(user.getId())
                            .child("followers").child(firebaseUser.getUid()).removeValue();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return listUsers.size();
    }




    private void isFollowing(final String id, final Button btn_follow) {
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("Follow").child(firebaseUser.getUid()).child("following");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(id).exists()){
                    btn_follow.setText("following");

                }else {
                    btn_follow.setText("follow");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}

package com.nexti.android.ui.discover;

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
import com.nexti.android.ui.main.TestUser;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private Context context;
    private List<UserModel>listUsers;

    FirebaseUser firebaseUser;

    public UserAdapter(Context context, List<UserModel> listUsers) {
        this.context = context;
        this.listUsers = listUsers;
    }


    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_user_discover , parent , false);
        return new UserAdapter.UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final UserViewHolder holder, int position) {
//        String userImage = listUsers.get(position).getImageUrl();

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final UserModel user= listUsers.get(position);

        holder.username.setText(user.getUsername());
        holder.country.setText(user.getCountry());

        holder.btn_follow.setVisibility(View.VISIBLE);

        Glide.with(context).asBitmap()
                .load(user.getImageUrl())
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



    public static class UserViewHolder extends RecyclerView.ViewHolder {
        public CircleImageView image_profile;
        public TextView username, country;
        public Button btn_follow;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            image_profile = itemView.findViewById(R.id.image_user_search);
            username = itemView.findViewById(R.id.username_search);
            country = itemView.findViewById(R.id.country_search);
            btn_follow = itemView.findViewById(R.id.action_follow);

        }
    }

    private void isFollowing(final String userId , final Button button){
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference()
                .child("Follow").child(firebaseUser.getUid()).child("following");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.child(userId).exists()){
                    button.setText("following");

                }else {
                    button.setText("follow");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

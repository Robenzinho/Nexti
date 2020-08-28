package com.nexti.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.HashMap;


public class RegisterActivity extends AppCompatActivity {
    EditText username , password , confirmPass, email , country;
    Button register;
    TextView login;

    private FirebaseAuth auth;
    private DatabaseReference reference;

    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        username = findViewById(R.id.username_register);
        password = findViewById(R.id.password_register);
        confirmPass = findViewById(R.id.confirm_password_register);
        email = findViewById(R.id.email_register);
        country = findViewById(R.id.country_register);
        register =findViewById(R.id.action_register);
        login = findViewById(R.id.link_to_login);

        auth = FirebaseAuth.getInstance();

//        StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();

        // login Button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });

        // Implement Register : New User Auth. + Firebase
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd = new ProgressDialog(RegisterActivity.this);
                pd.setMessage("Please Wait..");
                pd.show();


                String vName = username.getText().toString();
                String vEmail = email.getText().toString();
                String vCountry = country.getText().toString();
                String vPassword = password.getText().toString();
                String vConfirmPass = confirmPass.getText().toString();

                if(TextUtils.isEmpty(vName)||TextUtils.isEmpty(vEmail)||TextUtils.isEmpty(vPassword)||TextUtils.isEmpty(vConfirmPass)||TextUtils.isEmpty(vCountry)){
                    Toast.makeText(RegisterActivity.this, "All field are required", Toast.LENGTH_LONG).show();
                    pd.dismiss();
                } else if(vPassword.length()<6){
                    Toast.makeText(RegisterActivity.this, "Too short , at least 6 characters", Toast.LENGTH_SHORT).show();
                    pd.dismiss();
                } else if(!vPassword.equals(vConfirmPass)){
                    Toast.makeText(RegisterActivity.this, "Password Should be the same", Toast.LENGTH_LONG).show();
                    pd.dismiss();
                }
                else{
                    pd = new ProgressDialog(RegisterActivity.this);
                    pd.setMessage("please wait a moment...");
                    pd.show();
                    registered(vName , vEmail, vPassword ,vCountry );
                }
            }
        });

    }



    private void registered(final String username , final String email , String password , final String country){
        auth.createUserWithEmailAndPassword(email , password )
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = auth.getCurrentUser();
                            String userId = firebaseUser.getUid();
                            reference = FirebaseDatabase.getInstance().getReference().child("Users").child(userId);

                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("id",userId);
                            hashMap.put("email",email);
                            hashMap.put("username",username.toLowerCase());
                            hashMap.put("country",country.toLowerCase());
                            hashMap.put("imageUrl","https://firebasestorage.googleapis.com/v0/b/nexti-1b612.appspot.com/o/usa.jpg?alt=media&token=c1662d88-b8f8-46d9-9925-dcce0d79713e");
                            hashMap.put("bio","");
                            hashMap.put("phone" , "");
                            hashMap.put("discover", "");
                            hashMap.put("age", "");
                            hashMap.put("sex" , "");
                            hashMap.put("badge" , "");
                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        pd.dismiss();
                                        Intent intent = new Intent( RegisterActivity.this , MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                    }
                                }
                            });

                        } else{
                            pd.dismiss();
                            Toast.makeText(RegisterActivity.this, "try again , couldn't register with this email or password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
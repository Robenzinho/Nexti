package com.nexti.android.ui.courses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nexti.android.R;

import java.util.ArrayList;

public class CoursesFragment extends Fragment {

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mColor = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();

    public CoursesFragment() {
        // Required empty public constructor
    }




    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
        init();



    }

    private void init(){
        mImageUrls.add("https://firebasestorage.googleapis.com/v0/b/nexti-1b612.appspot.com/o/usa.jpg?alt=media&token=c1662d88-b8f8-46d9-9925-dcce0d79713e");
        mNames.add("English");
        mImageUrls.add("https://firebasestorage.googleapis.com/v0/b/nexti-1b612.appspot.com/o/user.jpg?alt=media&token=25dbbca9-6d7a-40ce-a099-9628e98b6778");
        mNames.add("Spanish");
        mImageUrls.add("https://quinntessentialsports.files.wordpress.com/2014/06/flag-of-usa-1080x1920.jpg");
        mNames.add("French");
        mImageUrls.add("https://firebasestorage.googleapis.com/v0/b/nexti-1b612.appspot.com/o/user.jfif?alt=media&token=25dbbca9-6d7a-40ce-a099-9628e98b6778");
        mNames.add("Korean");
        mImageUrls.add("https://th.bing.com/th/id/OIP.Fcvtx_oODyyzu2X9es2cIAHaEo?w=249&h=180&c=7&o=5&pid=1.7");
        mNames.add("Country");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_courses, container, false);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext() , LinearLayoutManager.HORIZONTAL ,false);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        CoursesAdapter adapter = new CoursesAdapter(mNames , mImageUrls , getContext());
        recyclerView.setAdapter(adapter);
        return view;
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.pers_menu ,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.add) {
            Toast.makeText(getActivity(), "courses", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
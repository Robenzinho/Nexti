package com.nexti.android.ui.tools;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.nexti.android.R;
import com.nexti.android.ui.tools.activities.challenges.ChallengesActivity;
import com.nexti.android.ui.tools.activities.chatbot.ChatbotActivity;
import com.nexti.android.ui.tools.activities.resources.ResourcesActivity;
import com.nexti.android.ui.tools.activities.words.WordsActivity;


public class ToolsFragment extends Fragment {
CardView words , bot , challenges , resources;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tools, container, false);
        words = view.findViewById(R.id.words);
        bot = view.findViewById(R.id.chat_bot);
        challenges = view.findViewById(R.id.challenges);
        resources = view.findViewById(R.id.resources);

        words.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity() , WordsActivity.class));
            }
        });

        bot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity() , ChatbotActivity.class));
            }
        });

        challenges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity() , ChallengesActivity.class));
            }
        });

        resources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity() , ResourcesActivity.class));
            }
        });


        return view;
    }


    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.pers_menu ,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.notifications) {
            Toast.makeText(getActivity(), "hello world", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
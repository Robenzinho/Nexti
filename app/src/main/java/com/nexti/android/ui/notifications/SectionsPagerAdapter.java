package com.nexti.android.ui.notifications;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0 :
                return new ActivitiesFragment();
            case 1 :
                return new MessagesFragment();
            case 2:
                return new SuggestionsFragment();
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0:
                return "Activities";
            case 1 :
                return "Messages";
            case 2 :
                return "Ads";
            default:
                return null;
        }
    }

}

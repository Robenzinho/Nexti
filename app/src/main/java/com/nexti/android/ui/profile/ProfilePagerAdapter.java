package com.nexti.android.ui.profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


public class ProfilePagerAdapter extends FragmentPagerAdapter {
    public ProfilePagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0 :
                return new ProfilePostsFragment();
            case 1 :
                return new ProfileQueriesFragment();
            case 2:
                return new ProfileStatsFragment();
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
                return "Posts";
            case 1 :
                return "Discuss";
            case 2 :
                return "Statistics";
            default:
                return null;
        }
    }
}

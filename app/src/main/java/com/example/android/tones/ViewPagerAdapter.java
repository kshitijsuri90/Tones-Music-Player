package com.example.android.tones;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentPagerAdapter{

    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<String> title =new ArrayList<>();
    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }

    @Override
    public int getCount() {
        return title.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    public void AddFragment(Fragment fragment, String Title){
        fragments.add(fragment);
        title.add(Title);
    }
}

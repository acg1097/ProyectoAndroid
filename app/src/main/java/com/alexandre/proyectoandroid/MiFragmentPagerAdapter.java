package com.alexandre.proyectoandroid;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ALEXANDRE on 29/06/2017.
 */

public class MiFragmentPagerAdapter extends FragmentPagerAdapter{

    List<Fragment> fragments;

    public List<Fragment> getFragments() {
        return fragments;
    }

    public void setFragments(List<Fragment> fragments) {
        this.fragments = fragments;
    }

    /**
     * Constructor
     *
     * @param fm
     *            interface for interacting with Fragment objects inside of an
     *            Activity
     */
    public MiFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragments = new ArrayList<Fragment>();
    }

    /**
     * Add a new fragment in the list.
     *
     * @param fragment
     *            a new fragment
     */
    public void addFragment(Fragment fragment) {
        this.fragments.add(fragment);
    }

    @Override
    public Fragment getItem(int arg0) {
        return this.fragments.get(arg0);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }


}

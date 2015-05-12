package com.example.steven.app1;


import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class SwipeTab extends FragmentActivity {

    ActionBar act;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_tab);
//        act=getActionBar(
        viewPager=(ViewPager)findViewById(R.id.pager);
        viewPager.setAdapter(new Myadpter(getSupportFragmentManager()));
    }


}

class Myadpter extends FragmentStatePagerAdapter {


    public Myadpter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment temp=null;
        switch (position)
        {

            case 0:
            {
                temp=new Fragment1();
                break;
            }
            case 1:
            {
                temp=new Fragment2();
                break;
            }
            case 2:
            {
                temp=new Fragment3();
                break;
            }


        }
        return temp;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return new String("Title"+position);
    }
}
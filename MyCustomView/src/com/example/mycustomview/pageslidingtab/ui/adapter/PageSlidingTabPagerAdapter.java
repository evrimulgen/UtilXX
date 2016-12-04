package com.example.mycustomview.pageslidingtab.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.mycustomview.R;
import com.example.mycustomview.pageslidingtab.CommonUtil;
import com.example.mycustomview.pageslidingtab.ui.fragment.FragmentFactory;

public class PageSlidingTabPagerAdapter extends FragmentPagerAdapter {

    private String[] tabArr;

    public PageSlidingTabPagerAdapter(FragmentManager fm) {
        super(fm);
        tabArr = CommonUtil.getStringArray(R.array.tab_names);
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentFactory.create(position);
    }

    @Override
    public int getCount() {
        return tabArr.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabArr[position];
    }
}
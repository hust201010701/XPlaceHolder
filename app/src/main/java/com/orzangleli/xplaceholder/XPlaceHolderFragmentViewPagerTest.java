package com.orzangleli.xplaceholder;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class XPlaceHolderFragmentViewPagerTest extends AppCompatActivity {
    TabLayout mTabLayout;
    ViewPager mViewPager;
    FragmentStatePagerAdapter mFragmentStatePagerAdapter;
    List<Fragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xplace_holder_fragment_view_pager_test);

        // 创建一个viewpager  和 FragmentPagerAdapter
        mTabLayout = this.findViewById(R.id.tabLayout);
        mViewPager = this.findViewById(R.id.viewpager);

        mFragments = new ArrayList<>();
        ViewPagerItemFragment viewPagerItemFragment1 = new ViewPagerItemFragment();
        viewPagerItemFragment1.setName("第一");
        mFragments.add(viewPagerItemFragment1);

        ViewPagerItemFragment viewPagerItemFragment2 = new ViewPagerItemFragment();
        viewPagerItemFragment2.setName("第二");
        mFragments.add(viewPagerItemFragment2);

        mFragmentStatePagerAdapter = new FragmentStatePagerAdapter(this.getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        };

        mViewPager.setAdapter(mFragmentStatePagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);

        for (int i = 0; i < mTabLayout.getTabCount(); i++) {
            TabLayout.Tab tabAt = mTabLayout.getTabAt(i);
            if (tabAt != null) {
                tabAt.setText("第"+i+"个");
            }
        }
    }
}

package com.abu.dailyreminder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.abu.dailyreminder.ui.activity.BaseActivity;
import com.abu.dailyreminder.ui.fragment.DailyFragment;
import com.abu.dailyreminder.ui.fragment.StatisticFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @date: 2019/7/15 9:15
 * @author: 董长峰
 * @blog: https://www.jianshu.com/u/04a705fae99b
 * @description: 首页
 */
public class MainActivity extends BaseActivity {


    @BindView(R.id.tl_tabs)
    TabLayout tlTabs;
    @BindView(R.id.vp_content)
    ViewPager vpContent;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        super.init();
        //设置toolbar
        setSupportActionBar(toolbar);
        //状态栏标题设置透明
       // setStatusBarTransparent();
        //设置界面文件和文字一一对应
        final Fragment[] fragments = {new DailyFragment(), new StatisticFragment()};
        final String[] titles = {"日常提醒", "完成统计"};
        //设置适配器
        vpContent.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments[i];
            }

            @Override
            public int getCount() {
                return fragments.length;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return titles[position];
            }
        });
        //标题与fragment建立关联
        tlTabs.setupWithViewPager(vpContent);
        tlTabs.getTabAt(0).select();//设置第一个为选中
    }

}

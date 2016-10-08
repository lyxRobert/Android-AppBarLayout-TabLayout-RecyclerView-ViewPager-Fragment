package com.lyx.demo.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lyx.demo.R;
import com.lyx.demo.adapter.BannerAdapter;
import com.lyx.demo.adapter.MyViewPagerAdapter;
import com.lyx.demo.fragment.FragmentLowPrice;
import com.lyx.demo.fragment.FragmentHighPrice;
import com.lyx.demo.fragment.FragmentPopularity;
import com.lyx.demo.fragment.FragmentSecondsOpen;
import com.lyx.demo.fragment.FragmentNewProduct;
import com.lyx.demo.utils.DensityUtil;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    LinearLayout llIndexContainer;
    private List<String> mADParseArray;
    private final int HOME_AD_RESULT = 1;
    private ViewPager vp_shuffling;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                // 广告
                case HOME_AD_RESULT:
                    vp_shuffling.setCurrentItem(vp_shuffling.getCurrentItem() + 1,
                            true);
                    break;
            }
        };
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        ViewPager mViewPager = (ViewPager) findViewById(R.id.viewpager);
        MyViewPagerAdapter viewPagerAdapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFragment(FragmentPopularity.newInstance(), "人气");//添加Fragment
        viewPagerAdapter.addFragment(FragmentNewProduct.newInstance(), "新品");
        viewPagerAdapter.addFragment(FragmentSecondsOpen.newInstance(), "秒开");
        viewPagerAdapter.addFragment(FragmentHighPrice.newInstance(), "高价");
        viewPagerAdapter.addFragment(FragmentLowPrice.newInstance(), "低价");
        mViewPager.setAdapter(viewPagerAdapter);//设置适配器

        TabLayout mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mTabLayout.addTab(mTabLayout.newTab().setText("人气"));//给TabLayout添加Tab
        mTabLayout.addTab(mTabLayout.newTab().setText("新品"));
        mTabLayout.addTab(mTabLayout.newTab().setText("秒开"));
        mTabLayout.addTab(mTabLayout.newTab().setText("高价"));
        mTabLayout.addTab(mTabLayout.newTab().setText("低价"));
        mTabLayout.setupWithViewPager(mViewPager);//给TabLayout设置关联ViewPager，如果设置了ViewPager，那么ViewPagerAdapter中的getPageTitle()方法返回的就是Tab上的标题
    }

    private void initView() {
        vp_shuffling = (ViewPager) findViewById(R.id.vp_shuffling);
        // 广告数据
        llIndexContainer = (LinearLayout)findViewById(R.id.ll_index_container);
        mADParseArray = new ArrayList<String>();
        mADParseArray
                .add("http://m.easyto.com/m/zhulifuwu_banner.jpg");
        mADParseArray
                .add("http://m.easyto.com/m/japan/images/banner_3y_new.jpg");
        mADParseArray
                .add("http://m.easyto.com/m/japan/images/banner_5y_new.jpg");
        vp_shuffling.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                refreshPoint(position % mADParseArray.size());
                if (mHandler.hasMessages(HOME_AD_RESULT)) {
                    mHandler.removeMessages(HOME_AD_RESULT);
                }
                mHandler.sendEmptyMessageDelayed(HOME_AD_RESULT, 3000);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                if (ViewPager.SCROLL_STATE_DRAGGING == arg0
                        && mHandler.hasMessages(HOME_AD_RESULT)) {
                    mHandler.removeMessages(HOME_AD_RESULT);
                }
            }
        });
        BannerAdapter adapter = new BannerAdapter(MainActivity.this, mADParseArray);
        vp_shuffling.setAdapter(adapter);
        addIndicatorImageViews(mADParseArray.size());
        vp_shuffling.setCurrentItem(mADParseArray.size() * 1000, false);
        // 自动轮播线程
        mHandler.sendEmptyMessageDelayed(HOME_AD_RESULT, 3000);
    }
    // 添加指示图标
    private void addIndicatorImageViews(int size) {
        llIndexContainer.removeAllViews();
        for (int i = 0; i < size; i++) {
            ImageView iv = new ImageView(MainActivity.this);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(DensityUtil.dip2px(MainActivity.this, 5), DensityUtil.dip2px(MainActivity.this, 5));
            if (i != 0) {
                lp.leftMargin = DensityUtil.dip2px(MainActivity.this, 7);
            }
            iv.setLayoutParams(lp);
            iv.setBackgroundResource(R.drawable.xml_round_orange_grey_sel);
            iv.setEnabled(false);
            if (i == 0) {
                iv.setEnabled(true);
            }
            llIndexContainer.addView(iv);
        }
    }
    // 为ViewPager设置监听器
    private void refreshPoint(int position) {
        if (llIndexContainer != null) {
            for (int i = 0; i < llIndexContainer.getChildCount(); i++) {
                llIndexContainer.getChildAt(i).setEnabled(false);
                if (i == position) {
                    llIndexContainer.getChildAt(i).setEnabled(true);
                }
            }
        }
    }
}

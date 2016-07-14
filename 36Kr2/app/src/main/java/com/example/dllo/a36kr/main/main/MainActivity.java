package com.example.dllo.a36kr.main.main;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.example.dllo.a36kr.R;
import com.example.dllo.a36kr.main.base.BaseActivity;
import com.example.dllo.a36kr.main.base.ClickToEquity;
import com.example.dllo.a36kr.main.tools.the_values.TheValues;

public class MainActivity extends BaseActivity implements ClickToEquity {

    private MainAdapter adapter;
    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        viewPager = (ViewPager) findViewById(R.id.main_viewPager);
        tabLayout = (TabLayout) findViewById(R.id.main_tabLayout);
    }

    @Override
    public void initData() {
        adapter = new MainAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        //取消tab 下的 滑动标签
        tabLayout.setSelectedTabIndicatorHeight(Color.GRAY);

        //设置tab的背景色
//        tabLayout.setBackgroundColor(this.getResources().getColor(R.color.bg));
        //设置当前tab页签的下划线颜色
//        tabLayout.setSelectedTabIndicatorColor();

        tabLayout.getTabAt(0).setIcon(R.drawable.tab_new);
        tabLayout.getTabAt(1).setIcon(R.drawable.tab_equity);
        tabLayout.getTabAt(2).setIcon(R.drawable.tab_discovery);
        tabLayout.getTabAt(3).setIcon(R.drawable.tab_person);
    }

    //位置2 fragment 跳转 位置 1 fragment
    @Override
    public void toEquity() {
        viewPager.setCurrentItem(1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == TheValues.RESULT_CODE) {
            viewPager.setCurrentItem(1);

        }
    }

    /*
     * 判断返回再一次退出
	 */
    //标识是否退出
    private boolean isExit;
    //当按下物理按键的时候会调用此方法
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断是否为返回键
        if (keyCode == event.KEYCODE_BACK) {
            //退出
            exit();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            Toast.makeText(this, "再按一次返回退出", Toast.LENGTH_SHORT).show();
            //使用Handler延时3秒发送(postDelayed)
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 3000);
        } else {
            finish();
            System.exit(0);
        }
    }


}

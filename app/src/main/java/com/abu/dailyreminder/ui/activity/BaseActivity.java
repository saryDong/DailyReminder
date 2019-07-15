package com.abu.dailyreminder.ui.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import butterknife.ButterKnife;

/**
 *  @date: 2018/11/20 9:00
 *  @author: 董长峰
 *  @blog: https://www.jianshu.com/u/04a705fae99b
 *  @description:  所有Activity基类，提供延时跳转，状态栏，软键盘等功能
 */
public abstract class BaseActivity extends AppCompatActivity {
    /**
     * 延时执行任务的handler
     */
    private Handler mHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * 设置布局
         */
        setContentView(getLayoutResId());
        /**
         * 控件绑定
         */
        ButterKnife.bind(this);
        /**
         * 进行初始化工作
         */
        init();
    }

    /**
     * 进行初始化工作
     */
    protected void init() {
    }

    /**
     * 抽象方法每个子类必须实现，返回布局id
     * @return 返回布局id
     */
    public abstract int getLayoutResId();

    /**
     * 延时执行任务
     * @param runnable runnable对象
     * @param delayMillis 延迟时间
     */
    protected void postDelay(Runnable runnable, long delayMillis) {
        if (mHandler == null) {
            mHandler = new Handler();
        }
        mHandler.postDelayed(runnable, delayMillis);
    }

    /**
     * 带返回值Activity跳转
     * @param to   有返回值跳转
     * @param requestCode 请求码
     */
    public void navigateForResultTo(Class to, int requestCode) {
        Intent intent = new Intent(this, to);
        startActivityForResult(intent, requestCode);
    }

    /**
     * Activity普通跳转
     * @param to 跳转的Activity
     */
    public void navigateTo(Class to) {
        Intent intent = new Intent(this, to);
        startActivity(intent);
        finish();
    }

    /**
     * 隐藏软键盘
     */
    protected void hideSoftKeyboard() {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null) {
            assert inputMethodManager != null;
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
                default:
        }
        return true;
    }

    /**
     * 将状态栏背景颜色设置为透明
     */
    protected void setStatusBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //activity销毁时清除handler
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }
}

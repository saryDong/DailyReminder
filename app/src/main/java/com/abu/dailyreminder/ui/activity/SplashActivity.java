package com.abu.dailyreminder.ui.activity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.abu.dailyreminder.MainActivity;
import com.abu.dailyreminder.R;

import butterknife.BindView;

/**
 * @date: 2019/7/15 9:15
 * @author: 董长峰
 * @blog: https://www.jianshu.com/u/04a705fae99b
 * @description: 应用启动页面，防止白屏和黑屏情况的发生
 */
public class SplashActivity extends BaseActivity {
    @BindView(R.id.countdown)
    TextView countdown;
    private CountDownTimer timer;


    @Override
    public int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init() {
        super.init();

        timer = new CountDownTimer(5 * 1000, 1000) {
            @Override
            public void onTick(long l) {
                 countdown.setText("还剩"+(l/1000+1)+"秒");
            }

            @Override
            public void onFinish() {
                  navigateTo(MainActivity.class);
            }
        };
        timer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer!=null){
            timer.cancel();
        }
    }
}

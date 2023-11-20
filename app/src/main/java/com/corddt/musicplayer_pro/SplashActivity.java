package com.corddt.musicplayer_pro;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 判断日间/夜间模式
        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                // 日间模式
                setContentView(R.layout.splash_screen_day);
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                // 夜间模式
                setContentView(R.layout.splash_screen_night);
                break;
        }

        // 加载动画资源文件
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.splash_animation);

        // 获取布局文件中的 ImageView 元素
        ImageView splashImage = findViewById(R.id.splash_image);

        // 设置动画效果
        splashImage.startAnimation(anim);

        // 延迟一定时间后关闭启动 Activity，并跳转到主界面
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, 3000); // 延迟 3 秒
    }
}

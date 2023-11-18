// 定义包名
package com.corddt.musicplayer_pro;

// 导入所需的 Android 类和包

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

// 主活动类，继承自 AppCompatActivity
public class MainActivity extends AppCompatActivity {

    // 重写 AppCompatActivity 的 onCreate 方法
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);//这行代码是去调用父类的onCreate方法，这样才能保证父类的onCreate方法能够正常运行
        // 设置当前活动使用的布局文件为 activity_main.xml
        setContentView(R.layout.activity_main);

        // 通过 findViewById 获取布局文件中定义的 Button 控件
        Button buttonOpenPlayer = findViewById(R.id.buttonOpenPlayer);


        // 为 buttonOpenPlayer 设置一个点击事件监听器
        buttonOpenPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 当按钮被点击时执行以下代码

                // 创建一个 Intent，用于从 MainActivity 跳转到 MusicActivity
                Intent intent = new Intent(MainActivity.this, MusicActivity.class);
                // 启动目标活动
                startActivity(intent);
            }
        });


        Button buttonExit = findViewById(R.id.buttonExit);//这里的按钮的作用是退出应用
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 结束当前活动，退出应用
                finish();
            }
        });
    }
}

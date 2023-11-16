// 定义包名
package com.corddt.musicplayer_pro;

// 导入所需的 Android 类和包
import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

// MusicActivity 类，继承自 Android 的 Activity 类
public class MusicActivity extends AppCompatActivity {

    // 声明 MediaPlayer 对象，用于播放音乐
    private MediaPlayer mediaPlayer;
    // 声明 ImageView 对象，用作播放按钮
    private ImageView playButton;

    private ImageView imageView;
    private ObjectAnimator rotationAnimator;

    private SeekBar seekBar;
    private Handler handler = new Handler();

    // 重写 Activity 类的 onCreate 方法
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 在日志中记录 onCreate 方法的开始
        Log.d("MusicActivity", "onCreate started");
        // 调用超类的 onCreate 方法，进行基本的初始化操作
        super.onCreate(savedInstanceState);
        // 设置当前活动使用的布局文件为 activity_music.xml
        setContentView(R.layout.activity_music);

        // 为 mediaPlayer 创建一个实例，用于播放 R.raw.music1 中的音频文件
        mediaPlayer = MediaPlayer.create(this, R.raw.music2);
        // 检查 mediaPlayer 是否成功创建
        if (mediaPlayer == null) {
            // 如果 mediaPlayer 创建失败，则在日志中记录错误信息
            Log.e("MusicActivity", "Error creating MediaPlayer");
        } else {
            // 如果 mediaPlayer 成功创建，则在日志中记录成功信息
            Log.d("MusicActivity", "MediaPlayer created");
        }

        // 通过 findViewById 获取布局文件中定义的 ImageView 控件（播放按钮）
        playButton = findViewById(R.id.playButton);
        // 检查 playButton 是否成功找到
        if (playButton == null) {
            // 如果 playButton 未找到，则在日志中记录错误信息
            Log.e("MusicActivity", "Play button not found");
        } else {
            // 如果 playButton 成功找到，则在日志中记录成功信息
            Log.d("MusicActivity", "Play button found");
        }

        // 在日志中记录设置播放按钮监听器的操作
        Log.d("MusicActivity", "Setting up play button listener");

        imageView = findViewById(R.id.imageView);
        // 创建旋转动画
        rotationAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
        rotationAnimator.setDuration(10000); // 设置旋转周期时长（例如 10 秒）
        rotationAnimator.setRepeatCount(ObjectAnimator.INFINITE); // 设置无限重复
        rotationAnimator.setInterpolator(new LinearInterpolator()); // 设置匀速旋转

        seekBar = findViewById(R.id.seekBar);
        mediaPlayer = MediaPlayer.create(this, R.raw.music2);

        // 检查 mediaPlayer 是否成功创建
        if (mediaPlayer != null) {
            // 设置 SeekBar 的最大值为音频文件时长（毫秒转换为秒）
            seekBar.setMax(mediaPlayer.getDuration() / 1000);

            // 更新进度条的逻辑
            MusicActivity.this.runOnUiThread(new Runnable() {//这个的功能是使进度条与播放同步
                @Override
                public void run() {
                    if (mediaPlayer != null) {
                        int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000; // 获取当前播放位置（转换为秒）
                        seekBar.setProgress(mCurrentPosition); // 更新 SeekBar 的进度
                    }
                    handler.postDelayed(this, 1000); // 每秒更新一次
                }
            });
        } else {
            Log.e("MusicActivity", "Error creating MediaPlayer");
        }

        // 设置 SeekBar 的监听器
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // 如果进度改变是由用户触发的
                if (fromUser) {
                    mediaPlayer.seekTo(progress * 1000); // 跳转到指定的播放位置，需要将秒转换为毫秒
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // 用户开始拖动 SeekBar 时的逻辑（如果需要）
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // 用户停止拖动 SeekBar 时的逻辑（如果需要）
            }
        });

        // 为播放按钮设置点击事件监听器
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 在日志中记录播放按钮被点击的信息
                Log.d("MusicActivity", "Play button clicked");
                // 检查音乐是否正在播放
                if (mediaPlayer.isPlaying()) {
                    // 如果音乐正在播放，则暂停，并切换到播放图标
                    mediaPlayer.pause();
                    playButton.setImageResource(R.drawable.play_icon);
                    rotationAnimator.pause(); // 暂停旋转动画
                } else {
                    // 如果音乐没有在播放，则开始播放，并切换到暂停图标
                    mediaPlayer.start();
                    playButton.setImageResource(R.drawable.middle_icon);
                    rotationAnimator.start(); // 开始旋转动画
                }
            }
        });

        ImageView imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // 结束当前活动
            }
        });


        // 在日志中记录 onCreate 方法的完成
        Log.d("MusicActivity", "onCreate finished");


    }

    // 重写 Activity 类的 onDestroy 方法
    @Override
    protected void onDestroy() {
        // 在日志中记录 onDestroy 方法的开始
        Log.d("MusicActivity", "onDestroy started");
        // 调用超类的 onDestroy 方法，进行基本的清理操作
        super.onDestroy();
        // 检查 mediaPlayer 对象是否存在
        if (mediaPlayer != null) {
            // 释放 MediaPlayer 资源
            mediaPlayer.release();
            // 将 mediaPlayer 设置为 null，帮助垃圾回收
            mediaPlayer = null;
            // 在日志中记录 MediaPlayer 资源被释放的信息
            Log.d("MusicActivity", "MediaPlayer released");
        }
        // 在日志中记录 onDestroy 方法的完成
        Log.d("MusicActivity", "onDestroy finished");
    }


}

package com.corddt.musicplayer_pro;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class VideoActivity extends AppCompatActivity {

    private VideoView videoView;
    private SeekBar videoSeekBar;
    private Handler handler = new Handler();
    private Runnable updateProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView = findViewById(R.id.videoView);
        Button buttonReturn = findViewById(R.id.buttonReturn);
        videoSeekBar = findViewById(R.id.videoSeekBar);

        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.china);
        videoView.setVideoURI(videoUri);
        videoView.start(); // 开始播放视频

        // 返回按钮点击事件
        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // 结束当前活动
            }
        });

        // 视频点击暂停/播放
        videoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()) {
                    videoView.pause();
                } else {
                    videoView.start();
                }
            }
        });

        // 更新进度条
        updateProgress = new Runnable() {
            @Override
            public void run() {
                int progress = (int) (((float) videoView.getCurrentPosition() / videoView.getDuration()) * 100);
                videoSeekBar.setProgress(progress);
                handler.postDelayed(this, 1000);
            }
        };
        handler.post(updateProgress);

        // 设置进度条的拖动事件
        videoSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    int duration = videoView.getDuration();
                    int position = duration * progress / 100;
                    videoView.seekTo(position);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // 可选：在开始拖动时暂停视频
                videoView.pause();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // 可选：停止拖动后继续播放视频
                videoView.start();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(updateProgress); // 避免内存泄漏
    }
}

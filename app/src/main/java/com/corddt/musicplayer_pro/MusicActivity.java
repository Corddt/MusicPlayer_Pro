package com.corddt.musicplayer_pro;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

public class MusicActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private ImageView playButton, imageView;
    private ObjectAnimator rotationAnimator;
    private SeekBar seekBar;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        mediaPlayer = MediaPlayer.create(this, R.raw.music2);
        playButton = findViewById(R.id.playButton);
        imageView = findViewById(R.id.imageView);
        seekBar = findViewById(R.id.seekBar);

        setupRotationAnimator();
        setupSeekBar();

        imageView.setOnClickListener(v -> {
            Intent intent = new Intent(MusicActivity.this, LyricsActivity.class);
            startActivity(intent);
        });

        playButton.setOnClickListener(v -> togglePlayPause());

        ImageView imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(v -> finish());
    }

    private void setupRotationAnimator() {
        rotationAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0f, 360f);
        rotationAnimator.setDuration(10000);
        rotationAnimator.setRepeatCount(ObjectAnimator.INFINITE);
        rotationAnimator.setInterpolator(new LinearInterpolator());
    }

    private void setupSeekBar() {
        if (mediaPlayer != null) {
            seekBar.setMax(mediaPlayer.getDuration() / 1000);

            runOnUiThread(() -> {
                if (mediaPlayer != null) {
                    int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
                    seekBar.setProgress(mCurrentPosition);
                }
                handler.postDelayed(this::setupSeekBar, 1000);
            });
        } else {
            Log.e("MusicActivity", "Error creating MediaPlayer");
        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress * 1000);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    private void togglePlayPause() {
        Log.d("MusicActivity", "Play button clicked");
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            playButton.setSelected(false); // 图标切换为播放
            rotationAnimator.pause(); // 暂停动画
        } else {
            if (mediaPlayer.getCurrentPosition() == 0) {
                mediaPlayer.start(); // 如果是从头开始，则启动媒体播放器
                rotationAnimator.start(); // 启动动画
            } else {
                mediaPlayer.start(); // 否则，继续媒体播放器
                rotationAnimator.resume(); // 恢复动画
            }
            playButton.setSelected(true); // 图标切换为暂停
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}

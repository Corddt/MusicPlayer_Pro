package com.corddt.musicplayer_pro;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
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

        // 先初始化视图
        playButton = findViewById(R.id.playButton);
        imageView = findViewById(R.id.imageView);
        seekBar = findViewById(R.id.seekBar);

        // 获取从 Intent 传递的歌曲名称
        Intent intent = getIntent();
        String songName = intent.getStringExtra("SONG_NAME");

        // 然后配置媒体播放器
        setupMediaPlayer(songName);

        // 其余的设置
        setupRotationAnimator();
        setupSeekBar();

        imageView.setOnClickListener(v -> {
            Intent lyricsIntent = new Intent(MusicActivity.this, LyricsActivity.class);
            lyricsIntent.putExtra("SONG_NAME", songName); // Pass song name to LyricsActivity
            startActivity(lyricsIntent);
        });

        playButton.setOnClickListener(v -> togglePlayPause());
        ImageView imgBack = findViewById(R.id.imgBack);
        imgBack.setOnClickListener(v -> finish());
    }


    private void setupMediaPlayer(String songName) {
        Resources resources = getResources();
        String[] songTitles = resources.getStringArray(R.array.song_titles);
        TypedArray songFiles = resources.obtainTypedArray(R.array.song_files);
        TypedArray songCovers = resources.obtainTypedArray(R.array.song_covers);

        int songResource = -1;
        int coverResource = -1;

        for (int i = 0; i < songTitles.length; i++) {
            if (songName.equals(songTitles[i])) {
                songResource = songFiles.getResourceId(i, -1);
                coverResource = songCovers.getResourceId(i, -1);
                break;
            }
        }

        songFiles.recycle();
        songCovers.recycle();

        if (songResource != -1 && coverResource != -1) {
            imageView.setImageResource(coverResource);
            mediaPlayer = MediaPlayer.create(this, songResource);
        } else {
            // Handle the case where the song or cover resource is not found
            Log.e("MusicActivity", "Resource not found for song: " + songName);
        }
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
                // 可以在这里添加代码
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // 可以在这里添加代码
            }
        });
    }

    private void togglePlayPause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            playButton.setSelected(false);
            rotationAnimator.pause();
        } else {
            mediaPlayer.start();
            playButton.setSelected(true);
            if (mediaPlayer.getCurrentPosition() == 0) {
                rotationAnimator.start();
            } else {
                rotationAnimator.resume();
            }
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

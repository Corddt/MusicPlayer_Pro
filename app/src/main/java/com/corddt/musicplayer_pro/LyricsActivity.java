package com.corddt.musicplayer_pro;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

// LyricsActivity.java
public class LyricsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics);


        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  // 关闭当前 Activity，返回到之前的 Activity
            }
        });

        TextView lyricsView = findViewById(R.id.lyricsView);
        String lyrics = getResources().getString(R.string.song_lyrics);
        lyricsView.setText(lyrics.replace("\\n", "\n")); // 替换 \n 以实现换行
    }
}

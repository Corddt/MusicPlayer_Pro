package com.corddt.musicplayer_pro;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LyricsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics);

        setupBackButton();
        setupLyricsText();
    }

    private void setupBackButton() {
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());
    }

    private void setupLyricsText() {
        TextView lyricsView = findViewById(R.id.lyricsView);
        String lyrics = getResources().getString(R.string.song_lyrics_spanish);
        lyricsView.setText(lyrics.replace("\\n", "\n"));
    }
}

package com.corddt.musicplayer_pro;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LyricsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics);

        setupBackButton();
        setupLanguageSpinner();
    }

    private void setupBackButton() {
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());
    }

    private void setupLanguageSpinner() {
        Spinner languageSpinner = findViewById(R.id.languageSpinner);
        String[] languages = {"中文", "English", "origin"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView lyricsView = findViewById(R.id.lyricsView);
                switch (position) {
                    case 0: // 中文
                        lyricsView.setText(R.string.song_lyrics);
                        break;
                    case 1: // English
                        lyricsView.setText(R.string.song_lyrics_english);
                        break;
                    case 2: // Español
                        lyricsView.setText(R.string.song_lyrics_spanish);
                        break;
                }
                formatLyrics(lyricsView);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 可以根据需要处理没有选择任何项的情况
            }
        });
    }

    private void formatLyrics(TextView lyricsView) {
        String lyrics = lyricsView.getText().toString();
        lyricsView.setText(lyrics.replace("\\n", "\n"));
    }
}

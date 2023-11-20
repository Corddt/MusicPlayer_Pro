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

    private String songName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lyrics);

        songName = getIntent().getStringExtra("SONG_NAME");
        setupBackButton();
        setupLanguageSpinner();
    }

    private void setupBackButton() {
        ImageView backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(v -> finish());
    }

    private void setupLanguageSpinner() {
        Spinner languageSpinner = findViewById(R.id.languageSpinner);
        String[] languages = {"origin", "中文", "English"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        languageSpinner.setAdapter(adapter);

        languageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateLyricsView(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 可以根据需要处理没有选择任何项的情况
            }
        });
    }

    private void updateLyricsView(int languagePosition) {
        TextView lyricsView = findViewById(R.id.lyricsView);
        String lyricsType = "";
        switch (languagePosition) {
            case 0: // 原文
                lyricsType = "song_lyrics_origin";
                break;
            case 1: // 中文
                lyricsType = "song_lyrics_chinese";
                break;
            case 2: // English
                lyricsType = "song_lyrics_english";
                break;
        }

        int lyricsResourceId = getResources().getIdentifier(
                lyricsType + "_" + songName,
                "string",
                getPackageName()
        );

        if (lyricsResourceId != 0) {
            String lyrics = getString(lyricsResourceId);
            lyricsView.setText(lyrics.replace("\\n", "\n"));
        }
    }
}

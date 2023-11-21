package com.corddt.musicplayer_pro;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Spinner songSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 设置日间/夜间主题
        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                setTheme(R.style.Theme_MusicPlayer_Pro_Day);
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                setTheme(R.style.Theme_MusicPlayer_Pro_Night);
                break;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupSongSpinner();
        setupExitButton();
    }


    private void setupSongSpinner() {
        songSpinner = findViewById(R.id.songSpinner);

        // 获取歌曲标题并在前面添加一个指示性的选项
        String[] songTitles = getResources().getStringArray(R.array.song_titles);
        String[] spinnerTitles = new String[songTitles.length + 1];
        spinnerTitles[0] = "[select the song]"; // 初始指示性选项
        System.arraycopy(songTitles, 0, spinnerTitles, 1, songTitles.length);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerTitles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        songSpinner.setAdapter(adapter);

        songSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) { // 排除初始指示性选项
                    String selectedSong = (String) parent.getItemAtPosition(position);
                    openMusicActivity(selectedSong);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // 可以根据需要处理没有选择任何项的情况
            }
        });
    }




    private void setupExitButton() {
        findViewById(R.id.buttonExit).setOnClickListener(v -> finish());
    }

    private void openMusicActivity(String songName) {
        Intent intent = new Intent(this, MusicActivity.class);
        intent.putExtra("SONG_NAME", songName);
        startActivity(intent);
    }
}

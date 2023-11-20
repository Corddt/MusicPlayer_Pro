package com.corddt.musicplayer_pro;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 设置日间/夜间主题
        int currentNightMode = getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        switch (currentNightMode) {
            case Configuration.UI_MODE_NIGHT_NO:
                // 夜间模式未启用，使用日间主题
                setTheme(R.style.Theme_MusicPlayer_Pro_Day);
                break;
            case Configuration.UI_MODE_NIGHT_YES:
                // 夜间模式启用，使用夜间主题
                setTheme(R.style.Theme_MusicPlayer_Pro_Night);
                break;
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupOpenPlayerButton();
        setupExitButton();
    }

    private void setupOpenPlayerButton() {
        Button buttonOpenPlayer = findViewById(R.id.buttonOpenPlayer);
        buttonOpenPlayer.setOnClickListener(v -> openMusicActivity());
    }

    private void setupExitButton() {
        Button buttonExit = findViewById(R.id.buttonExit);
        buttonExit.setOnClickListener(v -> finish());
    }

    private void openMusicActivity() {
        Intent intent = new Intent(this, MusicActivity.class);
        startActivity(intent);
    }
}

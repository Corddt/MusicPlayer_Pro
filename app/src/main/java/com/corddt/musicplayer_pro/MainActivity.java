package com.corddt.musicplayer_pro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
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

package com.github.polurival.maze.feature;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class MainActivity extends AppCompatActivity {

    private static final String GAME_MANAGER_KEY = "gameManager";

    private MazeView mazeView;
    private GestureDetector gestureDetector;

    private GameManager gameManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initGameManager(savedInstanceState);
        mazeView = new MazeView(this, gameManager);
        setContentView(mazeView);

        gestureDetector = new GestureDetector(this, gameManager);

        playMusic();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putParcelable(GAME_MANAGER_KEY, gameManager);
        super.onSaveInstanceState(outState);
    }

    private void initGameManager(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey(GAME_MANAGER_KEY)) {
            gameManager = savedInstanceState.getParcelable(GAME_MANAGER_KEY);
        } else {
            gameManager = new GameManager();
        }
    }

    private void playMusic() {
        Intent intent = new Intent(this, MediaService.class);
        startService(intent);
    }
}

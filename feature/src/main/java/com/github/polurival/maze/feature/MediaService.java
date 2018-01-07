package com.github.polurival.maze.feature;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MediaService extends Service {

    MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        mediaPlayer = MediaPlayer.create(this, R.raw.nobuo_uematsu_memoria_final_fantasy_9_ost);
        mediaPlayer.start();
    }

    @Override
    public void onDestroy() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}

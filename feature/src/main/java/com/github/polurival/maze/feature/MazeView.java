package com.github.polurival.maze.feature;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

public class MazeView extends View {

    private GameManager gameManager;

    public MazeView(Context context, GameManager gameManager) {
        super(context);
        this.gameManager = gameManager;
        gameManager.setView(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.GRAY);
        gameManager.draw(canvas);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        gameManager.setScreenSize(w, h);
    }
}

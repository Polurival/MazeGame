package com.github.polurival.maze.feature;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class GameManager extends GestureDetector.SimpleOnGestureListener implements Parcelable {

    private static final int INITIAL_MAZE_SIZE = 5;
    private static final int MAZE_SIZE_STEP = 2;

    private List<Drawable> drawables = new ArrayList<>();
    private View view;
    private Maze maze;
    private Player player;
    private Exit exit;
    private Rect rect = new Rect();

    public GameManager() {
        create(INITIAL_MAZE_SIZE);
    }

    protected GameManager(Parcel in) {
        maze = in.readParcelable(Maze.class.getClassLoader());
        player = in.readParcelable(Player.class.getClassLoader());
        exit = in.readParcelable(Exit.class.getClassLoader());
        rect = in.readParcelable(Rect.class.getClassLoader());
    }

    public static final Creator<GameManager> CREATOR = new Creator<GameManager>() {
        @Override
        public GameManager createFromParcel(Parcel in) {
            return new GameManager(in);
        }

        @Override
        public GameManager[] newArray(int size) {
            return new GameManager[size];
        }
    };

    private void create(int mazeSize) {
        maze = new Maze(mazeSize);
        player = new Player(maze.getStart(), mazeSize);
        exit = new Exit(maze.getEnd(), mazeSize);

        drawables.clear();
        drawables.add(maze);
        drawables.add(exit);
        drawables.add(player);
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        int diffX = Math.round(e2.getX() - e1.getX());
        int diffY = Math.round(e2.getY() - e1.getY());

        if (Math.abs(diffX) > Math.abs(diffY)) {
            diffX = diffX > 0 ? 1 : -1;
            diffY = 0;
        } else {
            diffX = 0;
            diffY = diffY > 0 ? 1 : -1;
        }

        boolean stop = false;
        while (true) {
            int newX = player.getX() + diffX;
            int newY = player.getY() + diffY;
            stop |= !maze.canPlayerGoTo(newX, newY);
            if (stop) {
                break;
            }
            player.move(diffX, diffY);
            stop |= maze.isCrossroad(newX, newY);
        }

        if (player.getPoint().equals(exit.getPoint())) {
            create(maze.getSize() + MAZE_SIZE_STEP);
        }

        view.invalidate();
        return super.onFling(e1, e2, velocityX, velocityY);
    }

    public void draw(Canvas canvas) {
        for (Drawable drawable : drawables) {
            drawable.draw(canvas, rect);
        }
    }

    public void setView(View view) {
        this.view = view;
    }

    public void setScreenSize(int width, int height) {
        int screenSize = Math.min(width, height);
        rect.set((width - screenSize) / 2,
                (height - screenSize) / 2,
                (width + screenSize) / 2,
                (height + screenSize) / 2);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(maze, flags);
        dest.writeParcelable(player, flags);
        dest.writeParcelable(exit, flags);
        dest.writeParcelable(rect, flags);
    }
}

package com.github.polurival.maze.feature;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Parcel;

public class Player extends Dot {

    public Player(Point start, int mazeSize) {
        super(start, mazeSize);
        getPaint().setColor(Color.RED);
    }

    public Player(Parcel in) {
        super(in);
    }

    public void move(int diffX, int diffY) {
        getPoint().offset(diffX, diffY);
    }

    // region Parcelable

    public static final Creator<Player> CREATOR = new Creator<Player>() {
        @Override
        public Player createFromParcel(Parcel in) {
            return new Player(in);
        }

        @Override
        public Player[] newArray(int size) {
            return new Player[size];
        }
    };

    // endregion
}

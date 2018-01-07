package com.github.polurival.maze.feature;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Parcel;

public class Exit extends Dot {

    public Exit(Point point, int mazeSize) {
        super(point, mazeSize);
        getPaint().setColor(Color.GREEN);
    }

    // region Parcelable

    public Exit(Parcel in) {
        super(in);
    }

    public static final Creator<Exit> CREATOR = new Creator<Exit>() {
        @Override
        public Exit createFromParcel(Parcel in) {
            return new Exit(in);
        }

        @Override
        public Exit[] newArray(int size) {
            return new Exit[size];
        }
    };

    // endregion
}

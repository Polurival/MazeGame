package com.github.polurival.maze.feature;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;

public class Dot implements Drawable, Parcelable {

    private Point point;
    private Paint paint;
    private int mazeSize;

    public Dot(Point point, int mazeSize) {
        this.point = point;
        this.mazeSize = mazeSize;
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    protected Dot(Parcel in) {
        point = in.readParcelable(Point.class.getClassLoader());
        mazeSize = in.readInt();

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    public void draw(Canvas canvas, Rect rect) {
        float cellSize = ((float) (rect.right - rect.left)) / mazeSize;
        canvas.drawRect(rect.left + cellSize * point.x,
                rect.top + cellSize * point.y,
                rect.left + cellSize * (point.x + 1),
                rect.top + cellSize * (point.y + 1),
                paint);
    }

    public int getX() {
        return point.x;
    }

    public int getY() {
        return point.y;
    }

    public Point getPoint() {
        return point;
    }

    public Paint getPaint() {
        return paint;
    }

    // region Parcelable

    public static final Creator<Dot> CREATOR = new Creator<Dot>() {
        @Override
        public Dot createFromParcel(Parcel in) {
            return new Dot(in);
        }

        @Override
        public Dot[] newArray(int size) {
            return new Dot[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(point, flags);
        dest.writeInt(mazeSize);
    }

    // endregion
}

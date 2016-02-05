package com.github.chaossss.shapableloadingview.factory.path;

import android.graphics.Path;
import android.graphics.Point;

/**
 * Created by chaossss on 2016/2/5.
 */
public abstract class AbstractPath {
    protected Point center;
    protected int pathWidth;
    protected int pathHeight;
    protected int maxBallSize;

    public AbstractPath(Point center, int pathWidth, int pathHeight, int maxBallSize) {
        this.center = center;
        this.pathWidth = pathWidth;
        this.pathHeight = pathHeight;
        this.maxBallSize = maxBallSize;
    }

    public abstract Path draw();

    protected void initializePoints(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point();
        }
    }
}

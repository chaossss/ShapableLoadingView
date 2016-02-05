package com.github.chaossss.shapableloadingview.factory.path;

import android.graphics.Path;
import android.graphics.Point;

/**
 * Created by chaossss on 2016/2/5.
 */
public class Triangle extends AbstractPath {
    public Triangle(Point center, int pathWidth, int pathHeight, int maxBallSize) {
        super(center, pathWidth, pathHeight, maxBallSize);
    }

    @Override
    public Path draw() {
        Path path = new Path();
        path.moveTo(maxBallSize, pathHeight - maxBallSize);
        path.lineTo(center.x, maxBallSize);
        path.lineTo(pathWidth - maxBallSize, pathHeight - maxBallSize);
        path.lineTo(maxBallSize, pathHeight - maxBallSize);
        return path;
    }
}

package com.github.chaossss.shapableloadingview.factory.path;

import android.graphics.Path;
import android.graphics.Point;

/**
 * Created by chaossss on 2016/2/5.
 */
public class Circle extends AbstractPath {
    public Circle(Point center, int pathWidth, int pathHeight, int maxBallSize) {
        super(center, pathWidth, pathHeight, maxBallSize);
    }

    @Override
    public Path draw() {
        Path path = new Path();
        path.addCircle(center.x, center.y, pathWidth / 2 - maxBallSize, Path.Direction.CCW);
        return path;
    }
}

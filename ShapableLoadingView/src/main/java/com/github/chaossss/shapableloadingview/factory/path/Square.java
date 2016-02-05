package com.github.chaossss.shapableloadingview.factory.path;

import android.graphics.Path;
import android.graphics.Point;

/**
 * Created by chaossss on 2016/2/5.
 */
public class Square extends AbstractPath {
    public Square(Point center, int pathWidth, int pathHeight, int maxBallSize) {
        super(center, pathWidth, pathHeight, maxBallSize);
    }

    @Override
    public Path draw() {
        Point startPoint = new Point(center.x - pathWidth / 2, center.y - pathHeight / 2);
        Point endPoint = new Point(center.x + pathWidth / 2, center.y + pathHeight / 2);
        Path path = new Path();
        path.addRect(startPoint.x + maxBallSize, startPoint.y + maxBallSize, endPoint.x - maxBallSize,
                endPoint.y - maxBallSize, Path.Direction.CCW);
        return path;
    }
}

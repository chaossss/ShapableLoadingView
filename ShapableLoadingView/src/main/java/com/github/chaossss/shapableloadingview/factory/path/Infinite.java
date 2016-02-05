package com.github.chaossss.shapableloadingview.factory.path;

import android.graphics.Path;
import android.graphics.Point;

/**
 * Created by chaossss on 2016/2/5.
 */
public class Infinite extends AbstractPath {
    public Infinite(Point center, int pathWidth, int pathHeight, int maxBallSize) {
        super(center, pathWidth, pathHeight, maxBallSize);
    }

    @Override
    public Path draw() {
        pathWidth = pathWidth / 2 - maxBallSize;
        Point controlPoint1 = new Point();
        controlPoint1.set(center.x - pathWidth / 2, center.y - pathHeight);
        Point controlPoint2 = new Point();
        controlPoint2.set(center.x + pathWidth / 2, center.y + pathHeight);

        Point controlPoint3 = new Point();
        controlPoint3.set(center.x - pathWidth / 2, center.y + pathHeight);
        Point controlPoint4 = new Point();
        controlPoint4.set(center.x + pathWidth / 2, center.y - pathHeight);
        Path path = new Path();
        path.moveTo(center.x - pathWidth, center.y);
        path.cubicTo(controlPoint1.x, controlPoint1.y, controlPoint2.x, controlPoint2.y,
                center.x + pathWidth, center.y);

        path.cubicTo(controlPoint4.x, controlPoint4.y, controlPoint3.x, controlPoint3.y,
                center.x - pathWidth, center.y);
        return path;
    }
}

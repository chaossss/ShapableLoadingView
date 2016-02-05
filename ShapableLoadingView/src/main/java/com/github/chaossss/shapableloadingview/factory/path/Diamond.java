package com.github.chaossss.shapableloadingview.factory.path;

import android.graphics.Path;
import android.graphics.Point;

/**
 * Created by chaossss on 2016/2/5.
 */
public class Diamond extends AbstractPath {
    public Diamond(Point center, int pathWidth, int pathHeight, int maxBallSize) {
        super(center, pathWidth, pathHeight, maxBallSize);
    }

    @Override
    public Path draw() {
        Point[] diamondPoints = new Point[4];
        initializePoints(diamondPoints);

        int diamondWith = pathWidth - maxBallSize * 2;
        int middleDiamondWith = diamondWith / 2;

        diamondPoints[0].set(center.x - middleDiamondWith, center.y);
        diamondPoints[1].set(center.x, maxBallSize);
        diamondPoints[2].set(center.x + middleDiamondWith, center.y);
        diamondPoints[3].set(center.x, pathHeight - maxBallSize);

        Path path = new Path();
        path.moveTo(diamondPoints[0].x, diamondPoints[0].y);
        path.lineTo(diamondPoints[1].x, diamondPoints[1].y);
        path.lineTo(diamondPoints[2].x, diamondPoints[2].y);
        path.lineTo(diamondPoints[3].x, diamondPoints[3].y);
        path.close();

        return path;
    }
}

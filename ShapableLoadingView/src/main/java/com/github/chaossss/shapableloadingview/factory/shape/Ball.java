package com.github.chaossss.shapableloadingview.factory.shape;

import android.graphics.Path;

/**
 * Created by chaossss on 2016/2/5.
 */
public class Ball extends Shape {
    public Ball(int size, int color) {
        super(size, color);
    }

    @Override
    public Path getShapePath() {
        Path ballPath = new Path();
        ballPath.addCircle(position.x, position.y, size, Path.Direction.CCW);
        return ballPath;
    }
}

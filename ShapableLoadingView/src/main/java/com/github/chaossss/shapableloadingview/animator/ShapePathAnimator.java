package com.github.chaossss.shapableloadingview.animator;

import android.animation.ValueAnimator;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.animation.BaseInterpolator;
import android.view.animation.LinearInterpolator;

import com.github.chaossss.shapableloadingview.factory.shape.Shape;

public class ShapePathAnimator extends AbstractShapeAnimator implements ValueAnimator.AnimatorUpdateListener {
    private Path path;
    private float offset;
    private int duration;
    private ValueAnimator valueAnimator;
    private BaseInterpolator interpolator;

    public ShapePathAnimator(BaseInterpolator interpolator, Path path, int duration) {
        this.interpolator = interpolator;
        this.path = path;
        this.duration = duration;
        init();
    }

    public ShapePathAnimator(Path path, int duration) {
        this.path = path;
        this.duration = duration;
        interpolator = new LinearInterpolator();
        init();
    }

    private void init() {
        initValueAnimator();
    }

    private void initValueAnimator() {
        valueAnimator = new ValueAnimator();
        valueAnimator.setRepeatMode(ValueAnimator.INFINITE);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setDuration(duration);
        valueAnimator.setFloatValues(0, 1f);
        valueAnimator.setInterpolator(interpolator);
        valueAnimator.addUpdateListener(this);
    }

    @Override
    public void start() {
        float size = shapes.size();
        offset = 1 / size;
        valueAnimator.start();
    }

    @Override
    public void stop(){
        valueAnimator.cancel();
    }

    @Override
    public void restart(){
        stop();
        start();
    }

    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        float value = animation.getAnimatedFraction();

        for (int i = 0; i < shapes.size(); i++) {
            updateShape(shapes.get(i), value, i, path);
        }

        if (shapeAnimatorListener != null) {
            shapeAnimatorListener.onUpdate();
        }
    }

    private void updateShape(Shape shape, float fraction, int position, Path path) {
        float shapeFraction = fraction + (position * offset);
        if (shapeFraction > 1) {
            shapeFraction = shapeFraction - 1;
        }
        float[] coordinates = getPathCoordinates(path, shapeFraction);
        shape.setPosition((int) coordinates[0], (int) coordinates[1]);
    }

    private float[] getPathCoordinates(Path path, float fraction) {
        float aCoordinates[] = { 0f, 0f };
        PathMeasure pm = new PathMeasure(path, false);
        pm.getPosTan(pm.getLength() * fraction, aCoordinates, null);
        return aCoordinates;
    }
}

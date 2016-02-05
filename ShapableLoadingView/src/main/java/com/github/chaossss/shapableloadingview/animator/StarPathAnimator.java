package com.github.chaossss.shapableloadingview.animator;

import android.animation.ValueAnimator;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.view.animation.BaseInterpolator;
import android.view.animation.LinearInterpolator;

import com.github.chaossss.shapableloadingview.Star;

public class StarPathAnimator extends AbstractStarAnimator implements ValueAnimator.AnimatorUpdateListener {
    private Path path;
    private float offset;
    private int duration;
    private ValueAnimator valueAnimator;
    private BaseInterpolator interpolator;

    public StarPathAnimator(BaseInterpolator interpolator, Path path, int duration) {
        this.interpolator = interpolator;
        this.path = path;
        this.duration = duration;
        init();
    }

    public StarPathAnimator(Path path, int duration) {
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
        float size = stars.size();
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

        for (int i = 0; i < stars.size(); i++) {
            updateStar(stars.get(i), value, i, path);
        }

        if (starAnimatorListener != null) {
            starAnimatorListener.onUpdate();
        }
    }

    private void updateStar(Star star, float fraction, int position, Path path) {
        float starFraction = fraction + (position * offset);
        if (starFraction > 1) {
            starFraction = starFraction - 1;
        }
        float[] coordinates = getPathCoordinates(path, starFraction);
        star.setPosition((int) coordinates[0], (int) coordinates[1]);
    }

    private float[] getPathCoordinates(Path path, float fraction) {
        float aCoordinates[] = { 0f, 0f };
        PathMeasure pm = new PathMeasure(path, false);
        pm.getPosTan(pm.getLength() * fraction, aCoordinates, null);
        return aCoordinates;
    }
}

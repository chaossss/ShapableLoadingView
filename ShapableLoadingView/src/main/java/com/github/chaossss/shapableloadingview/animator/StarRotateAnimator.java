package com.github.chaossss.shapableloadingview.animator;

import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.github.chaossss.shapableloadingview.Star;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaossss on 2016/2/5.
 */
public class StarRotateAnimator extends AbstractStarAnimator {
    private int duration;
    private List<ValueAnimator> animators;

    public StarRotateAnimator(int duration) {
        this.duration = duration;
        init();
    }

    private void init() {
        animators = new ArrayList<>();
    }

    @Override
    public void start() {
        animators.clear();
        for (int i = 0; i < stars.size(); i++) {
            createStarAnimatorAndStart(stars.get(i), 300 * i);
        }
    }

    @Override
    public void stop() {
        for (ValueAnimator animator : animators) {
            animator.cancel();
        }
    }

    @Override
    public void restart() {
        stop();
        start();
    }

    private void createStarAnimatorAndStart(Star star, int startDelay) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new StarUpdateListener(star));
        valueAnimator.setFloatValues(0f, 360f);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setStartDelay(startDelay);
        valueAnimator.setDuration(duration);
        animators.add(valueAnimator);
        valueAnimator.start();
    }

    private class StarUpdateListener implements ValueAnimator.AnimatorUpdateListener {
        private Star star;

        public StarUpdateListener(Star star) {
            this.star = star;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            float value = (float) animation.getAnimatedValue();
            star.setAngle((int) value);
        }
    }
}

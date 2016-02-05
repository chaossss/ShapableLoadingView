package com.github.chaossss.shapableloadingview.animator;

import com.github.chaossss.shapableloadingview.Star;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaossss on 2016/2/5.
 */
public abstract class AbstractStarAnimator {
    protected List<Star> stars;
    protected StarAnimatorListener starAnimatorListener;

    public AbstractStarAnimator() {
        stars = new ArrayList<>();
    }

    public void addStar(Star star) {
        stars.add(star);
    }

    public void addStars(List<Star> stars) {
        this.stars.addAll(stars);
    }

    public void setStarPathAnimatorListener(StarAnimatorListener starAnimatorListener) {
        this.starAnimatorListener = starAnimatorListener;
    }

    public abstract void start();

    public abstract void stop();

    public abstract void restart();

    public interface StarAnimatorListener {
        void onUpdate();
    }
}

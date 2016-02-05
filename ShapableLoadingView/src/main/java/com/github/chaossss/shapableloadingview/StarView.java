package com.github.chaossss.shapableloadingview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import com.github.chaossss.shapableloadingview.animator.AbstractStarAnimator;
import com.github.chaossss.shapableloadingview.animator.StarPathAnimator;
import com.github.chaossss.shapableloadingview.animator.StarRotateAnimator;
import com.github.chaossss.shapableloadingview.animator.StarSizeAnimator;
import com.github.chaossss.shapableloadingview.factory.PathFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaossss on 2016/2/5.
 */
public class StarView extends View implements AbstractStarAnimator.StarAnimatorListener {
    private List<Star> stars;

    private float minStarSize;
    private float maxStarSize;
    private float starSize = 20;

    private int[] starColors;
    private int numberOfStars = 3;
    private int starSizeAnimationDuration;
    private int starPathAnimationDuration;
    private int starRotateAnimationDuration;

    private boolean sizeAnimationEnabled;
    private boolean rotateAnimationEnabled;

    private String pathType;

    private StarPathAnimator starPathAnimator;
    private StarSizeAnimator starSizeAnimator;
    private StarRotateAnimator starRotateAnimator;

    public StarView(Context context) {
        this(context, null);
    }

    public StarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        defaultStarColors();
        initAttributes(attrs);
        createStars();
    }

    private void defaultStarColors() {
        starColors = new int[] { -9956, -46262, -12742913 };
    }

    private void initAttributes(AttributeSet attrs) {
        TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.ShapableLoadingView);
        numberOfStars = attributes.getInt(R.styleable.ShapableLoadingView_numbers, numberOfStars);
        starSize = attributes.getDimension(R.styleable.ShapableLoadingView_shape_size, starSize);
        minStarSize = attributes.getDimension(R.styleable.ShapableLoadingView_min_shape_size, starSize / 2);
        maxStarSize = attributes.getDimension(R.styleable.ShapableLoadingView_max_shape_size, starSize * 2);
        starSizeAnimationDuration =
                attributes.getInt(R.styleable.ShapableLoadingView_size_cycle_time, 500);
        starPathAnimationDuration =
                attributes.getInt(R.styleable.ShapableLoadingView_movement_cycle_time, 2000);
        starRotateAnimationDuration =
                attributes.getInt(R.styleable.ShapableLoadingView_rotate_cycle_time, 300);
        sizeAnimationEnabled =
                attributes.getBoolean(R.styleable.ShapableLoadingView_enable_size_animation, false);
        rotateAnimationEnabled =
                attributes.getBoolean(R.styleable.ShapableLoadingView_enable_rotate_animation, false);
        initColorsAttributes(attributes);
        pathType = attributes.getString(R.styleable.ShapableLoadingView_path);
        if (pathType == null) {
            pathType = PathFactory.INFINITE;
        }
    }

    private void initColorsAttributes(TypedArray attributes) {
        int arrayResourceId = attributes.getResourceId(R.styleable.ShapableLoadingView_shape_colors, 0);
        if (arrayResourceId != 0) {
            starColors = getResources().getIntArray(arrayResourceId);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Path path = PathFactory.makePath(pathType, new Point(w / 2, h / 2), w, h, (int) maxStarSize);
        initStarPathAnimator(path);

        if (sizeAnimationEnabled) {
            initStarSizeAnimator();
        }

        if(rotateAnimationEnabled){
            initStarRotateAnimator();
        }

        start();
    }

    private void initStarPathAnimator(Path path) {
        starPathAnimator = new StarPathAnimator(path, starPathAnimationDuration);
        starPathAnimator.setStarPathAnimatorListener(this);
        starPathAnimator.addStars(stars);
    }

    private void initStarSizeAnimator() {
        starSizeAnimator =
                new StarSizeAnimator(starSizeAnimationDuration, minStarSize, maxStarSize);
        starSizeAnimator.addStars(stars);
    }

    private void initStarRotateAnimator() {
        starRotateAnimator =
                new StarRotateAnimator(starRotateAnimationDuration);
        starRotateAnimator.addStars(stars);
    }

    private void createStars(){
        stars = new ArrayList<>();
        for (int i = 0, k = 0; i < numberOfStars; i++, k++) {
            if (!(starColors.length - 1 >= k)) {
                k = 0;
            }
            stars.add(new Star((int)starSize, starColors[k]));
        }
    }

    public void start() {
        starPathAnimator.start();

        if (sizeAnimationEnabled) {
            starSizeAnimator.start();
        }

        if(rotateAnimationEnabled){
            starRotateAnimator.start();
        }
    }

    public void stop() {
        starPathAnimator.stop();

        if (sizeAnimationEnabled) {
            starSizeAnimator.stop();
        }

        if(rotateAnimationEnabled){
            starRotateAnimator.stop();
        }
    }

    public void restart() {
        starPathAnimator.restart();

        if (sizeAnimationEnabled) {
            starSizeAnimator.restart();
        }

        if (rotateAnimationEnabled) {
            starRotateAnimator.restart();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(Star star : stars) star.render(canvas);
    }

    @Override
    public void onUpdate() {
        invalidate();
    }
}

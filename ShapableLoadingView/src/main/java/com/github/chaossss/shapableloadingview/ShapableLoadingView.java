package com.github.chaossss.shapableloadingview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import com.github.chaossss.shapableloadingview.animator.AbstractShapeAnimator;
import com.github.chaossss.shapableloadingview.animator.ShapePathAnimator;
import com.github.chaossss.shapableloadingview.animator.ShapeRotateAnimator;
import com.github.chaossss.shapableloadingview.animator.ShapeSizeAnimator;
import com.github.chaossss.shapableloadingview.factory.PathFactory;
import com.github.chaossss.shapableloadingview.factory.ShapeFactory;
import com.github.chaossss.shapableloadingview.factory.shape.Shape;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaossss on 2016/2/5.
 */
public class ShapableLoadingView extends View implements AbstractShapeAnimator.ShapeAnimatorListener {
    private List<Shape> shapes;

    private float minShapeSize;
    private float maxShapeSize;
    private float shapeSize = 20;

    private int[] shapeColors;
    private int numberOfShapes = 3;
    private int shapeSizeAnimationDuration;
    private int shapePathAnimationDuration;
    private int shapeRotateAnimationDuration;

    private boolean sizeAnimationEnabled;
    private boolean rotateAnimationEnabled;

    private String pathType;
    private String shapeType;

    private ShapePathAnimator shapePathAnimator;
    private ShapeSizeAnimator shapeSizeAnimator;
    private ShapeRotateAnimator shapeRotateAnimator;

    public ShapableLoadingView(Context context) {
        this(context, null);
    }

    public ShapableLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShapableLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        defaultShapeColors();
        initAttributes(attrs);
        createShapes();
    }

    private void defaultShapeColors() {
        shapeColors = new int[] { -9956, -46262, -12742913 };
    }

    private void initAttributes(AttributeSet attrs) {
        TypedArray attributes = getContext().obtainStyledAttributes(attrs, R.styleable.ShapableLoadingView);
        numberOfShapes = attributes.getInt(R.styleable.ShapableLoadingView_numbers, numberOfShapes);
        shapeSize = attributes.getDimension(R.styleable.ShapableLoadingView_shape_size, shapeSize);
        minShapeSize = attributes.getDimension(R.styleable.ShapableLoadingView_min_shape_size, shapeSize / 2);
        maxShapeSize = attributes.getDimension(R.styleable.ShapableLoadingView_max_shape_size, shapeSize * 2);
        shapeSizeAnimationDuration =
                attributes.getInt(R.styleable.ShapableLoadingView_size_cycle_time, 500);
        shapePathAnimationDuration =
                attributes.getInt(R.styleable.ShapableLoadingView_movement_cycle_time, 2000);
        shapeRotateAnimationDuration =
                attributes.getInt(R.styleable.ShapableLoadingView_rotate_cycle_time, 300);
        sizeAnimationEnabled =
                attributes.getBoolean(R.styleable.ShapableLoadingView_enable_size_animation, false);
        rotateAnimationEnabled =
                attributes.getBoolean(R.styleable.ShapableLoadingView_enable_rotate_animation, false);
        initColorsAttributes(attributes);

        pathType = attributes.getString(R.styleable.ShapableLoadingView_path_type);
        if (pathType == null) pathType = PathFactory.INFINITE;

        shapeType = attributes.getString(R.styleable.ShapableLoadingView_shape_type);
        if(shapeType == null) shapeType = ShapeFactory.PENTAGRAM;
    }

    private void initColorsAttributes(TypedArray attributes) {
        int arrayResourceId = attributes.getResourceId(R.styleable.ShapableLoadingView_shape_colors, 0);
        if (arrayResourceId != 0) {
            shapeColors = getResources().getIntArray(arrayResourceId);
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Path path = PathFactory.makePath(pathType, new Point(w / 2, h / 2), w, h, (int) maxShapeSize);
        initShapePathAnimator(path);

        if (sizeAnimationEnabled) {
            initShapeSizeAnimator();
        }

        if(rotateAnimationEnabled){
            initShapeRotateAnimator();
        }

        start();
    }

    private void initShapePathAnimator(Path path) {
        shapePathAnimator = new ShapePathAnimator(path, shapePathAnimationDuration);
        shapePathAnimator.setShapePathAnimatorListener(this);
        shapePathAnimator.addShapes(shapes);
    }

    private void initShapeSizeAnimator() {
        shapeSizeAnimator =
                new ShapeSizeAnimator(shapeSizeAnimationDuration, minShapeSize, maxShapeSize);
        shapeSizeAnimator.addShapes(shapes);
    }

    private void initShapeRotateAnimator() {
        shapeRotateAnimator =
                new ShapeRotateAnimator(shapeRotateAnimationDuration);
        shapeRotateAnimator.addShapes(shapes);
    }

    private void createShapes(){
        shapes = new ArrayList<>();
        for (int i = 0, k = 0; i < numberOfShapes; i++, k++) {
            if (!(shapeColors.length - 1 >= k)) {
                k = 0;
            }
            shapes.add(ShapeFactory.getShape(shapeType, (int)shapeSize, shapeColors[k]));
        }
    }

    public void start() {
        shapePathAnimator.start();

        if (sizeAnimationEnabled) {
            shapeSizeAnimator.start();
        }

        if(rotateAnimationEnabled){
            shapeRotateAnimator.start();
        }
    }

    public void stop() {
        shapePathAnimator.stop();

        if (sizeAnimationEnabled) {
            shapeSizeAnimator.stop();
        }

        if(rotateAnimationEnabled){
            shapeRotateAnimator.stop();
        }
    }

    public void restart() {
        shapePathAnimator.restart();

        if (sizeAnimationEnabled) {
            shapeSizeAnimator.restart();
        }

        if (rotateAnimationEnabled) {
            shapeRotateAnimator.restart();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for(Shape shape : shapes) shape.render(canvas);
    }

    @Override
    public void onUpdate() {
        invalidate();
    }
}

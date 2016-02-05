package com.github.chaossss.shapableloadingview.animator;

import com.github.chaossss.shapableloadingview.factory.shape.Shape;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chaossss on 2016/2/5.
 */
public abstract class AbstractShapeAnimator {
    protected List<Shape> shapes;
    protected ShapeAnimatorListener shapeAnimatorListener;

    public AbstractShapeAnimator() {
        shapes = new ArrayList<>();
    }

    public void addShape(Shape shape) {
        shapes.add(shape);
    }

    public void addShapes(List<Shape> shapes) {
        this.shapes.addAll(shapes);
    }

    public void setShapePathAnimatorListener(ShapeAnimatorListener shapeAnimatorListener) {
        this.shapeAnimatorListener = shapeAnimatorListener;
    }

    public abstract void start();

    public abstract void stop();

    public abstract void restart();

    public interface ShapeAnimatorListener {
        void onUpdate();
    }
}

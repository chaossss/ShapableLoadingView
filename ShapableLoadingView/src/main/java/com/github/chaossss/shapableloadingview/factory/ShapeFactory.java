package com.github.chaossss.shapableloadingview.factory;

import com.github.chaossss.shapableloadingview.factory.shape.Ball;
import com.github.chaossss.shapableloadingview.factory.shape.Shape;
import com.github.chaossss.shapableloadingview.factory.shape.Pentagram;

/**
 * Created by chaossss on 2016/2/5.
 */
public class ShapeFactory {
    public static final String BALL = "ball";
    public static final String PENTAGRAM = "pentagram";

    public static Shape getShape(String shapeType, int size, int color){
        switch (shapeType){
            case BALL:
                return new Ball(size, color);

            case PENTAGRAM:
                return new Pentagram(size, color);

            default:
                return new Pentagram(size, color);
        }
    }
}

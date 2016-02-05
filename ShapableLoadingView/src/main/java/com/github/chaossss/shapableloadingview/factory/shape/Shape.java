package com.github.chaossss.shapableloadingview.factory.shape;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

/**
 * Created by chaossss on 2016/2/5.
 */
public abstract class Shape {
    protected int size;
    protected int angle;
    protected int color;

    protected Paint paint;
    protected Point position;

    public Shape(int size, int color) {
        this.size = size;
        this.color = color;
        
        initPaint();
        initPoint();
    }

    protected void initPaint(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
    }
    
    protected void initPoint(){
        position = new Point();
    }

    protected void updatePoint(){
    }

    public void render(Canvas canvas){
        canvas.drawPath(getShapePath(), paint);
    }

    public abstract Path getShapePath();

    public void setSize(int size) {
        if(this.size == size) return;

        this.size = size;
        updatePoint();
    }

    public void setAngle(int angle){
        this.angle = angle;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setPosition(int x, int y) {
        if(position == null) return;

        position.set(x, y);
    }
    
    protected int getRotateX(Point point){
        return getRotatePos(true, point);
    }

    protected int getRotateY(Point point){
        return getRotatePos(false, point);
    }

    protected int getRotatePos(boolean isX, Point point){
        if(point == null) return -1;

        return isX ? (int)(point.x * Math.cos(Math.toRadians(angle)) + point.y * Math.sin(Math.toRadians(angle)))
                : (int)(point.y * Math.cos(Math.toRadians(angle)) - point.x * Math.sin(Math.toRadians(angle)));
    }
}

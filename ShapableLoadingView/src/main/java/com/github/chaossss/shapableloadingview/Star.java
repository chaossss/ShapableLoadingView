package com.github.chaossss.shapableloadingview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

/**
 * Created by chaossss on 2016/2/5.
 */
public class Star {
    private static final double TAN_18 = Math.tan(Math.toRadians(18));
    private static final double SIN_54 = Math.sin(Math.toRadians(54));
    private static final double COS_54 = Math.cos(Math.toRadians(54));
    private static final double SIN_72 = Math.sin(Math.toRadians(72));
    private static final double COS_72 = Math.cos(Math.toRadians(72));
    
    private int size;
    private int angle;
    private int color;
    private boolean isInitialized;

    private Paint paint;

    private Point a;
    private Point b;
    private Point c;
    private Point d;
    private Point e;
    private Point a_i;
    private Point b_i;
    private Point c_i;
    private Point d_i;
    private Point e_i;
    private Point position;

    public Star(int size, int color) {
        this.size = size;
        this.color = color;
        
        initPaint();
        updatePoint();
    }

    public void setSize(int size) {
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
        position.set(x, y);
    }

    private void initPaint(){
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
    }

    private void updatePoint(){
        if(!isInitialized){
            isInitialized = true;

            position = new Point();

            a = new Point(0, size);
            b = new Point((int)(size * SIN_72), (int)(size * COS_72));
            c = new Point((int)(size * COS_54), (int)(-size * SIN_54));
            d = new Point(-c.x, c.y);
            e = new Point(-b.x, b.y);

            a_i = new Point((int)(size * (1 - COS_72) * TAN_18), (int)(size * COS_72));
            b_i = new Point((int)(size * COS_72 * SIN_72 / SIN_54), (int)(-size * COS_72 * COS_72 / SIN_54));
            c_i = new Point(0, (int)(-size * COS_72 / SIN_54));
            d_i = new Point(-b_i.x, b_i.y);
            e_i = new Point(-a_i.x, a_i.y);
        } else {
            a.set(0, size);
            b.set((int)(size * SIN_72), (int)(size * COS_72));
            c.set((int)(size * COS_54), (int)(-size * SIN_54));
            d.set(-c.x, c.y);
            e.set(-b.x, b.y);

            a_i.set((int)(size * (1 - COS_72) * TAN_18), (int)(size * COS_72));
            b_i.set((int)(size * COS_72 * SIN_72 / SIN_54), (int)(-size * COS_72 * COS_72 / SIN_54));
            c_i.set(0, (int)(-size * COS_72 / SIN_54));
            d_i.set(-b_i.x, b_i.y);
            e_i.set(-a_i.x, a_i.y);
        }
    }
    
    public void render(Canvas canvas){
        canvas.drawPath(getStarPath(), paint);
    }
    
    private Path getStarPath(){
        Path starPath = new Path();

        starPath.moveTo(position.x + getRotateX(a), position.y + getRotateY(a));
        starPath.lineTo(position.x + getRotateX(a_i), position.y + getRotateY(a_i));
        starPath.lineTo(position.x + getRotateX(b), position.y + getRotateY(b));
        starPath.lineTo(position.x + getRotateX(b_i), position.y + getRotateY(b_i));
        starPath.lineTo(position.x + getRotateX(c), position.y + getRotateY(c));
        starPath.lineTo(position.x + getRotateX(c_i), position.y + getRotateY(c_i));
        starPath.lineTo(position.x + getRotateX(d), position.y + getRotateY(d));
        starPath.lineTo(position.x + getRotateX(d_i), position.y + getRotateY(d_i));
        starPath.lineTo(position.x + getRotateX(e), position.y + getRotateY(e));
        starPath.lineTo(position.x + getRotateX(e_i), position.y + getRotateY(e_i));
        starPath.lineTo(position.x + getRotateX(a), position.y + getRotateY(a));
        starPath.close();
        
        return starPath;
    }

    private int getRotateX(Point point){
        return getRotatePos(true, point);
    }

    private int getRotateY(Point point){
        return getRotatePos(false, point);
    }

    private int getRotatePos(boolean isX, Point point){
        return isX ? (int)(point.x * Math.cos(Math.toRadians(angle)) + point.y * Math.sin(Math.toRadians(angle)))
                : (int)(point.y * Math.cos(Math.toRadians(angle)) - point.x * Math.sin(Math.toRadians(angle)));
    }
}

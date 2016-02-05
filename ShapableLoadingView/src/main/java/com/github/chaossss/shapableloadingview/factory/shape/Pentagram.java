package com.github.chaossss.shapableloadingview.factory.shape;

import android.graphics.Path;
import android.graphics.Point;

/**
 * Created by chaossss on 2016/2/5.
 */
public class Pentagram extends Shape {
    private static final double TAN_18 = Math.tan(Math.toRadians(18));
    private static final double SIN_54 = Math.sin(Math.toRadians(54));
    private static final double COS_54 = Math.cos(Math.toRadians(54));
    private static final double SIN_72 = Math.sin(Math.toRadians(72));
    private static final double COS_72 = Math.cos(Math.toRadians(72));

    private boolean isInitialized;

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

    public Pentagram(int size, int color) {
        super(size, color);
    }

    @Override
    protected void initPoint() {
        super.initPoint();

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

        isInitialized = true;
    }

    @Override
    protected void updatePoint() {
        if(!isInitialized || size == 0) return;

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

    @Override
    public Path getShapePath() {
        Path pentagramPath = new Path();

        pentagramPath.moveTo(position.x + getRotateX(a), position.y + getRotateY(a));
        pentagramPath.lineTo(position.x + getRotateX(a_i), position.y + getRotateY(a_i));
        pentagramPath.lineTo(position.x + getRotateX(b), position.y + getRotateY(b));
        pentagramPath.lineTo(position.x + getRotateX(b_i), position.y + getRotateY(b_i));
        pentagramPath.lineTo(position.x + getRotateX(c), position.y + getRotateY(c));
        pentagramPath.lineTo(position.x + getRotateX(c_i), position.y + getRotateY(c_i));
        pentagramPath.lineTo(position.x + getRotateX(d), position.y + getRotateY(d));
        pentagramPath.lineTo(position.x + getRotateX(d_i), position.y + getRotateY(d_i));
        pentagramPath.lineTo(position.x + getRotateX(e), position.y + getRotateY(e));
        pentagramPath.lineTo(position.x + getRotateX(e_i), position.y + getRotateY(e_i));
        pentagramPath.lineTo(position.x + getRotateX(a), position.y + getRotateY(a));
        pentagramPath.close();

        return pentagramPath;
    }
}

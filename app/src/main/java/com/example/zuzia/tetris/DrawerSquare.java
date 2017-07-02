package com.example.zuzia.tetris;

import android.graphics.RectF;

/**
 * Created by zuzia on 28.06.17.
 */
public class DrawerSquare {

    public RectF blockDraw;
    public int color;

    DrawerSquare(RectF blockDraw, int color)
    {
        this.blockDraw=blockDraw;
        this.color=color;
    }

}

package me.tom.range.slider;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

class ThumbLayer {

    private float mRadius;
    private float mOutlineSize;

    private Paint mOutlinePaint;
    private Paint mFillPaint;

    ThumbLayer(float radius, float outlineSize, int outlineColor) {
        mRadius = radius;
        mOutlineSize = outlineSize;

        mOutlinePaint = new Paint();
        mOutlinePaint.setAntiAlias(true);
        mOutlinePaint.setColor(outlineColor);
        mOutlinePaint.setStyle(Paint.Style.STROKE);
        mOutlinePaint.setStrokeWidth(outlineSize);

        mFillPaint = new Paint();
        mFillPaint.setAntiAlias(true);
        mFillPaint.setColor(Color.WHITE);
        mFillPaint.setStyle(Paint.Style.FILL);
        mFillPaint.setStrokeWidth(0);
    }

    void draw(Canvas canvas, float cx, float cy) {
        canvas.drawCircle(cx, cy, mRadius, mOutlinePaint);
        canvas.drawCircle(cx, cy, mRadius - mOutlineSize / 2, mFillPaint);
    }
}

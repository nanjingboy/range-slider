package me.tom.range.slider;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

class ThumbLayer {

    private float mRadius;
    private float mOutlineSize;

    private int mkHighlightColor;

    private Paint mOutlinePaint;
    private Paint mFillPaint;

    boolean isHighlight = false;

    ThumbLayer(float radius, float outlineSize, int outlineColor, int highlightColor) {
        mRadius = radius;
        mOutlineSize = outlineSize;
        mkHighlightColor = highlightColor;

        mOutlinePaint = new Paint();
        mOutlinePaint.setAntiAlias(true);
        mOutlinePaint.setColor(outlineColor);
        mOutlinePaint.setStyle(Paint.Style.STROKE);
        mOutlinePaint.setStrokeWidth(outlineSize);

        mFillPaint = new Paint();
        mFillPaint.setAntiAlias(true);
        mFillPaint.setStyle(Paint.Style.FILL);
        mFillPaint.setStrokeWidth(0);
    }

    void draw(Canvas canvas, float cx, float cy) {
        canvas.drawCircle(cx, cy, mRadius, mOutlinePaint);

        if (isHighlight) {
            mFillPaint.setColor(mkHighlightColor);
        } else {
            mFillPaint.setColor(Color.WHITE);
        }
        canvas.drawCircle(cx, cy, mRadius - mOutlineSize / 2, mFillPaint);
    }
}

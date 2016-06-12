package me.tom.range.slider;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

class TrackLayer {

    private float mHeight;
    private float mRadius;

    private Paint mLeftPaint;
    private Paint mCenterPaint;
    private Paint mRightPaint;

    TrackLayer(float height, int color, int highlightColor) {
        mHeight = height;
        mRadius = mHeight / 2;

        mLeftPaint = new Paint();
        mLeftPaint.setAntiAlias(true);
        mLeftPaint.setColor(color);
        mLeftPaint.setStyle(Paint.Style.FILL);

        mCenterPaint = new Paint();
        mCenterPaint.setAntiAlias(true);
        mCenterPaint.setColor(highlightColor);
        mCenterPaint.setStyle(Paint.Style.FILL);

        mRightPaint = new Paint();
        mRightPaint.setAntiAlias(true);
        mRightPaint.setColor(color);
        mRightPaint.setStyle(Paint.Style.FILL);    }

    void draw(Canvas canvas, int width, float offsetLeft, float offsetRight, float offsetY) {
        if (offsetLeft > 0) {
            draw(canvas, mLeftPaint, 0, offsetLeft, offsetY);
        }

        if (offsetRight - offsetLeft > 0) {
            draw(canvas, mCenterPaint, offsetLeft, offsetRight - offsetLeft, offsetY);
        }

        if (width - offsetRight > 0) {
            draw(canvas, mRightPaint, offsetRight, width - offsetRight, offsetY);
        }
    }

    void draw(Canvas canvas, Paint paint, float offsetX, float width, float offsetY) {
        canvas.drawRoundRect(
                new RectF(offsetX, offsetY, offsetX + width, offsetY + mHeight),
                mRadius,
                mRadius,
                paint
        );
    }
}

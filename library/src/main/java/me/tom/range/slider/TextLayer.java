package me.tom.range.slider;

import android.graphics.Canvas;
import android.graphics.Paint;

class TextLayer {

    private Paint mPaint;

    TextLayer(float fontSize, int color) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(color);
        mPaint.setTextSize(fontSize);
        mPaint.setTextAlign(Paint.Align.CENTER);
    }

    void draw(Canvas canvas, String text, float offsetX, float offsetY) {
        canvas.drawText(text, offsetX, offsetY, mPaint);
    }
}

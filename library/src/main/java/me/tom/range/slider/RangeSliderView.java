package me.tom.range.slider;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

public class RangeSliderView extends View {

    private int mTrackTintColor;
    private int mTrackHighlightTintColor;
    private float mTrackHeight;
    private float mThumbRadius;
    private float mThumbOutlineSize;
    private float mDisplayTextFontSize;
    private float mDisplayTextBasicOffsetY;

    private ThumbLayer mMinValueThumb;
    private TextLayer mMinValueDisplayLabel;

    private ThumbLayer mMaxValueThumb;
    private TextLayer mMaxValueDisplayLabel;

    private TrackLayer mTrack;

    private ArrayList<Integer> mValues;

    private int mMinValue;
    private int mMaxValue;

    public RangeSliderView(Context context) {
        this(context, null);
    }

    public RangeSliderView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RangeSliderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.RangeSliderView);
        mTrackTintColor = typedArray.getColor(
                R.styleable.RangeSliderView_trackTintColor,
                ContextCompat.getColor(context, R.color.trackTintColor)
        );
        mTrackHighlightTintColor = typedArray.getColor(
                R.styleable.RangeSliderView_trackHighlightTintColor,
                ContextCompat.getColor(context, R.color.trackHighlightTintColor)
        );

        Resources resources = context.getResources();
        mTrackHeight = typedArray.getDimension(
                R.styleable.RangeSliderView_trackHeight,
                resources.getDimension(R.dimen.trackHeight)
        );
        mThumbRadius = typedArray.getDimension(
                R.styleable.RangeSliderView_thumbRadius,
                resources.getDimension(R.dimen.thumbRadius)
        );
        mThumbOutlineSize = typedArray.getDimension(
                R.styleable.RangeSliderView_thumbOutlineSize,
                resources.getDimension(R.dimen.thumbOutlineSize)
        );
        mDisplayTextFontSize = typedArray.getDimension(
                R.styleable.RangeSliderView_displayTextFontSize,
                resources.getDimension(R.dimen.displayTextFontSize)
        );
        mDisplayTextBasicOffsetY = resources.getDimension(R.dimen.displayTextBasicOffsetY);

        mMinValueThumb = new ThumbLayer(mThumbRadius, mThumbOutlineSize, mTrackHighlightTintColor);
        mMinValueDisplayLabel = new TextLayer(mDisplayTextFontSize, mTrackHighlightTintColor);

        mMaxValueThumb = new ThumbLayer(mThumbRadius, mThumbOutlineSize, mTrackHighlightTintColor);
        mMaxValueDisplayLabel = new TextLayer(mDisplayTextFontSize, mTrackHighlightTintColor);

        mTrack = new TrackLayer(mTrackHeight, mTrackTintColor, mTrackHighlightTintColor);

        mValues = new ArrayList<>();
        for (int index = 1; index <= 100; index++) {
            mValues.add(index);
        }
        mMinValue = 1;
        mMaxValue = 100;
    }

    public void setRangeValues(ArrayList<Integer> values) {
        mValues = values;
        setMinAndMaxValue(mValues.get(0), mValues.get(mValues.size() - 1));
    }

    public void setMinAndMaxValue(int minValue, int maxValue) {
        mMinValue = minValue;
        mMaxValue = maxValue;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float offsetY = getHeight() / 2;
        float minValueOffsetX = position(mMinValue);
        float maxValueOffsetX = position(mMaxValue);
        float displayLabelOffsetY = offsetY - mThumbRadius - mDisplayTextBasicOffsetY;

        mTrack.draw(canvas, getWidth(), minValueOffsetX, maxValueOffsetX, offsetY - mTrackHeight / 2);

        mMinValueThumb.draw(canvas, minValueOffsetX, offsetY);
        mMinValueDisplayLabel.draw(canvas, String.valueOf(mMinValue), minValueOffsetX, displayLabelOffsetY);

        mMaxValueThumb.draw(canvas, maxValueOffsetX, offsetY);
        mMaxValueDisplayLabel.draw(canvas, String.valueOf(mMaxValue), maxValueOffsetX, displayLabelOffsetY);
    }

    protected float position(int value) {
        int index = mValues.indexOf(value);
        int count = mValues.size();
        float radius = mThumbRadius + mThumbOutlineSize / 2;
        if (index == 0) {
            return radius;
        } else if (index == count - 1) {
            return getWidth() - radius;
        }

        return (getWidth() - radius * 2) * index / count + radius;
    }
}

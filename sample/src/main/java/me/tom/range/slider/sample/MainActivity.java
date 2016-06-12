package me.tom.range.slider.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import me.tom.range.slider.RangeSliderView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        RangeSliderView rangeSliderView = (RangeSliderView) findViewById(R.id.rangeSliderView);
        rangeSliderView.setOnValueChangedListener(new RangeSliderView.OnValueChangedListener() {
            @Override
            public void onValueChanged(int minValue, int maxValue) {
                Log.d("min value:", String.valueOf(minValue));
                Log.d("max value:", String.valueOf(maxValue));
            }

            @Override
            public String parseMinValueDisplayText(int minValue) {
                return super.parseMinValueDisplayText(minValue);
            }

            @Override
            public String parseMaxValueDisplayText(int maxValue) {
                return super.parseMinValueDisplayText(maxValue);
            }
        });
    }
}

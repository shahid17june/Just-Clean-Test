package com.q8coders.justClean.utility;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;


public class MyTextView extends AppCompatTextView {

    Typeface normalTypeface = Typeface.createFromAsset(getContext().getAssets(), "Dubai-Bold.ttf");
    Typeface boldTypeface = Typeface.createFromAsset(getContext().getAssets(),  "Dubai-Regular.ttf");

    public MyTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context) {
        super(context);
    }

    public void setTypeface(Typeface tf, int style) {
        if (style == Typeface.BOLD) {
            super.setTypeface(boldTypeface);
        } else {
            super.setTypeface(normalTypeface);
        }
    }
}
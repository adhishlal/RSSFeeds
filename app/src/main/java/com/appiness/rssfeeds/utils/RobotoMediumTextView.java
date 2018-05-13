package com.appiness.rssfeeds.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;


public class RobotoMediumTextView extends android.support.v7.widget.AppCompatTextView {

    public RobotoMediumTextView(Context context) {

        super(context);
        init();
    }

    public RobotoMediumTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RobotoMediumTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(StringUtils.getToUpper(text + ""), type);
        init();
    }

    @Override
    public void setTypeface(Typeface tf, int style) {
        super.setTypeface(tf, style);

    }

    public void init() {

        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "fonts/Roboto-Medium.ttf");
        setTypeface(tf, 1);

    }
}



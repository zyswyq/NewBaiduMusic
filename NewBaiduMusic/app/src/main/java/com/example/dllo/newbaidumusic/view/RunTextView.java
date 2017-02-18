package com.example.dllo.newbaidumusic.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by dllo on 17/2/18.
 */

public class RunTextView extends TextView{

    public RunTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RunTextView(Context context) {
        super(context);
    }

    public RunTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean isFocused() {
        return true;
    }
}

package com.example.play.myapplication.behavior;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.lguplus.usimsvcm.R;

public class HeaderLayout extends LinearLayout {
    private View mFixView;

    public HeaderLayout(Context context) {
        super(context);
    }

    public HeaderLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HeaderLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public HeaderLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mFixView = findViewById(R.id.fixed_view);
    }

    public int getScrollRange() {
        return getHeight() - mFixView.getHeight();
    }

    private void init() {
    }

}

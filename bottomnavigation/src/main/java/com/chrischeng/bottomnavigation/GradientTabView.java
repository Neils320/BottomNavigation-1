package com.chrischeng.bottomnavigation;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.chrischeng.gradienttabview.GradientImageView;
import com.chrischeng.gradienttabview.GradientTextView;

public class GradientTabView extends LinearLayout {

    private static final float DEFAULT_TEXT_SIZE = 12f;
    private static final int DEFAULT_TEXT_COLOR = 0xff666666;
    private static final float DEFAULT_TEXT_TOPMARGIN = 3f;

    private GradientImageView mImageView;
    private GradientTextView mTextView;

    public GradientTabView(Context context) {
        this(context, null);
    }

    public GradientTabView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public void setImageResources(@DrawableRes int botResId, @DrawableRes int topResId) {
        mImageView.setBotResource(botResId);
        mImageView.setTopResource(topResId);
    }

    public void setImageDrawable(@Nullable Drawable botDrawable, @Nullable Drawable topDrawable) {
        mImageView.setBotDrawable(botDrawable);
        mImageView.setTopDrawable(topDrawable);
    }

    public void setText(@StringRes int resId) {
        mTextView.setText(resId);
    }

    public void setText(String text) {
        mTextView.setText(text);
    }

    public void setTextSize(float size) {
        mTextView.setTextSize(size);
    }

    public void setTextColor(int color) {
        mTextView.setBotTextColor(color);
    }

    public void setTextMarginTop(int topMargin) {
        LayoutParams params = (LayoutParams) mTextView.getLayoutParams();
        params.topMargin = topMargin;
        mTextView.setLayoutParams(params);
    }

    private void init(Context context, AttributeSet attrs) {
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);

        findView(context);
        initAttrs(context, attrs);
    }

    private void findView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_tab, this, true);
        mImageView = (GradientImageView) findViewById(R.id.iv_tab);
        mTextView = (GradientTextView) findViewById(R.id.tv_tab);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.GradientTabView);

        mImageView.setBotDrawable(a.getDrawable(R.styleable.GradientTabView_gt_icon_bot_src));
        mImageView.setTopDrawable(a.getDrawable(R.styleable.GradientTabView_gt_icon_top_src));

        float iconWidth = a.getDimension(R.styleable.GradientTabView_gt_icon_width, 0);
        float iconHeight = a.getDimension(R.styleable.GradientTabView_gt_icon_height, 0);
        LayoutParams imgParams = (LayoutParams) mImageView.getLayoutParams();
        if (iconWidth > 0)
            imgParams.width = (int) iconWidth;
        if (iconHeight > 0)
            imgParams.height = (int) iconHeight;
        mImageView.setLayoutParams(imgParams);

        String text = a.getString(R.styleable.GradientTabView_gt_text);
        mTextView.setText(TextUtils.isEmpty(text) ? "" : text);

        mTextView.setTextSize(a.getDimension(R.styleable.GradientTabView_gt_text_size,
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, DEFAULT_TEXT_SIZE, dm)));

        mTextView.setBotTextColor(a.getColor(R.styleable.GradientTabView_gt_text_bot_color, DEFAULT_TEXT_COLOR));
        mTextView.setTopTextColor(a.getColor(R.styleable.GradientTabView_gt_text_top_color, DEFAULT_TEXT_COLOR));

        LayoutParams textParams = (LayoutParams) mTextView.getLayoutParams();
        textParams.topMargin = (int) a.getDimension(R.styleable.GradientTabView_gt_text_marginTop,
                TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_TEXT_TOPMARGIN, dm));
        mTextView.setLayoutParams(textParams);

        a.recycle();
    }
}

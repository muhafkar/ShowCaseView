package smartdevelop.ir.eram.showcaseviewlib;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.Spannable;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import smartdevelop.ir.eram.showcaseviewlib.listener.GuideListener;

/**
 * Created by Mohammad Reza Eram  on 20/01/2018.
 */

class GuideMessageView extends LinearLayout {

    private static final int RADIUS_SIZE = 5;
    private static final int PADDING_SIZE = 10;
    private static final int BOTTOM_PADDING_SIZE = 5;
    private static final int DEFAULT_TITLE_TEXT_SIZE = 18;
    private static final int DEFAULT_CONTENT_TEXT_SIZE = 14;

    private final Paint mPaint;
    private final RectF mRect;

    private final TextView mTitleTextView;
    private final TextView mContentTextView;
    private final LinearLayout linearLayout;
    private final TextView btnSkip;
    private final TextView btnNext;

    int padding;
    int paddingBottom;

    int[] location = new int[2];

    GuideMessageView(Context context) {
        super(context);

        float density = context.getResources().getDisplayMetrics().density;
        setWillNotDraw(false);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER);

        mRect = new RectF();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStrokeCap(Paint.Cap.ROUND);

        paddingBottom = (int) (BOTTOM_PADDING_SIZE * density);
        padding = (int) (PADDING_SIZE * density);

        mTitleTextView = new TextView(context);
        mTitleTextView.setPadding(padding, padding, padding, paddingBottom);
        mTitleTextView.setGravity(Gravity.CENTER);
        mTitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_TITLE_TEXT_SIZE);
        mTitleTextView.setTextColor(Color.BLACK);
        addView(
            mTitleTextView,
            new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        );

        mContentTextView = new TextView(context);
        mContentTextView.setTextColor(Color.BLACK);
        mContentTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, DEFAULT_CONTENT_TEXT_SIZE);
        mContentTextView.setPadding(padding, paddingBottom, padding, padding);
        mContentTextView.setGravity(Gravity.CENTER);
        addView(
            mContentTextView,
            new LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        );

        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(params);
        linearLayout.setGravity(Gravity.RIGHT);
        linearLayout.setPadding(15, 15, 15, 15);
        addView(linearLayout);

        btnSkip = new TextView(context);
        btnSkip.setText("Skip");
        btnSkip.setTextColor(Color.BLACK);
        btnSkip.setPadding(25, 15, 25, 15);
        btnSkip.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_border));
        btnSkip.setGravity(android.view.Gravity.RIGHT);

        linearLayout.addView(btnSkip);

        btnNext = new TextView(context);
        btnNext.setText("Next");
        btnNext.setTextColor(Color.BLACK);
        btnNext.setPadding(25, 15, 25, 15);
        btnNext.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_border));
        btnNext.setGravity(android.view.Gravity.RIGHT);
        LinearLayout.LayoutParams paramsBtn = new LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        paramsBtn.setMargins(10, 0, 0, 0);
        btnNext.setLayoutParams(paramsBtn);

        linearLayout.addView(btnNext);
    }

    public void setTitle(String title) {
        if (title == null) {
            removeView(mTitleTextView);
            return;
        }
        mTitleTextView.setText(title);
    }

    public void setContentText(String content) {
        mContentTextView.setText(content);
    }

    public void setContentSpan(Spannable content) {
        mContentTextView.setText(content);
    }

    public void setContentTypeFace(Typeface typeFace) {
        mContentTextView.setTypeface(typeFace);
    }

    public void setTitleTypeFace(Typeface typeFace) {
        mTitleTextView.setTypeface(typeFace);
    }

    public void setTitleTextSize(int size) {
        mTitleTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    public void setContentTextSize(int size) {
        mContentTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, size);
    }

    public void setColor(int color) {
        mPaint.setColor(color);
        invalidate();
    }

    public void setTitleColor(int titleColor) {
        if(titleColor != 0) {
            mTitleTextView.setTextColor(titleColor);
        }
    }

    public void setContentTextColor(int contentTextColor) {
        if(contentTextColor != 0) {
            mContentTextView.setTextColor(contentTextColor);
        }
    }

    public void setIsContentTextVisible(boolean isVisible) {
        if (isVisible) {
            mContentTextView.setVisibility(View.VISIBLE);
        } else {
            mTitleTextView.setPadding(padding, padding, padding, padding);
            mContentTextView.setVisibility(View.GONE);
        }
    }

    public void setBtnSkipText(String text) {
        btnSkip.setText(text);
    }

    public void setBtnNextText(String text) {
        btnNext.setText(text);
    }

    public void setBtnSkipOnClickListener(OnClickListener onClickListener) {
        btnSkip.setOnClickListener(onClickListener);
    }

    public void setBtnNextOnClickListener(OnClickListener onClickListener) {
        btnNext.setOnClickListener(onClickListener);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        this.getLocationOnScreen(location);

        mRect.set(
            getPaddingLeft(),
            getPaddingTop(),
            getWidth() - getPaddingRight(),
            getHeight() - getPaddingBottom()
        );

        final int density = (int) getResources().getDisplayMetrics().density;
        final int radiusSize = RADIUS_SIZE * density;
        canvas.drawRoundRect(mRect, radiusSize, radiusSize, mPaint);
    }
}

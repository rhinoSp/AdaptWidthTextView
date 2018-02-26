package com.rhino.adaptwidthtextview.view;

import android.content.Context;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * <p>The custom TextView adjusted text size by width.</p>
 *
 * @since Created by LuoLin on 2018/2/23.
 **/
public class AdaptWidthTextView extends TextView {

    private Paint mTextPaint;
    private float mMaxTextSize;
    private float mMinTextSize = 3;

    public AdaptWidthTextView(Context context) {
        super(context);
        init();
    }

    public AdaptWidthTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AdaptWidthTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        refitText(text.toString(), this.getWidth());
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w != oldw) {
            refitText(this.getText().toString(), w);
        }
    }

    /**
     * init
     */
    private void init() {
        this.mTextPaint = new TextPaint();
        this.mTextPaint.set(this.getPaint());
        this.mMaxTextSize = this.getTextSize();
    }

    /**
     * Resize the font so the specified text fits in the text box
     * assuming the text box is the specified width.
     */
    private void refitText(String text, int textWidth) {
        if (textWidth > 0) {
            int availableWidth = textWidth - this.getPaddingLeft() - this.getPaddingRight();
            float trySize = mMaxTextSize;

            mTextPaint.setTextSize(trySize);
            while (mTextPaint.measureText(text) > availableWidth) {
                trySize -= 1;
                if (trySize <= mMinTextSize) {
                    trySize = mMinTextSize;
                    break;
                }
                mTextPaint.setTextSize(trySize);
            }
            setTextSize(px2sp(getContext(), trySize));
        }
    }

    /**
     * Change px to sp.
     *
     * @param ctx the context
     * @param pxValue the px value
     * @return the sp value
     */
    private float px2sp(Context ctx, float pxValue) {
        float fontScale = ctx.getResources().getDisplayMetrics().scaledDensity;
        return (pxValue / fontScale);
    }

    /**
     * Set the min text size.
     */
    public void setMinTextSize(int textSize) {
        this.mMinTextSize = textSize;
        refitText(this.getText().toString(), this.getWidth());
    }

}

package tddc73.leopo.project1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by leopo on 2017-12-19.
 */

/**
 * A single dot for PasswordStrengthMeter.
 */
public class PasswordStrengthDot extends View {

    /**
     * The paint given to the dot. Should be given a color and a fill style.
     */
    private Paint paint;

    /**
     * Constructor
     * @param context Application context.
     * @param paint Paint with color and fill style.
     */
    public PasswordStrengthDot(Context context, final Paint paint) {
        super(context);
        this.paint = paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawRoundRect(10.0f, 10.0f, 80.0f, 40.0f, 20.0f, 20.0f, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredWidth = 100;
        int desiredHeight = 50;

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width;
        int height;

        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else if (widthMode == MeasureSpec.AT_MOST) {
            width = Math.min(desiredWidth, widthSize);
        } else {
            width = desiredWidth;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else if (heightMode == MeasureSpec.AT_MOST) {
            height = Math.min(desiredHeight, heightSize);
        } else {
            height = desiredHeight;
        }

        setMeasuredDimension(width, height);
    }
}

package tddc73.leopo.project2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.TextView;

/**
 * Created by leopo on 2017-12-20.
 */

/**
 * One step dot. Changes color if it is important.
 */
public class StepDot extends View {

    /**
     * Is it important?
     */
    private boolean isImportant;

    /**
     * Its number.
     */
    private String number;

    public StepDot(Context context, boolean importantDot, int number) {
        super(context);
        isImportant = importantDot;
        this.number = number + "";
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        if (isImportant) paint.setColor((0xff) << 24 | (255 & 0xff) << 16 | (150 & 0xff) << 8 | (200 & 0xff));
        else paint.setColor((0xff) << 24 | (100 & 0xff) << 16 | (200 & 0xff) << 8 | (255 & 0xff));

        Paint textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(80.0f);
        textPaint.setAntiAlias(true);

        if (isImportant) canvas.drawCircle(50.0f, 50.0f, 50.0f, paint);
        else canvas.drawCircle(50.0f, 50.0f, 40.0f, paint);
        canvas.drawText(number, 49.0f, 77.0f, textPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredWidth = 100;
        int desiredHeight = 100;

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

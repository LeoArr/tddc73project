package tddc73.leopo.project2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by leopo on 2017-12-20.
 */

/**
 * A line. It is pretty.
 */
public class StepsLine extends View {

    /**
     * Line length, scales with screen size.
     */
    private int width;

    /**
     * Constructor.
     * @param context Application context.
     */
    public StepsLine(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor((0xff) << 24 | (100 & 0xff) << 16 | (200 & 0xff) << 8 | (255 & 0xff));
        canvas.drawRoundRect(200.0f, 35.0f, ((float) width - 200), 65.0f, 10.0f, 10.0f, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int desiredHeight = 100;
        int height;

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        //as large as possible
        width = widthSize;

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

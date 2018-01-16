package tddc73.leopo.project2;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

/**
 * Created by leopo on 2017-12-20.
 */

public class ArrowButton extends View {

    /**
     * Left or right arrow?
     */
    private boolean pointsLeft;

    /**
     * Constructor
     * @param context Application context.
     * @param pointsLeft Left arrow?
     */
    public ArrowButton(Context context, boolean pointsLeft) {
        super(context);
        this.pointsLeft = pointsLeft;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor((0xff) << 24 | (100 & 0xff) << 16 | (200 & 0xff) << 8 | (255 & 0xff));
        paint.setAntiAlias(true);

        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        if (pointsLeft) {
            path.moveTo(00.0f, 50.0f);
            path.lineTo(100.0f, 0.0f);
            path.lineTo(100.0f, 100.0f);
            path.lineTo(0.0f, 50.0f);
        } else {
            path.moveTo(100.0f, 50.0f);
            path.lineTo(0.0f, 0.0f);
            path.lineTo(0.0f, 100.0f);
            path.lineTo(100.0f, 50.0f);
        }
        path.close();
        canvas.drawPath(path, paint);
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

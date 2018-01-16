package tddc73.leopo.project1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.FloatMath;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by leopo on 2017-12-19.
 */

/**
 * Displays the password strength by lighting colored dots.
 * There is 5 dots in total and the more are lit, the stronger the password.
 */
public class PasswordRatingBar extends LinearLayout {

    /**
     * Number of lit dots.
     */
    private int numOfDots;

    /**
     * The given password strength. A float from 0.0 to 1.0
     */
    private float str;

    /**
     * Application context
     */
    private Context context;

    /**
     * Constructor.
     * @param context Application context.
     * @param strength The given password strength.
     */
    public PasswordRatingBar(Context context, float strength) {
        super(context);
        this.context = context;
        str = Math.max(Math.min(strength, 1.0f), 0.0f);
        numOfDots = (int) (str * 5.0f);
        setOrientation(LinearLayout.HORIZONTAL);
        setUpDots();
    }

    /**
     * Positions the dots and gives them the correct colors.
     */
    private void setUpDots() {
        Paint litPaint = new Paint();
        litPaint.setStyle(Paint.Style.FILL);
        litPaint.setColor(getLitColor());

        Paint unlitPaint = new Paint();
        unlitPaint.setStyle(Paint.Style.FILL);
        unlitPaint.setColor(Color.GRAY);
        for (int i = 0; i < 5; i++) {
            PasswordStrengthDot dot;
            if (i <= numOfDots)
                dot = new PasswordStrengthDot(context, litPaint);
            else
                dot = new PasswordStrengthDot(context, unlitPaint);
            addView(dot);
        }
    }

    /**
     * Hard coded colors.
     * @return
     */
    public int getLitColor() {
        int A = 0xff;
        int R = 0, G = 0, B = 0;
        switch (numOfDots) {
            case 0:
                R = 255;
                G = 50;
                B = 100;
                break;
            case 1:
                R = 255;
                G = 100;
                B = 140;
                break;
            case 2:
                R = 150;
                G = 125;
                B = 200;
                break;
            case 3:
                R = 130;
                G = 150;
                B = 210;
                break;
            case 4:
                R = 140;
                G = 170;
                B = 255;
            case 5:
                R = 150;
                G = 210;
                B = 255;
                break;
        }
        return (A & 0xff) << 24 | (R & 0xff) << 16 | (G & 0xff) << 8 | (B & 0xff);
    }
}

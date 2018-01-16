package tddc73.leopo.project2;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by leopo on 2017-12-20.
 */

/**
 * A navigation bar to be used with ViewStepper, for example.
 * Recommended number of steps is 2 - 8.
 */
public class NavigationBar extends RelativeLayout {

    /**
     * Navigation button left.
     */
    private ArrowButton leftButton;

    /**
     * Navigation button right.
     */
    private ArrowButton rightButton;

    /**
     * Layout that holds buttons and dots.
     */
    private LinearLayout barLayout;

    /**
     * Total number of steps.
     */
    private int steps;

    /**
     * Current step.
     */
    private int currentStep;

    /**
     * Application context.
     */
    private Context context;

    /**
     * List with all shown dot layouts. For easy referencing
     * and removal.
     */
    private List<LinearLayout> stepDots;

    /**
     * Layout holding all dots.
     */
    private LinearLayout dotLayout;

    /**
     * The line in the background.
     */
    private StepsLine line;

    /**
     * Constructor.
     * @param context Application context.
     * @param steps Number of steps to show.
     */
    public NavigationBar(Context context, int steps) {
        super(context);
        this.context = context;
        this.steps = steps;
        currentStep = 0;
        stepDots = new ArrayList<>(steps);
        initBase();
    }

    /**
     * Sets up the dots in the dot layout.
     * Removes old dots if necessary.
     */
    private void setUpDots() {
        if (!stepDots.isEmpty())
            for (LinearLayout dotContainer: stepDots) {
                dotContainer.removeAllViews();
                dotLayout.removeView(dotContainer);
            }

        for (int i = 0; i < steps; i++) {
            StepDot dot = new StepDot(context, (i == currentStep), i + 1);
            LinearLayout dotContainer = new LinearLayout(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1f);
            dotContainer.setLayoutParams(params);
            dotContainer.setGravity(Gravity.CENTER_HORIZONTAL);
            dotContainer.addView(dot);
            stepDots.add(dotContainer);
            dotLayout.addView(dotContainer);
        }
    }

    /**
     * Increment step.
     */
    public void increment() {
        if (currentStep >= steps - 1) return;
        currentStep++;
        setUpDots();
    }

    /**
     * Decrement step.
     */
    public void decrement() {
        if (currentStep <= 0) return;
        currentStep--;
        setUpDots();
    }

    /**
     * Sets up the bar.
     */
    private void initBase() {
        //line
        line = new StepsLine(context);
        addView(line);
        barLayout = new LinearLayout(context);

        barLayout.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        barLayout.setLayoutParams(params);
        addView(barLayout);

        //left button
        leftButton = new ArrowButton(context, true);
        barLayout.addView(leftButton);
        params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f);
        params.setMargins(50,0,50,0);
        leftButton.setLayoutParams(params);
        //Dot layout
        dotLayout = new LinearLayout(context);
        dotLayout.setOrientation(LinearLayout.HORIZONTAL);
        barLayout.addView(dotLayout);
        params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 150f);
        params.setMargins(50,0,50,0);
        dotLayout.setLayoutParams(params);
        setUpDots();
        //right button
        rightButton = new ArrowButton(context, false);
        barLayout.addView(rightButton);
        params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT, 1f);
        params.setMargins(50,0,50,0);
        rightButton.setLayoutParams(params);
    }

    /**
     * Left navigation button, provided for hooks etc.
     */
    public View getLeftButton() {
        return leftButton;
    }

    /**
     * Right navigation button, provided for hooks etc.
     */
    public View getRightButton() {
        return rightButton;
    }
}

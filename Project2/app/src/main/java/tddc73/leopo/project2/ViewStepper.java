package tddc73.leopo.project2;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by leopo on 2017-12-20.
 */

/**
 * ViewStepper by Leopold Arreström.
 * This component allows you to show your content in steps.
 * The user can cycle through steps with an easy intuitive
 * navigation bar.
 */
public class ViewStepper extends LinearLayout {

    /**
     * Application context.
     */
    private Context context;

    /**
     * The view that holds the navigation bar.
     */
    private LinearLayout navigationView;

    /**
     * The navigation bar that displays info and
     * navigational information.
     */
    private NavigationBar navigationBar;

    /**
     * The title of the current step.
     * Displayed at the top of the component.
     */
    private TextView titleView;

    /**
     * The container for the developer provided content to show.
     */
    private RelativeLayout contentView;

    /**
     * A list of provided views with titles.
     */
    private List<ViewStep> viewSteps;

    /**
     * Current step showing.
     */
    private int currentStep;

    /**
     * Total number of steps.
     */
    private int maxStep;

    /**
     * Constructor.
     * @param context Application context.
     * @param viewSteps The views which to display in steps, with titles.
     */
    public ViewStepper(Context context, List<ViewStep> viewSteps) {
        super(context);
        this.context = context;
        this.viewSteps = viewSteps;
        currentStep = 0;
        maxStep = viewSteps.size();
        setLayoutParams(new LinearLayoutCompat.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        setOrientation(LinearLayout.VERTICAL);

        if (viewSteps.isEmpty()) return;
        setUpViewStepper();
    }

    /**
     * Sets up the navigation bar in the navigation view.
     */
    private void initNavigationView() {
        navigationView = new LinearLayout(context);
        navigationView.setOrientation(LinearLayout.VERTICAL);
        titleView = new TextView(context);
        titleView.setText(viewSteps.get(currentStep).getTitle());
        titleView.setTextAlignment(TEXT_ALIGNMENT_CENTER);
        titleView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
        navigationView.addView(titleView);

        setUpNavigationBar();

        addView(navigationView);
    }

    /**
     * Sets up the navigation bar with touch listeners on the navigation buttons.
     */
    private void setUpNavigationBar() {
        navigationBar = new NavigationBar(context, viewSteps.size());

        navigationBar.getLeftButton().setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                decrement();
                return false;
            }
        });

        navigationBar.getRightButton().setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                increment();
                return false;
            }
        });

        navigationView.addView(navigationBar);
    }

    /**
     * Updates the title. Called on step changes.
     */
    private void updateTitle() {
        titleView.setText(viewSteps.get(currentStep).getTitle());
    }

    /**
     * Step change down.
     */
    private void decrement() {
        if (currentStep <= 0) {
            currentStep = 0;
            return;
        }
        contentView.removeAllViews();
        currentStep--;
        contentView.addView(viewSteps.get(currentStep).getView());
        navigationBar.decrement();
        updateTitle();
    }

    /**
     * Step change up.
     */
    private void increment() {
        if (currentStep >= maxStep - 1) {
            currentStep = maxStep - 1;
            return;
        }
        contentView.removeAllViews();
        currentStep++;
        contentView.addView(viewSteps.get(currentStep).getView());
        navigationBar.increment();
        updateTitle();
    }

    /**
     * Sets up the container for the views to show in steps.
     */
    private void initContentView() {
        contentView = new RelativeLayout(context);
        contentView.addView(viewSteps.get(currentStep).getView());
        addView(contentView);
    }

    /**
     * Basic setup.
     */
    private void setUpViewStepper() {
        currentStep = 0;
        initNavigationView();
        initContentView();
    }
}

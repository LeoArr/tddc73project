package tddc73.leopo.project2;

import android.content.Context;
import android.view.View;

/**
 * Created by leopo on 2017-12-20.
 */

/**
 * One view step for ViewStepper
 */
public class ViewStep {

    /**
     * Step title
     */
    private String title;

    /**
     * Step view.
     */
    private View view;

    /**
     * Constructor.
     * @param title Step title.
     */
    public ViewStep(String title, View view) {
        this.title = title;
        this.view = view;
    }

    /**
     * Returns step title.
     */
    public String getTitle() { return title; }

    /**
     * Return view.
     */
    public View getView() { return view; }
}

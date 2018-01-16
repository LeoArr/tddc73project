package tddc73.leopo.project1;

import android.content.Context;
import android.os.SystemClock;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.Random;

/**
 * Password strength meter by Leopold Arreström.
 * This component allows you to show your users how strong you think their passwords are.
 * Apply it to a text field by using setPasswordSource(TextView source) then apply your
 * algorithm for determining password strength by injecting your own PasswordChecker class.
 */
public class PasswordStrengthMeter extends LinearLayout {

    /**
     * Context for this component.
     */
    private Context context;

    /**
     * Class that implements the PasswordChecker interface.
     * Contains the password strength determining algorithm.
     */
    private PasswordChecker passwordChecker;

    /**
     * The given text field which to check.
     */
    private TextView passwordSource;

    /**
     * Value for password strength, from 0.0 to 1.0.
     */
    private float currStrength = 0.0f;

    /**
     * Current change id, for concurrency with threads.
     */
    private int currChangeID = 0;

    /**
     * Text with short info about the current password strength.
     */
    private TextView infoText;

    /**
     * A visual representation of the current password strength.
     */
    private PasswordRatingBar ratingBar;

    /**
     * On key listener.
     */
    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        /**
         * Dispatches a new thread that checks the password and later posts results to UI thread
         * if there is a valid change. ID:s are used to keep track of what order threads are dispatched.
         * If a later thread finishes before an earlier thread to older thread's changes are discarded.
         */
        @Override
        public void onTextChanged(final CharSequence charSequence, int i, int i1, int i2) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    final float newStrength;
                    if ((newStrength = passwordChecker.checkPassword(charSequence.toString())) != currStrength) {
                        updateStrength(newStrength, currChangeID);
                    }
                    infoText.post(new Runnable() {
                        @Override
                        public void run() {
                            infoText.setText(updateText(newStrength));
                        }
                    });
                }
            });
            currChangeID++;
            thread.start();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    /**
     * Gets the response text.
     * @param str current strength.
     * @return new response.
     */
    private String updateText(float str) {
        if (passwordSource != null &&
                passwordSource.getText().length() < passwordChecker.getMinNumberOfCharacters())
            return "Password too short";
        if (str < 0.2) return "Very Weak";
        else if (str < 0.4) return "Weak";
        else if (str < 0.6) return "Fair";
        else if (str < 0.8) return "Good";
        else return "Very Good";
    }

    /**
     * Constructor.
     * @param context context for the application.
     * @param passwordChecker class that implements the PasswordChecker interface.
     */
    public PasswordStrengthMeter(Context context, PasswordChecker passwordChecker) {
        super(context);
        initPasswordStrengthMeter(context, passwordChecker);
    }

    public PasswordStrengthMeter(Context context, PasswordChecker passwordChecker, TextView passwordSource) {
        super(context);
        initPasswordStrengthMeter(context, passwordChecker);
        setPasswordSource(passwordSource);
    }

    /**
     * Initial set-up.
     * @param context Application context.
     * @param passwordChecker Dependency injected password checking algorithm.
     */
    private void initPasswordStrengthMeter(Context context, PasswordChecker passwordChecker) {
        this.context = context;
        this.passwordChecker = passwordChecker;
        setOrientation(LinearLayout.VERTICAL);

        infoText = new TextView(context);
        infoText.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        infoText.setText("Please enter a password");
        addView(infoText);

        ratingBar = new PasswordRatingBar(context, 0.0f);
        ratingBar.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        addView(ratingBar);
    }

    /**
     * Sets a textfield to be the password holder.
     * Adds a TextWatcher that triggers the strength meter to be updates every
     * time the text is changed.
     * @param passwordSource any TextView to be password checked.
     */
    public void setPasswordSource(final TextView passwordSource) {
        if (this.passwordSource != null)
            passwordSource.removeTextChangedListener(watcher);
        this.passwordSource = passwordSource;
        passwordSource.addTextChangedListener(watcher);
    }

    /**
     * Update the view to show the new password strength.
     * Is run from a separate thread, thus uses .post().
     * If the process takes to long another, later process
     * might have finished earlier - then the earlier process
     * is discarded.
     * @param currStrength the strength the algorithm found the password to have, from 0.0 to 1.0
     * @param ID the change ID to keep track of in what order the threads are dispatched.
     */
    private boolean updateStrength(final float currStrength, final int ID) {
        final PasswordRatingBar PRB = new PasswordRatingBar(context, currStrength);
        if (ID < currChangeID) return false;

        ratingBar.post(new Runnable() {
            @Override
            public void run() {
                removeView(ratingBar);
                ratingBar = PRB;
                addView(ratingBar);
            }
        });
        return true;
    }
}

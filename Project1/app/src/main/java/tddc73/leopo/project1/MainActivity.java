package tddc73.leopo.project1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        //Password row
        LinearLayout passBarInfo = new LinearLayout(this);
        passBarInfo.setGravity(1);
        TextView info = new TextView(this);
        info.setText("Choose Password:");
        passBarInfo.addView(info);

        //edit field
        LinearLayout passBarField = new LinearLayout(this);
        passBarField.setOrientation(LinearLayout.HORIZONTAL);
        passBarField.setGravity(1);
        EditText passField = new EditText(this);
        passField.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        passBarField.addView(passField);

        //My component
        PasswordChecker PC = new MockPC(); //dependency injection
        PasswordStrengthMeter PSM = new PasswordStrengthMeter(this, PC);
        PSM.setPasswordSource(passField);
        PSM.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f));
        passBarField.addView(PSM);

        layout.addView(passBarInfo);
        layout.addView(passBarField);

        setContentView(layout);
    }
}

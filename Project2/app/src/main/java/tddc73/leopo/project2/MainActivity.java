package tddc73.leopo.project2;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        //setting up views to step through
        //List<ViewStep> vSteps = getMockSteps();
        List<ViewStep> vSteps = getKingSteps();
        //My component
        layout.addView(new ViewStepper(this, vSteps));

        setContentView(layout);
    }

    private List<ViewStep> getMockSteps() {
        List<ViewStep> res = new ArrayList<ViewStep>();
        for (int i = 0; i < 6; i++) {
            res.add(new ViewStep("Step " + (i + 1), new MockView(this, Color.RED)));
        }
        return res;
    }

    private List<ViewStep> getKingSteps() {
        List<ViewStep> res = new ArrayList<>();
        ImageView firstKing = new ImageView(this);
        Drawable firstKingDrawable = getResources().getDrawable(R.drawable.kungen);
        firstKing.setImageDrawable(firstKingDrawable);
        res.add(new ViewStep("Detta är Kungen", firstKing));

        ImageView secondKing = new ImageView(this);
        Drawable secondKingDrawable = getResources().getDrawable(R.drawable.kungen2);
        secondKing.setImageDrawable(secondKingDrawable);
        TextView someText = new TextView(this);
        someText.setText("Här är lite text också.");
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(secondKing);
        linearLayout.addView(someText);
        res.add(new ViewStep("Detta är också kungen", linearLayout));

        ImageView thrid = new ImageView(this);
        Drawable thirdDrawable = getResources().getDrawable(R.drawable.kungen3);
        thrid.setImageDrawable(thirdDrawable);
        res.add(new ViewStep("Kungen är glad", thrid));

        return res;
    }
}

package org.staysee.forcefitness;

import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.Guideline;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;

public class ScheduleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_schedule);
        setSupportActionBar(myToolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        populateSchedule();
    }

    private void populateSchedule() {
        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.Schedule_Activity_Constraint_Layout);
        ConstraintSet set = new ConstraintSet();
        set.clone(layout);

        Guideline guideline = (Guideline) findViewById(R.id.guidelineSchedule);
        int buttonNum = 1;

        ImageButton[] buttons = new ImageButton[buttonNum];
        buttons[0] = new ImageButton(this);
        buttons[0].setId(R.id.imageButton);
        buttons[0].setBackgroundColor(Color.GRAY);
        buttons[0].setImageResource(R.drawable.circle_add);
        buttons[0].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
        layout.addView(buttons[0]);
        set.connect(buttons[0].getId(), ConstraintSet.TOP, guideline.getId(), ConstraintSet.BOTTOM, 0);
        set.connect(buttons[0].getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
        set.constrainHeight(buttons[0].getId(), 425);
        set.constrainWidth(buttons[0].getId(), 325);
        set.applyTo(layout);

        CheckBox[] checkBox = new CheckBox[buttonNum];
        checkBox[0] = new CheckBox(this);
        checkBox[0].setId(R.id.checkBox);
        checkBox[0].setText(R.string.incomplete);
        layout.addView(checkBox[0]);
        set.connect(checkBox[0].getId(), ConstraintSet.TOP, buttons[0].getId(), ConstraintSet.BOTTOM, 10);
        set.connect(checkBox[0].getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
        set.applyTo(layout);

        if (checkBox[0].isChecked()) {
            checkBox[0].setText("");
        } else {
            checkBox[0].setText("");
        }

    }


}

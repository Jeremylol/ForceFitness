package org.staysee.forcefitness;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.Guideline;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

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

        Button[] buttons = new Button[buttonNum];
        buttons[0] = new Button(this);
        buttons[0].setId(R.id.scheduleButton);
        buttons[0].setBackgroundResource(R.drawable.circle_add);
        buttons[0].setBackgroundColor(Color.GRAY);
        buttons[0].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
        layout.addView(buttons[0]);
        set.connect(buttons[0].getId(), ConstraintSet.TOP, guideline.getId(), ConstraintSet.BOTTOM, 0);
        set.connect(buttons[0].getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
        set.constrainHeight(buttons[0].getId(), 225);
        set.constrainWidth(buttons[0].getId(), 225);
        set.applyTo(layout);
    }


}

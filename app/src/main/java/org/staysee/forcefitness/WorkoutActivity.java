package org.staysee.forcefitness;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.Guideline;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class WorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String muscleGroup = getIntent().getStringExtra("EXTRA_MUSCLE_GROUP");
        setTitle(muscleGroup);
        setContentView(R.layout.activity_workout);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.Workout_Activity_Constraint_Layout);
        ConstraintSet set = new ConstraintSet();
        set.clone(layout);

        Guideline guideline = (Guideline) findViewById(R.id.guideline);
        int buttonNum = 0;

        try {
            InputStream IS = getAssets().open(muscleGroup + ".txt");
            Scanner input = new Scanner (IS);
            while (input.hasNextLine()) {
                buttonNum++;
                input.nextLine();
            }
            input.close();
            IS.close();
            Button[] buttons = new Button[buttonNum];
            InputStream IS2 = getAssets().open(muscleGroup + ".txt");
            Scanner input2 = new Scanner (IS2);
            buttons[0] = new Button(this);
            buttons[0].setText(input2.nextLine());
            buttons[0].setId(1);
            buttons[0].setTextSize(35);
            layout.addView(buttons[0]);
            set.connect(buttons[0].getId(), ConstraintSet.TOP, guideline.getId(), ConstraintSet.BOTTOM, 0);
            set.connect(buttons[0].getId(),ConstraintSet.RIGHT,ConstraintSet.PARENT_ID,ConstraintSet.RIGHT,0);
            set.connect(buttons[0].getId(),ConstraintSet.LEFT,ConstraintSet.PARENT_ID,ConstraintSet.LEFT,0);
            set.constrainHeight(buttons[0].getId(), 200);
            set.applyTo(layout);

            for (int i = 1; i < buttons.length; i++) {
                buttons[i] = new Button(this);
                String text = input2.nextLine();
                Log.d("TEXT", text);
                buttons[i].setText(text);
                buttons[i].setId(i+1);
                buttons[i].setTextSize(35);
                layout.addView(buttons[i]);
                set.connect(buttons[i].getId(), ConstraintSet.TOP, buttons[i-1].getId(), ConstraintSet.BOTTOM, 0);
                set.connect(buttons[i].getId(),ConstraintSet.RIGHT,ConstraintSet.PARENT_ID,ConstraintSet.RIGHT,0);
                set.connect(buttons[i].getId(),ConstraintSet.LEFT,ConstraintSet.PARENT_ID,ConstraintSet.LEFT,0);
                set.constrainHeight(buttons[i].getId(), 200);
                set.applyTo(layout);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        overridePendingTransition(R.anim.fade_from_left, 0);
        return super.onOptionsItemSelected(item);
    }

    public void toMuscleActivity(View v) {
        Intent myIntent = new Intent(this, MuscleActivity.class);
        this.startActivity(myIntent);
        overridePendingTransition(R.anim.fade_from_left, 0);
    }

}

package org.staysee.forcefitness;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.Guideline;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class WorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_workout);
        setSupportActionBar(myToolbar);

        final String muscleGroup = getIntent().getStringExtra("MUSCLE_GROUP");
        setTitle(muscleGroup);
        TextView title = (TextView) findViewById(R.id.toolbar_title);
        title.setText(muscleGroup);

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

            ImageView[] images = new ImageView[buttonNum];

            images[0] = new ImageView(this);
            images[0].setBackgroundResource(R.color.buttonColor);
            images[0].setImageResource(R.mipmap.abwheelroller);
            images[0].setId(R.id.imageViewFun);
            layout.addView(images[0]);
            set.connect(images[0].getId(), ConstraintSet.TOP, guideline.getId(), ConstraintSet.BOTTOM, 0);
            set.connect(images[0].getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
            set.constrainHeight(images[0].getId(), 225);
            set.constrainWidth(images[0].getId(), 225);
            set.applyTo(layout);

            buttons[0] = new Button(this);
            buttons[0].setText(input2.nextLine());
            buttons[0].setId(R.id.buttonfun);
            buttons[0].setBackgroundResource(R.color.buttonColor);
            buttons[0].setTextSize(35);
            buttons[0].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Button button = (Button) v;
                    String buttonText = button.getText().toString();
                    Intent myIntent = new Intent(WorkoutActivity.this, ExerciseActivity.class);
                    myIntent.putExtra("SOURCE", "workout");
                    myIntent.putExtra("MUSCLE", muscleGroup);
                    myIntent.putExtra("WORKOUT_EXERCISE", buttonText);
                    startActivity(myIntent);
                }
            });
            layout.addView(buttons[0]);
            set.connect(buttons[0].getId(), ConstraintSet.TOP, guideline.getId(), ConstraintSet.BOTTOM, 0);
            set.connect(buttons[0].getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
            set.connect(buttons[0].getId(), ConstraintSet.START, images[0].getId(), ConstraintSet.END, 13);
            set.constrainHeight(buttons[0].getId(), 225);
            set.applyTo(layout);

            for (int i = 1; i < buttons.length; i++) {
                images[i] = new ImageView(this);
                images[i].setBackgroundResource(R.color.buttonColor);
                images[i].setImageResource(R.mipmap.abs);
                images[i].setId(R.id.imageViewFun + i);
                layout.addView(images[i]);
                set.connect(images[i].getId(), ConstraintSet.TOP, images[i - 1].getId(), ConstraintSet.BOTTOM, 15);
                set.connect(images[i].getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
                set.constrainHeight(images[i].getId(), 225);
                set.constrainWidth(images[i].getId(), 225);
                set.applyTo(layout);

                buttons[i] = new Button(this);
                buttons[i].setText(input2.nextLine());
                buttons[i].setId(i+1);
                buttons[i].setTextSize(35);
                buttons[i].setBackgroundResource(R.color.buttonColor);
                buttons[i].setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Button button = (Button) v;
                        String buttonText = button.getText().toString();
                        Intent myIntent = new Intent(WorkoutActivity.this, ExerciseActivity.class);
                        myIntent.putExtra("SOURCE", "workout");
                        myIntent.putExtra("MUSCLE", muscleGroup);
                        myIntent.putExtra("WORKOUT_EXERCISE", buttonText);
                        startActivity(myIntent);
                    }
                });
                layout.addView(buttons[i]);
                set.connect(buttons[i].getId(), ConstraintSet.TOP, buttons[i - 1].getId(), ConstraintSet.BOTTOM, 15);
                set.connect(buttons[i].getId(), ConstraintSet.RIGHT, ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
                set.connect(buttons[i].getId(), ConstraintSet.START, images[i].getId(), ConstraintSet.END, 13);
                set.constrainHeight(buttons[i].getId(), 225);
                set.applyTo(layout);
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
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
}
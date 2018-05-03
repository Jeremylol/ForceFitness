package org.staysee.forcefitness;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.Guideline;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    private static final String TAG = "FavoritesActivity";

    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_favorites);
        setSupportActionBar(myToolbar);

        mDatabaseHelper = new DatabaseHelper(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        try {
            populateListView();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.getMessage();
        }
    }

    private void populateListView() throws NoSuchFieldException, IllegalAccessException {
        Log.d(TAG, "populateListView: Displaying data in the ListView.");
        //get the data and append to a list
        Cursor data = mDatabaseHelper.getData();

        ConstraintLayout layout = (ConstraintLayout) findViewById(R.id.Favorites_Activity_Constraint_Layout);
        ConstraintSet set = new ConstraintSet();
        set.clone(layout);

        Guideline guideline = (Guideline) findViewById(R.id.guidelinefav);
        int buttonNum = 0;
        ArrayList<String> favTitles = new ArrayList<>();

        if (data.getCount() == 0) {
            data.close();
            TextView textview = (TextView) findViewById(R.id.textView2);
            textview.setText(R.string.noFavorites);
            textview.setTextSize(25);
        } else {
            while (data.moveToNext()) {
                buttonNum++;
                favTitles.add(data.getString(1));
            }
            Log.d("THIS IS WHAT YOURE", Integer.toString(buttonNum));
            data.close();

            String name = favTitles.get(0).replaceAll("\\s", "").toLowerCase();
            int resourceId = R.mipmap.class.getField(name).getInt(null);
            ImageView[] images = new ImageView[buttonNum];
            images[0] = new ImageView(this);
            images[0].setImageResource(resourceId);
            images[0].setBackgroundResource(R.color.buttonColor);
            images[0].setId(R.id.imageViewFun);
            layout.addView(images[0]);
            set.connect(images[0].getId(), ConstraintSet.TOP, guideline.getId(), ConstraintSet.BOTTOM, 0);
            set.connect(images[0].getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
            set.constrainHeight(images[0].getId(), 225);
            set.constrainWidth(images[0].getId(), 225);
            set.applyTo(layout);

            Button[] buttons = new Button[buttonNum];
            buttons[0] = new Button(this);
            buttons[0].setText(favTitles.get(0));
            buttons[0].setId(R.id.buttonfun);
            buttons[0].setTextSize(20);
            buttons[0].setBackgroundResource(R.color.buttonColor);
            buttons[0].setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Button button = (Button) v;
                    String buttonText = button.getText().toString();
                    Intent myIntent = new Intent(FavoritesActivity.this, ExerciseActivity.class);
                    myIntent.putExtra("SOURCE", "favorites");
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
                String name1 = favTitles.get(i).replaceAll("\\s", "").toLowerCase();
                int resourceId1 = R.mipmap.class.getField(name1).getInt(null);

                images[i] = new ImageView(this);
                images[i].setBackgroundResource(R.color.buttonColor);
                images[i].setImageResource(resourceId1);
                images[i].setId(R.id.imageViewFun + i);
                layout.addView(images[i]);
                set.connect(images[i].getId(), ConstraintSet.TOP, images[i - 1].getId(), ConstraintSet.BOTTOM, 15);
                set.connect(images[i].getId(), ConstraintSet.LEFT, ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
                set.constrainHeight(images[i].getId(), 225);
                set.constrainWidth(images[i].getId(), 225);
                set.applyTo(layout);

                buttons[i] = new Button(this);
                buttons[i].setText(favTitles.get(i));
                buttons[i].setId(i + 1);
                buttons[i].setTextSize(20);
                buttons[i].setBackgroundResource(R.color.buttonColor);
                buttons[i].setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Button button = (Button) v;
                        String buttonText = button.getText().toString();
                        Intent myIntent = new Intent(FavoritesActivity.this, ExerciseActivity.class);
                        myIntent.putExtra("SOURCE", "favorites");
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
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        overridePendingTransition(R.anim.fade_from_left, 0);
        return super.onOptionsItemSelected(item);
    }
}
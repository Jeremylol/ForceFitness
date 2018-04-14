package org.staysee.forcefitness;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ExerciseActivity extends AppCompatActivity {

    private static final String TAG = "ExerciseActivity";
//lol
    private String source;
    private String muscle;
    private String workoutTitle;

    DatabaseHelper mDatabaseHelper;
    public Boolean activated = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_exercise);
        setSupportActionBar(myToolbar);
        mDatabaseHelper = new DatabaseHelper(this);

        source = getIntent().getStringExtra("SOURCE");
        muscle = getIntent().getStringExtra("MUSCLE");
        workoutTitle = getIntent().getStringExtra("WORKOUT_EXERCISE");
        setTitle(workoutTitle);
        TextView title = (TextView) findViewById(R.id.toolbar_title);
        title.setText(workoutTitle);

        getWindow().setFormat(PixelFormat.UNKNOWN);
        VideoView video = (VideoView) findViewById(R.id.videoView);
        workoutTitle = workoutTitle.toLowerCase().replaceAll("\\s", "");
        video.setVideoPath("android.resource://" + getPackageName() + "/raw/" + workoutTitle);
        video.requestFocus();
        video.start();
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        TextView tV = (TextView) findViewById(R.id.description);
        try {
            InputStream IS = getAssets().open(workoutTitle + ".txt");
            Scanner input = new Scanner(IS);
            String description = "";
            while (input.hasNextLine()) {
                description += input.nextLine() + "\n\n";
            }
            tV.setText(description);
            input.close();
            IS.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        updateMenu(menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (source.equals("workout")) {
                    Intent myIntent = new Intent(this, WorkoutActivity.class);
                    myIntent.putExtra("MUSCLE_GROUP", muscle);
                    this.startActivity(myIntent);
                } else {
                    Intent myIntent = new Intent(this, FavoritesActivity.class);
                    this.startActivity(myIntent);
                }
                overridePendingTransition(R.anim.fade_from_left, 0);
                return true;
            case R.id.action_favorite_class:
                startActivity(new Intent(this, FavoritesActivity.class));
                overridePendingTransition(R.anim.fade_from_left, 0);
                return true;
            case R.id.action_main_menu:
                startActivity(new Intent(this, MainMenuActivity.class));
                overridePendingTransition(R.anim.fade_from_left, 0);
                return true;

            case R.id.action_favorite:
                if (activated) {
                    addData(workoutTitle);
                    item.setIcon(R.mipmap.favorite_filled);
                    overridePendingTransition(R.anim.fade_from_left, 0);
                    activated = false;
                } else {
                    deleteName(workoutTitle);
                    item.setIcon(R.drawable.favorite_border);
                    overridePendingTransition(R.anim.fade_from_left, 0);
                    activated = true;
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void addData(String newEntry) {
        boolean insertData = mDatabaseHelper.addData(newEntry);
        if (insertData) {
            Toast.makeText(getApplicationContext(), R.string.favorites_message,
                    Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "This is not a routine you can add",
                    Toast.LENGTH_LONG).show();
        }
    }

    public void deleteName(String name) {
        mDatabaseHelper.deleteName(name);
        Toast.makeText(getApplicationContext(), "Workout Deleted!",
                Toast.LENGTH_SHORT).show();
    }

    public void updateMenu(Menu menu) {
        if (mDatabaseHelper.getItemID(workoutTitle).getCount() > 0) {
            activated = false;
            MenuItem item = menu.findItem(R.id.action_favorite);
            item.setIcon(R.mipmap.favorite_filled);
            overridePendingTransition(R.anim.fade_from_left, 0);
        }
    }

}
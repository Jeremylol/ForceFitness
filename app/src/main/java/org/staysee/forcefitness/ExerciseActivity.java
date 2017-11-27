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
import android.widget.VideoView;

public class ExerciseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_exercise);
        setSupportActionBar(myToolbar);

        String workoutTitle = getIntent().getStringExtra("EXTRA_MUSCLE_GROUP");
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

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:
                finish();
                overridePendingTransition(R.anim.fade_from_left, 0);
                return true;
            case R.id.action_main_menu:
                startActivity(new Intent(this, MainMenuActivity.class));
                overridePendingTransition(R.anim.fade_from_left, 0);
                return true;

            case R.id.action_favorite:
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }
}
package org.staysee.forcefitness;

import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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

        String muscleGroup = getIntent().getStringExtra("EXTRA_MUSCLE_GROUP");
        setTitle(muscleGroup);
        TextView title = (TextView) findViewById(R.id.toolbar_title);
        title.setText(muscleGroup);

        getWindow().setFormat(PixelFormat.UNKNOWN);

        VideoView video = (VideoView) findViewById(R.id.videoView);
        video.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.test);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        overridePendingTransition(R.anim.fade_from_left, 0);
        return super.onOptionsItemSelected(item);
    }
}
package org.staysee.forcefitness;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.VideoView;

public class ExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar_exercise);
        setSupportActionBar(myToolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);

        VideoView simpleView = (VideoView) findViewById(R.id.video1);
        simpleView.setVideoURI(Uri.parse("android/resource://" + getPackageName() + "/" + R.raw.abs_declinereversecrunch));
        simpleView.start();
        simpleView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });


    }
}

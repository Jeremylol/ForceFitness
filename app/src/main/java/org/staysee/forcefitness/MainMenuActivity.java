package org.staysee.forcefitness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    public void toMuscleActivity(View v) {
        startActivity(new Intent(this, MuscleActivity.class));
        overridePendingTransition(R.anim.fade_from_left, 0);
    }

    public void toFavoritesActivity(View v) {
        startActivity(new Intent(this, FavoritesActivity.class));
        overridePendingTransition(R.anim.fade_from_left, 0);
    }

    public void toLoginActivity(View v) {
        startActivity(new Intent(this, LoginActivity.class));
        overridePendingTransition(R.anim.fade_from_left, 0);
    }

    public void toAboutActivity(View v) {
        startActivity(new Intent(this, AboutActivity.class));
        overridePendingTransition(R.anim.fade_from_left, 0);
    }

    public void toScheduleActivity(View v) {
        startActivity(new Intent(this, ScheduleActivity.class));
        overridePendingTransition(R.anim.fade_from_left, 0);
    }

}
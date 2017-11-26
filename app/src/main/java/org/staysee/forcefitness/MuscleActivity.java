package org.staysee.forcefitness;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MuscleActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

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

    public void onClick(View v) throws ClassNotFoundException {
        switch(v.getId())
        {
            case R.id.button0:
                Button b = (Button)v;

                String buttonText = b.getText().toString();
                Intent myIntent = new Intent(this, WorkoutActivity.class);
                myIntent.putExtra("EXTRA_MUSCLE_GROUP", buttonText);
                this.startActivity(myIntent);
                overridePendingTransition(R.anim.fade_from_left, 0);
                break;
        }
        /*
        Button b = (Button)v;
        String buttonText = b.getText().toString() + "Activity";
        Log.d("CREATION", buttonText);
        toWorkoutActivity(v, buttonText);
        */
    }
    /*
    public void toWorkoutActivity(View v, String b) throws ClassNotFoundException{
        Intent myIntent = new Intent(this, Class.forName(b));
        this.startActivity(myIntent);
        overridePendingTransition(R.anim.fade_from_left, 0);
    }

    public void toWorkoutActivity(View v) {
        Intent myIntent = new Intent(this, WorkoutActivity.class);
        this.startActivity(myIntent);
        overridePendingTransition(R.anim.fade_from_left, 0);
    }*/
}
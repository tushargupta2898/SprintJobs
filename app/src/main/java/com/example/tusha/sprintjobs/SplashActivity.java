package com.example.tusha.sprintjobs;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

private TextView name;
private ImageView icon;
    private static int SPLASH_TIME = 3000; //This is 4 seconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        name = (TextView) findViewById(R.id.textsprintjob);
        icon = (ImageView) findViewById(R.id.icon);
        Animation iconanim = AnimationUtils.loadAnimation(this,R.anim.icontransition);
        name.startAnimation(iconanim);
        icon.startAnimation(iconanim);

        //Code to start timer and take action after the timer ends
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do any action here. Now we are moving to next page
                Intent mySuperIntent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(mySuperIntent);
                /* This 'finish()' is for exiting the app when back button pressed
                 *  from Home page which is ActivityHome
                 */
                finish();
            }
        }, SPLASH_TIME);
    }
}
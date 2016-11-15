package linkedlistview.sample.github.linkedlistview.view;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import linkedlistview.sample.github.linkedlistview.R;

public class ActivitySplash extends AppCompatActivity {

    private static final long DELAY_SPLASH_SCREEN = 1200L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /**
         * This just to avoid white screen, during loading and
         * saving state for permission checking.
         */
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), ActivityMainPlay.class));
            }
        }, DELAY_SPLASH_SCREEN);
    }
}

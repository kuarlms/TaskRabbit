package killbit.taskrabbit.actvity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by kural mughil selvam on 08-10-2017.
 */

public class Launcher extends AppCompatActivity {


    private static final int SPLASH_TIME_OUT = 100;
    public static Context contextOfApplication;
    Intent i ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        contextOfApplication = getApplicationContext();
        i = new Intent(Launcher.this,MainActivity.class);
        Intent2Activity();

    }
    private void Intent2Activity() {

        new Handler().postDelayed(new Runnable() {
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                startActivity(i);
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }


    public static Context getContextOfApplication(){
        return contextOfApplication;
    }
}

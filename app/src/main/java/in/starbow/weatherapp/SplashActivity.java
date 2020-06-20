package in.starbow.weatherapp;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private Handler handler;
    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashs);

        mRunnable = new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));

            }
        };
        handler=new Handler();
        handler.postDelayed(mRunnable,2000);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        if(handler!=null&&mRunnable!=null)
            handler.removeCallbacks(mRunnable);

    }
}

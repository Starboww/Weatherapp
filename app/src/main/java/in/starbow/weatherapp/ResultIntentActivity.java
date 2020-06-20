package in.starbow.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultIntentActivity extends AppCompatActivity {
    TextView avg_temp,pressur,humidit,spee,rai,cit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_intent);
        avg_temp= findViewById(R.id.avg_temp);
        pressur= findViewById(R.id.pressure);
        humidit=findViewById(R.id.humidity);
        spee = findViewById(R.id.speed);
         rai=findViewById(R.id.rain);
        cit = findViewById(R.id.city);

        String avg_temprature = getIntent().getStringExtra("avg_temp");
        String pressure = getIntent().getStringExtra("pressure");
        String humidity = getIntent().getStringExtra("humidity");
        String speed = getIntent().getStringExtra("speed");
        String rain = getIntent().getStringExtra("rain");
        String city = getIntent().getStringExtra("city_name");

        avg_temp.setText("Avg temp.\n\n"+avg_temprature+"°C");
        pressur.setText("Pressure: \n\n"+pressure+" mb");
        humidit.setText("Humidity:\n\n "+humidity+"%");
        spee.setText("Wind Speed:\n\n"+speed+" km/h");
      //  rai.setText("Rain"+rain+"mm");
        cit.setText(city.toUpperCase());

    }
    public void front_intent(View view)
    {
        Intent intent = new Intent(ResultIntentActivity.this,MainActivity.class);
        startActivity(intent);
    }

}
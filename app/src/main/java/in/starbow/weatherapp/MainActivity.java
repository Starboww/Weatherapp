package in.starbow.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.Sampler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDateTime;

public class MainActivity extends AppCompatActivity {
      ImageButton go_button;
      Button result_btn;
      SearchView city;
      TextView result,maxi_temp,mini_temp;

   public String avg_temp;
   public String pressure;
   public String humidity;
   public String speed;
 //  public String rain;
      String baseURL ="http://api.openweathermap.org/data/2.5/weather?q=";
      String API ="&units=metric&appid=ee88badafe062e5bcdc52042aeebae15";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        go_button = findViewById(R.id.go_btn);
        city = findViewById(R.id.getCity);
        result = findViewById(R.id.result);
        maxi_temp = findViewById(R.id.max_temp);
        mini_temp = findViewById(R.id.min_temp);
        result_btn= findViewById(R.id.button);
       // LocalDateTime now = LocalDateTime.now();
        go_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           String myURL= baseURL+city.getQuery().toString()+API;

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, myURL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        String info = null;
                        String temp = null;
                        String windflow = null;
                        String rainfall = null;

                        try {
                            info = jsonObject.getString("weather");
                            temp = jsonObject.getString("main");
                            windflow =jsonObject.getString("wind");
                           // rainfall =jsonObject.getString("rain");
                            Log.i("INFO", "INFO: " + info);
                            Log.i("INFO", "temp " + temp);
                            Log.i("INFO", "windflow " + windflow);
                           // Log.i("INFO", "rainfall " + rainfall);

                            JSONObject jsonObject1 = new JSONObject(temp);
                            JSONArray jsonArray = new JSONArray(info);
                            JSONObject jsonObject2 = new JSONObject(windflow);
                           // JSONObject jsonObject3 = new JSONObject(rainfall);

                           for(int i=0;i<jsonArray.length();i++)
                           {
                             JSONObject perObj = jsonArray.getJSONObject(i);
                             String myWeather = perObj.getString("description");
                              result.setText(myWeather);
                           }

                          String max_temp = jsonObject1.getString("temp_max");
                           String min_temp = jsonObject1.getString("temp_min");
                           maxi_temp.setText(max_temp+"°C");
                           mini_temp.setText(min_temp+"°C");

                           ///////////////////////////////////for intent

                           avg_temp=jsonObject1.getString("temp");
                           pressure = jsonObject1.getString("pressure");
                           humidity = jsonObject1.getString("humidity");
                           speed = jsonObject2.getString("speed");
                         //  rain = jsonObject3.getString("1h");

                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i("Error", "Something went wrong" + error);
                    }
                }

                );
                 VolleySingleton.getInstance(getApplicationContext()).addTORequestQue(jsonObjectRequest);

            }
        });

        result_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(city.getQuery().equals(""))
                {
                    Toast.makeText(MainActivity.this,"Enter Something",Toast.LENGTH_SHORT).show();

                }
                else
                {

                    Intent intent = new Intent(MainActivity.this,ResultIntentActivity.class);
                      Bundle bundle = new Bundle();
                      bundle.putString("city_name",city.getQuery().toString());
                      bundle.putString("avg_temp",avg_temp);
                      bundle.putString("pressure",pressure);
                      bundle.putString("humidity",humidity);
                      bundle.putString("speed",speed);
                    // bundle.putString("rain",rain);

                      intent.putExtras(bundle);
                       startActivity(intent);

                }

            }
        });

    }


}


//todo : max and min temp
//todo : background change

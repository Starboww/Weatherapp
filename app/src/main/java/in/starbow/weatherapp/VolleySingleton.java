package in.starbow.weatherapp;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    private static VolleySingleton mInstance;
    private RequestQueue mRequestQueue;
    private  static  Context mcontext;
    public VolleySingleton(Context context) {
       mcontext = context;
       mRequestQueue =getRequestQueue();
    }
    public  static synchronized VolleySingleton getInstance(Context context){
        if(mInstance==null){
            mInstance=new VolleySingleton(context);
        }

        return mInstance;
    }
    public RequestQueue getRequestQueue(){
        if(mRequestQueue==null)
        {
            mRequestQueue= Volley.newRequestQueue(mcontext.getApplicationContext());
        }

        return mRequestQueue;
    }
    public void addTORequestQue(Request request){
        mRequestQueue.add(request);
    }
}

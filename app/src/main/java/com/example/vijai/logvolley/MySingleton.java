package com.example.vijai.logvolley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by vijai on 10/11/16.
 */

public class MySingleton {
    private static MySingleton mInstancee;
    private RequestQueue requestQueue;
    private static Context mctx;

    public RequestQueue getRequestQueue (){

        if(requestQueue==null){

            requestQueue = Volley.newRequestQueue(mctx.getApplicationContext()) ;

        }
        return requestQueue;
    }
    private MySingleton(Context ctx){
        mctx=ctx;
        requestQueue=getRequestQueue();
    }
    public static synchronized MySingleton getInstance(Context ctx ){

        if(mInstancee == null )
            mInstancee = new MySingleton(ctx);
        return mInstancee ;

    }
    public <T>void addToRequestQueue(Request<T> request ){

        requestQueue.add(request);

    }
}

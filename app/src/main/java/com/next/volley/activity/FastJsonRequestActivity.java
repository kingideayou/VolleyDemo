package com.next.volley.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.next.volley.R;
import com.next.volley.model.MessageModel;
import com.next.volley.model.WeatherModel;
import com.next.volley.support.FastJsonRequest;
import com.next.volley.support.MySingleton;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class FastJsonRequestActivity extends ActionBarActivity {

    @InjectView(R.id.text)
    TextView text;

    String url = "http://www.weather.com.cn/adat/sk/101010100.html";
    String urlPost = "http://askcar.api.gongsh.com/answer/get";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_json_request);
        ButterKnife.inject(this);
//        initFastJsonRequest();
        initFastJsonRequestPost();
    }

    private void initFastJsonRequestPost(){
        Map<String, String> params = new HashMap<>();
        params.put("answer_id", "105");
        FastJsonRequest fastJsonRequestPost = new FastJsonRequest<>(
                urlPost, MessageModel.class, null, params,
                new Response.Listener<MessageModel>() {
                    @Override
                    public void onResponse(MessageModel messageModel) {
                        Log.i("FastJsonPostResponse", messageModel.toString());
                        text.setText(messageModel.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        MySingleton.getInstance(this).addToRequestQueue(fastJsonRequestPost);
    }

    private void initFastJsonRequest() {
        FastJsonRequest fastJsonRequest = new FastJsonRequest<>(
                url, WeatherModel.class, null,
                new Response.Listener<WeatherModel>() {
                    @Override
                    public void onResponse(WeatherModel weatherModel) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                text.setText("出错了");
            }
        });
        MySingleton.getInstance(this).getRequestQueue().add(fastJsonRequest);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_fast_json_request, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

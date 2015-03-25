package com.next.volley.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.StringRequest;
import com.next.volley.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by NeXT on 15-3-25.
 */
public class RequestQueueSetUpDemo extends ActionBarActivity{

    @InjectView(R.id.tv_result)
    TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_requestqueue);
        ButterKnife.inject(this);

        setUpRequestQueue();
    }

    private void buildForAllVersion(){
        //1. 创建兼容各个版本的 HttpClient
        HttpStack stack = null;
        //如果设备运行在 9（Gingerbread） 或以下版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD){
            //使用 HttpURLConnection
        } else {
            //使用 AndroidHttpClient
        }
        Network network = new BasicNetwork(stack);
    }

    private void setUpRequestQueue(){
        RequestQueue requestQueue;
        //初始化缓存
        Cache cache = new DiskBasedCache(getCacheDir(), 1024 * 1024);
        //初始化 HttpURLConnection 网络请求，兼容 SDK 9 及以上版本
        Network network = new BasicNetwork(new HurlStack());
        //创建含网络请求和缓存的 RequestQueue 实例
        requestQueue = new RequestQueue(cache, network);
        //启动 requestQueue
        requestQueue.start();

        String url = "http://www.weibo.com";
        //设置请求和处理结果
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        tv_result.setText(s);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                tv_result.setText(volleyError.getMessage());
            }
        });
        requestQueue.add(stringRequest);
    }

}

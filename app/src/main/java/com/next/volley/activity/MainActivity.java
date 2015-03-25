package com.next.volley.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.next.volley.R;

import butterknife.ButterKnife;
import butterknife.InjectView;


/**
 * RequestQueue 可以管理多线程的网络操作，以及读写缓存，解析服务端返回的数据。
 */
public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.tv_result)
    TextView tv_result;
    @InjectView(R.id.button)
    Button button;
    @InjectView(R.id.button_single)
    Button button_single;

    public static final String TAG = "MyTag";

    RequestQueue queue;
    StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initVolley();
    }

    private void initVolley(){
        //实例化 RequestQueue
        queue = Volley.newRequestQueue(this);
        String url = "http://www.baidu.com";
        //像指定的 URL 发起请求
        stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //显示前 500 个字符
                tv_result.setText("Response is : " + s.substring(0, 50));
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                tv_result.setText("That didn't work!");
            }
        });
        //给 StringRequest 设置标签，可以在 onStop() 方法中取消任务
        stringRequest.setTag(TAG);
        //将 StringRequest 添加到 RequestQueue 中
        queue.add(stringRequest);
    }

    private void initView(){
        ButterKnife.inject(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RequestQueueSetUpDemo.class);
                startActivity(intent);
            }
        });
        button_single.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SingleInstanceActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    @Override
    protected void onStop() {
        super.onStop();
        if (queue != null) {
            queue.cancelAll(TAG);
        }
    }
}

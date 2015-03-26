package com.next.volley.activity;

import android.graphics.Bitmap;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.next.volley.support.MySingleton;
import com.next.volley.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class StandardRequestActivity extends ActionBarActivity {

    /**
     * 统一的请求类型
     *
     * StringRequest 指定一个 URL ，请求后返回 String 类型数据
     * ImageRequest 从指定 URL 获取图片
     * JsonObjectRequest 和 JsonArrayRequest 从指定 URL 获取 JSON 数据
     *
     * @param savedInstanceState
     */


    @InjectView(R.id.image)
    ImageView imageView;
    @InjectView(R.id.image_net)
    NetworkImageView image_net;

    String url = "http://b.hiphotos.baidu.com/baike/c0%3Dbaike80%2C5%2C5%2C80%2C26/" +
            "sign=3b03c837572c11dfcadcb771024e09b5/ae51f3deb48f8c54cd34cafb3a292df5e1fe7f7a.jpg";
    String url2 = "http://b.hiphotos.baidu.com/image/pic/item" +
            "/4ec2d5628535e5dd066035d674c6a7efce1b626f.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_standard_request);
        ButterKnife.inject(this);
//        initImageResponse();
        initImageLoaderForImageView();
        initImageLoaderForNetworkImageView();
    }

    private void initImageLoaderForNetworkImageView() {
        ImageLoader imageLoader = MySingleton.getInstance(this).getImageLoader();
        //设置图片加载路径和空股指显示图片 ImageLoader
        image_net.setImageUrl(url2, imageLoader);
    }

    //从指定的 URL 获取图片并显示
    private void initImageResponse(){
        //第二个参数为 MaxWidth，第三个参数为 MaxHeight，第四个参数为图片选项
        ImageRequest request = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                imageView.setImageBitmap(bitmap);
            }
        }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                imageView.setImageResource(R.mipmap.ic_launcher);
            }
        });
        MySingleton.getInstance(this).addToRequestQueue(request);
    }

    private void initImageLoaderForImageView(){
        ImageLoader imageLoader;
        imageLoader = MySingleton.getInstance(this).getImageLoader();
        imageLoader.get(url, ImageLoader.getImageListener(imageView,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_standard_request, menu);
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

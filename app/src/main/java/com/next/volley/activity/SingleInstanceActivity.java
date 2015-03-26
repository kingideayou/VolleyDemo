package com.next.volley.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.next.volley.support.MySingleton;
import com.next.volley.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SingleInstanceActivity extends ActionBarActivity {

    @InjectView(R.id.image)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_instance);
        ButterKnife.inject(this);

        String imageUrl = "http://f.hiphotos.baidu.com/baike/c0%3Dbaike180%2C5%2C5%2C180%2C60/" +
                "sign=79273b583e292df583cea447dd583705/d0c8a786c9177f3e8ac0027a74cf3bc79e3d56d5.jpg";
        ImageLoader imageLoader = MySingleton.getInstance(
                this.getApplicationContext()).getImageLoader();
        imageLoader.get(imageUrl,
                new ImageLoader.ImageListener() {
                    @Override
                    public void onResponse(ImageLoader.ImageContainer imageContainer, boolean b) {
                        imageView.setImageBitmap(imageContainer.getBitmap());
                    }
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        imageView.setImageResource(R.mipmap.ic_launcher);
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_single_instance, menu);
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

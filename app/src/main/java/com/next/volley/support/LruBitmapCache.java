package com.next.volley.support;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * Created by NeXT on 15-3-25.
 */
public class LruBitmapCache extends LruCache<String, Bitmap> implements ImageLoader.ImageCache{

    /**
     * Volley 提供一个基于 DiskBasedCache 磁盘缓存 ，如果使用 ImageLoader
     * 则需要提供 LRU 进行内存缓存
     *
     * 初始化使用 LruCache 的 ImageLoader
     * RequestQueue mRequestQueue; // assume this exists.
     * ImageLoader mImageLoader = new ImageLoader(mRequestQueue, new LruBitmapCache(
     *     LruBitmapCache.getCacheSize()));
     */

    public LruBitmapCache(int maxSize) {
        super(maxSize);
    }

    public LruBitmapCache(Context context){
        this(getCacheSize(context));
    }

    @Override
    protected int sizeOf(String key, Bitmap value) {
        return value.getRowBytes() * value.getHeight();
    }

    @Override
    public Bitmap getBitmap(String url) {
        return get(url);
    }

    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        put(url, bitmap);
    }

    //返回三个屏幕大小的图片缓存
    public static int getCacheSize(Context context) {
        final DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        final int screenWidth = displayMetrics.widthPixels;
        final int screenHeight = displayMetrics.heightPixels;
        //每个像素四个字节
        final int screenBytes = screenWidth * screenHeight * 4;
        return screenBytes * 3;
    }

}

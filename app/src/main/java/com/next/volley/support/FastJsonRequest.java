package com.next.volley.support;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by NeXT on 15-3-25.
 */
public class FastJsonRequest<T> extends Request<T> {

    private JSON json = new JSONObject();
    private final Class<T> clazz;
    private final Map<String, String> headers;
    private Map<String, String> params;
    private final Response.Listener<T> listener;


    public FastJsonRequest(String url, Class<T> clazz, Map<String, String> headers,
                       Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.GET, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
        try {
            this.params = super.getParams();
        } catch (AuthFailureError authFailureError) {
            authFailureError.printStackTrace();
        }
    }

    public FastJsonRequest(String url, Class<T> clazz, Map<String, String> headers,
                           Map<String, String> params, Response.Listener<T> listener,
                           Response.ErrorListener errorListener) {
        super(Method.POST, url, errorListener);
        this.clazz = clazz;
        this.headers = headers;
        this.listener = listener;
        this.params = params;
    }


    @Override
    public Map<String, String> getHeaders() {
        try {
            return headers != null ? headers : super.getHeaders();
        } catch (AuthFailureError authFailureError) {
            authFailureError.printStackTrace();
            return null;
        }
    }

    @Override
    protected Map<String, String> getParams() {
        try {
            return params != null ? params : super.getParams();
        } catch (AuthFailureError authFailureError) {
            authFailureError.printStackTrace();
            return null;
        }
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse networkResponse) {
        try {
            String jsonStr = new String(networkResponse.data,
                    HttpHeaderParser.parseCharset(networkResponse.headers));
            Log.i("FastJsonRequest", "获取的返回数据："+jsonStr);
            /**
             * {"weatherinfo":{"city":"北京","cityid":"101010100","temp":"9","WD":"西南风","WS":"2级",
             *      "SD":"22%","WSE":"2","time":"10:45","isRadar":"1","Radar":"JC_RADAR_AZ9010_JB",
             *      "njd":"暂无实况","qy":"1014"}}
             */
            return Response.success(
                    json.parseObject(jsonStr, clazz),
                    HttpHeaderParser.parseCacheHeaders(networkResponse));
        } catch (UnsupportedEncodingException e){
            return Response.error(new ParseError(e));
        } catch(JSONException e){
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        listener.onResponse(response);
    }
}

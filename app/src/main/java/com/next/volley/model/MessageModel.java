package com.next.volley.model;

/**
 * Created by NeXT on 15-1-22.
 */
public class MessageModel {

    private int         user_id;
    private String      token;
    private boolean     result;
    private String      msg;
    private String      data;
    private String      server;
    private String      category_version;
    private String      media_id;
    private String      url;
    private String      homepage_expert;

    public String getHomepage_expert() {
        return homepage_expert;
    }
    public void setHomepage_expert(String homepage_expert) {
        this.homepage_expert = homepage_expert;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMedia_id() {
        return media_id;
    }

    public void setMedia_id(String media_id) {
        this.media_id = media_id;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getCategory_version() {
        return category_version;
    }

    public void setCategory_version(String category_version) {
        this.category_version = category_version;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean getResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "user_id=" + user_id +
                ", token='" + token + '\'' +
                ", result=" + result +
                ", msg='" + msg + '\'' +
                ", data='" + data + '\'' +
                ", server='" + server + '\'' +
                ", category_version='" + category_version + '\'' +
                ", media_id='" + media_id + '\'' +
                ", url='" + url + '\'' +
                ", homepage_expert='" + homepage_expert +
                '}';
    }
}

package com.szxb.base;

import com.szxb.http.CallServer;
import com.szxb.http.HttpListener;
import com.szxb.http.RSAUtil;
import com.szxb.util.Config;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.CacheMode;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.Response;

import org.json.JSONObject;

/**
 * 作者：Tangren on 2017/6/9 13:16
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */
public abstract class BasePresenter {

    protected RequestModel model;

    public enum RequestModel {
        FIRST, ReFRESH
    }

    public void requestData(String entity, RequestModel model, String url) {
        if (null == entity)
            throw new IllegalArgumentException("no enity");
        this.model = model;
        String es = RSAUtil.encryptString(entity);
        Request<JSONObject> request = NoHttp.createJsonObjectRequest(url, RequestMethod.POST);
        request.setCacheMode(CacheMode.ONLY_REQUEST_NETWORK);
        request.setHeader("mid", Config.mid);
        request.setDefineRequestBodyForJson(es);
        CallServer.getHttpclient().add(1, request, new HttpListener<JSONObject>() {
            @Override
            public void success(int what, Response<JSONObject> response) {
                if (response.get() != null) {
                    onAllSuccess(response.get());
                } else onFail();
            }

            @Override
            public void fail(int what, Response<JSONObject> response) {
                onFail();
            }
        });
    }

    protected void onAllSuccess(JSONObject result) {
    }

    protected abstract void onFail();

}

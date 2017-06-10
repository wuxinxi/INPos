package com.szxb.util;

import com.szxb.entity.MainSweepEntity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：Tangren on 2017/6/9 14:38
 * 邮箱：wu_tangren@163.com
 * TODO:请求参数
 */
public class RequestParameter {

    //主扫
    public static String getMainSweepParameter(MainSweepEntity entity) {
        if (null == entity)
            throw new IllegalArgumentException("no entity");
        try {
            JSONObject object = new JSONObject();
            object.put("mid",Config.mid);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}

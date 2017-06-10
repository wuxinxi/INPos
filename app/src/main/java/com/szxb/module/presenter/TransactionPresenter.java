package com.szxb.module.presenter;

import com.szxb.base.BasePresenter;
import com.szxb.module.transaction.TransactionActivity;
import com.szxb.util.Util;
import com.yanzhenjie.nohttp.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;

/**
 * 作者：Tangren on 2017/6/9 14:08
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */
public class TransactionPresenter extends BasePresenter {

    private WeakReference<TransactionActivity> weakReference;

    public TransactionPresenter(TransactionActivity activity) {
        weakReference = new WeakReference<TransactionActivity>(activity);
    }

    @Override
    protected void onAllSuccess(JSONObject result) {
        Logger.d(result.toString());
        try {
            String code = result.getString("code");
            if (Util.isNumber(code)) {
                if (Integer.valueOf(code) == 0) {

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onFail() {

    }
}

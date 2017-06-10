package com.szxb.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.szxb.loading.VaryViewHelperController;
import com.szxb.module.view.BaseView;

/**
 * 作者：Tangren on 2017/6/9 13:15
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */
public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements BaseView {

    protected T mPresenter;

    protected T getChildPresenter() {
        return null;
    }

    protected String getRequestParams() {
        return null;
    }

    protected VaryViewHelperController mVaryViewHelperController;


    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (null != getLoadingTargetView())
            mVaryViewHelperController = new VaryViewHelperController(getLoadingTargetView());
        if (null != getChildPresenter()) {
            mPresenter = getChildPresenter();
        }
        super.onCreate(savedInstanceState);
    }

    @Override
    public void showEmptyView(String msg) {

    }

    @Override
    public void showNetError() {

    }


    @Override
    public void refreshView() {
        if (mVaryViewHelperController == null) {
            throw new IllegalStateException("no ViewHelperController");
        }
        mVaryViewHelperController.restore();
    }


}

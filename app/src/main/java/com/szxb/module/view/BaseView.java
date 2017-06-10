package com.szxb.module.view;

import java.util.List;

/**
 * 作者：Tangren on 2017/6/9 13:10
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */
public interface BaseView {

    void onSuccess();

    void onFail();

    void showNetError();

    void showEmptyView(String msg);

    void showToastError();

    void showRefreshFinish(List score);

    void refreshView();
}

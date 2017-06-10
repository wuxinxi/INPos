package com.szxb.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 作者：Tangren on 2017/6/9 18:07
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */
public abstract class BaseFragment extends SupportFragment {


    protected static final String ARG_MENUS = "arg_menus";

    protected static final String ARG_TYPE = "arg_type";

    private View mRootView;

    protected abstract void initData();

    protected abstract int layoutID();

    private Unbinder unbind;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == mRootView) {
            mRootView = inflater.inflate(layoutID(), container, false);
            unbind = ButterKnife.bind(this, mRootView);
            initView();
            initData();
        } else {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (null != parent) {
                parent.removeView(mRootView);
            }
        }
        return mRootView;
    }

    protected void initView() {
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        unbind.unbind();
    }
}

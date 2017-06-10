package com.szxb.module.setting;

import android.support.v4.app.FragmentTransaction;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.szxb.R;
import com.szxb.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：Tangren on 2017/6/9 17:50
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */
public class Setting extends BaseActivity {

    @BindView(R.id.toolBarTitle)
    TextView toolBarTitle;
    @BindView(R.id.home)
    Button home;
    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;

    @Override
    protected int layoutID() {
        return R.layout.activity_setting;
    }

    @Override
    protected void initView() {
        toolBarTitle.setText("设置");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frameLayout, new SettingFragment()).commit();
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.home)
    public void onViewClicked() {
        finishActivityFromRight();
    }
}

package com.szxb.module.setting;

import android.os.Bundle;
import android.widget.Toast;

import com.szxb.R;
import com.szxb.base.BaseFragment;

/**
 * 作者：Tangren on 2017/6/9 18:06
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */
public class ContentFragment extends BaseFragment {


    private int postion;

    public static ContentFragment newInstance(int type) {

        Bundle args = new Bundle();
        args.putInt(ARG_TYPE, type);
        ContentFragment fragment = new ContentFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        Bundle args = getArguments();
        if (args != null) {
            postion = args.getInt(ARG_TYPE, 0);
        }
    }

    @Override
    protected void initData() {
        switch (postion) {
            case 0:
                Toast.makeText(getContext(), "postion:" + postion, Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(getContext(), "postion:" + postion, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }

    @Override
    protected int layoutID() {
        return R.layout.fragment_content;
    }


}

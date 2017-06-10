package com.szxb.module.setting;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.szxb.R;
import com.szxb.base.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * 作者：Tangren on 2017/6/9 19:50
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */
public class SettingFragment extends BaseFragment {


    @BindView(R.id.menuList)
    FrameLayout menuList;
    @BindView(R.id.content)
    FrameLayout content;

    public static SettingFragment newInstance() {
        Bundle args = new Bundle();
        SettingFragment fragment = new SettingFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initData() {
        ArrayList<String> listMenus = new ArrayList<>();
        listMenus.add("基础设置");
        listMenus.add("支付设置");
        MenuFragment menuListFragment = MenuFragment.newInstance(listMenus);
        loadRootFragment(R.id.menuList, menuListFragment);
        replaceLoadRootFragment(R.id.content, ContentFragment.newInstance(0), false);
    }

    @Override
    protected int layoutID() {
        return R.layout.fragment_setting;
    }

    public void switchContentFragment(ContentFragment fragment) {
        SupportFragment contentFragment = findChildFragment(ContentFragment.class);
        if (contentFragment != null) {
            contentFragment.replaceFragment(fragment, false);
        }
    }
}

package com.szxb.module.setting;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.szxb.R;
import com.szxb.adapter.MenuListAdapter;
import com.szxb.base.BaseFragment;
import com.szxb.inter.OnItemClick;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 作者：Tangren on 2017/6/9 18:06
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */
public class MenuFragment extends BaseFragment implements OnItemClick {

    @BindView(R.id.menuRecyclerView)
    RecyclerView menuRecyclerView;
    private ArrayList<String> mMenus;

    private MenuListAdapter menuAdapter;

    private int currentPostion = -1;

    public static MenuFragment newInstance(ArrayList<String> menus) {
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_MENUS, menus);
        MenuFragment fragment = new MenuFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected void initView() {
        Bundle args = getArguments();
        if (args != null) {
            mMenus = args.getStringArrayList(ARG_MENUS);
        }

    }

    @Override
    protected void initData() {
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        menuRecyclerView.setLayoutManager(manager);
        menuAdapter = new MenuListAdapter(mMenus, R.layout.view_item_menu);
        menuRecyclerView.setAdapter(menuAdapter);
        menuAdapter.setItemChecked(0);
        menuAdapter.setItemClick(this);
    }


    @Override
    protected int layoutID() {
        return R.layout.fragment_menulist;
    }


    @Override
    public void onItemClick(View view, int postion) {
        showContent(postion);
    }

    private void showContent(int position) {
        if (currentPostion == position) return;
        currentPostion = position;
        menuAdapter.setItemChecked(position);
        ContentFragment fragment = ContentFragment.newInstance(position);
        ((SettingFragment) getParentFragment()).switchContentFragment(fragment);
    }
}

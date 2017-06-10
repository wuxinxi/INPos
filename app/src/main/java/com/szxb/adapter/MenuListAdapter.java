package com.szxb.adapter;

import com.szxb.R;
import com.szxb.base.BaseAdapter;
import com.szxb.base.BaseViewHolder;

import java.util.List;

/**
 * 作者：Tangren on 2017/6/9 18:28
 * 邮箱：wu_tangren@163.com
 * TODO:menuListAdapter
 */
public class MenuListAdapter extends BaseAdapter<String> {

    private List<String> menuList;

    public MenuListAdapter(List datas, int layoutID) {
        super(datas, layoutID);
        this.menuList = datas;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.setText(R.id.menuName, menuList.get(position));
    }
}

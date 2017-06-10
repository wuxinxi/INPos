package com.szxb.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.szxb.R;
import com.szxb.entity.HomeData;
import com.szxb.holder.HomeHolder;
import com.szxb.inter.OnItemClick;
import com.szxb.inter.OnItemLongClick;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Tangren on 2017/6/8 16:22
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */
public class HomeAdapter extends RecyclerView.Adapter<HomeHolder> {

    private List<HomeData> datas = new ArrayList<>();

    private LayoutInflater mInfalter;

    private OnItemClick click;
    private OnItemLongClick longClick;


    public HomeAdapter(List<HomeData> datas) {
        this.datas = datas;
    }

    public void addDatas(List<HomeData> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public HomeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_item_home, parent, false);
        return new HomeHolder(view, click, longClick);
    }

    @Override
    public void onBindViewHolder(HomeHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public void setItemClick(OnItemClick click) {
        this.click = click;
    }

    public void setItemLongClick(OnItemLongClick longClick) {
        this.longClick = longClick;
    }
}

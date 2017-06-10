package com.szxb.holder;


import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.szxb.R;
import com.szxb.inter.OnItemClick;
import com.szxb.inter.OnItemLongClick;

/**
 * 作者：Tangren on 2017/6/8 16:14
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */
public class HomeHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private OnItemClick clickListener;
    private OnItemLongClick longClickListener;
    public TextView gasNo, gasCapacity, gasMoney, gasMemberMoney, gasPayStatus;

    public HomeHolder(View itemView, OnItemClick clickListener, OnItemLongClick longClickListener) {
        super(itemView);
        this.clickListener = clickListener;
        this.longClickListener = longClickListener;
        gasNo = (TextView) itemView.findViewById(R.id.gasNo);
        gasCapacity = (TextView) itemView.findViewById(R.id.gasCapacity);
        gasMoney = (TextView) itemView.findViewById(R.id.gasMoney);
        gasMemberMoney = (TextView) itemView.findViewById(R.id.gasMemberMoney);
        gasPayStatus = (TextView) itemView.findViewById(R.id.gasPayStatus);

        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (clickListener != null) {
            clickListener.onItemClick(v, getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (longClickListener != null) {
            longClickListener.onLongClick(v, getAdapterPosition());
        }
        return true;
    }
}

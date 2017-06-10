package com.szxb.base;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.szxb.inter.OnItemClick;
import com.szxb.inter.OnItemLongClick;


public class BaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

    private final SparseArray<View> views;

    public View convertView;

    private OnItemClick itemClick;
    private OnItemLongClick itemLongClick;


    public BaseViewHolder(View view, OnItemClick itemClick, OnItemLongClick itemLongClick) {
        super(view);
        this.views = new SparseArray<>();
        this.convertView = view;
        this.itemClick = itemClick;
        this.itemLongClick = itemLongClick;

        convertView.setOnClickListener(this);
        convertView.setOnLongClickListener(this);
    }

    public View getConvertView() {
        return convertView;
    }

    public static BaseViewHolder createViewHolder(ViewGroup parent, int layoutId, OnItemClick
            itemClick, OnItemLongClick itemLongClick) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        BaseViewHolder holder = new BaseViewHolder(itemView, itemClick, itemLongClick);
        return holder;
    }

    public BaseViewHolder setText(int viewId, CharSequence value) {
        TextView view = getView(viewId);
        view.setText(value);
        return this;
    }


    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    @Override
    public void onClick(View v) {
        if (itemClick != null) {
            itemClick.onItemClick(v, getAdapterPosition());
        }
    }

    @Override
    public boolean onLongClick(View v) {
        if (itemLongClick != null) {
            itemLongClick.onLongClick(v, getAdapterPosition());
        }
        return true;
    }
}

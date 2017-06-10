package com.szxb.base;

import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.ViewGroup;

import com.szxb.R;
import com.szxb.inter.OnItemClick;
import com.szxb.inter.OnItemLongClick;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Tangren on 2017/6/8 16:46
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */
public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected List<T> datas = new ArrayList<>();

    protected int layoutID;

    protected OnItemClick itemClick;
    protected OnItemLongClick itemLongClick;

    private SparseBooleanArray mBooleanArray;

    private int mLastCheckedPosition = -1;


    public BaseAdapter(List<T> datas, int layoutID) {
        this.datas = datas;
        this.layoutID = layoutID;
        mBooleanArray = new SparseBooleanArray(datas.size());
    }


    public void addDatas(List<T> datas) {
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder holder = BaseViewHolder.createViewHolder(parent, layoutID, itemClick, itemLongClick);
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        if (!mBooleanArray.get(position)) {
            holder.itemView.setBackgroundResource(R.color.colorWhite);
        } else {
            holder.itemView.setBackgroundResource(R.color.colorSettingNormal);
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public void setItemClick(OnItemClick click) {
        this.itemClick = click;
    }

    public void setItemLongClick(OnItemLongClick longClick) {
        this.itemLongClick = longClick;
    }

    public void setItemChecked(int position) {
        mBooleanArray.put(position, true);

        if (mLastCheckedPosition > -1) {
            mBooleanArray.put(mLastCheckedPosition, false);
            notifyItemChanged(mLastCheckedPosition);
        }
        notifyDataSetChanged();

        mLastCheckedPosition = position;
    }

}

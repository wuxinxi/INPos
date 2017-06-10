package com.szxb.adapter;

import com.szxb.R;
import com.szxb.base.BaseAdapter;
import com.szxb.base.BaseViewHolder;
import com.szxb.entity.HomeData;

import java.util.List;

/**
 * 作者：Tangren on 2017/6/8 16:56
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */
public class HomeListAdapter extends BaseAdapter<HomeData> {

    private List<HomeData> datas;

    public HomeListAdapter(List<HomeData> datas, int layoutID) {
        super(datas, layoutID);
        this.datas = datas;

    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        holder.setText(R.id.gasNo, datas.get(position).getGasNo())
                .setText(R.id.gasCapacity, datas.get(position).getGasCapacity())
                .setText(R.id.gasMemberMoney, datas.get(position).getGasMemberMoney())
                .setText(R.id.gasMoney, datas.get(position).getGasMoney())
                .setText(R.id.gasPayStatus, datas.get(position).getGasPayStatus());
    }
}

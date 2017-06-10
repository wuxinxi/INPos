package com.szxb.module.main;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.szxb.R;
import com.szxb.adapter.HomeListAdapter;
import com.szxb.base.BaseActivity;
import com.szxb.entity.HomeData;
import com.szxb.inter.OnItemClick;
import com.szxb.module.setting.Setting;
import com.szxb.module.transaction.TransactionActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：Tangren on 2017/6/8 11:11
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */
public class HomeActivity extends BaseActivity implements OnItemClick, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.choseGasNo)
    Button choseGasNo;
    @BindView(R.id.queryRecord)
    Button queryRecord;
    @BindView(R.id.setting)
    Button setting;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    SwipeRefreshLayout refresh;
    @BindView(R.id.qrCodePay)
    Button qrCodePay;
    @BindView(R.id.shortCodePay)
    Button shortCodePay;
    @BindView(R.id.refreshLayout)
    LinearLayout refreshLayout;

    private HomeListAdapter adapter;

    private List<HomeData> listData = new ArrayList<>();

    private int mCurrentPosition = -1;

    @Override
    protected int layoutID() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        refresh.setColorSchemeResources(android.R.color.holo_blue_bright, android.R.color.holo_red_light);
    }

    @Override
    protected void initData() {
        for (int i = 0; i < 8; i++) {
            HomeData data = new HomeData();
            data.setNoId(i);
            if (i % 2 == 0)
                data.setGasNo("01");
            else data.setGasNo("02");
            data.setGasCapacity(i + "L");
            data.setGasMoney(i + "0" + i);
            data.setGasMemberMoney(i + "00");
            data.setGasPayStatus("未支付");
            listData.add(data);
        }

        adapter = new HomeListAdapter(listData, R.layout.view_item_home);
        recyclerView.setAdapter(adapter);
        adapter.setItemClick(this);
        refresh.setOnRefreshListener(this);
    }


    @OnClick({R.id.choseGasNo, R.id.queryRecord, R.id.setting, R.id.qrCodePay, R.id.shortCodePay, R.id.refreshLayout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.choseGasNo:
                Toast.makeText(this, "chose", Toast.LENGTH_SHORT).show();
                break;
            case R.id.queryRecord:
                Toast.makeText(this, "recode", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                startActivityFromRight(new Intent(HomeActivity.this, Setting.class));
                break;
            case R.id.qrCodePay:
                startActivityFromRight(new Intent(HomeActivity.this, TransactionActivity.class), RESULT_LIST_CODE);
                break;
            case R.id.shortCodePay:
                break;
            case R.id.refreshLayout:
                break;
        }
    }

    @Override
    public void onItemClick(View view, int postion) {
        if (postion == mCurrentPosition) {
            return;
        }
        mCurrentPosition = postion;
        HomeData data = listData.get(postion);
        adapter.setItemChecked(postion);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        Bundle bundle = data.getBundleExtra(RESULT_LIST_CODE_NAME);
//        switch (requestCode) {
//            case RESULT_LIST_CODE:
//                if (bundle != null) {
//                    Toast.makeText(this, "修改", Toast.LENGTH_SHORT).show();
//                }
//                break;
//            default:
//                break;
//        }
    }

    @Override
    public void onRefresh() {
        refresh.setRefreshing(false);
    }
}

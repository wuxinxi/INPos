package com.szxb.module.transaction;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.szxb.R;
import com.szxb.base.BaseMvpActivity;
import com.szxb.entity.MainSweepEntity;
import com.szxb.module.presenter.TransactionPresenter;
import com.szxb.util.RequestParameter;
import com.szxb.util.Util;
import com.szxb.widget.ImageDialog;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：Tangren on 2017/6/8 18:07
 * 邮箱：wu_tangren@163.com
 * TODO:一句话描述
 */
public class TransactionActivity extends BaseMvpActivity<TransactionPresenter> {
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.gasNo)
    TextView gasNo;
    @BindView(R.id.gasOils)
    TextView gasOils;
    @BindView(R.id.transactionFlow)
    TextView transactionFlow;
    @BindView(R.id.gasCapacity)
    TextView gasCapacity;
    @BindView(R.id.gasPrice)
    TextView gasPrice;
    @BindView(R.id.memberPrice)
    TextView memberPrice;
    @BindView(R.id.gasTotal)
    TextView gasTotal;
    @BindView(R.id.memberTotal)
    TextView memberTotal;
    @BindView(R.id.wechatPay)
    Button wechatPay;
    @BindView(R.id.aliPay)
    Button aliPay;
    @BindView(R.id.toolBarTitle)
    TextView toolBarTitle;
    @BindView(R.id.home)
    Button home;

    private ImageDialog imageDialog;

    @Override
    protected int layoutID() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initView() {
        toolBarTitle.setText("交易明细");
        imageDialog = ImageDialog.getImageDialog();
        imageDialog.getDialog();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected TransactionPresenter getChildPresenter() {
        return new TransactionPresenter(this);
    }


    @Override
    protected String getRequestParams() {
        MainSweepEntity entity = new MainSweepEntity();

        return RequestParameter.getMainSweepParameter(entity);
    }

    @OnClick({R.id.wechatPay, R.id.aliPay, R.id.home})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wechatPay:
//                mPresenter.requestData(getRequestParams(), BasePresenter.RequestModel.FIRST, "");
                imageDialog.show(getSupportFragmentManager(), "img");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                imageDialog.setImage(Util.CreateCode("1111"));
                                imageDialog.hideProgress();
                            }
                        });

                    }
                }).start();
                break;
            case R.id.aliPay:
                imageDialog.show(getSupportFragmentManager(), "img");
                break;
            case R.id.home:
                finishActivityFromRight();
                break;
            default:
                break;
        }
    }


    @Override
    public void onSuccess() {

    }

    @Override
    public void onFail() {

    }

    @Override
    public void showToastError() {

    }

    @Override
    public void showRefreshFinish(List score) {

    }
}

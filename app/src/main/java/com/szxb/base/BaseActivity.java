package com.szxb.base;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import com.szxb.R;
import com.szxb.module.main.HomeActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * 作者：Tangren on 2017/6/8 11:11
 * 邮箱：wu_tangren@163.com
 * TODO:基类Activity
 */
public abstract class BaseActivity extends SupportActivity {

    protected abstract int layoutID();

    protected void initView() {
    }

    protected abstract void initData();

    protected Bitmap bitmap;

    private Unbinder unbinder;

    protected static final String TAG = "BaseActivity";

    protected static final int RESULT_LIST_CODE = 0x11;
    protected static final String RESULT_LIST_CODE_NAME = "CHANGE";
    protected static final String HOME_FLAG = HomeActivity.class.getName();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        super.onCreate(savedInstanceState);
        setContentView(layoutID());
        unbinder = ButterKnife.bind(this);
        initView();
        initData();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT > 19) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (bitmap != null) {
            bitmap.recycle();
            bitmap = null;
            Log.e(TAG, "onDestroy: bitmap已释放");
        }
    }

    protected void startActivityFromRight(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
    }

    protected void startActivityFromRight(Intent intent, int flag) {
        startActivityForResult(intent, flag);
        overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_remain);
    }

    protected void finishActivityFromRight() {
        finish();
        overridePendingTransition(0, R.anim.base_slide_right_out);
    }

}

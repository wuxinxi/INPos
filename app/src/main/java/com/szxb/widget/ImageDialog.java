package com.szxb.widget;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.szxb.PosApplication;
import com.szxb.R;

/**
 * 作者：Tangren on 2017/6/8 11:44
 * 邮箱：wu_tangren@163.com
 * TODO:二维码Dialog
 */
public class ImageDialog extends DialogFragment implements View.OnClickListener, DialogInterface.OnKeyListener {

    private static volatile ImageDialog imageDialog = null;

    private ImageView img;

    private Button button;

    private ProgressBar progressBar;

    public ImageDialog() {
    }

    public static ImageDialog getImageDialog() {
        if (imageDialog == null) {
            synchronized (ImageDialog.class) {
                if (imageDialog == null)
                    imageDialog = new ImageDialog();
            }
        }
        return imageDialog;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(PosApplication.getInstance().getDrawable(R.color.transparent));
        View view = inflater.inflate(R.layout.view_dialog, container, false);
        img = (ImageView) view.findViewById(R.id.img);
        button = (Button) view.findViewById(R.id.dis);
        progressBar = (ProgressBar) view.findViewById(R.id.progress);
        button.setOnClickListener(this);
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().setOnKeyListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        if (getDialog() != null && getDialog().isShowing())
            getDialog().dismiss();
    }


    @Override
    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK)
            return true;
        return false;
    }

    public void setImage(Bitmap bm) {
        if (img == null)
            throw new NullPointerException("img null");
        else
            img.setImageBitmap(bm);
    }

    public void hideProgress() {
        if (progressBar == null)
            throw new NullPointerException("progressBar null");
        else progressBar.setVisibility(View.GONE);
    }
}

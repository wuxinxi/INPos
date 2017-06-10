package com.szxb.module.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.szxb.R;
import com.szxb.inter.OnImageShow;
import com.szxb.util.Util;
import com.szxb.widget.ImageDialog;

public class MainActivity extends AppCompatActivity implements OnImageShow{

    private ImageDialog mDialog;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView) findViewById(R.id.text);

    }

    @Override
    public void setOnShowImage(ImageView img) {
        img.setImageBitmap(Util.CreateCode("333333333333333"));
    }
}

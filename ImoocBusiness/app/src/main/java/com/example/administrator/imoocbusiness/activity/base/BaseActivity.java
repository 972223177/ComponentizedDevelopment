package com.example.administrator.imoocbusiness.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * 这里执行所有activity的公共行为
 */
public class BaseActivity extends AppCompatActivity {

    public String TAG;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG=getComponentName().getShortClassName();
    }
}

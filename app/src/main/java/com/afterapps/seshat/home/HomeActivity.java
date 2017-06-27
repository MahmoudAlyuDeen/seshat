package com.afterapps.seshat.home;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.afterapps.seshat.BaseActivity;
import com.afterapps.seshat.R;

import butterknife.ButterKnife;

public class HomeActivity
        extends BaseActivity<HomeView, HomePresenter>
        implements HomeView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
    }

    @NonNull
    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void displayLoadingState() {

    }

    @Override
    protected void discardData() {

    }
}

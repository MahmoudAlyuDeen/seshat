package com.afterapps.seshat.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.afterapps.seshat.Application;
import com.afterapps.seshat.BaseActivity;
import com.afterapps.seshat.R;
import com.afterapps.seshat.beans.Book;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class HomeActivity
        extends BaseActivity<HomeView, HomePresenter>
        implements HomeView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.home_toolbar)
    Toolbar mHomeToolbar;
    @BindView(R.id.home_app_bar)
    AppBarLayout mHomeAppBar;
    @BindView(R.id.home_books_recycler)
    RecyclerView mHomeBooksRecycler;
    @BindView(R.id.home_swipe_refresh_layout)
    SwipeRefreshLayout mHomeSwipeRefreshLayout;
    @BindView(R.id.home_error_text_view)
    TextView mHomeErrorTextView;

    @NonNull
    @Override
    public HomePresenter createPresenter() {
        return new HomePresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(mHomeToolbar);
        initializeSwipeRefresh();
        showProgress();
        presenter.getBooks();
    }

    private void initializeSwipeRefresh() {
        mHomeSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent));
        mHomeSwipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        showProgress();
        presenter.getBooks();
    }

    @Override
    public void onBooksReady(List<Book> bookList) {
        hideProgress();
        ((Application) getApplication()).setBookList(bookList);
        displayBooks(bookList);
    }

    private void displayBooks(List<Book> bookList) {
        BooksAdapter adapter = new BooksAdapter(this, bookList);
        mHomeBooksRecycler.setAdapter(adapter);
        mHomeBooksRecycler.setVisibility(VISIBLE);
        mHomeErrorTextView.setVisibility(GONE);
    }

    @Override
    public void onBooksFailure() {
        hideProgress();
        mHomeErrorTextView.setVisibility(VISIBLE);
        mHomeBooksRecycler.setVisibility(GONE);
    }

    @Override
    protected void displayLoadingState() {
        mHomeSwipeRefreshLayout.setRefreshing(isLoading);
    }

    @Override
    public void showProgress() {
        super.showProgress();
        mHomeErrorTextView.setVisibility(GONE);
    }

    @Override
    protected void discardData() {
    }
}

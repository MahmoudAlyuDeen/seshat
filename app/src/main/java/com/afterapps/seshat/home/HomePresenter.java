package com.afterapps.seshat.home;

import com.afterapps.seshat.beans.Book;
import com.afterapps.seshat.model.BooksModel;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.List;

/*
 * Created by mahmoudalyudeen on 4/19/17.
 */

class HomePresenter extends MvpBasePresenter<HomeView> implements BooksModel.BooksCallBack {

    private List<Book> mBookList;
    private BooksModel mBooksModel;

    void getBooks() {
        if (mBookList != null) {
            onBooksReady(mBookList);
            return;
        }
        if (mBooksModel == null) {
            mBooksModel = new BooksModel(this);
        }
        mBooksModel.getBooks();
    }

    @Override
    public void onBooksReady(List<Book> bookList) {
        mBookList = bookList;
        if (isViewAttached() && getView() != null) {
            getView().onBooksReady(mBookList);
        }
    }

    @Override
    public void onBooksFailure() {
        if (isViewAttached() && getView() != null) {
            getView().onBooksFailure();
        }
    }
}

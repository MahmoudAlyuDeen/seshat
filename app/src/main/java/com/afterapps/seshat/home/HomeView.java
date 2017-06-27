package com.afterapps.seshat.home;

import com.afterapps.seshat.beans.Book;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.List;

/*
 * Created by mahmoudalyudeen on 4/19/17.
 */

interface HomeView extends MvpView {

    void onBooksReady(List<Book> bookList);

    void onBooksFailure();
}

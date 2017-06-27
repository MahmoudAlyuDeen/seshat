package com.afterapps.seshat.model;

/*
 * Created by mahmoudalyudeen on 6/27/17.
 */

import android.util.Log;

import com.afterapps.seshat.api.BooksService;
import com.afterapps.seshat.api.ServiceGenerator;
import com.afterapps.seshat.beans.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BooksModel implements Callback<List<Book>> {

    private BooksCallBack mBooksCallBack;
    private Call<List<Book>> mBooksCall;

    public interface BooksCallBack {

        void onBooksReady(List<Book> bookList);

        void onBooksFailure();

    }

    public BooksModel(BooksCallBack callBack) {
        mBooksCallBack = callBack;
    }

    public void getBooks() {
        BooksService service = ServiceGenerator.createBooksService(BooksService.class);
        if (mBooksCall != null) {
            mBooksCall.cancel();
        }
        mBooksCall = service.getBooks();
        mBooksCall.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
        if (response.isSuccessful() && mBooksCallBack != null) {
            Log.d("@@@@", "onBooksSuccessfulResponse: ");
            mBooksCallBack.onBooksReady(response.body());
        } else {
            Log.d("@@@@", "onBooksUnsuccessfulResponse: ");
        }
    }

    @Override
    public void onFailure(Call<List<Book>> call, Throwable t) {
        Log.d("@@@@", "onBooksFailure: " + t.getMessage());
        mBooksCallBack.onBooksFailure();
    }
}

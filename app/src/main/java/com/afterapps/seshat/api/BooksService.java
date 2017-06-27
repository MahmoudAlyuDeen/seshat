package com.afterapps.seshat.api;

/*
 * Created by mahmoudalyudeen on 6/27/17.
 */

import com.afterapps.seshat.beans.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface BooksService {

    @GET("TNTest/xyzreader/master/data.json")
    Call<List<Book>> getBooks();

}

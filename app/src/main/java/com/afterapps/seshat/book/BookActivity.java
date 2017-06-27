package com.afterapps.seshat.book;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.afterapps.seshat.Application;
import com.afterapps.seshat.BaseActivity;
import com.afterapps.seshat.Constants;
import com.afterapps.seshat.R;
import com.afterapps.seshat.beans.Book;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookActivity
        extends BaseActivity<BookView, BookPresenter>
        implements BookView {

    @BindView(R.id.book_cover_image_View)
    ImageView mBookCoverImageView;
    @BindView(R.id.book_toolbar)
    Toolbar mBookToolbar;
    @BindView(R.id.book_body_text_view)
    TextView mBookBodyTextView;
    @BindView(R.id.book_fab)
    FloatingActionButton mBookFab;

    private Book mBook;

    @NonNull
    @Override
    public BookPresenter createPresenter() {
        return new BookPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        ButterKnife.bind(this);
        setSupportActionBar(mBookToolbar);
        loadBook();
    }

    private void loadBook() {
        int bookIndex = getIntent().getIntExtra(Constants.BOOK_INDEX_EXTRA, -1);
        if (bookIndex == -1) {
            showLoadingError();
            finish();
        } else {
            final List<Book> bookList = ((Application) getApplication()).getBookList();
            if (bookList == null) {
                showLoadingError();
                finish();
            } else {
                mBook = bookList.get(bookIndex);
                displayBook();
            }
        }
    }

    private void displayBook() {
        setTitle(mBook.getTitle());
        Glide.with(this)
                .load(mBook.getPhoto())
                .centerCrop()
                .dontAnimate()
                .placeholder(R.drawable.placeholder_book)
                .into(mBookCoverImageView);
        mBookBodyTextView.setText(mBook.getBody());
    }

    @Override
    protected void displayLoadingState() {
    }

    @Override
    protected void discardData() {
    }

    @OnClick(R.id.book_fab)
    public void onViewClicked() {
        Intent share = new Intent(android.content.Intent.ACTION_SEND);
        share.setType("text/plain");
        share.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        //adding data to the share intent
        share.putExtra(Intent.EXTRA_TEXT, getString(R.string.format_share_book
                , mBook.getTitle()
                , mBook.getAuthor()));

        startActivity(Intent.createChooser(share
                , getString(R.string.format_share_book_title, mBook.getTitle())));
    }
}

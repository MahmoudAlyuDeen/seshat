package com.afterapps.seshat.home;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.afterapps.seshat.Constants;
import com.afterapps.seshat.R;
import com.afterapps.seshat.beans.Book;
import com.afterapps.seshat.book.BookActivity;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/*
 * Created by mahmoudalyudeen on 6/27/17.
 */

class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> {

    private final List<Book> mBookList;
    private final Context mContext;

    BooksAdapter(Context context, List<Book> bookList) {
        mBookList = bookList;
        mContext = context;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_book, parent, false);
        return new BookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        Book book = mBookList.get(position);
        if (book != null) {
            holder.setBook(book);
        }
    }

    @Override
    public int getItemCount() {
        return mBookList.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.item_book_cover_image_view)
        ImageView mItemBookCoverImageView;
        @BindView(R.id.item_book_title_text_view)
        TextView mItemBookTitleTextView;
        @BindView(R.id.item_book_author_text_view)
        TextView mItemBookAuthorTextView;

        BookViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        void setBook(Book book) {
            Glide.with(mContext)
                    .load(book.getPhoto())
                    .centerCrop()
                    .dontAnimate()
                    .placeholder(R.drawable.placeholder_book)
                    .into(mItemBookCoverImageView);
            mItemBookTitleTextView.setText(book.getTitle());
            mItemBookAuthorTextView.setText(book.getAuthor());
        }

        @Override
        public void onClick(View v) {
            Intent book = new Intent(mContext, BookActivity.class);
            book.putExtra(Constants.BOOK_INDEX_EXTRA, getLayoutPosition());
            mContext.startActivity(book);

        }
    }
}

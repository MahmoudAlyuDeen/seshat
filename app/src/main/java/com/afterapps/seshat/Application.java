package com.afterapps.seshat;

/*
 * Created by mahmoud on 1/9/17.
 */


import com.afterapps.seshat.beans.Book;

import java.util.List;

public class Application extends android.app.Application {

    private List<Book> mBookList;

    public List<Book> getBookList() {
        return mBookList;
    }

    public void setBookList(List<Book> bookList) {
        mBookList = bookList;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //todo: enable un-comment out this before release
//        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
//            @Override
//            public void uncaughtException(Thread thread, Throwable throwable) {
//                Log.e("@@@@@", "uncaughtException: " + throwable.toString());
//                Cat.e(throwable);
//                Intent splash = new Intent(getApplicationContext(), SplashActivity.class);
//                splash.putExtra(SPLASH_CRASHED_FLAG, true);
//                PendingIntent splashPending = PendingIntent.getActivity(getBaseContext(), 0, splash, 0);
//                AlarmManager mgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//                mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, splashPending);
//                System.exit(2);
//            }
//        });
    }
}

package com.example.login;

import android.app.Application;

import com.example.login.db.DaoMaster;
import com.example.login.db.DaoSession;

import org.greenrobot.greendao.database.Database;

public class WAApplication extends Application {

    public static Application mApplicationContext;
    private static DaoSession mDaoSession;


    public static boolean mIsLogin = false;


    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = this;
    }

    public synchronized static DaoSession getDaoSession(){
        if(mDaoSession == null){
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(mApplicationContext, "play.db");
            DaoMaster daoMaster = new DaoMaster(helper.getWritableDatabase());
            mDaoSession = daoMaster.newSession();
        }
        return mDaoSession;
    }
}

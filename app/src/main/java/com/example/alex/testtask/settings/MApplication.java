package com.example.alex.testtask.settings;

import android.app.Application;
import android.content.res.Resources;

public class MApplication extends Application {

    private Resources res;
    //    private TokenModel token;
    private static MApplication instance;

    @Override
    public void onCreate() {
//        FirebaseApp.initializeApp(this);
        instance = this;
//        allClasses = new ArrayList<>();
//        futureClasses = new ArrayList<>();
//        teachers = new ArrayList<>();
//        applicationData = new ApplicationData();
//        userInfo = new UserInfoModel();
        res = getResources();
//        token = new TokenModel();
        super.onCreate();
    }

    public static MApplication getInstance() {
        return instance;
    }

    /**
     * быстрый доступ к ресурсам приложения
     */
    public Resources getMResources() {
        return res;
    }

//    public TokenModel getToken() {
//        return token;
//    }

//    public void setToken(TokenModel token) {
//        this.token = token;
//    }

}

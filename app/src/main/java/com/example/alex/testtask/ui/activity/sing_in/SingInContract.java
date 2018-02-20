package com.example.alex.testtask.ui.activity.sing_in;

import android.content.Context;

/**
 * Created by alex on 20.02.18.
 */

public interface SingInContract {
    interface View {
        Context getContext();
    }

    interface EventListener{
        void register();

        void login(String s);

        void forgotPass();
    }
}

package com.example.alex.testtask.ui.activity.sing_up;

import android.content.Context;

import okhttp3.MultipartBody;

/**
 * Created by alex on 20.02.18.
 */

public interface SingUpContract {
    interface View {
        Context getContext();
    }

    interface EventListener{
        void register(MultipartBody.Part image, String s);
    }
}

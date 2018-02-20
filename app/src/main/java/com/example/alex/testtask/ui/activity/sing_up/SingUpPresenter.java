package com.example.alex.testtask.ui.activity.sing_up;

import android.widget.Toast;

import com.example.alex.testtask.client.Request;
import com.example.alex.testtask.interfaces.ILoadingStatus;
import com.example.alex.testtask.utils.Connection;
import com.example.alex.testtask.utils.StaticValues;

import okhttp3.MultipartBody;

/**
 * Created by alex on 20.02.18.
 */

class SingUpPresenter implements SingUpContract.EventListener {
    SingUpContract.View mView;


    SingUpPresenter(SingUpContract.View view) {
        mView = view;
    }

    @Override
    public void register(MultipartBody.Part image, String s) {
        if (Connection.isNetworkAvailable(mView.getContext())) {
            Request.getInstance().singUpUser(image, s, (ILoadingStatus<String>) mView.getContext());
        } else {
            Toast.makeText(mView.getContext(), StaticValues.INTERNET_ERROR, Toast.LENGTH_SHORT).show();
        }
    }
}

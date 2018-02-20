package com.example.alex.testtask.ui.activity.sing_in;

import android.widget.Toast;

import com.example.alex.testtask.client.Request;
import com.example.alex.testtask.interfaces.ILoadingStatus;
import com.example.alex.testtask.utils.Connection;
import com.example.alex.testtask.utils.StaticValues;

/**
 * Created by alex on 20.02.18.
 */

public class SingInPresenter implements SingInContract.EventListener {
    private SingInContract.View mView;

    SingInPresenter(SingInContract.View view) {
        mView = view;
    }

    @Override
    public void register() {

    }

    @Override
    public void login(String s) {
        if (Connection.isNetworkAvailable(mView.getContext())) {
            Request.getInstance().singInUser(s, (ILoadingStatus<String>) mView.getContext());
        } else {
            Toast.makeText(mView.getContext(), StaticValues.INTERNET_ERROR, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void forgotPass() {
        Toast.makeText(mView.getContext(), "Переход на активити с восстановлением пароля", Toast.LENGTH_SHORT).show();
    }
}

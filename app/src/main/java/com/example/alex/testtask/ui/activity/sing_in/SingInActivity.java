package com.example.alex.testtask.ui.activity.sing_in;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alex.testtask.R;
import com.example.alex.testtask.databinding.ActivitySingInBinding;
import com.example.alex.testtask.interfaces.ILoadingStatus;
import com.example.alex.testtask.models.SingInModel;
import com.example.alex.testtask.ui.activity.profile.ProfileActivity;
import com.example.alex.testtask.ui.activity.sing_up.SingUpActivity;
import com.example.alex.testtask.utils.CheckerInputData;

import org.json.JSONException;

import java.util.Arrays;
import java.util.List;

public class SingInActivity extends AppCompatActivity implements ILoadingStatus<String>, SingInContract.View {

    private ActivitySingInBinding mBinding;
    private SingInPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sing_in);

        mPresenter = new SingInPresenter(this);

        lauchApp();

        initListeners();

    }

    private void lauchApp() {
        SharedPreferences sharedPref = getContext().getSharedPreferences(
                getString(R.string.file_share), Context.MODE_PRIVATE);
        int value = sharedPref.getInt(getString(R.string.launch_app), 0);
        if(value > 0){
            startActivity(new Intent(this, ProfileActivity.class));
            finish();
        }
    }

    private void initListeners() {
        mBinding.signInLayout.registerLiner.setOnClickListener(v -> {
            startActivity(new Intent(SingInActivity.this, SingUpActivity.class));
        });

        mBinding.signInLayout.singIn.setOnClickListener(v -> {
            boolean isEmpty = false;
            List<EditText> list = Arrays.asList(
                    mBinding.signInLayout.login,
                    mBinding.signInLayout.password
            );
            for (EditText edit : list) {
                if (TextUtils.isEmpty(edit.getText().toString().trim())) {
                    edit.setError("Обязательное поле");
                    isEmpty = true;
                }
            }

            if (isEmpty) return;
            if (!CheckerInputData.isEmail(mBinding.signInLayout.login.getText().toString().trim())) {
                Toast.makeText(SingInActivity.this, "Логин не валидный.\nExample:example@gmail.com", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!CheckerInputData.isPassword(mBinding.signInLayout.password.getText().toString().trim())) {
                Toast.makeText(SingInActivity.this, "Пароль должен содержать не менее 8 символов, цифры, буквы верхнего и нижнего регистра", Toast.LENGTH_SHORT).show();
                return;
            }
            SingInModel model = new SingInModel();
            model.setEmail(mBinding.signInLayout.login.getText().toString().trim());
            model.setPassword(mBinding.signInLayout.password.getText().toString().trim());
            try {
                mPresenter.login(model.toJSONString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            testLaunch();
        });

        mBinding.signInLayout.forgotPass.setOnClickListener(v -> {
            mPresenter.forgotPass();
        });
    }

    private void testLaunch() {
        SharedPreferences sharedPref = getContext().getSharedPreferences(
                getString(R.string.file_share), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(getString(R.string.launch_app), 1);
        editor.commit();

        startActivity(new Intent(this, ProfileActivity.class));
        finish();
    }

    @Override
    public void onSuccess(String info) {
        startActivity(new Intent(this, ProfileActivity.class));
        finish();
    }

    @Override
    public void onFailure(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getContext() {
        return this;
    }
}
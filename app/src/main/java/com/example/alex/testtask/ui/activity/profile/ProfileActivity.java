package com.example.alex.testtask.ui.activity.profile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.alex.testtask.R;
import com.example.alex.testtask.databinding.ActivityProfileBinding;
import com.example.alex.testtask.ui.activity.sing_in.SingInActivity;

public class ProfileActivity extends AppCompatActivity {

    private ActivityProfileBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_profile);

        initListeners();
        setData();
        setAvatar();

    }

    private void setAvatar() {
        Uri uri = Uri.parse("http://bipbap.ru/wp-content/uploads/2017/04/leto_derevo_nebo_peyzazh_dom_derevya_domik_priroda_3000x2000.jpg");
        Glide.with(getApplicationContext()) //передаем контекст приложения
                .load(uri)
                .fitCenter()
                .thumbnail(0.5f)
                .priority(Priority.IMMEDIATE)
                .placeholder(R.mipmap.ic_launcher)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(mBinding.signInLayout.avatar); //ссылка на ImageView


    }

    private void setData() {
        mBinding.signInLayout.address.setText("text");
        mBinding.signInLayout.company.setText("text");
        mBinding.signInLayout.login.setText("text");
        mBinding.signInLayout.phone.setText("text");
        mBinding.signInLayout.site.setText("text");
    }

    private void initListeners() {
        mBinding.signInLayout.logOut.setOnClickListener(v -> {
            SharedPreferences sharedPref = v.getContext().getSharedPreferences(
                    getString(R.string.file_share), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(getString(R.string.launch_app), 0);
            editor.commit();
            startActivity(new Intent(ProfileActivity.this, SingInActivity.class));
            finish();
        });
    }
}
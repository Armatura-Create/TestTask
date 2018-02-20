package com.example.alex.testtask.ui.activity.profile;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
    }

    private void initListeners() {
        mBinding.signInLayout.logOut.setOnClickListener(v -> {
            startActivity(new Intent(ProfileActivity.this, SingInActivity.class));
            finish();
        });
    }
}
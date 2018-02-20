package com.example.alex.testtask.ui.activity.sing_up;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.alex.testtask.R;
import com.example.alex.testtask.databinding.ActivitySingUpBinding;
import com.example.alex.testtask.interfaces.ILoadingStatus;
import com.example.alex.testtask.models.SingUpModel;
import com.example.alex.testtask.ui.activity.sing_in.SingInActivity;
import com.example.alex.testtask.utils.CheckerInputData;
import com.google.firebase.iid.FirebaseInstanceId;

import org.json.JSONException;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class SingUpActivity extends AppCompatActivity implements ILoadingStatus<String>, SingUpContract.View {

    ActivitySingUpBinding mBinding;
    private static final int PERMISSION_REQUEST_CODE = 100;
    private static final int REQUEST_CODE = 200;

    private Uri imageUri;
    private SingUpPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        presenter = new SingUpPresenter(this);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_sing_up);
        initListeners();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null)
            if (data.getData() != null) {
                imageUri = data.getData();
                Glide.with(getContext()).load(imageUri).into(mBinding.signUpLayout.avatar);
            }
    }

    public void initListeners() {
        mBinding.signUpLayout.avatar.setOnClickListener(v -> {
            requestMultiplePermissions();
            if (ContextCompat.checkSelfPermission(SingUpActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
                choosePicture();
        });

        mBinding.toolbarSingUp.setNavigationOnClickListener(v -> onBackPressed());

        mBinding.signUpLayout.registerBt.setOnClickListener(v -> {
            boolean isEmpty = false;
            List<EditText> list = Arrays.asList(
                    mBinding.signUpLayout.login,
                    mBinding.signUpLayout.company,
                    mBinding.signUpLayout.address,
                    mBinding.signUpLayout.phone,
                    mBinding.signUpLayout.site,
                    mBinding.signUpLayout.password,
                    mBinding.signUpLayout.confirm
            );
            for (EditText edit : list) {
                if (TextUtils.isEmpty(edit.getText().toString().trim())) {
                    edit.setError("Обязательное поле");
                    isEmpty = true;
                }
            }
            if (getImage() == null) {
                Toast.makeText(SingUpActivity.this, "Выбирете аватар", Toast.LENGTH_SHORT).show();
                isEmpty = true;
            }

            if (isEmpty) return;

            if (!CheckerInputData.isEmail(mBinding.signUpLayout.login.getText().toString().trim())) {
                Toast.makeText(SingUpActivity.this, "Логин не валидный.\nExample:example@gmail.com", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!CheckerInputData.isPassword(mBinding.signUpLayout.password.getText().toString().trim())) {
                Toast.makeText(SingUpActivity.this, "Пароль должен содержать не менее 8 символов, цифры, буквы верхнего и нижнего регистра", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!mBinding.signUpLayout.password.getText().toString().trim().equals(mBinding.signUpLayout.confirm.getText().toString().trim())) {
                Toast.makeText(SingUpActivity.this, "Пароли не совпадают", Toast.LENGTH_SHORT).show();
                return;
            }
            if (!CheckerInputData.isCheckPhone(mBinding.signUpLayout.phone.getText().toString().trim())) {
                Toast.makeText(SingUpActivity.this, "Телефон не валидный.\nExample:+380 00 000 00 00", Toast.LENGTH_SHORT).show();
                return;
            }

            SingUpModel model = new SingUpModel();
            model.setEmail(mBinding.signUpLayout.login.getText().toString().trim());
            model.setCompany(mBinding.signUpLayout.company.getText().toString().trim());
            model.setAddress(mBinding.signUpLayout.address.getText().toString().trim());
            model.setPhone(mBinding.signUpLayout.phone.getText().toString().trim());
            model.setSite(mBinding.signUpLayout.site.getText().toString().trim());
            model.setPassword(mBinding.signUpLayout.password.getText().toString().trim());
            model.setToken(FirebaseInstanceId.getInstance().getToken());

            try {
                Log.e("initListeners: ", model.toJSONString());
                presenter.register(getImage(), model.toJSONString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });
    }

    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(contentUri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
            if (cursor != null) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null) cursor.close();
        }
        return null;
    }

    public void requestMultiplePermissions() {
        ActivityCompat.requestPermissions(this,
                new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                },
                PERMISSION_REQUEST_CODE);
    }

    private void choosePicture() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, REQUEST_CODE);
    }

    @Override
    public void onSuccess(String info) {
        startActivity(new Intent(this, SingInActivity.class));
        onBackPressed();
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

    private MultipartBody.Part getImage() {
        if (imageUri == null) return null;
        File file = new File(getRealPathFromURI(this, imageUri));
        RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
        return MultipartBody.Part.createFormData("company", file.getName(), requestFile);
    }
}
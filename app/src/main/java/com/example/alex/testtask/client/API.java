package com.example.alex.testtask.client;

import com.example.alex.testtask.models.ResponseModel;
import com.example.alex.testtask.models.SingInModel;
import com.example.alex.testtask.models.SingUpModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface API {

    @Multipart
    @POST("/test/reg/")
    Call<ResponseModel> singUpUser(@Part MultipartBody.Part picture, @Part("data") RequestBody data);

    @Multipart
    @POST("/test/auth/")
    Call<ResponseModel> singInUser(@Part("data") RequestBody data);
}
package com.example.alex.testtask.client;

import com.example.alex.testtask.interfaces.ILoadingStatus;
import com.example.alex.testtask.models.ResponseModel;

import java.io.IOException;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.alex.testtask.utils.StaticValues.CONNECTION_ERROR;

public class Request {

    private static Request request;

    public static Request getInstance() {
        if (request == null) request = new Request();
        return request;
    }

    public void singUpUser(MultipartBody.Part image, String data, final ILoadingStatus<String> loader) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), data);
        Call<ResponseModel> call;
        call = RetrofitClient.getAPI().singUpUser(image, body);

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {


                if (response.isSuccessful()) {
                    if (Objects.equals(response.body().getStatus(), "true"))
                        loader.onSuccess("Ok");
                    else
                        loader.onFailure(response.body().getMessage());

                } else {
                    String errMessage = "";
                    try {
                        errMessage = response.errorBody().string();
                        loader.onFailure(errMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                loader.onFailure(CONNECTION_ERROR);
            }
        });
    }

    public void singInUser(String data, final ILoadingStatus<String> loader) {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), data);
        Call<ResponseModel> call;
        call = RetrofitClient.getAPI().singInUser(body);

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                if (response.isSuccessful()) {
                    if (Objects.equals(response.body().getStatus(), "true"))
                        loader.onSuccess("Ok");
                    else
                        loader.onFailure(response.body().getMessage());

                } else {
                    String errMessage;
                    try {
                        errMessage = response.errorBody().string();
                        loader.onFailure(errMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                loader.onFailure(CONNECTION_ERROR);
            }
        });
    }
}

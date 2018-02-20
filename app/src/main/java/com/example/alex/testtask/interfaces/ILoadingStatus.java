package com.example.alex.testtask.interfaces;

public interface ILoadingStatus<T> {

    void onSuccess(T info);

    void onFailure(String message);
}

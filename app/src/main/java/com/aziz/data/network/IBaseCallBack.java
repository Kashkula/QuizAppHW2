package com.aziz.data.network;

public interface IBaseCallBack <T> {

    void onSuccess(T result);

    void onFailure(Exception e);
}

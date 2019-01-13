package com.glinka.inputconsumer;

public interface CallBack {

    interface ConfigCallBack extends CallBack {}
    interface BlackSpotsCallBack extends CallBack {}
    interface MoveCallBack extends CallBack {}

    void notify(String callBack);
}

package com.glinka;

import com.glinka.inputconsumer.GameInputConsumer;
import com.glinka.inputconsumer.CallBack;

public class Main {

    public static void main(String[] args) {

        GameInputConsumer gameInputConsumer = GameInputConsumer.provideInputConsumer();
        gameInputConsumer.consumeConfig(new CallBack.ConfigCallBack() {
            @Override
            public void notify(String callBack) {
                System.out.println(callBack);
            }
        });

        gameInputConsumer.consumeBlackSpotsConfig(new CallBack.BlackSpotsCallBack() {
            @Override
            public void notify(String callBack) {
                System.out.println(callBack);
            }
        });

        gameInputConsumer.startGame(new CallBack.MoveCallBack() {
            @Override
            public void notify(String callBack) {
                System.out.println(callBack);
            }
        });
    }
}

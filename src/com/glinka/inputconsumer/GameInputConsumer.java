package com.glinka.inputconsumer;

import com.glinka.controller.Controller;
import com.glinka.logic.Board;
import com.glinka.logic.MCTSLogic;
import com.glinka.logic.algorithm.MCTS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameInputConsumer {

    private BufferedReader bufferedReader;
    private Controller controller;
    private static GameInputConsumer INSTANCE = null;

    public GameInputConsumer(BufferedReader bufferedReader, Controller controller) {
        this.bufferedReader = bufferedReader;
        this.controller = controller;
    }

    public static synchronized GameInputConsumer getInstance(BufferedReader bufferedReader, Controller controller) {
        if (INSTANCE == null)
            INSTANCE = new GameInputConsumer(bufferedReader, controller);
        return INSTANCE;
    }

    public void consumeConfig(CallBack.ConfigCallBack configCallBack){
        try{
            String input = bufferedReader.readLine();
            configCallBack.notify(controller.initBoard(input));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void consumeBlackSpotsConfig(CallBack.BlackSpotsCallBack blackSpotsCallBack){
        try{
            String input = bufferedReader.readLine();
            blackSpotsCallBack.notify(controller.initSpots(input));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void consumeMove(CallBack.MoveCallBack moveCallBack){
        try{
            String input = bufferedReader.readLine();
            moveCallBack.notify(controller.responseBasedOnInput(input));
        } catch (Exception e){
            System.out.println(e.getMessage());
            closeReader();
            controller.stop();
        }
    }

    public void startGame(CallBack.MoveCallBack moveCallBack){
        do {
            consumeMove(moveCallBack);
        } while(controller.isGameRunning());
        closeReader();
    }

    private void closeReader(){
        try{
            bufferedReader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static GameInputConsumer provideInputConsumer(){
        return GameInputConsumer.getInstance(new BufferedReader(new InputStreamReader(System.in)), new Controller(new MCTSLogic(new MCTS(), new Board())));
    }
}

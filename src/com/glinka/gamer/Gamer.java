package com.glinka.gamer;

import com.glinka.controller.Controller;
import com.glinka.logic.Board;
import com.glinka.logic.MCTSLogic;
import com.glinka.logic.algorithm.MCTS;
import com.glinka.util.Answer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Gamer {

    private BufferedReader bufferedReader;
    private Controller controller;
    private static Gamer INSTANCE = null;

    public Gamer(BufferedReader bufferedReader, Controller controller) {
        this.bufferedReader = bufferedReader;
        this.controller = controller;
    }

    public static synchronized Gamer getInstance(BufferedReader bufferedReader, Controller controller) {
        if (INSTANCE == null)
            INSTANCE = new Gamer(bufferedReader, controller);
        return INSTANCE;
    }

    public void consumeConfig(Answer.ConfigAnswer configAnswer){
        try{
            String input = bufferedReader.readLine();
            configAnswer.notify(controller.initBoard(input));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void consumeBlackSpotsConfig(Answer.BlackSpotsAnswer blackSpotsAnswer){
        try{
            String input = bufferedReader.readLine();
            blackSpotsAnswer.notify(controller.initSpots(input));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void consumeMove(Answer.MoveAnswer moveAnswer){
        try{
            String input = bufferedReader.readLine();
            moveAnswer.notify(controller.responseBasedOnInput(input));
        } catch (Exception e){
            System.out.println(e.getMessage());
            closeReader();
            controller.stop();
        }
    }

    public void startGame(Answer.MoveAnswer moveAnswer){
        do {
            consumeMove(moveAnswer);
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

    public static Gamer provideInputConsumer(){
        return Gamer.getInstance(new BufferedReader(new InputStreamReader(System.in)), new Controller(new MCTSLogic(new MCTS(), new Board())));
    }
}

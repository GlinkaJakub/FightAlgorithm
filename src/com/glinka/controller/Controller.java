package com.glinka.controller;

import com.glinka.logic.Spot;
import com.glinka.logic.MCTSLogic;
import com.glinka.util.Parser;

public class Controller {
    private MCTSLogic mctsLogic;
    private boolean isGameRunning = false;
    public static final String ok = "ok";

    public Controller(MCTSLogic mctsLogic){
        this.mctsLogic = mctsLogic;
    }

    public String initBoard(String size){
        mctsLogic.initSize(Integer.valueOf(size));
        return ok;
    }

    public String initSpots(String config){
        mctsLogic.initBlackSopts(Parser.parseInputData(config));
        return ok;
    }

    private String makeStartMove(){
        return Parser.parseOutputData(mctsLogic.getStartMoveData());
    }

    private String makeMove(String moveData){
        Spot optimalMove = mctsLogic.getOptimalMove((Parser.parseInputData(moveData)));
        if(optimalMove == null){
            isGameRunning = false;
            return "No find move";
        } else {
            if(mctsLogic.isGameEnd())
                isGameRunning = false;
            return Parser.parseOutputData(optimalMove);
        }
    }

    public String responseBasedOnInput(String input){
        if(input.toLowerCase().equals("stop")){
            isGameRunning = false;
            return "End game";
        } else if(input.toLowerCase().equals("start")){
            if(!isGameRunning){
                isGameRunning = true;
                return makeStartMove();
            } else
                return "Game already started";
        } else {
            isGameRunning = true;
            return makeMove(input);
        }
    }

    public boolean isGameRunning() {
        return isGameRunning;
    }

    public void stop() {
        isGameRunning = false;
    }

}

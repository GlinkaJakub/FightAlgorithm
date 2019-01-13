package com.glinka.controller;

import com.glinka.logic.Element;
import com.glinka.logic.MCTSLogic;
import com.glinka.util.DataParser;

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
        mctsLogic.initBlackSopts(DataParser.parseInputData(config));
        return ok;
    }

    private String makeStartMove(){
        return DataParser.parseOutputData(mctsLogic.getStartMoveData());
    }

    private String makeMove(String moveData){
        Element optimalMove = mctsLogic.getOptimalMove((DataParser.parseInputData(moveData)));
        if(optimalMove == null){
            isGameRunning = false;
            return "No find move";
        } else {
            if(mctsLogic.isGameEnd())
                isGameRunning = false;
            return DataParser.parseOutputData(optimalMove);
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

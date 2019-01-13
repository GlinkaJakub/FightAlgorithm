package com.glinka.logic;

import com.glinka.logic.algorithm.MCTS;
import com.glinka.util.DataParser;

import java.util.ArrayList;

public class MCTSLogic {
    private MCTS monteCarloTreeSearch;
    private Board board;
    private int player = 1;

    public MCTSLogic(MCTS monteCarloTreeSearch, Board board) {
        this.monteCarloTreeSearch = monteCarloTreeSearch;
        this.board = board;
    }

    public void initSize(int size){
        DataParser.size = size;
        board.setSize(size);
    }

    public void initBlackSopts(ArrayList<Element> configuration){
        board.filter(configuration);
    }

    public Element getOptimalMove(ArrayList<Element> coordinates){
        board.performMove(player, DataParser.parseInputDataToElement(coordinates));
        if(board.getMoves().size() > 1200){
            board = Reverse.reverseMove(board, DataParser.parseInputDataToElement(coordinates), player);
        } else {
            board = monteCarloTreeSearch.findNextMove(board, player);
        }
        return board.getLastMove();
    }

    public Element getStartMoveData() {
        if(board.getMoves().size() > 1200){
            Element e = board.getMoves().iterator().next();
            board.performMove(player, e);
        } else {
            board = monteCarloTreeSearch.findNextMove(board, player);
        }
        return board.getLastMove();
    }

    public boolean isGameEnd(){
        return board.getMoves().isEmpty();
    }

    public void setplayer(int player){
        board.setLastPlayer(player);
    }
}

package com.glinka.logic;

import com.glinka.logic.algorithm.MCTS;
import com.glinka.util.Parser;

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
        Parser.size = size;
        board.setSize(size);
    }

    public void initBlackSopts(ArrayList<Spot> configuration){
        board.filter(configuration);
    }

    public Spot getOptimalMove(ArrayList<Spot> coordinates){
        board.performMove(player, Parser.parseInputDataToElement(coordinates));
        if(board.getMoves().size() > 1200){
            board = Reverse.reverseMove(board, Parser.parseInputDataToElement(coordinates), player);
        } else {
            board = monteCarloTreeSearch.findNextMove(board, player);
        }
        return board.getLastMove();
    }

    public Spot getStartMoveData() {
        if(board.getMoves().size() > 1200){
            Spot e = board.getMoves().iterator().next();
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

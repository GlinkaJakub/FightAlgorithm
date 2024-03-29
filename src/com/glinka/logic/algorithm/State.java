package com.glinka.logic.algorithm;

import com.glinka.logic.Board;
import com.glinka.logic.Spot;

import java.util.HashSet;

public class State {

    private Board board;
    private int playerNo;
    private int visitCount;
    private double winScore;

    public State() {
        board = new Board();
    }

    public State(State state) {
        this.board = new Board(state.getBoard());
        this.playerNo = state.getPlayerNo();
        this.visitCount = state.getVisitCount();
        this.winScore = state.getWinScore();
    }

    public State(Board board) {
        this.board = new Board(board);
    }

    public HashSet<State> getAllPossibleStates() {
        HashSet<State> possibleStates = new HashSet<>();
        HashSet<Spot> availableMoves = this.board.getMoves();
        availableMoves.forEach(m -> {

            State newState = new State(this.board);
            newState.setPlayerNo(3 - this.playerNo);
            newState.getBoard().performMove(newState.getPlayerNo(), m);
            possibleStates.add(newState);

        });
        return possibleStates;
    }

    void incrementVisit() {
        this.visitCount++;
    }

    void addScore(double score) {
        if (this.winScore != Integer.MIN_VALUE)
            this.winScore += score;
    }

    void play() {
        this.board.performMove(this.playerNo, this.board.getMoves().iterator().next());
    }

    void togglePlayer() {
        this.playerNo = 3 - this.playerNo;
    }

    int getOpponent() {
        return 3 - playerNo;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getPlayerNo() {
        return playerNo;
    }

    public void setPlayerNo(int playerNo) {
        this.playerNo = playerNo;
    }

    public int getVisitCount() {
        return visitCount;
    }

    public void setVisitCount(int visitCount) {
        this.visitCount = visitCount;
    }

    public double getWinScore() {
        return winScore;
    }

    public void setWinScore(double winScore) {
        this.winScore = winScore;
    }
}
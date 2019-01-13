package com.glinka.logic;

import java.util.ArrayList;
import java.util.HashSet;

public class Board {

    private HashSet<Spot> moves;
    private int lastPlayer;
    private Spot lastMove = new Spot(0, 0);
    public static final int IN_PROGRESS = -1;
    private int size;

    public Board() {
    }

    public Board(int size) {
        this.size = size;
        this.moves = new HashSet<>();
        addPossibleMoves();
    }

    public Board(Board board) {
        this.moves = new HashSet<>(board.getMoves());
    }

    private void addPossibleMoves() {

        int ai, aj, head;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                head = i * size + j;
                ai = head - 1 - (i * size) < 0 ? i * size + j - 1 + size : i * size + j - 1;
                aj = head - size < 0 ? i * size + j - size + (size * size) : i * size + j - size;
                moves.add(new Spot(head, ai));
                moves.add(new Spot(head, aj));
            }
        }
    }

    public void filter(ArrayList<Spot> spots) {
        spots.forEach(move -> {
            int x = move.getX() * size + move.getY();
            moves.removeIf(element -> (element.getX() == x || (element.getY() == x)));
        });
    }

    public void performMove(int lastPlayer, Spot move) {
        this.lastPlayer = lastPlayer;
        this.lastMove = move;
        moves.removeIf(element -> (element.getX() == move.getX()) || (element.getY() == move.getY() || element.getX() == move.getY()) || (element.getY() == move.getX()));
    }

    public int checkStatus() {
        if (!moves.isEmpty())
            return IN_PROGRESS;
        else {
            return lastPlayer;
        }
    }

    public HashSet<Spot> getMoves() {
        return moves;
    }

    public void setMoves(HashSet<Spot> moves) {
        this.moves = moves;
    }

    public Spot getLastMove() {
        return lastMove;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
        this.moves = new HashSet<>();
        addPossibleMoves();
    }

    public void setLastPlayer(int lastPlayer) {
        this.lastPlayer = lastPlayer;
    }

}
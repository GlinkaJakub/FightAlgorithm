package com.glinka.logic;

public class Reverse {

    public static Board reverseMove(Board board, Spot spot, int player) {

        Spot e = new Spot(board.getSize() - 1 - spot.getX(), board.getSize() - 1 - spot.getY());
        if (board.getMoves().contains(e)) {
            board.performMove(1, e);
            return board;
        }
        e = new Spot(board.getSize() - 1 - spot.getX(), spot.getY());
        if (board.getMoves().contains(e)) {
            board.performMove(1, e);
            return board;
        }
        e = new Spot(spot.getX(), board.getSize() - 1 - spot.getY());
        if (board.getMoves().contains(e)) {
            board.performMove(player, e);
            return board;
        }
        e = new Spot(spot.getY(), spot.getX());
        if (board.getMoves().contains(e)) {
            board.performMove(player, e);
            return board;
        } else {
            e = board.getMoves().iterator().next();
            board.performMove(player, e);
            return board;
        }
    }
}

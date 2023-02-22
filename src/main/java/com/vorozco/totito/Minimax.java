package com.vorozco.totito;

import com.vorozco.adapter.Board;
import com.vorozco.adapter.Piece;

public class Minimax {

    public static<Move> double minimax(Board<Move> board, boolean maximizing, Piece originalPlayer, int maxDepth){
        //condicion de escape
        if (board.isWin() || board.isDraw() || maxDepth == 0){
            return board.evaluate(originalPlayer);
        }
        //minimax
        if(maximizing){
            double v = Double.NEGATIVE_INFINITY;
            for(Move move: board.getLegalMoves()){
                double result = minimax(board.move(move), false,
                        originalPlayer, maxDepth-1);
                v = Math.max(v, result);
            }
            return v;
        }else{
            //minimizacion
            double v = Double.POSITIVE_INFINITY;
            for(Move move: board.getLegalMoves()){
                double result = minimax(board.move(move), true,
                        originalPlayer, maxDepth-1);
                v = Math.min(v, result);
            }
            return v;
        }
    }

    public static <Move> Move findBestMove(Board<Move> board, int maxDepth){
        double bestValue = Double.NEGATIVE_INFINITY;
        Move bestMove = null;
        for(Move move : board.getLegalMoves()){
            double result = minimax(board.move(move), false, board.getTurn(), maxDepth);
            if(result > bestValue){
                bestValue = result;
                bestMove = move;
            }
        }
        return bestMove;
    }


}

import java.util.ArrayList;
import java.util.Collections;

public class AlphaBeta{
    Board best;
    long start = System.currentTimeMillis();

    public Board AlphaBetaSearch(Board board){
        start = System.currentTimeMillis();
        int parent = maxValue(board, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return this.best;
    }

    private int maxValue(Board board, int alpha, int beta){
        if(!timeAllowed(board.timeInSec)){
            return board.heuristicValue;
        }
        int v = Integer.MIN_VALUE;
        Board arrayBoard[] = successors(board);
        Board bestBoard = arrayBoard[0];
        for(int i = 0 ; i < arrayBoard.length; i++){
            v = Math.max(v, minValue(bestBoard,alpha,beta));
            if(v >= beta){
                this.best = bestBoard;
                return v;
            }
            alpha = Math.max(alpha,v);
        }
        this.best = bestBoard;
        return v;
    }

    private int minValue(Board board, int alpha, int beta){
        if(!timeAllowed(board.timeInSec)){
            return board.heuristicValue;
        }
        int v = Integer.MAX_VALUE;
        Board arrayBoard[] = successors(board);
        for(int i = 0 ; i < arrayBoard.length; i++){
            v = Math.min(v, maxValue(arrayBoard[i], alpha, beta));
            if( v <= alpha){
                return v;
            }
            beta = Math.min(beta, v);
        }
        return v;
    }

    //Get the successor boards
    private Board[] successors(Board state){
        ArrayList<Board> boardStates = new ArrayList<Board>();
        String agent;
        String empty = " - ";
        String[] board = state.board.clone();

        if(state.lastMove.equals("O")){
            agent = "X";
            state.lastMove = agent;
        }else{
            agent = "O";
            state.lastMove = agent;
        }
        for(int i = 0 ; i < board.length; i++){
            // if(!board[i].equals(empty)){
            //     //Look left
            //     if(i % 8 != 0){
            //         if(board[i-1].equals(empty)){
            //             Board newBoard = new Board(board);
            //             newBoard.setPlayerMove(newBoard.convertIndexToString(i-1), agent);
            //             boardStates.add(newBoard);
            //             board = state.board.clone();
            //         }
            //     }
            //     //Looking right
            //     if(i != 7 && i != 15 && i != 23 && i != 31 && i!= 39 &&
            //        i != 47 && i != 55 && i != 63){
            //         if(board[i+1].equals(empty)){
            //             Board newBoard = new Board(board);
            //             newBoard.setPlayerMove(newBoard.convertIndexToString(i+1), agent);
            //             boardStates.add(newBoard);
            //             board = state.board.clone();
            //         }
            //     }
            //     //Looking upwards
            //     if(i > 8){
            //         if(board[i-8].equals(empty)){
            //             Board newBoard = new Board(board);
            //             newBoard.setPlayerMove(newBoard.convertIndexToString(i-8), agent);
            //             boardStates.add(newBoard);
            //             board = state.board.clone();
            //         }
            //     }
            //     //Looking downwards
            //     if(i < 56){
            //         if(board[i+8].equals(empty)){
            //             Board newBoard = new Board(board);
            //             newBoard.setPlayerMove(newBoard.convertIndexToString(i+8), agent);
            //             boardStates.add(newBoard);
            //             board = state.board.clone();
            //         }
            //     }
            // }
            if(board[i].equals(empty)){
            Board newBoard = new Board(board);
            newBoard.setPlayerMove(newBoard.convertIndexToString(i), agent);
            newBoard.setParent(state);
            newBoard.arrayList.add(newBoard.convertIndexToString(i));
            boardStates.add(newBoard);
            board = state.board.clone();
            }
        }
        for(int i = 0 ; i < boardStates.size(); i++){
            patternScoring(boardStates.get(i));
        }
        Collections.sort(boardStates);
        Board boardArray[] = boardStates.toArray(new Board[boardStates.size()]);
        boardStates.clear();
        return boardArray;
    }

    public void patternScoring(Board board){
        for(int i = 0 ; i < board.board.length; i+=8){
            for(int j = 0; j < 8; j++){
                if(!board.board[i+j].equals(" - ")){
                if(i % 8 != 0 && j < 6){
                    //Looks for [ ][X][X][ ] and assigns 50
                    if(board.board[i+j].equals(board.board[i+j+1]) &&
                       board.board[i+j-1].equals(" - ") && board.board[i+j+2].equals(" - ")){
                           int value = 50;
                           if(board.heuristicValue < value){
                               board.heuristicValue = value;
                           }
                       }
                }
                if(i % 8 != 0 && j < 5){
                    // Looks for [ ][X][X][X][ ] three in a row where left and right are empty
                    if(board.board[i+j].equals(board.board[i+j+1]) && 
                       board.board[i+j].equals(board.board[i+j+2]) &&
                       board.board[i+j-1].equals(" - ") && 
                       board.board[i+j+3].equals(" - ")){
                           int value = 150;
                           if(board.heuristicValue < value){
                               board.heuristicValue = value;
                           }
                       }
                }
                if(j < 5){
                    //Looks for [X][X][X][X] and assigns 1000
                    if(board.board[i+j].equals(board.board[i+j+1])&&
                    board.board[i+j].equals(board.board[i+j+2])&&
                    board.board[i+j].equals(board.board[i+j+3])){
                        int value = 1000;
                        if(board.heuristicValue < value){
                            board.heuristicValue = value;
                        }
                    }
                }
                if(i != 0 && i < 40){
                    //Looking for [ ]
                    //            [X]
                    //            [X]
                    //            [ ]
                    if(board.board[i+j].equals(board.board[i+j+8]) &&
                       board.board[i+j - 8].equals(" - ") &&
                       board.board[i+j + 16].equals(" - ")){
                        int value = 50;
                        if(board.heuristicValue < value){
                            board.heuristicValue = value;
                        }
                    }
                }
                if(i != 0 && i < 40){
                    //Looks for [ ]
                    //          [X]
                    //          [X]
                    //          [X]
                    //          [ ]
                    if(board.board[i+j].equals(board.board[i+j+8])&&
                       board.board[i+j].equals(board.board[i+j+16]) &&
                       board.board[i+j-8].equals(" - ") &&
                       board.board[i+j+24].equals(" - ")){
                        int value = 150;
                        if(board.heuristicValue < value){
                            board.heuristicValue = value;
                        }
                    }
                }
                if(i < 40){
                    //Looks for [X]
                    //          [X]
                    //          [X]
                    //          [X]
                    if(board.board[i+j].equals(board.board[i+j+8]) &&
                    board.board[i+j].equals(board.board[i+j+16]) &&
                    board.board[i+j].equals(board.board[i+j+24])){
                        int value = 1000;
                        if(board.heuristicValue < value){
                            board.heuristicValue = value;
                        }
                    }
                }
            }
        }
        }
    }
    

    public boolean timeAllowed(int timeInSecs){
        long timeInMillis = timeInSecs * 1000;

        if(System.currentTimeMillis() - start >= timeInMillis){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Board board = new Board();
        AlphaBeta ab = new AlphaBeta();
        String symbol = "X";
        Driver driver = new Driver();
        int maxTime = driver.askForTime();
        board.timeInSec = maxTime;
        board.determineStart();
        while(!board.win(board.board)){
        String move = board.returnPlayerMove();
        if(board.lastMove.equals("X")){
            symbol = "O";
            board.lastMove = "O";
        }else{
            symbol = "X";
            board.lastMove = "X";
        }
        board.setPlayerMove(move, board.lastMove);
        Board newBoard = ab.AlphaBetaSearch(board);
        for(int i = 0 ; i < newBoard.board.length; i++){
            board.board[i] = newBoard.board[i];
        }
        board.printBoard();
    }
    }
}
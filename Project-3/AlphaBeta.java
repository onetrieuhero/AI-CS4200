import java.util.ArrayList;

public class AlphaBeta{
    Board highest;
    long start = System.currentTimeMillis();

    public Board AlphaBetaSearch(Board board){

        int parent = maxValue(board, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return this.highest;
    }

    private int maxValue(Board board, int alpha, int beta){
        if(!timeAllowed(board.timeInSec)){
            return board.heuristicValue;
        }
        int v = Integer.MIN_VALUE;
        Board arrayBoard[] = successors(board);
        for(int i = 0 ; i < arrayBoard.length; i++){
            v = Math.max(v, minValue(arrayBoard[i],alpha,beta));
            if(v >= beta){
                return v;
            }
            alpha = Math.max(alpha,v);
        }
        return v;
    }

    private int minValue(Board board, int alpha, int beta){
        if(!timeAllowed(board.timeInSec)){
            return board.heuristicValue;
        }
        int v = Integer.MAX_VALUE;
        Board arrayBoard[] = successors(board);
        for(int i = 0 ; i < arrayBoard.length; i++){
            v = Math.min(v, maxValue(board, alpha, beta));
            if( v <= alpha){
                return v;
            }
            beta = Math.min(beta, v);
        }
        return v;
    }

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
            boardStates.add(newBoard);
            board = state.board.clone();
            board = state.board.clone();
            }
        }
        Board boardArray[] = boardStates.toArray(new Board[boardStates.size()]);
        return boardArray;
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
        String move = board.returnPlayerMove();
        board.setPlayerMove(move, "X");
        board.printBoard();
        Board array[] = ab.successors(board);
        // Board array1[] = ab.successors(array[0]);
        // Board array2[] = ab.successors(array1[0]);
        for(int i = 0 ; i < array.length;i++){
            array[i].printBoard();
        }
        System.out.println(array.length);

    }
}
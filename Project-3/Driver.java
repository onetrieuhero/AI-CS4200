import java.util.Scanner;

public class Driver{
    public static void main(String[] args) {
        Board board = new Board();
        AlphaBeta ab = new AlphaBeta();
        String symbol = "X";
        Driver driver = new Driver();
        int maxTime = driver.askForTime();
        board.timeInSec = maxTime;
        board.determineStart();
        while(!board.win(board.board)){
            if(board.agentStarts == true){
                Board newBoard = ab.AlphaBetaSearch(board);
                for(int i = 0 ; i < newBoard.board.length; i++){
                    board.board[i] = newBoard.board[i];
                }
                board.printBoard();
                board.agentStarts = false;
            }
            String move = board.returnPlayerMove();
            if(board.lastMove.equals("X")){
                symbol = "O";
                board.lastMove = "O";
            }else{
                symbol = "X";
                board.lastMove = "X";
        }
            board.setPlayerMove(move, board.lastMove);
            board.printBoard();
            Board newBoard = ab.AlphaBetaSearch(board);
            for(int i = 0 ; i < newBoard.board.length; i++){
                board.board[i] = newBoard.board[i];
            }
            board.printBoard();
        }

    }

    public int askForTime(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter max time allowed in seconds");
        int maxTime = scanner.nextInt();
        if(maxTime < 0){
            System.out.println("That is not valid");
            return askForTime();
        }else {return maxTime;}
    }
    
}
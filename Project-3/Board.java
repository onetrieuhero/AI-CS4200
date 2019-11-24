import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Board{
    String board[] = createBoard();
    ArrayList<String> arrayList = new ArrayList<>();
    HashMap<String, Boolean> map = new HashMap<>();
    boolean playerStarts;
    int arrayCounter = 0;
    int totalMoves = 0;


    /**
     * This method creates a board state that is empty except for " - "
     * @return a new board
     */
    public String[] createBoard(){
        String createBoard[] = new String[64];
        for(int i = 0 ; i < createBoard.length; i++){
            createBoard[i] = " - ";
        } 
        return createBoard;
    }

    /**
     * This method prints out the 1D array in a grid format along with correct formatting 
     * along with player's moves and their history
     */
    public void printBoard(){
        int index = 1;
        int counter = 1;
        int moveCounter = 0;
        int moveLength = arrayList.size();
        char letter = 'A';

        for(int i = 0 ; i < board.length; i++){
            //At index 0 it prints out the numbers from 1 to 8 along with the "Player vs. Opponent"
            if(i == 0){
                System.out.print("  ");
                while(index != 9){
                System.out.print(" "+ index + " ");
                index++;
                }
                System.out.print("\tPlayer vs. Opponent");
            }
            if(i % 8 ==0){
                System.out.print( "\n" + letter + " " + board[i]);
                letter++;
            }else{
                System.out.print(board[i]);
                if(i == 7 && moveCounter < moveLength){
                    System.out.print("\t" + counter++ + ". " + arrayList.get(moveCounter++) + " ");
                    if(moveCounter < moveLength)
                        System.out.print(arrayList.get(moveCounter++));
                }
                else if(i == 15 && moveCounter < moveLength){
                    System.out.print("\t" + counter++ + ". " + arrayList.get(moveCounter++) + " ");
                    if(moveCounter < moveLength)
                        System.out.print(arrayList.get(moveCounter++));
                }
                else if(i == 23 && moveCounter < moveLength){
                    System.out.print("\t" + counter++ + ". " + arrayList.get(moveCounter++) + " ");
                    if(moveCounter < moveLength)
                        System.out.print(arrayList.get(moveCounter++));
                }
                else if(i == 31 && moveCounter < moveLength){
                    System.out.print("\t" + counter++ + ". " + arrayList.get(moveCounter++) + " ");
                    if(moveCounter < moveLength)
                        System.out.print(arrayList.get(moveCounter++));
                }
                else if(i == 39 && moveCounter < moveLength){
                    System.out.print("\t" + counter++ + ". " + arrayList.get(moveCounter++) + " ");
                    if(moveCounter < moveLength)
                        System.out.print(arrayList.get(moveCounter++));
                }
                else if(i == 47 && moveCounter < moveLength){
                    System.out.print("\t" + counter++ + ". " + arrayList.get(moveCounter++) + " ");
                    if(moveCounter < moveLength)
                        System.out.print(arrayList.get(moveCounter++));
                }
                else if(i == 55 && moveCounter < moveLength){
                    System.out.print("\t" + counter++ + ". " + arrayList.get(moveCounter++) + " ");
                    if(moveCounter < moveLength)
                        System.out.print(arrayList.get(moveCounter++));
                }
                else if(i == 63 && moveCounter < moveLength){
                    System.out.print("\t" + counter++ + ". " + arrayList.get(moveCounter++) + " ");
                    if(moveCounter < moveLength)
                        System.out.println(arrayList.get(moveCounter++));
                }
            }      
        }
        //This part prints it past the board state
        while(moveCounter < moveLength){
            System.out.print("\t\t\t\t" + counter++ + ". " + arrayList.get(moveCounter++) + " ");
            if(moveCounter < moveLength)
                System.out.println(arrayList.get(moveCounter++));
        }
        System.out.println();
    }


    /**
     * Get players moves and adds it into the arraylist this also prevents duplicate moves
     */
    public void returnPlayerMove(){
        String playerMove = "";
        Scanner kb = new Scanner(System.in);
        boolean continuing = true;

        //Regex to check if alphanumeric is between a-h or A-H and 1-8
        String n = ".*[1-8].*";
        String a = ".*[a-hA-H].*";

        while(playerMove.length() > 2 || playerMove.length() < 2 ||
                !playerMove.matches(n) || !playerMove.matches(a)|| continuing == true){
                    System.out.print("Choose Opponent's next Move: ");
                    playerMove = kb.nextLine();
            if(playerMove.length() > 2 || playerMove.length() < 2 ||
                !playerMove.matches(n) || !playerMove.matches(a) || map.containsKey(playerMove)){
                    System.out.println("That was not a valid Move!");
                    continuing = true;
            }else{
                map.put(playerMove,true);
                continuing = false;
            }
        }
        this.arrayList.add(playerMove);
    }

    /**
     * This method determins who starts the Agent or the Opponent
     */
    public void determineStart(){
        System.out.println("Who Goes First?\nA for Agent\nO for Opponent");
        Scanner kb = new Scanner(System.in);
        String choice = kb.nextLine();
        switch(choice.toUpperCase()){
            case "A": this.playerStarts = true;
                      break;
            case "X": this.playerStarts = false;
                      break;
            default: System.out.println("That isn't a valid choice!");
                     System.out.println("Ending Program...");
                     System.exit(0);
        }
    }

    /**
     * This method sets the players moves and alternates the x and o's 
     */
    public void setPlayerMove(){
        String token[] = new String[2];
        char moveSymbol;
        int indexForBoard = 0;

        //Depending on totalMoves it is either X or O
        if(totalMoves % 2 == 0){
            moveSymbol = 'X';
        }else{moveSymbol = 'O';}

        //Splits the string and stores it into a String[] array
        if(arrayList.size() != 0){
            token = arrayList.get(arrayCounter).split("");
            arrayCounter++;
        }

        //Capitalizes the letters of the array
        for(int i = 0 ; i < token.length; i++){
            if(Character.isLetter(token[i].charAt(0))){
                token[i] = token[i].toUpperCase();
            }

            //Depending on the Letter it adds to the index for the boards so that X or O can be applied
            switch(token[i]){
                case "A": indexForBoard += 0;
                          break;
                case "B": indexForBoard += 8;
                          break;
                case "C": indexForBoard += 16;
                          break;
                case "D": indexForBoard += 24;
                          break;
                case "E": indexForBoard += 32;
                          break;
                case "F": indexForBoard += 40;
                          break;
                case "G": indexForBoard += 48;
                          break;
                case "H": indexForBoard += 56;
                          break;
                case "1": indexForBoard += 0;
                          break;
                case "2": indexForBoard += 1;
                          break;
                case "3": indexForBoard += 2;
                          break;
                case "4": indexForBoard += 3;
                          break;
                case "5": indexForBoard += 4;
                          break;
                case "6": indexForBoard += 5;
                          break; 
                case "7": indexForBoard += 6;
                          break;
                case "8": indexForBoard += 7;
                          break;
            }
    }
    //Saves X or O to the board 
    board[indexForBoard] = " " + moveSymbol + " ";
    this.printBoard();
    totalMoves++;
    }

    public boolean winByRow(String[] board){
        boolean status = false;
        String xValue = " X ";
        String oValue = " O ";

        for(int i = 0; i < board.length; i+=8){
            if(i % 8 == 0){
                if(board[i].equals(xValue) &&
                   board[i+1].equals(xValue) &&
                   board[i+2].equals(xValue) &&
                   board[i+3].equals(xValue)){
                        status = true;
                        return status;
                }
                if(board[i].equals(oValue) &&
                   board[i+1].equals(oValue) &&
                   board[i+2].equals(oValue) &&
                   board[i+3].equals(oValue)){
                        status = true;
                        return status;
                }
            }
            for(int j = i; j < 6; j++){
                if(board[j].equals(xValue) &&
                board[j+1].equals(xValue) &&
                board[j+2].equals(xValue) &&
                board[j+3].equals(xValue)){
                    status = true;
                    return status;
                }
                if(board[j].equals(oValue) &&
                board[j+1].equals(oValue) &&
                board[j+2].equals(oValue) &&
                board[j+3].equals(oValue)){
                    status = true;
                    return status;
                }
            }
        }
        return status;
    }

    public boolean winByCol(String[] board){
        boolean status = false;



        return status;
    }

    public boolean win(String[] board){
        return winByRow(board);
    }

    public void endGameAfterWin(boolean winCondition){
        if(winCondition == true){
            
        }
    }

    public static void main(String[] args){
        Board board = new Board();
        board.printBoard();
        while(true){
        board.returnPlayerMove();
        board.setPlayerMove();
        System.out.println(board.win(board.board));
        }
    }
}
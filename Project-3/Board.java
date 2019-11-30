import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Board{
    Board parentBoard;
    String board[];
    String lastMove;
    ArrayList<String> arrayList = new ArrayList<>();
    HashMap<String, Boolean> map = new HashMap<>();
    boolean agentStarts;
    int arrayCounter = 0;
    int totalMoves = 0;
    int timeInSec = 0;
    int heuristicValue;


    public Board(){
        this.board = createBoard();
    }

    public Board(String[] board){
        this.board = board;
    }

    public Board getparent(){
        return parentBoard;
    }

    public void setParent(Board board){
        parentBoard = board;
    }
    
    

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
                if(agentStarts)
                    System.out.print("\tAgent vs. Opponent");
                else{System.out.print("\tOpponent vs. Agent");}
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
        System.out.println("\n");
    }


    /**
     * Get players moves and adds it into the arraylist this also prevents duplicate moves
     */
    public String returnPlayerMove(){
        String playerMove = "";
        Scanner kb = new Scanner(System.in);
        boolean continuing = true;

        //Regex to check if alphanumeric is between a-h or A-H and 1-8
        String n = ".*[1-8].*";
        String a = ".*[a-hA-H].*";

        if(arrayList.size() == 64){
            System.out.println("DRAW");
            System.exit(0);
        }

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
        return playerMove;
    }

    /**
     * This method determins who starts the Agent or the Opponent
     */
    public void determineStart(){
        System.out.println("Who Goes First?\nA for Agent\nO for Opponent");
        Scanner kb = new Scanner(System.in);
        String choice = kb.nextLine();
        switch(choice.toUpperCase()){
            case "A": this.agentStarts = true;
                      break;
            case "O": this.agentStarts = false;
                      break;
            default: System.out.println("That isn't a valid choice!");
                     determineStart();
        }
    }


    // /**
    //  * This method sets the agents move taking in a string of what the agent is going to do
    //  * @param move
    //  */
    // public void setAgentsMove(String move){
    //     String agent = "";
    //     if(arrayList.size() == 64){
    //         System.out.println("DRAW");
    //         System.exit(0);
    //     }
    //     System.out.println("Agent's move is " + move);
    //     arrayList.add(move);
    //     if(agentStarts == true){
    //         agent = "X";
    //     }else{
    //         agent = "O";
    //     }
    //     setPlayerMove(move,agent);
    // }

    /**
     * This method sets the players moves and alternates the x and o's 
     */
    public void setPlayerMove(String playerMove, String agent){
        String token[] = new String[2];
        String moveSymbol = "X";
        int indexForBoard = 0;
        Board newParent = new Board(board.clone());
        setParent(newParent);

        //Depending on argument it is either X or O
        if(agent.equals("X")){
            moveSymbol = "X";
            lastMove = "X";
        }else if(
            agent.equals("O")){
            moveSymbol = "O";
            lastMove = "O";
        }

        //Splits the string and stores it into a String[] array
        if(/*arrayList.size()*/ playerMove.length() != 0){
            //token = arrayList.get(arrayCounter).split("");
            //arrayCounter++;
            token = playerMove.split("");
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
    //this.printBoard();
    totalMoves++;

    }

    /**
     * This method determins if the player or agent won by row
     * @param board 
     * @return true if win by row else false
     */
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

    /**
     * This method determines if player or agent wins by col
     * @param board
     * @return true if win by col else false
     */
    public boolean winByCol(String[] board){
        boolean status = false;
        String xValue = " X ";
        String oValue = " O ";

        for(int i = 0 ; i < 40; i++){
            if(board[i].equals(oValue) &&
            board[i+8].equals(oValue) &&
            board[i+16].equals(oValue) &&
            board[i+24].equals(oValue)){
                status = true;
                return status;
            }
            if(board[i].equals(xValue) &&
            board[i+8].equals(xValue) &&
            board[i+16].equals(xValue) &&
            board[i+24].equals(xValue)){
                status = true;
                return status;
            }
        }
        return status;
    }

    /**
     * This method will end the game if the player has won
     * @param board
     * @return
     */
    public boolean win(String[] board){
        if(winByCol(board) || winByRow(board)){
            System.out.println("WIN!");
            System.exit(0);
        }
        return winByCol(board) || winByRow(board);
    }

    public String convertIndexToString(int index){
        if(index < 8){
            return "a"+(index+1);
        }
        if(index >= 8 && index < 16){
            return "b"+(index%8+1);
        }
        if(index >= 16 && index < 24){
            return "c"+(index%8+1);
        }
        if(index >= 24 && index < 32){
            return "d"+(index%8+1);
        }
        if(index >= 32 && index < 40){
            return "e"+(index%8+1);
        }
        if(index >= 40 && index < 48){
            return "f"+(index%8+1);
        }
        if(index >= 48 && index < 56){
            return "g"+(index%8+1);
        }
        if(index >= 56 && index < 64){
            return "h"+(index%8+1);
        }else return null;
    }


    public static void main(String[] args){
        Board board = new Board();

    }
}
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

    public String[] createBoard(){
        String createBoard[] = new String[64];
        for(int i = 0 ; i < createBoard.length; i++){
            createBoard[i] = " - ";
        } 
        return createBoard;
    }

    public void printBoard(){
        int index = 1;
        int counter = 1;
        int moveCounter = 0;
        int moveLength = arrayList.size();
        char letter = 'A';

        for(int i = 0 ; i < board.length; i++){
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
                // 7 15 23 31 47 55 63
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
        while(moveCounter < moveLength){
            System.out.print("\t\t\t\t" + counter++ + ". " + arrayList.get(moveCounter++) + " ");
            if(moveCounter < moveLength)
                System.out.println(arrayList.get(moveCounter++));
        }
        System.out.println();
    }


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

    public void setPlayerMove(){
        String token[] = new String[2];
        char moveSymbol;
        int indexForBoard = 0;

        if(totalMoves % 2 == 0){
            moveSymbol = 'X';
        }else{moveSymbol = 'O';}


        if(arrayList.size() != 0){
            token = arrayList.get(arrayCounter).split("");
            arrayCounter++;
        }
        for(int i = 0 ; i < token.length; i++){
            if(Character.isLetter(token[i].charAt(0))){
                token[i] = token[i].toUpperCase();
            }

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
    board[indexForBoard] = " " + moveSymbol + " ";
    this.printBoard();
    totalMoves++;
    }


    public static void main(String[] args){
        Board board = new Board();
        while(true){
        board.returnPlayerMove();
        board.setPlayerMove();
        }
    }
}
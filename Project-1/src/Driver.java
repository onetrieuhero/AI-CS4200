
import java.util.*;


public class Driver{

    /*
    * This method is to generate a random puzzle
    * Using random numbers from 0 to 8
    * integers only appearing once
    * @returns random board state
    */
    private int[] getRandomBoard(){

        Integer list[] = {0,1,2,3,4,5,6,7,8};
        int board[] = new int[9];
        do{
        Collections.shuffle(Arrays.asList(list));
            for (int i = 0; i < board.length; i++) {
                board[i] = list[i];
            }
        } while(this.checkValidity(board) == false);
        return board;
    }

    /**
     * @return an user's board state
     *
     * This method gets user inputs and checks to see if the board is valid
     * Exceptions Handling for:
     *      -NumberFormatException
     *      -ArrayIndexOutOfBoundsException
     */

    private int[] getUsersInput(){
        Scanner kb = new Scanner(System.in);
        int[] board = new int[9];

        try {
            String[] token = kb.nextLine().split(" ");
            for (int i = 0; i < token.length; i++) {
                board[i] = Integer.parseInt(token[i]);
            }
            if(this.checkValidity(board) == false){
                System.out.println("The Puzzle Is Not Valid!");
                System.exit(0);
            }
        }
        catch(NumberFormatException e){
            System.out.println("Invalid Input!");
            System.exit(0);
        }
        catch(ArrayIndexOutOfBoundsException i){
            System.out.println("The puzzle can only take in 9 Integers!");
            System.exit(0);
        }

    return board;
    }


    /*
     * @param board
     * @return boolean of validity of board
     *
     * This method checks for the validity of the board
     * range from 0 to 8 and no repeats is valid
     */
    private boolean checkValidity(int[] board){
        HashMap<Integer,Boolean> map = new HashMap<>();
        int inversions = 0;
        for(int i = 0 ; i < board.length;i++){
            if(board[i] > 8 || board[i] < 0){
                return false;
            }
            if(map.containsKey(board[i])){
                return false;
            }
            else {map.put(board[i],true);}
        }

        for(int i = 0 ; i < 8 ; i++){
            if(board[i] == 0 || board[i] == 1) {
				continue;
			}
            for(int j = i + 1; j < 9; j++){
                if((board[i] > board[j]) && (board[j] != 0)){
                    inversions++;
                }
            }
        }
        if(inversions % 2 == 0){return true;}
        else {return false;}
    }


    /**
     * Prompts the user to choose a option and then preform the action
     * Exception Handling for:
     *      - InputMismatchException
     */
    public void prompt(){
        int choice = 0;
        Scanner kb = new Scanner(System.in);

        System.out.println("1: Generate a randomly 8-puzzle problem");
        System.out.println("2: Enter a specific 8-puzzle configuration from 0 to 8");
        try {
            while (choice != 1 && choice != 2) {
                System.out.print("Choose a Option: ");
                choice = kb.nextInt();
                if (choice > 2 || choice < 1) {
                    System.out.println("That Is Not a Valid Choice!");
                }
            }
        }
        catch(InputMismatchException e){
            System.out.println("That Is Not a Valid Choice");
            System.exit(0);
        }

        if(choice == 1){
            int randomBoard[] = this.getRandomBoard();
            this.printPuzzle(randomBoard);
        }
        else{
            int userBoard[] = this.getUsersInput();
            this.printPuzzle(userBoard);
        }
    }

    /**
     * @param array
     * Goes through an Array and prints out the integers
     * in a box to help visualize the puzzle
     */
    private void printPuzzle(int[] board){
        System.out.println("+-------+");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                if (board[3 * i + j] == 0) {
                    System.out.print("  ");
                } else {
                    System.out.print(board[3 * i + j] + " ");
                }
            }
            System.out.println("| ");
        }
        System.out.println("+-------+");
    }

    //TODO
    private void getStats(int n){

    }

    public static void main(String[] args){
        Driver driver = new Driver();
        driver.prompt();
    }
}
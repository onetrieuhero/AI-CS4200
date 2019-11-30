import java.util.Scanner;

public class Driver{
    public static void main(String[] args) {
        Driver driver = new Driver();
        int maxTime = driver.askForTime();
        Board board = new Board();
        board.timeInSec = maxTime;
        boolean aiStarts = board.agentStarts;
        AlphaBeta ab = new AlphaBeta();



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
import java.util.Random;
public class Node implements Comparable<Node>{
    public int[] board;
    public int cost;
    public int nodesGen = 0;

    /**
     * This method generates a random board
     * each value represent a row
     * @param n
     * @return array of length, n 
     */
    private static int[] generateBoard(int n){
        int[] board = new int[n];
        Random random = new Random();
        for(int i = 0 ; i < n; i++){
            board[i] = random.nextInt(n);
        }
        return board;
    }

    public Node(int[] board){
        this.board = board;
        this.cost = countAttackingQueens(board);
        this.nodesGen++;
    }

    public Node(int n){
        this(generateBoard(n));
    }

    private int countAttackingQueens(int [] board){
        int counter = 0;
        for(int i = 0 ; i < board.length; i++){
            for(int j = 0 ; j < i; j++){
                if(board[i] - board[j] == i - j ||
                   board[j] - board[i] == i - j ||
                   board[j] == board[i]){
                       counter++;
                   }
            }
        }
        return counter;
    }
    /**
     * (board[i] * n) + i
        loop over the array of 25
        String[] fullBoard = new String[n*n]
        for (int i = 0; i < board.length; i++)
        int position = (n * board[i]) + i
        fullBoard[position] = "Q"
     */

    private String[] printHelper(){
        String fullBoard[] = new String[this.board.length * this.board.length];
        for(int i = 0 ; i < this.board.length; i++){
            int position = (this.board.length * this.board[i]) + i;
            fullBoard[position] = "Q";
        }

        return fullBoard;
    }

    public void print(){
        String[] array = printHelper();
        for(int i = 0; i < array.length; i++){
            if(i != 0 && i % this.board.length == 0)
                System.out.println();
            if(array[i] == null)
                System.out.print("X ");
            else
                System.out.print("Q ");
        }
        System.out.println();
    }

    public int compareTo(Node node){
        return this.cost - node.cost;
    }
}
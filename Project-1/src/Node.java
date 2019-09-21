
public class Node{
    private int[] board;
    private int moves;
    private int totalCost;

    public Node(int[] board){
        this.board = board;
    }

    /*
    * This method returns the number of misplaced titles 
    * in the board
    * @return number of misplaced tiles
    */
    public int heuristics1(){
        int misplaced = 0;
        for(int i = 0 ; i < this.board.length;i++){
            if(this.board[i] != i){
                misplaced++;
            }
        }
        return misplaced;
    } 

    /**
     * This method finds the sum of distances of the tile from their goal positions
     * 
     * @return the sum of the distances of the tiles from their goal positions
     */
    public int heuristices2(){
        int totalDistance = 0;
        for(int i = 0 ; i < this.board.length; i++){
            int rowDist = Math.abs(i / 3 - this.board[i] / 3);
            int colDist = Math.abs(i / 3 - this.board[i] / 3);
            totalDistance = totalDistance + rowDist + colDist;
        }
        return totalDistance;
    }

    /**
     * This method finds the index of zero in the board
     * @return index of zero in the board
     */
    private int indexOfZero(){
        for(int i = 0 ;i < this.board.length;i++){
            if(this.board[i] == 0){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args){
        int[] board = {1,2,3,4,0,5,6,7,8};
        Node node = new Node(board);
        System.out.println(node.heuristics1());
    }
}
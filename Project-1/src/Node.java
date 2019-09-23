import java.util.HashMap;

public class Node implements Comparable<Node>{
    private int[] board;
    public int moves;
    public int totalCost;
    public int specificConfig;
    public Node parent;

    /**
     * The constructor
     * @param board
     * @param moves count
     * @param parent of node
     */
    public Node(int[] board, int moves, Node parent){
        this.board = board;
        this.moves = moves;
        this.parent = parent;
        this.specificConfig = getSpecificConfig();
    }

    
    /**
     * @param array
     * Goes through an Array and prints out the integers
     * in a box to help visualize the puzzle
     */
    public void print(){
        System.out.println("+-------+");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                if (this.board[3 * i + j] == 0) {
                    System.out.print("  ");
                } else {
                    System.out.print(this.board[3 * i + j] + " ");
                }
            }
            System.out.println("| ");
        }
        System.out.println("+-------+");
    }

        /* If index is from 0 to 2 then blank tile cannot move up
        * +---+ 
        * | 12|
        * |345|
        * |678|
        * +---+
        */

    public Node moveUp(){
        int indexZero = indexOfZero();
        int size = 3;
        HashMap<Integer,Boolean> map = new HashMap<>();

        for(int i = 0 ; i < this.board.length; i++){
            if(indexZero < size){map.put(board[i],false);}
            else{map.put(board[i],true);}
        }

        int indexUp = indexZero - size;
        int newBoard[] = new int[9];
        for(int i = 0 ; i < newBoard.length;i++){
            newBoard[i] = this.board[i];
        }
        if(map.get(0) == false){
            return new Node(this.board, moves, this);
        }
        
        else{
            newBoard[indexZero] = this.board[indexUp];
            newBoard[indexUp] = 0;
            return new Node(newBoard, moves+1, this);
        }
    } 


        /* If index is from 6 to 8 then blank tile cannot move down
        * +---+ 
        * |123|
        * |456|
        * |78 |
        * +---+
        */
    public Node moveDown(){
        int indexZero = indexOfZero();
        int size = 3;
        HashMap<Integer, Boolean> map = new HashMap<>();

        for(int i = 0 ; i < this.board.length; i++){
            if(indexZero >= this.board.length - size){map.put(this.board[i],false);}
            else{map.put(this.board[i],true);}
        }

        int indexDown = indexZero + size;
        int newBoard[] = new int[9];
        for(int i = 0 ; i < newBoard.length; i++){
            newBoard[i] = this.board[i];
        }
        if(map.get(0) == false){
            return new Node(this.board, moves, this);
        }
        else{
            newBoard[indexZero] = this.board[indexDown];
            newBoard[indexDown] = 0;
            return new Node(newBoard, moves + 1, this);
        }
    }

        /* If index is 2, 5 ,8 then blank tile cannot move right
        * +---+ 
        * |123|
        * |456|
        * |78 |
        * +---+
        */

    public Node moveRight(){
        int indexZero = indexOfZero();
        HashMap<Integer, Boolean> map = new HashMap<>();

        for(int i = 0 ; i < this.board.length; i++){
            if(indexZero == 2 || indexZero == 5 || indexZero == 8){map.put(this.board[i],false);}
            else{map.put(this.board[i],true);}
        }

        if(map.get(0) == false){
            return new Node(this.board,moves, this);
        }
        else{
            int indexRight = indexZero + 1;
            int newBoard[] = new int[9];
            for(int i = 0 ; i < newBoard.length; i++){
                newBoard[i] = this.board[i];
            }
            newBoard[indexZero] = this.board[indexRight];
            newBoard[indexRight] = 0;

            return new Node(newBoard,moves + 1, this);
        }
    }
        /* If index is 0, 3, 6 then blank tile cannot move left
        * +---+ 
        * | 12|
        * |345|
        * |678|
        * +---+
        */
    public Node moveLeft(){
        int indexZero = indexOfZero();
        int size = 3;
        HashMap<Integer,Boolean> map = new HashMap<>();
        for(int i = 0 ; i < this.board.length; i++){
            if(indexZero % size == 0){map.put(this.board[i],false);}
            else{map.put(this.board[i],true);}
        }

        int indexLeft = indexZero - 1;
        int newBoard[] = new int[9];

        for(int i = 0 ; i < newBoard.length; i++){
            newBoard[i] = this.board[i];
        }

        if(map.get(0) == false){
            return new Node(this.board, moves, this);
        }
        else{
            newBoard[indexZero] = this.board[indexLeft];
            newBoard[indexLeft] = 0;
            return new Node(newBoard, moves+1, this);
        }
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

    /**
     * This method gets the config of the board state
     * @return the specific config of the board state
     */
    private int getSpecificConfig(){
        String config = "";
        for(int i = 0 ; i < this.board.length; i++){
            config = config + this.board[i];
        }
        return Integer.parseInt(config);
    }

    /**
     * This method Checks to see if the board is at it's goal state
     * @return true if the board is at it's goal state and false if otherwise
     */
    public boolean isGoal(){
        for(int i = 0 ; i < this.board.length; i++){
            if(this.board[i] != i){return false;}
        }
        return true;
    }

    /**
     * This method is to return -1,0,or 1 depending on totalcost compared to the object cost
     * this puts it in the collection's order 
     * @Override
     */
    public int compareTo(Node node){
        return this.totalCost - node.totalCost;
    }
}
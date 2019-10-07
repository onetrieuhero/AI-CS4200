import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

public class Genetic{
    public Node solution;
    public int generations = 0;
    public long totalTime = 0;
    final double MUTATIONPROB = .9;
    final int MAXGEN = 5000;
    

    public Genetic(Node[] board){
        long time = System.nanoTime() / 1000000;
        this.solution = this.begin(board);
        this.totalTime = System.nanoTime() / 1000000 - time;
    }

    private Node begin(Node[] board){
        PriorityQueue<Node> pop = new PriorityQueue<>(Arrays.asList(board));
        return this.geneticAlgo(pop);
    }

    private Node geneticAlgo(PriorityQueue<Node> board){
        Random random = new Random();
        do{
            PriorityQueue<Node> newBoard = new PriorityQueue<>();
            for(int i = 0; i < board.size(); i++){
                Node x = board.poll();
                Node y = board.peek();
                Node[] child = this.reproduce(x, y);
                if(random.nextDouble() < MUTATIONPROB){
                    child[0] = mutate(child[0]);
                }
                if(random.nextDouble() < MUTATIONPROB){
                    child[1] = mutate(child[1]);
                }

                newBoard.offer(child[0]);
                newBoard.offer(child[1]);
            }

            board = newBoard;
        } while(++this.generations < this.MAXGEN && board.peek().cost != 0);

        return board.poll();
    }

    private Node mutate(Node node){
        Random random = new Random();
        int[] mutate = Arrays.copyOf(node.board, node.board.length);
        int position = random.nextInt(mutate.length);
        mutate[position] = random.nextInt(mutate.length);
        return new Node(mutate);
    }

    /**
     * n = Length
     * c = random number from 1 to n
     * @param a parent node
     * @param b parent node
     * @return Node[] new node a and b
     */
    private Node[] reproduce(Node a, Node b){
        Random random = new Random();
        int n = a.board.length;
        int c = random.nextInt(n);

        int[] boardA = new int[n];
        //arraycopy(src, indSrc, dest, indDest, length)
        System.arraycopy(a.board, 0, boardA, 0, c);
        System.arraycopy(b.board, c, boardA, c, n-c);
        Node NodeA = new Node(boardA);

        int[] boardB = new int[n];
        System.arraycopy(b.board, 0, boardB, 0, n);
        System.arraycopy(a.board, c, boardB, c, n-c);
        Node NodeB = new Node(boardB);

        return new Node[] {NodeA, NodeB};
    }

}
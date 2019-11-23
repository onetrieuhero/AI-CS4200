import java.util.Random;
import java.util.Arrays;
import java.util.PriorityQueue;

public class SimulatedAnnealing{
    Node solution;
    double temperature = 1;
    final double DECAYRATE = .99;
    final int MAXGEN = 100000;
    public long totalTime = 0;


    public Node SimulatedAlgo(Node board){
        long time = System.nanoTime() / 1000000;
        Random random = new Random();
        int[] mutate = new int[board.board.length];
        Node node;
        int delta = 0;
        int count = 0;
        do{
            if(board.cost == 0){
                return board;
            } 
            else{
                mutate = Arrays.copyOf(board.board, board.board.length);
                int position = random.nextInt(mutate.length);
                mutate[position] = random.nextInt(mutate.length);
                node = new Node(mutate);
                temperature = temperature * DECAYRATE;
            }
            
            if (node.cost < board.cost){
                board = node;
                delta = node.cost - board.cost;
                temperature = temperature * DECAYRATE;
            }
            else{
                if(random.nextFloat() > Math.exp(-1 * delta/temperature)){
                    board = node;
        
                }
            }
            count++;
        } while(temperature > 0.0000000000001 && count < MAXGEN);
        totalTime = System.nanoTime() / 1000000 - time;
        return board;
    }
}
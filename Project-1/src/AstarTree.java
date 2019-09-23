import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class AstarTree{
    private ArrayList<Node> solution = new ArrayList<>();
    private PriorityQueue<Node> frontierQ = new PriorityQueue<>();

    private int heuristicChoice;
    public int nodesGenerated = 0;
    public long cpuTime = 0;
    
    public AstarTree(Node node, int heuristicChoice){
        this.heuristicChoice = heuristicChoice;
        long timeStart = System.nanoTime() / 1000000;
        addTree(node);
        search();
        this.cpuTime = System.nanoTime() / 1000000 - timeStart;
    }
    
    public void addTree(Node node){
        if(node != null){
            if(heuristicChoice == 1){node.totalCost = node.heuristics1() + node.moves;}
            else{node.totalCost = node.heuristices2() + node.moves;}
            frontierQ.offer(node);
            nodesGenerated++;
        }
    }

    public void search(){
        Node node;
        do{
            node = frontierQ.poll();
            addTree(node.moveUp());
            addTree(node.moveDown());
            addTree(node.moveLeft());
            addTree(node.moveRight());

        } while(node.isGoal() == false);
        getSolution(node);
    }

    private ArrayList<Node> getSolution(Node node){
        solution.add(node);
        while(node.parent != null){
            solution.add(node.parent);
            node = node.parent;
        }

        return solution;
    }

    public void printPuzzle() {
        Collections.reverse(solution);
        for (Node node : solution) {
          node.print();
        }
      }
}
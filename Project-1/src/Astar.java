import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class Astar{
    private ArrayList<Node> solution = new ArrayList<>();
    private PriorityQueue<Node> frontierQ = new PriorityQueue<>();
    private HashSet<Integer> explored = new HashSet<>();
    private HashSet<Integer> frontier = new HashSet<>();

    private int heuristicChoice;
    public int nodesGenerated = 0;
    public long cpuTime = 0;

    

    /**
     * Constructor of A* graph search
     */
    public Astar(Node node, int heuristicChoice){
        this.heuristicChoice = heuristicChoice;
        long timeStart = System.nanoTime() / 1000000;
        addGraph(node);
        searched();
        this.cpuTime = System.nanoTime() /1000000 - timeStart;
    }

    /**
     * This method adds to the priority Queue a node along with the node's specific config
     * into the frontier of the set and increments the nodeGenerated counter by 1
     * @param node
     */
    private void addGraph(Node node){
        //Checks if the config has been seen before
        if(node != null && frontier.contains(node.specificConfig) == false && explored.contains(node.specificConfig) == false){
            if(heuristicChoice == 1){node.totalCost = node.heuristics1() + node.moves;}
            else{node.totalCost = node.heuristices2() + node.moves;}
            frontierQ.offer(node);
            frontier.add(node.specificConfig);
            nodesGenerated++;
        }
    }

    /**
     * This method pulls the first node from the priority Queue and removes it after it has been invoked
     * after it will add to the explored set of the node's config and will preform the actions of 
     * moving the blank tilt up, down, left, right until it has reached the goal state
     */
    public void searched(){
        Node node;
        do{
            node = frontierQ.poll();
            explored.add(node.specificConfig);
            addGraph(node.moveUp());
            addGraph(node.moveDown());
            addGraph(node.moveLeft());
            addGraph(node.moveRight());
        } while(node.isGoal() == false);

        getSolution(node);
    }

    /**
     * This method returns the arrayList of nodes that is the solution and then goes
     * through the path until it is null
     * @param node
     * @return the ArrayList of nodes
     */
    private ArrayList<Node> getSolution(Node node){
        solution.add(node);
        while(node.parent != null){
            solution.add(node.parent);
            node = node.parent;
        }

        return solution;
    }

    /**
     * This method prints the arraylist that was the solution
     * but first it has to reverse the arraylist so that the 
     * solved puzzle is last
     */
    public void printPuzzle(){
        Collections.reverse(solution);
        for(Node node: solution){
            node.print();
        }
    }
}
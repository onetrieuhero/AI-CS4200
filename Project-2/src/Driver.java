import java.util.Scanner;

public class Driver{
    public static void main(String[] args){
        Scanner kb = new Scanner(System.in);
        int choice = 0;
        long totalTime = 0;
        int totalGen = 0;
        double totalSolved = 0;
        do{
            System.out.println("Choose a choice:");
            System.out.println("1: Genetic");
            System.out.println("2: Simulated Annealing");
            System.out.println("3: Exit");
            System.out.print("Your Choice? ");
            choice  = kb.nextInt();

            if(choice == 1){
                System.out.println("Running 500 times");
                for(int i = 0 ; i < 500; i++){
                    Node population[] = new Node[100];
                    for(int j = 0 ; j < population.length;j++){
                        population[j] = new Node(25);
                }
                    Genetic gen = new Genetic(population);
                    Node end = gen.solution;
                    totalTime += gen.totalTime;
                    totalGen += gen.generations;
                    if( end.cost == 0){
                        totalSolved++;
                    }
                if(i == 499){
                    end.print();
                    System.out.println();
                    System.out.println("Average Node Generations: " + totalGen/500);
                    System.out.println("Average Time of 500 runs: " + totalTime/500 + "ms");
                    System.out.println("Solved: " + totalSolved/500 * 100 + "%");
                    totalSolved = 0;
                    totalGen = 0;
                    }   
                }
            }
            if(choice == 2){
                System.out.println("Running 500 Times");
                for(int i = 0 ; i < 500; i++){
                    long time = System.nanoTime() / 1000000;
                    Node population[] = new Node[100];
                    for(int j = 0 ; j < population.length; j++){
                        population[j] = new Node(25);
                    }
                    SimulatedAnnealing sim = new SimulatedAnnealing();
                    for(int j = 0 ; j < population.length; j++){
                        Node node = sim.SimulatedAlgo(population[j]);
                        totalGen += node.cost;
                        totalTime += sim.totalTime;
                        if(node.cost == 0){
                            totalSolved++;
                        }
                        if(i == 499 && j == population.length -1){
                            node.print();
                            System.out.println();
                            System.out.println("Average Generations: " + totalGen/500);
                            System.out.println("Average Time of 500 runs: " + totalTime / 500 + "ms");
                            System.out.println("Solved: " + totalSolved/500 * 100 + "%");
                            totalSolved = 0;
                            totalGen = 0;
                            }   
                    }
                }

            }
        } while(choice != 3);
    }
}
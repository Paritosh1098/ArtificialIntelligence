package com.wpi.ai;

import java.io.*;
import java.util.*;

public class Search {

    public static Queue queue;
    public static Problem problemG;

    public static void main(String[] args) throws IOException {

        if(args == null || args.length <=0) {
            System.out.println("No input file specified, please specify input file name");
            return;
        }



        String inputFile = args[0];


        //generate problem
        Problem problem = generateproblem(inputFile);

        //call search here
        System.out.println("Problem ==>    " + problem);

        String searchmethod = "BFS";
        Boolean searchMethodProvided = false;
        if(args.length > 1) {
           searchmethod = args[1];
           searchMethodProvided = true;
           if(args.length > 2) {
               Problem.depthLimit = Integer.parseInt(args[2]);
           }
        }
        switch(searchmethod) {
            case "BFS":
                System.out.println("Breadth First Search");
                problem = generateproblem(inputFile);
                problemG = problem;
                GeneralSearch(problem, SearchType.BreadthFirstSearch);
                if(searchMethodProvided)
                    break;

            case "DFS":
                System.out.println("\n\n\nDepth First Search");
                problem = generateproblem(inputFile);
                GeneralSearch(problem, SearchType.DepthFirstSearch);
                if(searchMethodProvided)
                    break;
            case "UNIFORM":
                System.out.println("\n\n\n Uniform Cost Search");
                problem = generateproblem(inputFile);
                GeneralSearch(problem, SearchType.UniformCostSearch);
                if(searchMethodProvided)
                    break;

            case "LIMITEDDEPTH":
                System.out.println("\n\n\n Depth Limited Search");
                problem = generateproblem(inputFile);
                GeneralSearch(problem, SearchType.DepthLimitedSearch);
                if(searchMethodProvided)
                    break;

            case "ITERATIVE":
                System.out.println("\n\n\n Iterative Depth Limited Search");
                problem = generateproblem(inputFile);
                for (int depth = 1; depth < 10; depth++) {
                    Problem.depthLimit = depth;
                    boolean solutionFound = GeneralSearch(problem, SearchType.DepthLimitedSearch);
                    if (solutionFound)
                        break;
                    System.out.println("=========");
                    for (Node node : problem.nodes) {
                        node.depth = 0;
                        node.isExplored = false;
                    }
                }
                if(searchMethodProvided)
                    break;
            case "GREEDY":
                System.out.println("\n\n\n Greedy Search");
                problem = generateproblem(inputFile);
                GeneralSearch(problem, SearchType.GreedySearch);
                if(searchMethodProvided)
                    break;

            case "ASTAR":
                System.out.println("\n\n\n Astar Search");
                problem = generateproblem(inputFile);
                GeneralSearch(problem, SearchType.Astar);
                if(searchMethodProvided)
                    break;
            case "BEAM":
                System.out.println("\n\n\n Beam Search");
                problem = generateproblem(inputFile);
                GeneralSearch(problem, SearchType.BeamSearch);
                if(searchMethodProvided)
                    break;

        }
    }

    public static Problem generateproblem(String inputFile) throws IOException {

        File file = new File("./" + inputFile);

        BufferedReader fileReader = new BufferedReader(new FileReader(file));

        Problem problem = new Problem();
        problemG = problem;
        String line = fileReader.readLine();

        //set the initial state
        problem.initialState = problem.getNodeOtherwiseGeneratenew(line.split(" ")[0]);
        while(!line.contains("####")) {

            String[] connection = line.split(" ");

            //add the connection to the problem
            Node srcNode = problem.getNodeOtherwiseGeneratenew(connection[0]);
            Node destNode = problem.getNodeOtherwiseGeneratenew(connection[1]);
            srcNode.addConnection(destNode,Float.parseFloat(connection[2]));
            destNode.addConnection(srcNode,Float.parseFloat(connection[2]));
            line = fileReader.readLine();
        }
        line = fileReader.readLine();
        while(line != null && line.length() !=0) {
            String[] heuristic = line.split(" ");
            problem.heuristics.put(problem.getNodeOtherwiseGeneratenew(heuristic[0]),Float.parseFloat(heuristic[1]));
            line = fileReader.readLine();
        }
        problem.heuristics.put(problem.getNodeOtherwiseGeneratenew("G"),0f);
        return problem;
    }

    public static boolean GeneralSearch(Problem problem, SearchType searchType) {
        boolean solutionFound = false;

        queue = QueueMaker.getQueueHandler(searchType);

        queue.enqueue(problem.initialState);
        System.out.println("Node Visited    Queue Status");
        while(!queue.isEmpty()) {
            ArrayList<Node> queueNodes = new ArrayList<>(queue.getAllElements());
            Node node = queue.deQueue();
            node.isExplored = true;
            System.out.print(node.nodeName + "               ");
            printQueue(queueNodes);

            if(node.isGoalState()) {
                solutionFound = true;
                System.out.println("Solution Found!");
                break;
            }

            List<Connection> children = node.childNodes;
            //insert child nodes accordingly
            queue.enqueue(node,children);

        }
        return solutionFound;
    }

    public static void printQueue(List<Node> queueNodes) {


        System.out.print("[");
        Iterator<Node> iterator = queueNodes.iterator();
        while(iterator.hasNext()) {
            Node parentNode = iterator.next();
            System.out.print("<");
            while(parentNode != null && parentNode != problemG.initialState) {
                System.out.print(parentNode);
                parentNode = parentNode.parentNode;
            }
            System.out.print(problemG.initialState);
            System.out.print(">");
        }
        System.out.println("]");
    }
}

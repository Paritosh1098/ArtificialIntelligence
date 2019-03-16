package com.wpi.ai;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GreedySearchQueue extends Queue {

    ArrayList<Node> queue = new ArrayList<>();

    @Override
    public void enqueue(Node pnode, List<Connection> children) {
        for(Connection connection: children) {
            if(!queue.contains(connection.endNode) && !connection.endNode.isExplored) {
                queue.add(connection.endNode);
                connection.endNode.parentNode = pnode;
            }
        }
    }

    @Override
    public void enqueue(Node node) {
        queue.add(node);
    }

    @Override
    public Node deQueue() {
        Node nodeWithMinimumHeuristics = queue.get(0);
        Iterator<Node> iterator = queue.iterator();
        Float min = Problem.heuristics.get(queue.get(0));
        while(iterator.hasNext()) {
            Node node = iterator.next();
            if(Problem.heuristics.get(node) < min) {
                nodeWithMinimumHeuristics = node;
            }
        }
        queue.remove(nodeWithMinimumHeuristics);
        return nodeWithMinimumHeuristics;
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public List<Node> getAllElements() {
        return queue;
    }
}
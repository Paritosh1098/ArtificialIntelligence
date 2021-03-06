package com.wpi.ai;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AstarSearchQueue extends Queue {

    ArrayList<Node> queue = new ArrayList<>();

    @Override
    public void enqueue(Node pnode, List<Connection> children) {
        for(Connection connection: children) {
            if(!queue.contains(connection.endNode) && !connection.endNode.isExplored) {
                queue.add(connection.endNode);
                connection.endNode.parentNode = pnode;
                connection.endNode.costToReachfromSource = pnode.costToReachfromSource + connection.cost;
            }
        }
    }

    @Override
    public void enqueue(Node node) {
        queue.add(node);
    }

    @Override
    public Node deQueue() {
        Node nodeWithMinimumHeuristicsCost = queue.get(0);
        Iterator<Node> iterator = queue.iterator();
        Float minCost = Problem.heuristics.get(queue.get(0)) + queue.get(0).costToReachfromSource;
        while(iterator.hasNext()) {
            Node node = iterator.next();
            if(Problem.heuristics.get(node) + node.costToReachfromSource < minCost) {
                nodeWithMinimumHeuristicsCost = node;
            }
        }
        queue.remove(nodeWithMinimumHeuristicsCost);
        return nodeWithMinimumHeuristicsCost;
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

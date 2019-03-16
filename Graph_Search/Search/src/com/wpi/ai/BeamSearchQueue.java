package com.wpi.ai;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BeamSearchQueue extends Queue {

    ArrayList<Node> queue = new ArrayList<>();

    @Override
    public void enqueue(Node pnode, List<Connection> children) {

        List<Connection> newChildren = new ArrayList<>();

        for(Connection connection: children) {
            if(!queue.contains(connection.endNode) && !connection.endNode.isExplored) {
                newChildren.add(connection);
            }
        }

        //get two best from newChildren
        List<Node> newNodeList = new ArrayList<>();
        Iterator<Connection> iter = newChildren.iterator();
        while(iter.hasNext()) {
            newNodeList.add(iter.next().endNode);
        }
        Node[] newNodes = getTwoBest(newNodeList);
        if(newNodes != null) {
            for (int i = 0; i < newNodes.length; i++) {
                queue.add(newNodes[i]);
                newNodes[i].parentNode = pnode;
            }
        }
        //if length of queue is 4, keep the best two
        if(queue.size() >= 4) {
            Node[] nodesToKeep = getTwoBest(queue);
            queue.removeAll(queue);
            queue.add(nodesToKeep[0]);
            queue.add(nodesToKeep[1]);
            nodesToKeep[0].parentNode = pnode;
            nodesToKeep[1].parentNode = pnode;
        }



    }

    public Node[] getTwoBest(List<Node> newChildren) {
        if(newChildren.size() <=0)
            return null;

        if(newChildren.size() < 2 && newChildren.size() > 0) {
            Node[] list = new Node[1];
            list[0] = newChildren.get(0);
            return list;
        }

        Iterator<Node> iterator = newChildren.iterator();
        Node firstBest = newChildren.get(0);
        Node secondBest = newChildren.get(1);
        if(Problem.heuristics.get(firstBest) > Problem.heuristics.get(secondBest)) {
            Node temp = firstBest;
            firstBest = secondBest;
            secondBest = temp;
        }

        while(iterator.hasNext()) {
            Node node = iterator.next();
            if(Problem.heuristics.get(firstBest) > Problem.heuristics.get(node)) {
                firstBest = node;
            }
            else if(Problem.heuristics.get(secondBest) > Problem.heuristics.get(node) && Problem.heuristics.get(node) > Problem.heuristics.get(firstBest)) {
                secondBest = node;
            }
        }
        Node[] list = new Node[2];
        list[0] = firstBest;
        list[1] = secondBest;
        return list;
    }
    @Override
    public void enqueue(Node node) {
        queue.add(node);
    }

    @Override
    public Node deQueue() {
        return queue.remove(0);
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




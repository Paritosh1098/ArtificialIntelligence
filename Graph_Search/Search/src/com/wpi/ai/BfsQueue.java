package com.wpi.ai;

import java.util.*;

public class BfsQueue extends Queue {

    LinkedList<Node> queue = new LinkedList<>();
    Node lastParent = null;

    public boolean contains(Node node) {
        Iterator<Node> iterator = queue.iterator();
        while(iterator.hasNext()) {
            if(iterator.next().equals(node))
                return true;
        }
        return false;
    }
    @Override
    public void enqueue(Node pnode, List<Connection> children) {

        children.sort(new ChildComparator());

        for(Connection connection: children) {
            if(!queue.contains(connection.endNode) && !connection.endNode.isExplored) {
                queue.add(connection.endNode);
                connection.endNode.parentNode = pnode;
            }
        }
    }

    @Override
    public void enqueue(Node node) {
        if(!queue.contains(node) && !node.isExplored)
            queue.add(node);
            //System.out.println("Added to queue " + node.nodeName);
    }

    @Override
    public Node deQueue() {
        lastParent = queue.remove();
        return lastParent;
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public List<Node> getAllElements() {
        return queue;
    }

    public class ChildComparator implements Comparator<Connection> {

        @Override
        public int compare(Connection c1, Connection c2) {
            return c1.endNode.nodeName.compareTo(c2.endNode.nodeName);
        }
    }
}

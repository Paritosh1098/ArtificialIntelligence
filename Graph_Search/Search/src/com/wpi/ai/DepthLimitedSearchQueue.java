package com.wpi.ai;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DepthLimitedSearchQueue extends Queue {

    int lastElementDepth = -1;

    ArrayList<Node> stack = new ArrayList<>();

    @Override
    public void enqueue(Node pnode, List<Connection> children) {

        if(pnode.depth >= Problem.depthLimit)
            return;

        children.sort(new ChildComparator());

        for(Connection connection: children) {
            if(!stack.contains(connection.endNode) && !connection.endNode.isExplored) {
                stack.add(connection.endNode);
                connection.endNode.depth = pnode.depth + 1;
                connection.endNode.parentNode = pnode;
            }
            //System.out.print(connection.endNode.nodeName + " inserted in queue");
        }
    }

    @Override
    public void enqueue(Node node) {

        if(lastElementDepth >= Problem.depthLimit)
            return;

        if(!stack.contains(node) && !node.isExplored) {
            stack.add(node);
        }
    }

    @Override
    public Node deQueue() {
        Node node = stack.remove(0);
        lastElementDepth = node.depth;
        return node;
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public List<Node> getAllElements() {
        return stack;
    }

    public class ChildComparator implements Comparator<Connection> {

        @Override
        public int compare(Connection c1, Connection c2) {
            return c1.endNode.nodeName.compareTo(c2.endNode.nodeName);
        }
    }
}

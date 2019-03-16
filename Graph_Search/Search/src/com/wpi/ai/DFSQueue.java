package com.wpi.ai;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class DFSQueue extends Queue {

    ArrayList<Node> stack = new ArrayList<>();

    @Override
    public void enqueue(Node pnode, List<Connection> children) {
        children.sort(new ChildComparator());

        for(Connection connection: children) {
            if(!stack.contains(connection.endNode) && !connection.endNode.isExplored) {
                stack.add(connection.endNode);
                connection.endNode.parentNode = pnode;
            }
        }
    }

    @Override
    public void enqueue(Node node) {
        if(!stack.contains(node) && !node.isExplored)
            stack.add(node);
    }

    @Override
    public Node deQueue() {
        return stack.remove(stack.size() - 1);
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
            return c2.endNode.nodeName.compareTo(c1.endNode.nodeName);
        }
    }
}

package com.wpi.ai;

import java.util.*;

public class UniformCostSearchQueue extends Queue {

    java.util.Queue<NodeWithCost> queue = new PriorityQueue<>();
    NodeWithCost lastNodeWithCost = null;
    public class NodeWithCost implements Comparable<NodeWithCost>{
        Node node;
        Float cost;
        public NodeWithCost(Node node, Float cost) {
            this.cost = cost;
            this.node = node;
        }

        @Override
        public int compareTo(NodeWithCost n2) {
            if(this.cost < n2.cost)
                return -1;
            else if (this.cost > n2.cost)
                return 1;
            return 0;
        }

        @Override
        public boolean equals(Object obj) {
            NodeWithCost enode = (NodeWithCost)obj;
            if(this.node.equals(enode.node))
                return true;
            return false;
        }
    }

    @Override
    public void enqueue(Node pnode, List<Connection> children) {
        List<NodeWithCost> list = new ArrayList<>();
        for(Connection connection: children) {
            list.add(new NodeWithCost(connection.endNode,connection.cost + lastNodeWithCost.cost));
        }

        Collections.sort(list);
        //Collections.reverse(list);

        for(NodeWithCost nodeC: list) {
            if(nodeC.node.isExplored)
                continue;

            if(!queue.contains(nodeC)) {
                queue.add(nodeC);
                nodeC.node.parentNode = pnode;
            }
            else {
                //check the cost of previous present node
                Iterator iterator = queue.iterator();

                while(iterator.hasNext()) {
                   NodeWithCost fromQueue =  (NodeWithCost)iterator.next();
                   if(fromQueue.equals(nodeC)) {
                       if (nodeC.cost < fromQueue.cost) {
                           queue.remove(fromQueue);
                           queue.add(nodeC);
                           nodeC.node.parentNode = pnode;
                       }
                       break;
                   }
                }


            }
        }
    }

    @Override
    public void enqueue(Node node) {
        NodeWithCost nodeC = new NodeWithCost(node,(float)0);
        queue.add(nodeC);

    }

    @Override
    public Node deQueue() {
        lastNodeWithCost = queue.remove();
        return lastNodeWithCost.node;
    }

    @Override
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    @Override
    public List<Node> getAllElements() {
        List<Node> listOfNodes = new ArrayList<>();
        Iterator<NodeWithCost> iterator = queue.iterator();
        while(iterator.hasNext()) {
            listOfNodes.add(iterator.next().node);
        }
        return listOfNodes;
    }
}

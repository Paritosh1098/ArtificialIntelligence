package com.wpi.ai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Node {

    String nodeName;

    boolean isExplored = false;

    int depth = 0;

    Node parentNode;

    Float costToReachfromSource = 0f;

    //map of connected nodes with distances
    List<Connection> childNodes = new ArrayList<>();

    public Node(String nodeName) {
        this.nodeName = nodeName;
    }

    public Node() {
    }


    public void addConnection(Node destNode, Float cost) {
        childNodes.add(new Connection(destNode,cost));
    }


    public boolean isGoalState() {
        if(nodeName.equals("G") || nodeName.equals('g')) {
            return true;
        }

        return false;
    }

    @Override
    public boolean equals(Object node) {

        if( nodeName.equals( ((Node)node).nodeName ) )
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return nodeName;
    }
}

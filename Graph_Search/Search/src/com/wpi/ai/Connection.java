package com.wpi.ai;

public class Connection {

    Node endNode;
    Float cost;

    public Connection(Node endNode, Float cost) {
        this.endNode = endNode;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "" + endNode + cost;
    }
}

package com.wpi.ai;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class Problem {

    Node initialState;
    HashSet<Node> nodes = new HashSet<>();
    public static Map<Node,Float> heuristics = new HashMap<>();
    static int depthLimit = 2;

    public Node getNodeOtherwiseGeneratenew(String nodeName) {

        Node dummynode = new Node(nodeName);

        if(!nodes.contains(dummynode)) {
            Iterator<Node> iterator = nodes.iterator();
            while(iterator.hasNext()) {
                Node node = iterator.next();
                if(node.equals(dummynode))
                    return node;
            }
        }
        Node newNode = new Node(nodeName);
        nodes.add(newNode);
        return newNode;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Node node : nodes) {
            sb.append(node + " " + node.childNodes);
        }
        return sb.toString();
    }
}

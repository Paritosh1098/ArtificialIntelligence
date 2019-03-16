package com.wpi.ai;

import java.util.*;

public abstract class Queue {

        public abstract void enqueue(Node node, List<Connection> children) ;

        public abstract void enqueue(Node node) ;

        public abstract Node deQueue();

        public abstract boolean isEmpty();

        public abstract List<Node> getAllElements();


}

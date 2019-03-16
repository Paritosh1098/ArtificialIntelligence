package com.wpi.ai;

public class QueueMaker {

    public static Queue getQueueHandler(SearchType type) {

        Queue queue = null;

        switch(type) {
            case BreadthFirstSearch:
                queue = new BfsQueue();
                break;
            case DepthFirstSearch:
                queue = new DFSQueue();
                break;
            case DepthLimitedSearch:
                queue = new DepthLimitedSearchQueue();
                break;
            case UniformCostSearch:
                queue = new UniformCostSearchQueue();
                break;
            case GreedySearch:
                queue = new GreedySearchQueue();
                break;
            case Astar:
                queue = new AstarSearchQueue();
                break;
            case BeamSearch:
                queue = new BeamSearchQueue();
                break;
            default:
                break;

        }

        return queue;
    }
}

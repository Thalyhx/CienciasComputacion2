package grafos.algoritmos;

import grafos.representaciones.AdjacencyListGraph;

import java.util.*;

public class Dijkstra {

    private final PriorityQueue<NodeCost> nodesInQueue = new PriorityQueue<>();
    private final Map<String, Double> shortestPaths = new HashMap<>();

    public Dijkstra(AdjacencyListGraph AdjacencyList, String rootNode) {
        findShortestPath(rootNode, AdjacencyList);
    }

    private static class NodeCost implements Comparable<NodeCost> {
        String nodeKey;
        double cost;

        public NodeCost(String nodeKey, double cost) {
            this.nodeKey = nodeKey;
            this.cost = cost;
        }

        @Override
        public int compareTo(NodeCost other) {
            return Double.compare(this.cost, other.cost);
        }
    }

    private void findShortestPath(String rootNodeKey, AdjacencyListGraph AdjacencyList) {
        shortestPaths.put(rootNodeKey, 0.0);
        nodesInQueue.offer(new NodeCost(rootNodeKey, 0.0));

        while (!nodesInQueue.isEmpty()) {
            NodeCost current = nodesInQueue.poll();

            if (current.cost > shortestPaths.get(current.nodeKey)) {
                continue;
            }
            Map<String, Double> neighborsWithWeights = AdjacencyList.getNeighborsWithWeights(current.nodeKey);

            for (Map.Entry<String, Double> entry : neighborsWithWeights.entrySet()) {
                String neighborKey = entry.getKey();
                Double weight = entry.getValue();

                if (!shortestPaths.containsKey(neighborKey) || current.cost + weight < shortestPaths.get(neighborKey)) {
                    shortestPaths.put(neighborKey, current.cost + weight);
                    nodesInQueue.offer(new NodeCost(neighborKey, current.cost + weight));
                }
            }
        }
    }

    public Map<String, Double> getShortestPaths() {
        return shortestPaths;
    }
}

package data;

import java.util.*;

public class Graph {
    private Map<String, List<String>> adjList;

    public Graph() {
        adjList = new HashMap<>();
    }

    public void addNode(String node) {
        adjList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(String node1, String node2) {
        adjList.get(node1).add(node2);
        adjList.get(node2).add(node1); // Undirected graph
    }

    public List<String> getNeighbors(String node) {
        return adjList.getOrDefault(node, new ArrayList<>());
    }

    public void display() {
        for (String node : adjList.keySet()) {
            System.out.println(node + " -> " + adjList.get(node));
        }
    }
}

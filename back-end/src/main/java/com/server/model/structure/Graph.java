package com.server.model.structure;

import java.util.ArrayList;
import java.util.List;

public class Graph {

    private static final int MAX = 1000;

    private final List<Node> nodes;
    private int[][] adjacency;
    private int[][] adjacencyAux;
    private Node[][] verticesAux;

    public Graph() {
        nodes = new ArrayList<Node>();
    }

    public void add(Node node) {
        if (contains(node)) {
            return;
        }
        nodes.add(node);
        int[][] auxiliar = adjacency;
        adjacency = new int[nodes.size()][nodes.size()];
        if (auxiliar == null) {
            auxiliar = adjacency;
        }
        for (int i = 0; i < adjacency.length; i++) {
            int[] is = adjacency[i];
            for (int j = 0; j < is.length; j++) {
                is[j] = MAX;
                if (i < auxiliar.length && j < auxiliar.length) {
                    is[j] = auxiliar[i][j];
                }
            }
        }
    }

    public void addPath(Node nodeA, Node nodeB, int weight) {
        if (contains(nodeA) && contains(nodeB) && !containsPath(nodeA, nodeB)) {
            adjacency[nodes.indexOf(nodeA)][nodes.indexOf(nodeB)] = weight;
        }
    }

    public Node getNodeByValue(String value) {
        return nodes.stream()
            .filter(n -> n.getValue().equals(value))
            .findFirst()
            .orElse(null);
    }

    public void remove(Node node) {
        if (contains(node)) {
            int nodeIndex = nodes.indexOf(node);
            int[][] newAdjacency = new int[nodes.size()][nodes.size()];
            for (int i = nodeIndex; i < nodes.size() - 1; i++) {
                for (int j = 0; j < nodes.size(); j++) {
                    adjacency[i][j] = adjacency[i + 1][j];
                }
            }
            for (int i = 0; i < nodes.size(); i++) {
                for (int j = nodeIndex; j < nodes.size() - 1; j++) {
                    adjacency[i][j] = adjacency[i][j + 1];
                }
            }
            for (int i = 0; i < nodes.size(); i++) {
                for (int j = 0; j < nodes.size(); j++) {
                    newAdjacency[i][j] = adjacency[i][j];
                }
            }
            adjacency = newAdjacency;
            nodes.remove(nodeIndex);
        }
    }

    public boolean contains(Node node) {
        return nodes.contains(node);
    }

    public boolean containsPath(Node nodeA, Node nodeB) {
        int nodeAIndex = nodes.indexOf(nodeA);
        int nodeBIndex = nodes.indexOf(nodeB);
        if (nodeAIndex != -1 && nodeBIndex != -1) {
            return adjacency[nodeAIndex][nodeBIndex] != MAX;
        }
        return false;
    }

    public List<Node> getAdjacencyList(Node node) {
        List<Node> nodes = new ArrayList<>();
        if (contains(node)) {
            int pos = this.nodes.indexOf(node);
            for (int i = 0; i < this.nodes.size(); i++) {
                if (this.adjacency[pos][i] != MAX) {
                    nodes.add(this.nodes.get(i));
                }
            }
        }
        return nodes;
    }

    public void floyd() {
        adjacencyAux = adjacency;
        verticesAux = new Node[nodes.size()][nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++) {
                for (int k = 0; k < nodes.size(); k++) {
                    if (adjacencyAux[j][k] > (adjacencyAux[j][i] + adjacencyAux[i][k])) {
                        adjacencyAux[j][k] = adjacencyAux[j][i] + adjacencyAux[i][k];
                        verticesAux[j][k] = nodes.get(i);
                    }
                }
            }
        }
    }

    public List<Node> minimumPath(Node nodeA, Node nodeB) {
        List<Node> path = new ArrayList<>();
        if (contains(nodeA) && contains(nodeB)) {
            int nodeAIndex = nodes.indexOf(nodeA);
            int nodeBIndex = nodes.indexOf(nodeB);
            if (verticesAux[nodeAIndex][nodeBIndex] != null) {
                Node current = verticesAux[nodeAIndex][nodeBIndex];
                List<Node> left = minimumPath(nodeA, current);
                List<Node> right = minimumPath(current, nodeB);
                left.addAll(right);
                path = left;
            } else if (!containsPath(nodeA, nodeB)) {
                return path;
            } else {
                path.add(nodeA);
                path.add(nodeB);
            }
        }
        return path;
    }
}
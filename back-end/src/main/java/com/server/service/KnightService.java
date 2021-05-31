package com.server.service;

import com.server.model.structure.Graph;
import com.server.model.structure.Node;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class KnightService {

    public List<Node> knightTour(Graph graph, Node node, boolean fix) {
        List<Node> adjacency = new ArrayList<>();
        knightTour(graph, node, adjacency, fix);
        return adjacency;
    }

    private void knightTour(Graph graph, Node node, List<Node> adjacency, boolean fix) {
        List<Node> currentAdjacency = graph.getAdjacencyList(node);
        adjacency.add(node);
        graph.remove(node);
        List<Integer> distances = new ArrayList<Integer>();
        int n = currentAdjacency.size();
        for (int i = 0; i < n; i++) {
            distances.add(graph.getAdjacencyList(currentAdjacency.get(i)).size());
        }
        if (n != 0) {
            int lowerDistance = distances.get(0);
            int pos = 0;
            for (int i = 1; i < currentAdjacency.size(); i++) {
                if (fix) {
                    if (lowerDistance >= distances.get(i)) {
                        lowerDistance = distances.get(i);
                        pos = i;
                    }
                } else {
                    if (lowerDistance > distances.get(i)) {
                        lowerDistance = distances.get(i);
                        pos = i;
                    }
                }
            }
            knightTour(graph, currentAdjacency.get(pos), adjacency, false);
        }
    }
}

package com.server.service;

import com.server.model.PieceType;
import com.server.model.structure.Graph;
import com.server.model.structure.Node;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChessService {

    private final GraphService graphService;
    private final KnightService knightService;

    public ChessService(GraphService graphService, KnightService knightService) {
        this.graphService = graphService;
        this.knightService = knightService;
    }

    public List<Node> capturePiece(PieceType pieceType, String positionA, String positionB, int boardSize) {
        Graph graph = graphService.buildGraph(boardSize, pieceType);

        Node nodePosA = graph.getNodeByValue(positionA);
        Node nodePosB = graph.getNodeByValue(positionB);

        graph.floyd();

        return graph.minimumPath(nodePosA, nodePosB);
    }

    public List<Node> knightTour(String position, int boardSize) {
        Graph graph = graphService.buildGraph(boardSize, PieceType.KNIGHT);

        Node nodePos = graph.getNodeByValue(position);

        return knightService.knightTour(graph, nodePos, false);
    }
}

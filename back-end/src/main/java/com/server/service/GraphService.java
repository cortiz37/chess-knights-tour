package com.server.service;

import com.server.model.PieceType;
import com.server.model.structure.Graph;
import com.server.model.structure.Node;
import org.springframework.stereotype.Service;

@Service
public class GraphService {

    public Graph buildGraph(int size, PieceType pieceType) {
        Graph graph = initGraph(size);

        switch (pieceType) {
            case PAWN:
                initPawn(size, graph);
                break;
            case BISHOP:
                initBishop(size, graph);
                break;
            case KNIGHT:
                initKnight(size, graph);
                break;
            case ROOK:
                initRook(size, graph);
                break;
            case KING:
                initKing(size, graph);
                break;
            case QUEEN:
                initQueen(size, graph);
                break;
        }

        return graph;
    }

    private Graph initGraph(int size) {
        Graph graph = new Graph();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                graph.add(new Node(i + "," + j));
            }
        }
        return graph;
    }

    private void initPawn(int size, Graph graph) {
        int x = 0;
        while (x < size) {
            for (int i = 0, j = 1; i < size - 1; i++, j++) {
                graph.addPath(graph.getNodeByValue(i + "," + x), graph.getNodeByValue(j + "," + x), 1);
            }
            x++;
        }
    }

    private void initBishop(int size, Graph graph) {
        int x = 0;
        while (x < size - 1) {
            for (int i = 0; i < size - 1; i++) {
                for (int j = i + 1; j < size; j++) {
                    String b = "" + (x + j - i);
                    String c = "" + (size - 1 - i);
                    String d = "" + (size - 1 - j);
                    String e = "" + (x + (size - 1 - i) - (size - 1 - j));
                    graph.addPath(graph.getNodeByValue(x + "," + i), graph.getNodeByValue(b + "," + j), 1);
                    graph.addPath(graph.getNodeByValue(b + "," + j), graph.getNodeByValue(x + "," + i), 1);
                    graph.addPath(graph.getNodeByValue(x + "," + c), graph.getNodeByValue(e + "," + d), 1);
                    graph.addPath(graph.getNodeByValue(e + "," + d), graph.getNodeByValue(x + "," + c), 1);
                }
            }
            x++;
        }
    }

    private void initKnight(int size, Graph graph) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                String pos1i = "" + (i + 1);
                String pos2i = "" + (i - 1);
                String pos1j = "" + (j + 1);
                String pos2j = "" + (j - 1);
                String pos3i = "" + (i + 2);
                String pos4i = "" + (i - 2);
                String pos3j = "" + (j + 2);
                String pos4j = "" + (j - 2);
                int x1 = i + 1;
                int x2 = i - 1;
                int y1 = j + 1;
                int y2 = j - 1;
                int x3 = i + 2;
                int x4 = i - 2;
                int y3 = j + 2;
                int y4 = j - 2;
                if (x3 < size && y1 < size) {
                    graph.addPath(graph.getNodeByValue(i + "," + j), graph.getNodeByValue(pos3i + "," + pos1j), 1);
                }
                if (x1 < size && y3 < size) {
                    graph.addPath(graph.getNodeByValue(i + "," + j), graph.getNodeByValue(pos1i + "," + pos3j), 1);
                }
                if (x4 >= 0 && y2 >= 0) {
                    graph.addPath(graph.getNodeByValue(i + "," + j), graph.getNodeByValue(pos4i + "," + pos2j), 1);
                }
                if (x2 >= 0 && y4 >= 0) {
                    graph.addPath(graph.getNodeByValue(i + "," + j), graph.getNodeByValue(pos2i + "," + pos4j), 1);
                }
                if (x1 < size && y4 >= 0) {
                    graph.addPath(graph.getNodeByValue(i + "," + j), graph.getNodeByValue(pos1i + "," + pos4j), 1);
                }
                if (x4 >= 0 && y1 < size) {
                    graph.addPath(graph.getNodeByValue(i + "," + j), graph.getNodeByValue(pos4i + "," + pos1j), 1);
                }
                if (x3 < size && y2 >= 0) {
                    graph.addPath(graph.getNodeByValue(i + "," + j), graph.getNodeByValue(pos3i + "," + pos2j), 1);
                }
                if (x2 >= 0 && y3 < size) {
                    graph.addPath(graph.getNodeByValue(i + "," + j), graph.getNodeByValue(pos2i + "," + pos3j), 1);
                }
                if (x2 >= 0 && y4 >= 0) {
                    graph.addPath(graph.getNodeByValue(i + "," + j), graph.getNodeByValue(pos2i + "," + pos4j), 1);
                }
                if (x4 >= 0 && y2 >= 0) {
                    graph.addPath(graph.getNodeByValue(i + "," + j), graph.getNodeByValue(pos4i + "," + pos2j), 1);
                }
            }
        }
    }

    private void initRook(int size, Graph graph) {
        int x = 0;
        while (x < size) {
            for (int i = 0; i < size - 1; i++) {
                for (int j = i + 1; j < size; j++) {
                    graph.addPath(graph.getNodeByValue(x + "," + i), graph.getNodeByValue(x + "," + j), 1);
                    graph.addPath(graph.getNodeByValue(x + "," + j), graph.getNodeByValue(x + "," + i), 1);
                    graph.addPath(graph.getNodeByValue(i + "," + x), graph.getNodeByValue(j + "," + x), 1);
                    graph.addPath(graph.getNodeByValue(j + "," + x), graph.getNodeByValue(i + "," + x), 1);
                }
            }
            x++;
        }
    }

    private void initKing(int size, Graph graph) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                String pos1i = "" + (i + 1);
                String pos2i = "" + (i - 1);
                String pos1j = "" + (j + 1);
                String pos2j = "" + (j - 1);
                if ((i + 1) < size) {
                    graph.addPath(graph.getNodeByValue(i + "," + j), graph.getNodeByValue(pos1i + "," + j), 1);
                }
                if ((i + 1) < size && (j + 1) < size) {
                    graph.addPath(graph.getNodeByValue(i + "," + j), graph.getNodeByValue(pos1i + "," + pos1j), 1);
                }
                if ((i + 1) < size && (j - 1) >= 0) {
                    graph.addPath(graph.getNodeByValue(i + "," + j), graph.getNodeByValue(pos1i + "," + pos2j), 1);
                }
                if ((i - 1) >= 0 && (j - 1) >= 0) {
                    graph.addPath(graph.getNodeByValue(i + "," + j), graph.getNodeByValue(pos2i + "," + pos2j), 1);
                }
                if ((i - 1) >= 0 && (j + 1) < size) {
                    graph.addPath(graph.getNodeByValue(i + "," + j), graph.getNodeByValue(pos2i + "," + pos1j), 1);
                }
                if ((i - 1) >= 0) {
                    graph.addPath(graph.getNodeByValue(i + "," + j), graph.getNodeByValue(pos2i + "," + j), 1);
                }
                if ((j + 1) < size) {
                    graph.addPath(graph.getNodeByValue(i + "," + j), graph.getNodeByValue(i + "," + pos1j), 1);
                }
                if ((i + 1) < size && (j + 1) < size) {
                    graph.addPath(graph.getNodeByValue(i + "," + j), graph.getNodeByValue(pos1i + "," + pos1j), 1);
                }
                if ((i - 1) >= 0 && (j + 1) < size) {
                    graph.addPath(graph.getNodeByValue(i + "," + j), graph.getNodeByValue(pos2i + "," + pos1j), 1);
                }
                if ((j - 1) >= 0) {
                    graph.addPath(graph.getNodeByValue(i + "," + j), graph.getNodeByValue(i + "," + pos2j), 1);
                }
            }
        }
    }

    private void initQueen(int size, Graph graph) {
        int x = 0;
        while (x < size - 1) {
            for (int i = 0; i < size - 1; i++) {
                for (int j = i + 1; j < size; j++) {
                    String b = "" + (x + j - i);
                    String c = "" + (size - 1 - i);
                    String d = "" + (size - 1 - j);
                    String e = "" + (x + (size - 1 - i) - (size - 1 - j));
                    graph.addPath(graph.getNodeByValue(x + "," + i), graph.getNodeByValue(b + "," + j), 1);
                    graph.addPath(graph.getNodeByValue(b + "," + j), graph.getNodeByValue(x + "," + i), 1);
                    graph.addPath(graph.getNodeByValue(x + "," + c), graph.getNodeByValue(e + "," + d), 1);
                    graph.addPath(graph.getNodeByValue(e + "," + d), graph.getNodeByValue(x + "," + c), 1);
                }
            }
            x++;
        }
    }
}

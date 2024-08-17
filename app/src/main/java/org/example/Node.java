package org.example;

import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node> {

    int row, col;
    double distance;
    Node previous;
    List<Node> neighbors;
    boolean isObstacle;

    public Node(int row, int col) {
        this.row = row;
        this.col = col;
        this.distance = Double.MAX_VALUE;
        this.neighbors = new ArrayList<>();
        this.isObstacle = false;
    }

    public void addNeighbor(Node neighbor) {
        if (!neighbor.isObstacle) this.neighbors.add(neighbor);
    }

    @Override
    public int compareTo(Node other) {
        return Double.compare(this.distance, other.distance);
    }

    @Override
    public String toString() {
        return "Node{" +
                "col=" + col +
                ", row=" + row +
                '}';
    }
}

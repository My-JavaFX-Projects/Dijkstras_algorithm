package org.example;

import java.util.PriorityQueue;

public class DijkstraAlgorithm {
    public static void findShortestPath(Node start, Node end) {
        System.out.println("starting algo");
        PriorityQueue<Node> queue = new PriorityQueue<>();
        start.distance = 0;
        queue.add(start);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            if (current == end) break;
            for (Node neighbor : current.neighbors) {
                double altDist = current.distance + 1;
                if (altDist < neighbor.distance) {
                    neighbor.distance = altDist;
                    neighbor.previous = current;
                    queue.add(neighbor);
                }
            }
        }
    }
}

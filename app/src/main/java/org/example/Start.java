package org.example;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;



public class Start extends Application {
    private static final int GRID_SIZE = 20;
    private static final int CELL_SIZE = 25;
    private Rectangle[][] grid = new Rectangle[GRID_SIZE][CELL_SIZE];
    private Node startNode;
    private Node endNode;
    private boolean setStartPoint = false;
    private boolean setEndPoint = false;

    @Override
    public void start(Stage stage) throws Exception {
        BackgroundMusic backgroundMusic = new BackgroundMusic();
        new Thread(backgroundMusic).start();
        GridPane gridPane = new GridPane();

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Rectangle cell = new Rectangle(CELL_SIZE, CELL_SIZE);
                grid[row][col] = cell;
                int finalRow = row;
                int finalCol = col;
                cell.setFill(Color.WHITE);
                cell.setStroke(Color.BLACK);
                gridPane.add(cell, row, col);
                cell.setOnMouseClicked(mouseEvent -> handleCellClick(cell, finalRow,finalCol));
            }
        }
        Button findPathButton = new Button("Find Path");
        Button setStartPoint = new Button("Set Start Point");
        Button setEndPoint = new Button("Set End Point");
        findPathButton.setOnAction(event -> startPathFinding());
        setStartPoint.setOnAction(event -> setStartPointValue());
        setEndPoint.setOnAction(event -> setEndPointValue());
        gridPane.add(findPathButton, 0, GRID_SIZE, GRID_SIZE, 1);
        gridPane.add(setStartPoint, 5, GRID_SIZE, GRID_SIZE, 2);
        gridPane.add(setEndPoint, 10, GRID_SIZE, GRID_SIZE, 3);
        stage.setScene(new Scene(gridPane));
        stage.setTitle("PathFinding");
        stage.show();
    }

    public void setStartPointValue() {
        this.setStartPoint = true;
        this.setEndPoint = false;
    }
    public void setEndPointValue() {
        this.setStartPoint = false;
        this.setEndPoint = true;
    }

    public void handleCellClick(Rectangle cell,int row,int col) {
        if (startNode == null && setStartPoint) {
            cell.setFill(Color.BLUE);  // Start node
            startNode = new Node(row, col);
        } else if (endNode == null && !cell.equals(startNode) && setEndPoint) {
            cell.setFill(Color.RED);  // End node
            endNode = new Node(row, col);
        } else if (cell.getFill() == Color.WHITE) {
            cell.setFill(Color.BLACK);  // Obstacle
        } else if (cell.getFill() == Color.BLACK) {
            cell.setFill(Color.WHITE);  // Free path
        }

    }

    private void startPathFinding() {
        if (startNode == null || endNode == null) return;

        Node[][] nodes = new Node[GRID_SIZE][GRID_SIZE];

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                nodes[row][col] = new Node(row, col);
                if (grid[row][col].getFill() == Color.BLACK) {
                    nodes[row][col].isObstacle = true;
                }
            }
        }
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                Node node = nodes[row][col];
                if (row > 0) node.addNeighbor(nodes[row - 1][col]);
                if (row < GRID_SIZE - 1)node.addNeighbor(nodes[row + 1][col]);
                if (col > 0) node.addNeighbor(nodes[row][col - 1]);
                if (col < GRID_SIZE - 1)node.addNeighbor(nodes[row][col + 1]);
            }
        }

        DijkstraAlgorithm.findShortestPath(nodes[startNode.row][startNode.col], nodes[endNode.row][endNode.col]);

        Node current = nodes[endNode.row][endNode.col];
        current = current.previous;
        while (current.previous != null) {
            grid[current.row][current.col].setFill(Color.GREEN);
            current = current.previous;
        }
    }
}
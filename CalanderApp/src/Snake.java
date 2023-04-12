import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Random;

public class Snake extends Application {

    // Constants for game dimensions
    private static final int WIDTH = 600;
    private static final int HEIGHT = 600;
    private static final int TILE_SIZE = 15;

    // Variables for game state
    private ArrayList<Point2D> snake;
    private Direction direction;
    private Point2D food;
    private boolean gameOver;

    // Variables for game objects
    private Canvas canvas;
    private GraphicsContext gc;
    private Timeline timeline;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Create game objects
        canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Create buttons
        Button startButton = new Button("Start");
        Button restartButton = new Button("Restart");

        // Set button actions
        startButton.setOnAction(event -> startGame());
        restartButton.setOnAction(event -> restartGame());

        // Add buttons to a layout container
        HBox buttonBox = new HBox(10, startButton, restartButton);

        // Add game objects and button container to root node
        Group root = new Group(canvas, buttonBox);

        // Create scene and set root node
        Scene scene = new Scene(root);

        // Add keyboard input handling
        scene.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case UP:
                    if (direction != Direction.DOWN) {
                        direction = Direction.UP;
                    }
                    break;
                case DOWN:
                    if (direction != Direction.UP) {
                        direction = Direction.DOWN;
                    }
                    break;
                case LEFT:
                    if (direction != Direction.RIGHT) {
                        direction = Direction.LEFT;
                    }
                    break;
                case RIGHT:
                    if (direction != Direction.LEFT) {
                        direction = Direction.RIGHT;
                    }
                    break;
            }
        });

        // Set the stage properties and show it
        primaryStage.setTitle("Covid 19");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    // Method to start the game
    private void startGame() {

        // Reset game state
        snake = new ArrayList<>();
        snake.add(new Point2D(WIDTH / 2, HEIGHT / 2));
        direction = Direction.LEFT;
        food = generateFood();
        gameOver = false;

        // Set up game loop
        timeline = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            // Move the snake
            Point2D head = snake.get(0);
            Point2D newHead = head.move(direction, TILE_SIZE);
            snake.add(0, newHead);
            if (!newHead.equals(food)) {
                snake.remove(snake.size() - 1);
            } else {
                food = generateFood();
            }

            // Check for collisions
            if (newHead.getX() < 0 || newHead.getX() >= WIDTH
                    || newHead.getY() < 0 || newHead.getY() >= HEIGHT) {
                gameOver = true;
            }
            for (int i = 1; i < snake.size(); i++) {
                if (newHead.equals(snake.get(i))) {
                    gameOver = true;
                    break;
                }
            }
    
            // Draw the game objects
            gc.setFill(Color.BLACK);
            gc.fillRect(0, 0, WIDTH, HEIGHT);
            gc.setFill(Color.GREEN);
            for (Point2D point : snake) {
                gc.fillRect(point.getX(), point.getY(), TILE_SIZE, TILE_SIZE);
            }
            gc.setFill(Color.RED);
            gc.fillRect(food.getX(), food.getY(), TILE_SIZE, TILE_SIZE);
    
            // Check for game over condition
            if (gameOver) {
                timeline.stop();
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    
    }
    
    // Method to restart the game
    private void restartGame() {
        timeline.stop();
        startGame();
    }
    
    // Method to generate a new food location
    private Point2D generateFood() {
        Random rand = new Random();
        int x = rand.nextInt(WIDTH / TILE_SIZE) * TILE_SIZE;
        int y = rand.nextInt(HEIGHT / TILE_SIZE) * TILE_SIZE;
        for (Point2D point : snake) {
            if (x == point.getX() && y == point.getY()) {
                return generateFood();
            }
        }
        return new Point2D(x, y);
    }
    
    // Enum for snake movement direction
    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
    
    // Class for 2D point
    private class Point2D {
        private int x;
        private int y;
    
        public Point2D(int x, int y) {
            this.x = x;
            this.y = y;
        }
    
        public int getX() {
            return x;
        }
    
        public int getY() {
            return y;
        }
    
        public Point2D move(Direction direction, int distance) {
            switch (direction) {
                case UP:
                    return new Point2D(x, y - distance);
                case DOWN:
                    return new Point2D(x, y + distance);
                case LEFT:
                    return new Point2D(x - distance, y);
                case RIGHT:
                    return new Point2D(x + distance, y);
                default:
                    return this;
            }
        }
    
        public boolean equals(Point2D other) {
            return x == other.getX() && y == other.getY();
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}    
        

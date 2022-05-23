package MapElements;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import GameBase.*;

// Bullet class to create a bullet
public class Bullet{
    private Circle bullet = new Circle(); // Basic shape/node of bullet
    private Position position = new Position(); // Position of bullet
    private Map map = new Map(); // Global map
    private int maxDistance = 100; // Max distance of bullet
    private int speed; // Speed of bullet
    private int direction; // Direction of bullet

    // Basic constructor of bullet
    public Bullet(){
        bullet = new Circle();
        bullet.setRadius(5);
        bullet.setFill(Color.RED);
    }

    // Create a bullet with some characteristics
    public Bullet(int s, int d, Position startPoint){
        speed = s;
        direction = d;
        bullet.setRadius(4);
        bullet.setFill(Color.RED);
        bullet.setViewOrder(1);
        setStartPosition(startPoint);
    }

    // Getter of bullet's node
    public Circle getBullet(){
        return bullet;
    }

    // Move bullet to certain direction
    public void fly(){
        if(bullet == null) return; // Check if bullet exists
        switch (direction){
            case 1 -> bullet.setCenterX(bullet.getCenterX()+speed); // move right
            case 2 -> bullet.setCenterX(bullet.getCenterX()-speed); // move left
            case 3 -> bullet.setCenterY(bullet.getCenterY()-speed); // move up
            case 4 -> bullet.setCenterY(bullet.getCenterY()+speed); // move down
        }
    }

    // Set start position for bullet
    public void setStartPosition(Position p){
        if(bullet == null) return; // Check if bullet exists

        // Calculated settings based on player's texture
        switch (direction){
            case 1 -> {
                bullet.setCenterX(p.getX()+40);
                bullet.setCenterY(p.getY()+22);
            }
            case 2 -> {
                bullet.setCenterX(p.getX());
                bullet.setCenterY(p.getY()+22 );
            }
            case 3 -> {
                bullet.setCenterX(p.getX()+20);
                bullet.setCenterY(p.getY());
            }
            case 4 -> {
                bullet.setCenterX(p.getX()+20);
                bullet.setCenterY(p.getY()+43);
            }
        }
        position = p;
    }

    // Getters & Setters for each field
    public Position getPosition() {
        return position;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
    }

    // Remove bullet from pane
    public void remove(){
        map.getPane().getChildren().remove(bullet);
    }
}

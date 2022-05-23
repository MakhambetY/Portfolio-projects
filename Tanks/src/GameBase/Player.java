package GameBase;

import javafx.scene.Node;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

public interface Player {
    // move player right, left, up and down
    void moveRight();
    void moveLeft();
    void moveUp();
    void moveDown();
    // Map Getter and Setter
    void setMap(Map map);
    Map getMap();
    // Position's getter and setter
    Position getPosition();
    void setPosition(Position position);
    // Getter of player's node
    Node getNode();
    // fire method interface
    void fire(int speed, int direction, Position startPosition);
    // Getter of direction
    int getDirection();
    // HP getter & setter
    void setHP(int hp);
    int getHP();
    // delete player (logic part)
    void delete();
    // destroy player (visual part)
    void destroy(Player player);
    // Vision area getter & setter
    Rectangle getVisionArea();
    void setVisionArea(Rectangle rectangle);
    // Indicator getter & setter
    HBox getIndicator();
    void setIndicator(HBox indicator);
    // Kills getter & setter
    int getKills();
    void setKills(int kills);
}

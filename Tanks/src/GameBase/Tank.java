package GameBase;

import MapElements.*;
import animations.Shake;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.util.ArrayList;

public class Tank implements Player {

    private Image image; // tank's image
    private ImageView tank; // tank's visual part
    private int direction; // direction of the tank
    private Position position = new Position(); // tank's position
    private Map map = new Map(); // Global map
    private int hp; // tank's hp
    private int speed; // tank's speed
    private int kills = 0; // number of kills
    private Rectangle vision = new Rectangle(); // vision area to auto-aim
    private HBox indicator = new HBox(); // indicator of hp etc.
    private Label name = new Label(); // label to show nickname
    private Label points = new Label(); // label to show number of kills
    private ArrayList<ImageView> hearts = new ArrayList<>(); // list of visual hearts

    // Create basic tank
    public Tank(){
        createTank();
    }

    // Create tank with special name
    public Tank(String nick){
        createTank();
        setName(nick);
    }

    // Main method to create tank
    private void createTank(){
        // Setting visual part of the tank
        image = new Image("texture/tank.png");
        tank = new ImageView(image);
        tank.setFitHeight(40);
        tank.setFitWidth(40);
        tank.setViewOrder(1);
        name.setText("Tank: ");
        name.setMinHeight(12);
        name.setWrapText(true);
        name.setTextFill(Color.BLACK);
        name.setAlignment(Pos.CENTER);
        indicator.setSpacing(5);
        indicator.getChildren().add(name);
        points.setText(kills + " kills ");
        points.setAlignment(Pos.CENTER);
        indicator.getChildren().addAll(points, new Label("HP: "));
        // Setting tank's characteristics
        setHP(4);
        setSpeed(5);
        vision.setOpacity(0.0);
    }

    // Getter of tank's node
    public synchronized Node getNode(){
        return tank;
    }

    // Getter & Setter of tank's image
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    // Getter & Setter of imageview of tank
    public synchronized ImageView getTank() {
        return tank;
    }

    public void setTank(Image texture) {
        tank = new ImageView(texture);
    }

    // Getter & Setter of name
    public String getName() {
        return name.getText();
    }

    public void setName(String nick) {
        name.setText(nick);
    }

    // Getter & Setter of tank's speed
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public synchronized Position getPosition() {
        return new Position((int)getTank().getTranslateX(),(int)getTank().getTranslateY()); // return current position
    }

    @Override
    public synchronized void setPosition(Position position) { // set position to tank
        this.position = position;
        getTank().setTranslateX(position.getX());
        getTank().setTranslateY(position.getY());
        syncRectPos();
    }

    void syncRectPos(){ // synchronize the vision area of the tank with tank
        switch (getDirection()){
            case 1 -> {
                vision.setWidth(500);
                vision.setHeight(40);
                vision.setTranslateX(position.getX()+45);
                vision.setTranslateY(position.getY());
            }
            case 2 -> {
                vision.setWidth(500);
                vision.setHeight(40);
                vision.setTranslateX(position.getX()-vision.getWidth()-5);
                vision.setTranslateY(position.getY());
            }
            case 3 -> {
                vision.setWidth(40);
                vision.setHeight(500);
                vision.setTranslateX(position.getX());
                vision.setTranslateY(position.getY()-vision.getHeight()-5);
            }
            case 4 -> {
                vision.setWidth(40);
                vision.setHeight(500);
                vision.setTranslateX(position.getX());
                vision.setTranslateY(position.getY()+45);
            }
        }
    }

    @Override
    public void moveRight() {
        if(tank == null) return;
        direction = 1;
        tank.setRotate(90);
        setPosition(new Position(getPosition().getX()+getSpeed(),getPosition().getY()));
        for (int i = 0; i < map.getSize(); i++) {
            for (int j = 0; j < map.getSize(); j++) {
                // Check if the next step intersects with some object on the global map
                if(tank.getBoundsInParent().intersects(map.getMap()[i][j].getImageView().getBoundsInParent())){
                    switch (map.getValueAt(i,j)){
                        // Go back if intersects
                        case 'S', 'B', 'W' -> setPosition(new Position(getPosition().getX()-getSpeed(),getPosition().getY()));
                    }
                }
            }
        }
        // Check the intersection with other tanks
        for (Player player : getMap().getPlayers()) {
            if(tank == null) break;
            if(player == Tank.this) continue;
            if (tank.getBoundsInParent().intersects(player.getNode().getBoundsInParent())) {
                setPosition(new Position(getPosition().getX()-getSpeed(),getPosition().getY()));
                break;
            }
        }

    }

    // The same algorithm for move left, up and down
    @Override
    public void moveLeft() {
        if(tank == null) return;
        direction = 2;
        tank.setRotate(270);
        setPosition(new Position(getPosition().getX()-getSpeed(),getPosition().getY()));
        for (int i = 0; i < map.getSize(); i++) {
            for (int j = 0; j < map.getSize(); j++) {
                if(tank.getBoundsInParent().intersects(map.getMap()[i][j].getImageView().getBoundsInParent())){
                    switch (map.getValueAt(i,j)){
                        case 'S', 'B', 'W' -> setPosition(new Position(getPosition().getX()+getSpeed(),getPosition().getY()));
                    }
                }
            }
        }
        for (Player player : getMap().getPlayers()) {
            if(tank == null) break;
            if(player == Tank.this) continue;
            if (tank.getBoundsInParent().intersects(player.getNode().getBoundsInParent())) {
                setPosition(new Position(getPosition().getX()+getSpeed(),getPosition().getY()));
                break;
            }
        }
    }

    @Override
    public void moveUp() {
        if(tank == null) return;
        direction = 3;
        tank.setRotate(0);
        setPosition(new Position(getPosition().getX(),getPosition().getY()-getSpeed()));
        for (int i = 0; i < map.getSize(); i++) {
            for (int j = 0; j < map.getSize(); j++) {
                if(tank.getBoundsInParent().intersects(map.getMap()[i][j].getImageView().getBoundsInParent())){
                    switch (map.getValueAt(i,j)){
                        case 'S', 'B', 'W' -> setPosition(new Position(getPosition().getX(),getPosition().getY()+getSpeed()));
                    }
                }
            }
        }
        for (Player player : getMap().getPlayers()) {
            if(tank == null) break;
            if(player == Tank.this) continue;
            if (tank.getBoundsInParent().intersects(player.getNode().getBoundsInParent())) {
                setPosition(new Position(getPosition().getX(),getPosition().getY()+getSpeed()));
                break;
            }
        }
    }

    @Override
    public void moveDown() {
        if(tank == null) return;
        direction = 4;
        tank.setRotate(180);
        setPosition(new Position(getPosition().getX(),getPosition().getY()+getSpeed()));
        for (int i = 0; i < map.getSize(); i++) {
            for (int j = 0; j < map.getSize(); j++) {
                if(tank.getBoundsInParent().intersects(map.getMap()[i][j].getImageView().getBoundsInParent())){
                    switch (map.getValueAt(i,j)){
                        case 'S', 'B', 'W' -> setPosition(new Position(getPosition().getX(),getPosition().getY()-getSpeed()));
                    }
                }
            }
        }
        for (Player player : getMap().getPlayers()) {
            if(tank == null) break;
            if(player == Tank.this) continue;
            if (tank.getBoundsInParent().intersects(player.getNode().getBoundsInParent())) {
                setPosition(new Position(getPosition().getX(),getPosition().getY()-getSpeed()));
                break;
            }
        }
    }

    // Getter & Setter of hp and set it to indicator
    public synchronized int getHP() {
        return hp;
    }

    public synchronized void setHP(int HP) {
        if(HP == 0) name.setText(getName() + " destroyed");
        if(HP == hp) return;
        else if( HP < hp){
            indicator.getChildren().remove(hearts.get(hearts.size()-1));
            hearts.remove(hearts.size()-1);
        }
        else {
            for (int i = 0; i < HP - hp; i++) {
                hearts.add(new ImageView(new Image("texture/heart.png")));
                indicator.getChildren().add(hearts.get(hearts.size()-1));
            }
        }
        hp = HP;
    }

    // Getter & Setter of kills and show it
    public synchronized int getKills() {
        return kills;
    }

    public synchronized void setKills(int kills) {
        this.kills = kills;
        points.setText(kills + " kills ");
    }

    // Getter & Setter of the global map
    @Override
    public synchronized void setMap(Map map) {
        this.map = map;
    }

    @Override
    public synchronized Map getMap() {
        return map;
    }

    // Getter & Setter of the direction
    public synchronized int getDirection(){
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    // Getter & Setter of the indicator
    public HBox getIndicator() {
        return indicator;
    }

    public void setIndicator(HBox indicator) {
        this.indicator = indicator;
    }

    // Complex method of shooting (1 bullet)
    public synchronized void fire(int s, int d, Position p){
        if(tank == null) return; // check if the tank is alive
        Bullet bullet = new Bullet(s, d, p); // create temp bullet
        getMap().getPane().getChildren().add(bullet.getBullet()); // show bullet
        bullet.setMap(getMap());

        Timeline timeline = new Timeline();// Animation of the bullet
        KeyFrame keyFrame = new KeyFrame(
            Duration.seconds(0.0125),
            action -> {
                bullet.fly();
                for (int i = 0; i < map.getSize(); i++) { // Track the interaction of the bullet with map objects and do some action
                    for (int j = 0; j < map.getSize(); j++) {
                        if (bullet.getBullet().getBoundsInParent().intersects(getMap().getMap()[i][j].getImageView().getBoundsInParent())) {
                            switch (map.getValueAt(i, j)) {
                                case 'S' -> {
                                    timeline.stop();
                                    bullet.remove();
                                }
                                case 'B' -> {
                                    timeline.stop();
                                    bullet.remove();
                                    if (map.getMap()[i][j].getImage().getUrl().equals((new Image("texture/BrickWall.png")).getUrl()))
                                        map.setElement(new BrickWall(1), i, j);
                                    else if (map.getMap()[i][j].getImage().getUrl().equals((new Image("texture/brickDMG1.png")).getUrl()))
                                        map.setElement(new BrickWall(2), i, j);
                                    else if (map.getMap()[i][j].getImage().getUrl().equals((new Image("texture/brickDMG2.png")).getUrl()))
                                        map.setElement(new BrickWall(3), i, j);
                                    else if (map.getMap()[i][j].getImage().getUrl().equals((new Image("texture/brickDMG3.png")).getUrl())) {
                                        map.setElement(new Air(), i, j);
                                        map.setValueAt(i, j, "0");
                                    }
                                    if (map.getValueAt(i, j) != '0') { // Shake if bullet damaged the brick wall
                                        Shake shaker = new Shake(map.getMap()[i][j].getImageView());
                                        shaker.playAnimation();
                                    }
                                }
                            }
                            break;
                        }
                    }
                    if(bullet.getBullet() == null) break; // remove a bullet
                }
                for (Player player : getMap().getPlayers()) { //Track the interaction of the bullet with other tanks and do some action
                    if(bullet.getBullet() == null) break;
                    if (bullet.getBullet().getBoundsInParent().intersects(player.getNode().getBoundsInParent())) {
                        timeline.stop();
                        bullet.remove();
                        player.setHP(player.getHP() - 1);
                        if (player.getHP() == 0) destroy(player);
                        break;
                    }
                }
            }
        );
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(bullet.getMaxDistance());
        timeline.setOnFinished(e -> bullet.remove());
        timeline.play(); // play animation and stop if it is needed during the animation
    }

    // Delete tank
    @Override
    public void delete(){
        tank = null;
        vision = null;
    }

    // Delete a visual part of tank and save the information (add kill's number)
    @Override
    public void destroy(Player player) {
        getMap().getPane().getChildren().remove(player.getNode());
        getMap().getPlayers().remove(player);
        getMap().getPane().getChildren().remove(player.getVisionArea());
        getMap().getPlayers().trimToSize();
        setKills(getKills()+1);
        player.delete();
    }

    // Getter & Setter of vision area
    @Override
    public Rectangle getVisionArea() {
        return vision;
    }

    @Override
    public void setVisionArea(Rectangle rectangle) {
        vision = rectangle;
    }

    // Fire if the tank on the vision area
    public void autoAim() {
        for (Player player : getMap().getPlayers()) {
            if (tank == null) break;
            if (getVisionArea().getBoundsInParent().intersects(player.getNode().getBoundsInParent())) {
                Platform.runLater(() -> fire(5, getDirection(), getPosition()));
            }
        }
    }

}

package GameBase;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Game extends Parent{
    private Map map; // Global map
    private AnchorPane battlefield; // Battlefield is battlefield :D
    private HBox game = new HBox(); // Game's main pane
    private VBox indicator = new VBox(); // Set of all indicators
    private Scene scene; // Game scene

    // Create game (empty battlefield) based on map
    public Game(Map m){
        this.map = m;
        battlefield = map.createMap();
        indicator.setSpacing(15);
        game.setSpacing(5);
        game.getChildren().addAll(battlefield, indicator);
        scene = new Scene(game);
    }

    // Map setter
    public void setMap(Map map) {
        this.map = map;
    }

    // Add player to the game
    public void addPlayer(Player player){
        int index = (int)(Math.random()*map.getPlayerPosition().size()); // random, but allowed position
        player.setPosition(map.getPlayerPosition().get(index)); // set player to position
        map.getPlayerPosition().remove(index); // remove taken positions from allowed list
        player.setMap(map);
        // add player to the visual part
        battlefield.getChildren().add(player.getNode());
        battlefield.getChildren().add(player.getVisionArea());
        map.addPlayer(player);
        // add player's indicator to the set
        indicator.getChildren().add(player.getIndicator());
    }

    // Getter of pane
    public Parent getPane(){
        return battlefield;
    }

    // Getter of map
    public Map getMap(){ return map;}

    // Getter of game scene
    public Scene getGameScene() {
        return scene;
    }

    // Add controller to the player based on WASD (+ SPACE to fire)
    public void addControl(Player player){
        scene.setOnKeyPressed(keyEvent -> {
            if(player.getHP() != 0){
                switch (keyEvent.getCode()){
                    case W -> player.moveUp();
                    case S -> player.moveDown();
                    case A -> player.moveLeft();
                    case D -> player.moveRight();
                    case SPACE -> Platform.runLater(() -> player.fire(5, player.getDirection(), player.getPosition()));
                }
            }
        });
    }

    // Command player automatically (Special for multiplayer)
    public void actionPlayer(Player player, int action){
        switch (action){
            case 1 -> player.moveUp();
            case 2 -> player.moveDown();
            case 3 -> player.moveLeft();
            case 4 -> player.moveRight();
            case 5 -> Platform.runLater(() -> player.fire(5, player.getDirection(), player.getPosition()));
        }
    }

    // Add single bot to the game
    public void addBotPlayer(){
        Player bot = new BotPlayer();

        while (true){
            int i = (int)(Math.random()*(map.getSize()-1));
            int j = (int)(Math.random()*(map.getSize()-1));
            if(map.getValueAt(i,j) == '0') {
                bot.setPosition(new Position(j*50+5,i*50+5)); // Place bot to random, but valid place
                break;
            }
        }

        // Add bot to the visual part
        bot.setMap(map);
        Platform.runLater(()->{
            battlefield.getChildren().add(bot.getNode());
            battlefield.getChildren().add(bot.getVisionArea());
        });
        map.addPlayer(bot);
    }

    // Spawn bots periodically
    public void spawnBots(){
        new Thread(()->{
            while (true){
                try {
                    Thread.sleep(5000); // 1 bot every 5 second
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(map.getPlayers().size() == 3) continue; // Limit is only 3 tanks on a map
                addBotPlayer();
            }
        }).start();
    }
}

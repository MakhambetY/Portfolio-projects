package GameBase;

import MapElements.*;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Scanner;

// Global map class thet contains the main visual information of the game
public class Map {

    private AnchorPane pane; // main pane
    private int size;
    private MapElement[][] map; // massive of map elements (Tree, Walls, Water)
    private ArrayList<Position> playerPosition = new ArrayList<>(); // Allowed position for player spawn
    private String[][] plan; // massive of map elements tag (value)
    private ArrayList<Player> players = new ArrayList<>(); // List of players on map

    public Map(){} // Basic constructor

    public Map(Scanner in) throws InvalidMapException { // Main constructor throwing custom exception
        int tempSize = in.nextInt();
        String s = in.nextLine();
        if(tempSize <= 0){
            throw new InvalidMapException("Map size can not be zero");
        }
        size = tempSize;
        plan = new String[size][size];
        map = new MapElement[size][size];

        boolean playerExist = false;
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                String tempValue = in.next();
                // Check for map mistakes
                if(tempValue.length() != 1)
                    throw new InvalidMapException("Not enough map elements");
                if(!tempValue.equals("0") && !tempValue.equals("S") && !tempValue.equals("B") && !tempValue.equals("W") && !tempValue.equals("T") && !tempValue.equals("P"))
                    throw new InvalidMapException("Unknown object on the map");
                if(tempValue.equals("P")){
                    playerExist = true;
                    playerPosition.add(new Position(j*50, i*50)); // Save allowed positions for player
                    plan[i][j] = "0";
                    continue;
                }
                plan[i][j] = tempValue;
            }
        }

        if(!playerExist) throw new InvalidMapException("Player do not exist");
    }

    // Getters & Setters of the value at certain place
    public synchronized char getValueAt(int x,int y){
        return plan[x][y].charAt(0);
    }
    public synchronized void setValueAt(int x,int y, String value){
        plan[x][y] = value.trim();
    }

    // print map's value map
    public void print(){
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                System.out.print(plan[i][j]+" ");
            }
            System.out.println();
        }
    }

    // Getter of player's allowed position
    public synchronized ArrayList<Position> getPlayerPosition(){
        return playerPosition;
    }

    // Getter of map size
    public int getSize() {
        return size;
    }

    // Getter of map objects' massive
    public synchronized MapElement[][] getMap() {
        return map;
    }

    // Create a visual part of map
    public AnchorPane createMap(){
        Position position = new Position();
        pane = new AnchorPane();
        pane.setStyle("-fx-background-color: dimgrey");
        pane.setMinSize(50*size,50*size);
        // Place elements to map and save them
        for(int i=0; i<size; i++){
            for(int j=0; j<size; j++){
                switch (getValueAt(i,j)){
                    case 'W' -> {
                        map[i][j] = new Water();
                        pane.getChildren().add(map[i][j].getOnPosition(position));
                    }
                    case 'B' -> {
                        map[i][j] = new BrickWall();
                        pane.getChildren().add(map[i][j].getOnPosition(position));
                    }
                    case 'S' -> {
                        map[i][j] = new SteelWall();
                        pane.getChildren().add(map[i][j].getOnPosition(position));
                    }
                    case 'T' -> {
                        map[i][j] = new Tree();
                        pane.getChildren().add(map[i][j].getOnPosition(position));
                    }
                    case '0' -> map[i][j] = new Air();

                }
                position.setX(position.getX()+50);
            }
            position.setY(position.getY()+50);
            position.setX(0);
        }
        return pane;
    }

    // Getter of main pane
    public synchronized AnchorPane getPane() {
        return pane;
    }

    // Setter of visual element to a certain place
    public synchronized void setElement(MapElement element, int i, int j){
        int index = getPane().getChildren().indexOf(map[i][j].getImageView());
        getMap()[i][j] = element;
        getPane().getChildren().set(index, getMap()[i][j].getOnPosition(new Position(j*50, i*50)));
    }

    // Getter of players' list
    public synchronized ArrayList<Player> getPlayers() {
        return players;
    }

    // Add player to a players' list
    public synchronized void addPlayer(Player player){
        players.add(player);
    }
}

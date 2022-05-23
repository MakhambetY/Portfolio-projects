package GameBase;

import javafx.scene.image.Image;

public class BotPlayer extends Tank implements Player{
    private int direction;

    // Bot addon based on tank constructor
    public BotPlayer(){
        createRandomBot();
        autoPlay();
    }

    // Create a random bot from 1040 variations
    private void createRandomBot(){
        int speed = (int)Math.ceil(Math.random()*10); // Random speed [1-10]
        int hp = (int)Math.ceil(Math.random()*8); // Random hp [1-8]
        int index = (int)(Math.random()*12); // Random texture [0-12]

        Image texture = new Image("texture/bot" + index + ".png"); // Create random texture
        if (speed == 15) texture = new Image("texture/bossBotSpeed.png"); // Special boss texture for max speed
        if ( hp == 8) texture = new Image("texture/bossBotHP.png"); // Special boss texture for max hp
        if ( speed == 15 && hp == 8) texture = new Image("texture/bossBotAll.png"); // Special boss texture for both speed & hp

        setHP(hp); // Set hp to tank
        setSpeed(speed); // Set speed to tank

        // Setting visual part of the tank
        setImage(texture); // Set a texture
        setTank(texture); // Set a base node of tank
        getTank().setFitHeight(40);
        getTank().setFitWidth(40);
        getTank().setViewOrder(1);
    }

    // Autoplay feature of bots
    public void autoPlay(){
        //if(getHP() == 0) return;
        new Thread(()->{
            while (getHP() != 0) {
                try {
                    Thread.sleep(300); // short break between actions
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int direction = toAim(); // Get the direction

                // Move a bot to a certain direction
                for (int i = 0; i < 10; i++) {
                    switch (direction) {
                        case 1 -> moveRight();
                        case 2 -> moveLeft();
                        case 3 -> moveUp();
                        case 4 -> moveDown();
                    }
                    try {
                        Thread.sleep(50); // imitate player's action speed
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                autoAim();
            }
        }).start();
    }

    // Find the best way to the nearest player/bot
    public int toAim(){
        if (getHP() == 0) return 0;
        Player aim = null;
        double nearest = Integer.MAX_VALUE;

        // Find the nearest player/bot
        for(Player player: getMap().getPlayers()){
            if(player == BotPlayer.this) continue; // Ignore himself
            // Calculate a distance
            double math = Math.sqrt( Math.pow(getPosition().getX()-player.getPosition().getX(), 2)
                    + Math.pow(getPosition().getY()-player.getPosition().getY(), 2) );
            // Choose an aim
            if(math < nearest){
                nearest = math;
                aim = player;
            }
        }

        if (aim == null) return (int) Math.ceil(Math.random() * 4); // Return random direction if there is not another player/bot

        // Save current position to compare then
        Position cur = getPosition();
        int x = getPosition().getX() - aim.getPosition().getX();
        int y = getPosition().getY() - aim.getPosition().getY();

        // Check the next step
        int d = chooseIf(x,y,false);
        switch (d) {
            case 1 -> moveRight();
            case 2 -> moveLeft();
            case 3 -> moveUp();
            case 4 -> moveDown();
        }
        if ( cur.equals(getPosition())) return altWay(aim); // Choose alternative way if it's stuck
        switch (d) {
            case 1 -> moveLeft();
            case 2 -> moveRight();
            case 3 -> moveDown();
            case 4 -> moveUp();
        }

        return d;
    }

    // 1 - right
    // 2 - left
    // 3 - up
    // 4 - down

    // Choose the direction if the difference is x & y
    private int chooseIf(int x, int y, boolean reverse){
        boolean normal = Math.abs(y) < Math.abs(x);
        if (reverse) normal = Math.abs(y) > Math.abs(x);

        // Algorithm based on the coordinates
        if( Math.abs(y) == Math.abs(x) ) {
            int[] temp = new int[]{x,y};
            int index = (int)(Math.random()*2);
            if ( temp[index] == x ){
                if ( x < 0 ) return 1;
                else return 2;
            }
            else {
                if ( y > 0 ) return 3;
                else return 4;
            }
        }
        else if ( normal ) {
            if( x < 0 ) return 1;
            else return 2;
        }
        else {
            if( y > 0 ) return 3;
            else return 4;
        }
    }

    // Alternative algorithm (based on the distance) if the firs one's stuck
    private int altWay(Player player){
        double minWay = Integer.MAX_VALUE;
        int direction = 0;

        // Check the next step and choose the most effective direction
        for( int i = 1; i < 5; i++){

            // Next step
            switch (i) {
                case 1 -> setPosition(new Position(getPosition().getX()+getSpeed(),getPosition().getY()));
                case 2 -> setPosition(new Position(getPosition().getX()-getSpeed(),getPosition().getY()));
                case 3 -> setPosition(new Position(getPosition().getX(),getPosition().getY()-getSpeed()));
                case 4 -> setPosition(new Position(getPosition().getX(),getPosition().getY()+getSpeed()));
            }
            // Calculate a distance
            double math = Math.sqrt( Math.pow(getPosition().getX()-player.getPosition().getX(), 2)
                    + Math.pow(getPosition().getY()-player.getPosition().getY(), 2) );
            if (minWay > math){
                minWay = math;
                direction = i;
            }
            // Go back
            switch (i) {
                case 2 -> setPosition(new Position(getPosition().getX()+getSpeed(),getPosition().getY()));
                case 1 -> setPosition(new Position(getPosition().getX()-getSpeed(),getPosition().getY()));
                case 4 -> setPosition(new Position(getPosition().getX(),getPosition().getY()-getSpeed()));
                case 3 -> setPosition(new Position(getPosition().getX(),getPosition().getY()+getSpeed()));
            }
        }

        return direction;
    }
}

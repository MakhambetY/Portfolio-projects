package MapElements;

import GameBase.*;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// Brick wall class to create a brick wall
public class BrickWall implements MapElement {
    private Image image = new Image("texture/BrickWall.png"); // Brick wall's texture
    private ImageView imageView;
    private Position position = new Position(); // Brick wall's position

    // Create basic brick wall with its characteristics and show it
    public BrickWall() {
        imageView = new ImageView(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
    }

    // Create a damaged brick wall depending on level of damage
    public BrickWall(int status){
        switch (status){
            case 0 -> image = new Image("texture/BrickWall.png");
            case 1 -> image = new Image("texture/brickDMG1.png");
            case 2 -> image = new Image("texture/brickDMG2.png");
            case 3 -> image = new Image("texture/brickDMG3.png");
        }
        imageView = new ImageView(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
    }

    // Getters & Setters for each field
    @Override
    public Node getImageView(){
        return imageView;
    }

    @Override
    public void setPosition(Position p){
        position = p;
        imageView.setX(p.getX());
        imageView.setY(p.getY());
    }

    @Override
    public Position getPosition(){
        return position;
    }

    @Override
    public Node getOnPosition(Position p){
        setPosition(p);
        return getImageView();
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}

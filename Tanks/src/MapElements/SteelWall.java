package MapElements;

import GameBase.*;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// Stell wall class to create a steel wall
public class SteelWall implements MapElement {
    private Image image = new Image("texture/SteelWall.png"); // Steel wall's texture
    private ImageView imageView;
    private Position position = new Position(); // Steel wall's position

    // Create a steel wall with its characteristics and show it
    public SteelWall() {
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

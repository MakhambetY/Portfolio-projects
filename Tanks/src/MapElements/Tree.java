package MapElements;

import GameBase.*;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// Class of Tree element to create a Tree block
public class Tree implements MapElement {
    private Image image = new Image("texture/Tree.png"); // Tree's texture
    private ImageView imageView;
    private Position position = new Position(); // Tree's position

    // Create Tree with its characteristics and show it
    public Tree() {
        imageView = new ImageView(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        imageView.setOpacity(0.8);
        imageView.setViewOrder(0);
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

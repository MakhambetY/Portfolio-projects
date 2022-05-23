package MapElements;

import GameBase.*;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

// Water Class to create a water block
public class Water implements MapElement {
    private Image image = new Image("texture/Water.png"); // Texture of water
    private ImageView imageView;
    private Position position = new Position(); // Position of water

    // Create Water with its characteristics and show it
    public Water() {
        imageView = new ImageView(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        imageView.setViewOrder(2);
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

package MapElements;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import GameBase.*;

// Empty map element class with getters & setters
public class Air implements MapElement {
    private Image image;
    private ImageView imageView = new ImageView(); // Empty imageview
    private Position position = new Position();

    public Air() {
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
    }

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

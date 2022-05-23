package MapElements;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import GameBase.*;

// Map interface to group map elements
public interface MapElement {

    // Getter & Setters for each field of map elements

    Node getImageView();

    void setPosition(Position p);

    Position getPosition();

    Node getOnPosition(Position p);

    Image getImage();

    void setImage(Image image);

    void setImageView(ImageView imageView);
}

package animations;

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

// Shaker class to shake some node (animation)
public class Shake {
    private TranslateTransition tt;

    public Shake(Node node){
        tt = new TranslateTransition(Duration.millis(25), node);
        tt.setFromX(0);
        tt.setByX(5);
        tt.setFromY(0);
        tt.setByY(5);
        tt.setCycleCount(2);
        tt.setAutoReverse(true);
    }
    public void playAnimation(){
        tt.playFromStart();
    }
}

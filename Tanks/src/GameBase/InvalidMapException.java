package GameBase;

// Custom exception to handle/avoid problems with map
public class InvalidMapException extends Exception{
    public InvalidMapException() {
    }
    public InvalidMapException(String message) {
        super(message);
    }
}

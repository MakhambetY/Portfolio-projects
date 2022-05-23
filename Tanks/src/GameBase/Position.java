package GameBase;

// Position class to declare a position on 2D field
public class Position {
    private int x;
    private int y;

    public Position(){
       x = 0;
       y = 0;
    }

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    public synchronized int getX() {
        return x;
    }

    public synchronized void setX(int x) {
        this.x = x;
    }

    public synchronized int getY() {
        return y;
    }

    public synchronized void setY(int y) {
        this.y = y;
    }

    public boolean equals(Position p) {
        if (this == p) return true;
        if (p == null) return false;
        return x == p.getX() &&
                y == p.getY();
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)",getX(),getY());
    }
}

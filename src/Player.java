public class Player {
    private int x;
    private int y;

    public Player() {
        this.x = 0;
        this.y = 0;
    }

    public void moveUp() {
        if (y > 0) y--;
    }

    public void moveDown() {
        if (y < 9) y++;
    }

    public void moveLeft() {
        if (x > 0) x--;
    }

    public void moveRight() {
        if (x < 9) x++;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}


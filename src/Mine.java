public class Mine {
    private int x;
    private int y;

    public Mine(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Bu metod subclass'lar tarafından override edilecek
    public String getDeathMessage() {
        return "You stepped on a mine!";
    }
}


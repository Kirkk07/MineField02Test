public class Item {
    private int x;
    private int y;
    private boolean collected = false;

    public Item(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public boolean isCollected() { return collected; }
    public void collect() { collected = true; }
}

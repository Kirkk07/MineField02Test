public class Target {
    private String name;
    private int x, y;
    private Target target;

    public Target(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }

    public String getName() {
        return name;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public Target getTarget() {
        return target;
    }
}


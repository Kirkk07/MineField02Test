public class SeaMine extends Mine {

    public SeaMine(int x, int y) {
        super(x, y);
    }

    @Override
    public String getDeathMessage() {
        return "You were killed by a SEA mine!";
    }
}


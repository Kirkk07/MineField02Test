public class FieldMine extends Mine {

    public FieldMine(int x, int y) {
        super(x, y);
    }

    @Override
    public String getDeathMessage() {
        return "You were killed by a FIELD mine!";
    }
}


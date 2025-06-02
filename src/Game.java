import java.awt.Point;
import java.util.*;

public class Game {
    private final int gridSize = 10;
    private Target target;
    private List<Mine> mines;
    private Player player;
    private Random rand;
    private List<String> commandHistory;
    private boolean gameOver;
    private Parser parser;
    private Game game;
    Scanner scanner = new Scanner(System.in);

    public Game() {
        this.rand = new Random();
        this.player = new Player();
        this.mines = new ArrayList<>();
        this.commandHistory = new ArrayList<>();
        this.gameOver = false;

    }

    public void startGame() {
        generateTarget();
        generateMines();
        printWelcome();
        drawMap(false);

    }

    public void generateTarget() {
        String[] targetNames = {
                "PostOffice", "School", "Hospital", "Supermarket",
                "SportField", "CityHall", "TrainStation", "Airport"
        };

        int x = rand.nextInt(gridSize);
        int y = rand.nextInt(gridSize);
        String name = targetNames[rand.nextInt(targetNames.length)];
        target = new Target(name, x, y);
        System.out.println("Target: " + name + " at (" + x + ", " + y + ")");
    }

    public void generateMines() {
        while (mines.size() < 8) {
            int x = rand.nextInt(gridSize);
            int y = rand.nextInt(gridSize);
            if ((x != 0 || y != 0) && (x != target.getX() || y != target.getY())) {
                boolean exists = mines.stream().anyMatch(m -> m.getX() == x && m.getY() == y);
                if (!exists) {
                    mines.add(new Mine(x, y));
                }
            }
        }
    }

    public boolean isMine(int x, int y) {
        return mines.stream().anyMatch(m -> m.getX() == x && m.getY() == y);
    }

    public void checkSurroundings() {
        int x = player.getX();
        int y = player.getY();
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        for (int[] d : dirs) {
            if (isMine(x + d[0], y + d[1])) {
                System.out.println("Red Alarm! Danger around you!");
                return;
            }
        }

        System.out.println("Your surroundings are clear.");
    }

    public void finish() {
        System.out.println("Congratulations! You reached the target: " + target.getName());
    }

    public Player getPlayer() {
        return player;
    }

    public Target getTarget() {
        return target;
    }


    public boolean step(String move) {
        commandHistory.add(move);

        CommandWord command = CommandWord.fromString(move);

        if (command == CommandWord.UNKNOWN) {
            System.out.println("Invalid command.");
            return false;
        }

        command.execute(player);

        int x = player.getX();
        int y = player.getY();

        if (isMine(x, y)) {
            System.out.println("Boom! You stepped on a mine. Game Over.");
            drawMap(true);
            gameOver = true;
            return true;
        }

        if (x == target.getX() && y == target.getY()) {
            finish();
            drawMap(true);
            gameOver = true;
            return true;
        }checkSurroundings();
        drawMap(false);
        return false;}


//     //   public static void printGrid(int size) {
//        String horizontal = "*";
//        for (int i = 0; i < size; i++) horizontal += "----*";
//        String vertical = "|";
//        for (int i = 0; i < size; i++) vertical += "    |";
//
//        for (int i = 0; i < size; i++) {
//            System.out.println(horizontal);
//            System.out.println(vertical);
//        }
//        System.out.println(horizontal);
//    }

    private void printWelcome() {
        System.out.println("Welcome to the Mine Field!");
        //printGrid(9);
    }
    public void drawMap(boolean revealMines) {
        System.out.println("Current Map:");
        for (int y = 0; y < gridSize; y++) {
            for (int x = 0; x < gridSize; x++) {
                if (player.getX() == x && player.getY() == y) {
                    System.out.print(" P ");
                } else if (target.getX() == x && target.getY() == y) {
                    System.out.print(" T ");
                } else if (revealMines && isMine(x, y)) {
                    System.out.print(" * ");
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void showCommandHistory() {
        System.out.println("\nCommand History:");
        for (int i = 0; i < commandHistory.size(); i++) {
            System.out.println((i + 1) + ". " + commandHistory.get(i));
        }
    }
    public void play(){
        while (!game.isGameOver()) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            game.step(input);

            if (!game.isGameOver()) {
                System.out.println("Position: (" + game.getPlayer().getX() + ", " + game.getPlayer().getY() + ")");
            }
        }

        game.showCommandHistory();
        scanner.close();
    }
    public boolean isGameOver() {
        return gameOver;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();
        game.startGame();
        Parser parser1=new Parser();

        while (!game.isGameOver()) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            game.step(input);

            if (!game.isGameOver()) {
                System.out.println("Position: (" + game.getPlayer().getX() + ", " + game.getPlayer().getY() + ")");
                System.out.println("Commands: "+parser1.showCommands());
            }
        }

        game.showCommandHistory();
        scanner.close();
    }


}


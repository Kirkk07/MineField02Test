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
    private Parser parser=new Parser();

    Scanner scanner = new Scanner(System.in);

    public Game() {
        this.rand = new Random();
        this.player = new Player();
        this.mines = new ArrayList<>();
        this.commandHistory = new ArrayList<>();
        this.gameOver = false;

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Game game = new Game();
        game.startGame();
       // game.play();
        Parser parser1=new Parser();
        System.out.println(game.isGameOver());

//        while (!game.isGameOver()) {
//            System.out.print("> ");
//            String input = scanner.nextLine().trim();
//            game.step(input);
//
//            if (!game.isGameOver()) {
//                System.out.println("Position: (" + game.getPlayer().getX() + ", " + game.getPlayer().getY() + ")");
//                System.out.println("Commands: "+parser1.showCommands());
//                game.showCommandHistory();
//            }
//        }
//
//        game.showCommandHistory();
//        scanner.close();
    }

    public void startGame() {
        generateTarget();
        generateMines();
        printWelcome();
        drawMap(false);
        play();

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
                    Mine mine;
                    if (rand.nextBoolean()) {
                        mine = new SeaMine(x, y);
                    } else {
                        mine = new FieldMine(x, y);
                    }
                    mines.add(mine);
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



    public boolean step(String move) {
        commandHistory.add(move);
        CommandWord command = CommandWord.fromString(move);

        if (command == CommandWord.UNKNOWN) {
            System.out.println("Invalid command.");
            return false;
        }

        boolean moved = command.execute(player);

        if (!moved) {
            return false; // Invalid move, skip surroundings check
        }

        int x = player.getX();
        int y = player.getY();

        if (isMine(x, y)) {
            Mine triggeredMine = mines.stream()
                    .filter(m -> m.getX() == x && m.getY() == y)
                    .findFirst()
                    .orElse(null);

            if (triggeredMine != null) {
                System.out.println(triggeredMine.getDeathMessage());
                drawMap(true);
                gameOver = true;
                return true;
            }

            drawMap(true);
            gameOver = true;
            return true;
        }

        if (x == target.getX() && y == target.getY()) {
            finish();
            drawMap(true);
            gameOver = true;
            return true;
        }

        checkSurroundings();
        drawMap(false);
        return false;
    }

    //Count mine.
     public String getMineCountByType() {
            int seaMineCount = 0;
            int fieldMineCount = 0;

            for (Mine mine : mines) {
                if (mine instanceof SeaMine) {
                    seaMineCount++;
                } else if (mine instanceof FieldMine) {
                    fieldMineCount++;
                }
            }
            String returnMine="Sea Mines: " + seaMineCount+"\n"+"Field Mines: " + fieldMineCount;
//            System.out.println("Sea Mines: " + seaMineCount);
//            System.out.println("Field Mines: " + fieldMineCount);
            return returnMine;
        }


//    public boolean step(String move) {
//        commandHistory.add(move);
//
//        CommandWord command = CommandWord.fromString(move);
//
//        if (command == CommandWord.UNKNOWN) {
//            System.out.println("Invalid command.");
//            return false;
//        }
//
//        command.execute(player);
//
//        int x = player.getX();
//        int y = player.getY();
//
//        if (isMine(x, y)) {
//            System.out.println("Boom! You stepped on a mine. Game Over.");
//            drawMap(true);
//            gameOver = true;
//            return true;
//        }
//
//        if (x == target.getX() && y == target.getY()) {
//            finish();
//            drawMap(true);
//            gameOver = true;
//            return true;
//        }checkSurroundings();
//        drawMap(false);
//        return false;}


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
        System.out.println(getMineCountByType());
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
    public boolean isGameOver() {
        return gameOver;
    }
    public void play(){

        while (!isGameOver()) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();
            step(input);

            if (!isGameOver()) {
                System.out.println("Position: (" + getPlayer().getX() + ", " + getPlayer().getY() + ")");
                System.out.println("Commands: "+parser.showCommands());
                showCommandHistory();
        }


      //  scanner.close();
    }}

    public Player getPlayer() {
        return player;
    }





}


import java.util.Scanner;



    public class Parser {
        private CommandWords commands;
        private Scanner reader;
        private Game game;
        private Parser parser;

        public Parser() {
            commands = new CommandWords();
            reader = new Scanner(System.in);
        }
//Get Commandi hic kullanmamisim
 //       public void getCommand() {
//            String word1 = null;
//            String word2 = null;
//
//            System.out.print("> ");
//            String inputLine = reader.nextLine();
//
//            String[] words = inputLine.trim().split("\\s+");
//            if (words.length > 0) word1 = words[0];
//            if (words.length > 1) word2 = words[1];
//
//            CommandWord command = commands.getCommand(word1);
//            return new Command(command, word2);
       // }
//        public Command getCommand() {
//            String inputLine;   // will hold the full input line
//            String word1 = null;
//            String word2 = null;
//
//            System.out.print("> ");     // print prompt
//
//            inputLine = reader.nextLine();
//
//            // Find up to two words on the line.
//            Scanner tokenizer = new Scanner(inputLine);
//            if (tokenizer.hasNext()) {
//                word1 = tokenizer.next();      // get first word
//                if (tokenizer.hasNext()) {
//                    word2 = tokenizer.next();      // get second word
//                    // note: we just ignore the rest of the input line.
//                }
//            }
//
//            // Now check whether this word is known. If so, create a command
//            // with it. If not, create a "null" command (for unknown command).
//            if (commands.isCommand(word1)) {
//                return new Command(commands.getCommand(word1), word2);
//            } else {
//                return new Command(CommandWord.UNKNOWN, word2);
//            }
//        }
        public Command getCommand(){
        while (!game.isGameOver()) {
            System.out.print("> ");
            Command command = parser.getCommand();
            String input = reader.nextLine().trim();
            game.step(input);

            if (!game.isGameOver()) {
                System.out.println("Position: (" + game.getPlayer().getX() + ", " + game.getPlayer().getY() + ")");
            }

        }

//        public String showCommands() {
//            return commands.showAll();
//        }




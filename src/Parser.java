import java.util.Scanner;



    public class Parser {
        private CommandWords commands;
        private Scanner reader;
        private Game game;

        public Parser() {
            commands = new CommandWords();
            reader = new Scanner(System.in);
        }

        public Command getCommand() {
            String word1 = null;
            String word2 = null;

            System.out.print("> ");
            String inputLine = reader.nextLine();

            String[] words = inputLine.trim().split("\\s+");
            if (words.length > 0) word1 = words[0];
            if (words.length > 1) word2 = words[1];

            CommandWord command = commands.getCommand(word1);
            return new Command(command, word2);
        }

        public String showCommands() {
            return commands.showAll();
        }



    }



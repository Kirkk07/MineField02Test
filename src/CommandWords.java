
import java.util.HashMap;

    public class CommandWords {
        private HashMap<String, CommandWord> validCommands;

        public CommandWords() {
            validCommands = new HashMap<>();
            for (CommandWord command : CommandWord.values()) {
                if (command != CommandWord.UNKNOWN) {
                    validCommands.put(command.toString(), command);
                }
            }
        }

        public boolean isCommand(String aString) {
            return validCommands.containsKey(aString);
        }

        public CommandWord getCommand(String aString) {
            return validCommands.getOrDefault(aString, CommandWord.UNKNOWN);
        }

        public String showAll() {
            return String.join(" ", validCommands.keySet());
        }
    }



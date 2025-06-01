public class Command {

        private CommandWord commandWord;
        private String secondWord;

        public Command(CommandWord firstWord, String secondWord) {
            this.commandWord = firstWord;
            this.secondWord = secondWord;
        }

        public CommandWord getCommandWord() {
            return commandWord;
        }

        public String getSecondWord() {
            return secondWord;
        }

        public boolean hasSecondWord() {
            return (secondWord != null);
        }
    }




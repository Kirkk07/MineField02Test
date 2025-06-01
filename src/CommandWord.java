
    public enum CommandWord {
        UP {
            @Override
            public void execute(Player player) {
                player.moveUp();
            }
        },
        DOWN {
            @Override
            public void execute(Player player) {
                player.moveDown();
            }
        },
        LEFT {
            @Override
            public void execute(Player player) {
                player.moveLeft();
            }
        },
        RIGHT {
            @Override
            public void execute(Player player) {
                player.moveRight();
            }
        },
        UNKNOWN {
            @Override
            public void execute(Player player) {
                System.out.println("Unknown command.");
            }
        };

        public abstract void execute(Player player);

        public static CommandWord fromString(String move) {
            try {
                return CommandWord.valueOf(move.toUpperCase());
            } catch (IllegalArgumentException e) {
                return UNKNOWN;
            }
        }

        @Override
        public String toString() {
            return name().toLowerCase();
        }
    }



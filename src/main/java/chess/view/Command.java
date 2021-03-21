package chess.view;

import chess.ChessGame;

import java.util.Arrays;

public enum Command {
    START("START", true) {
        @Override
        public void execute(ChessGame chessGame) {
            chessGame.initSetting();
        }
    },
    END("END", false) {
        @Override
        public void execute(ChessGame chessGame) {
            chessGame.end();
        }
    },
    MOVE("MOVE", true) {
        @Override
        public void execute(ChessGame chessGame) {
        }
    },
    STATUS("STATUS", false) {
        @Override
        public void execute(ChessGame chessGame) {

        }
    };

    public abstract void execute(ChessGame chessGame);

    private final String command;
    private final boolean isPrint;

    Command(final String command, final boolean isPrint) {
        this.command = command;
        this.isPrint = isPrint;
    }

    public static boolean isValidateCommand(final String command) {
        return Arrays.stream(Command.values())
                .anyMatch(cmd -> cmd.command.equals(command));
    }

    public boolean isPrint() {
        return this.isPrint;
    }
}

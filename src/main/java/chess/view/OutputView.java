package chess.view;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.pieces.Piece;
import chess.domain.position.Position;

import java.util.Arrays;

public class OutputView {

    public static final int BOARD_MAX_SIZE = 8;

    private OutputView() {
    }

    public static void printStartMessage() {
        System.out.println("> 체스 게임을 시작합니다.");
        System.out.println("> 게임 시작 : start");
        System.out.println("> 게임 종료 : end");
        System.out.println("> 게임 이동 : move source위치 target위치 - 예. move b2 b3");
    }

    public static void printBoard(final Board board) {
        String[][] boardStatus = getInitBoard();
        putPieceToBoard(board, boardStatus);

        for (int boardSize = 0; boardSize < BOARD_MAX_SIZE; ++boardSize) {
            printOneLine(boardStatus[boardSize]);
            System.out.printf("  %d", BOARD_MAX_SIZE - boardSize);
            System.out.println();
        }
        printColUI();
    }

    public static void printNoStartMessage() {
        System.out.println("게임이 아직 시작되지 않았습니다.");
    }


    private static void printColUI() {
        System.out.println();
        for (char alpha = 'a'; alpha <= 'h'; ++alpha) {
            System.out.print(alpha);
        }
        System.out.println();
    }

    private static void putPieceToBoard(final Board board, final String[][] arr) {
        board.toMap().values()
                .forEach(v -> {
                    for (Piece piece : v.toList()) {
                        Position position = piece.getPosition();
                        arr[position.getRow()][position.getCol()] = piece.getInitial();
                    }
                });
    }

    private static String[][] getInitBoard() {
        String[][] arr = new String[8][8];

        for (int i = 0; i < 8; ++i) {
            Arrays.fill(arr[i], ".");
        }
        return arr;
    }

    private static void printOneLine(final String[] line) {
        for (String val : line) {
            System.out.print(val);
        }
    }

    public static void printEachTeamScore(final Double blackTeamScore, final Double whiteTeamScore) {
        System.out.println("Black 팀 점수 : " + blackTeamScore);
        System.out.println("White 팀 점수 : " + whiteTeamScore);
    }

    public static void printWinner(final Team winner, final Double blackTeamScore, final Double whiteTeamScore) {
        printEachTeamScore(blackTeamScore, whiteTeamScore);
        System.out.println("승자는 " + winner.name() + "팀 입니다.");
    }
}

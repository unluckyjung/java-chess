package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.position.Position;
import chess.domain.util.RowConverter;

import java.util.ArrayList;
import java.util.List;

public class Bishop extends NoKingPieces {
    private static final String BLACK_TEAM_ROW = "8";
    private static final String WHITE_TEAM_ROW = "1";
    private static final double SCORE = 3.0;
    private static final int LEFT_SIDE_INIT_COL = 2;
    private static final int RIGHT_SIDE_INIT_COL = 5;

    public Bishop(final Team team, final Position position) {
        super(position, "B", team, SCORE);
    }

    public static Bishop of(final Team team, final int col) {
        if (col != LEFT_SIDE_INIT_COL && col != RIGHT_SIDE_INIT_COL) {
            throw new IllegalArgumentException("잘못된 초기 위치입니다.");
        }
        return new Bishop(team, getInitPosition(team, col));
    }

    private static Position getInitPosition(final Team team, final int col) {
        if (team.equals(Team.BLACK)) {
            return new Position(RowConverter.getLocation(BLACK_TEAM_ROW), col);
        }
        return new Position(RowConverter.getLocation(WHITE_TEAM_ROW), col);
    }

    @Override
    public final List<Position> getMovablePositions(final Board board) {
        List<Position> movablePositions = new ArrayList<>();

        int[] rowDir = {-1, 1, -1, 1};
        int[] colDir = {-1, 1, 1, -1};

        for (int dir = 0; dir < colDir.length; ++dir) {
            addMovablePositions(movablePositions, board, rowDir[dir], colDir[dir]);
        }
        return movablePositions;
    }

    private void addMovablePositions(final List<Position> movablePositions, final Board board, final int rowDir, final int colDir) {
        int curRow = getPosition().getRow();
        int curCol = getPosition().getCol();

        while (isMoveAbleDir(movablePositions, board, curRow + rowDir, curCol + colDir)) {
            movablePositions.add(new Position(curRow += rowDir, curCol += colDir));
        }
    }

    private boolean isMoveAbleDir(final List<Position> movablePositions, final Board board, final int nextRow, final int nextCol) {
        if (!board.validateRange(nextRow, nextCol)) {
            return false;
        }
        if (board.piecesByTeam(getTeam()).containByPosition(new Position(nextRow, nextCol))) {
            return false;
        }
        if (board.piecesByTeam(Team.getAnotherTeam(getTeam())).containByPosition(new Position(nextRow, nextCol))) {
            movablePositions.add(new Position(nextRow, nextCol));
            return false;
        }
        return true;
    }
}

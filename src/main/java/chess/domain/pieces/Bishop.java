package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.board.Board;
import chess.domain.pieces.Movable.MultiMove;
import chess.domain.position.Position;
import chess.domain.util.RowConverter;

import java.util.List;

public final class Bishop extends NoKingPieces implements MultiMove {
    private static final String BLACK_TEAM_ROW = "8";
    private static final String WHITE_TEAM_ROW = "1";
    private static final double SCORE = 3.0;
    private static final int LEFT_SIDE_INIT_COL = 2;
    private static final int RIGHT_SIDE_INIT_COL = 5;

    private Bishop(final Team team, final Position position) {
        super(position, "B", team, SCORE);
    }

    private Bishop(final Position position, final String initial, final Team team, final double score) {
        super(position, initial, team, score);
    }

    public static Bishop of(final Team team, final Position position) {
        return new Bishop(position, "B", team, SCORE);
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

    // 이놈을 추상메소드화 몸체를 자식클래스에서 구현
    @Override
    // 외부에서 호출하는 메소드
    public List<Position> getMovablePositions(Board board) {
        int[] rowDir = {-1, 1, -1, 1};
        int[] colDir = {-1, 1, 1, -1};
        return getMovablePositionsByDir(board, rowDir, colDir);
    }

    @Override
    public boolean isMoveAble(List<Position> movablePositions, Board board, int nextRow, int nextCol) {
        return isMoveAbleDir(movablePositions, board, nextRow, nextCol, getTeam());
    }
//    // 부모 클래스에서 구현, 여기서 addMovablePositions와 의존관계가 생김.
//    // addMoveblePos도 부모에서 구현
//    public List<Position> getMovablePositionsByDir(final Board board, final int[] rowDir, final int[] colDir) {
//        List<Position> movablePositions = new ArrayList<>();
//
//        for (int dir = 0; dir < colDir.length; ++dir) {
//            addMovablePositions(movablePositions, board, rowDir[dir], colDir[dir]);
//        }
//        return movablePositions;
//    }

//    @Override
//    public List<Position> getMovablePositions(final Board board) {
//        List<Position> movablePositions = new ArrayList<>();
//
//        int[] rowDir = {-1, 1, -1, 1};
//        int[] colDir = {-1, 1, 1, -1};
//
//        for (int dir = 0; dir < colDir.length; ++dir) {
//            addMovablePositions(movablePositions, board, rowDir[dir], colDir[dir]);
//        }
//        return movablePositions;
//    }

//    // 부모 클래스 구현
//    private void addMovablePositions(final List<Position> movablePositions, final Board board, final int rowDir, final int colDir) {
//        int curRow = getPosition().getRow();
//        int curCol = getPosition().getCol();
//
//        // isMoveAbleDir은 moveAble인터페이스에 있는거 사용함. 근데 이건 아직 안만들어진거, 자식에서 해결될것
//        while (isMoveAbleDir(movablePositions, board, curRow + rowDir, curCol + colDir)) {
//            movablePositions.add(new Position(curRow += rowDir, curCol += colDir));
//        }
//    }

//    // 구현체가 조금씩 다름. 멀티 이동이냐 싱글 이동이냐
//    // isMoveAbleDir를 인터페이스에 구현, 싱글인지 멀틴지로 나눠서
//    private boolean isMoveAbleDir(final List<Position> movablePositions, final Board board, final int nextRow, final int nextCol) {
//        if (!board.validateRange(nextRow, nextCol)) {
//            return false;
//        }
//        if (board.piecesByTeam(getTeam()).containByPosition(new Position(nextRow, nextCol))) {
//            return false;
//        }
//        if (board.piecesByTeam(Team.getAnotherTeam(getTeam())).containByPosition(new Position(nextRow, nextCol))) {
//            movablePositions.add(new Position(nextRow, nextCol));
//            return false;
//        }
//        return true;
//    }
}

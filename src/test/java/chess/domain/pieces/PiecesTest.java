package chess.domain.pieces;

import chess.domain.Team;
import chess.domain.position.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PiecesTest {
    private Pieces pieces;

    @BeforeEach
    void setUp() {
        List<Piece> piecesList = Arrays.asList(
                King.of(Team.WHITE, new Position(1, 2)),
                Queen.of(Team.WHITE, new Position(2, 2)),
                Rook.of(Team.WHITE, new Position(3, 3)),
                Bishop.of(Team.WHITE, new Position(4, 4)),
                Knight.of(Team.WHITE, new Position(5, 5)),

                Pawn.of(Team.WHITE, new Position(4, 5)),
                Pawn.of(Team.WHITE, new Position(3, 5))
        );
        pieces = new Pieces(piecesList);
    }

    @Test
    @DisplayName("특정 위치에 해당하는 piece가 있으면, true를 반환한다.")
    void containByPosition() {
        assertTrue(pieces.containByPosition(new Position(4, 5)));
    }

    @Test
    @DisplayName("특정 위치를 넣으면, 해당하는 위치의 piece를 가져온다.")
    void getPieceByPosition() {
        Position position = new Position(4, 5);
        Piece piece = pieces.getPieceByPosition(position);
        assertThat(piece.getPosition()).isEqualTo(position);
    }

    @Test
    @DisplayName("특정 위치에 해당하는 piece가 없으면, 예외가 발생한다.")
    void getPieceByPositionException() {
        assertThatThrownBy(
                () -> pieces.getPieceByPosition(new Position(7, 7))
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("특정 위치에 해당하는 piece가 있다면, 삭제한다.")
    void removePieceByPosition() {
        int originalSize = pieces.toList().size();
        pieces.removePieceByPosition(new Position(4, 5));
        assertThat(pieces.toList()).hasSize(originalSize - 1);
    }

    @Test
    @DisplayName("king이 살아있다면, true를 반환한다.")
    void kingAlive() {
        assertTrue(pieces.kingAlive());
    }

    @Test
    @DisplayName("점수를 계산한다.")
    void calculateScore() {
        double score = pieces.calculateScore(0, 7);
        assertThat((int) (score * 10)).isEqualTo((int) (20.5 * 10));
    }

}
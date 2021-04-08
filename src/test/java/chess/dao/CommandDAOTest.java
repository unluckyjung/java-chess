package chess.dao;

import chess.vo.PointsVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class CommandDAOTest {
    private final CommandDAO commandDAO = new CommandDAO(
            "localhost:13306",
            "db_chess",
            "?useSSL=false&serverTimezone=UTC&characterEncoding=utf8",
            "root",
            "root"
    );
    private final String testRoomId = "testId";
    private final List<PointsVO> commands = new ArrayList<>();

    @BeforeEach
    void setUp() {
        commands.add(new PointsVO("00", "02"));
        commands.add(new PointsVO("02", "04"));
    }

    @Test
    @DisplayName("DB 접속에 성공하면 null이 나오지 않는다.")
    void connection() {
        Connection connection = commandDAO.getConnection();
        assertThat(connection).isNotNull();
    }

    @Test
    @DisplayName("roomId에 testRoomId을 넣으면, (00, 02), (02,04) points 들을 얻는다.")
    void findPointsByRoomId() throws SQLException {
        addPoints(testRoomId);
        findPoint(testRoomId);
        deleteCommandsByRoomId(testRoomId);
    }

    @Test
    @DisplayName("roomId를 testRoomId으로 (00, 02), (02,04) points를 넣으면, 성공한다.")
    void addPointsToRoomId() throws SQLException {
        addPoints(testRoomId);
        findPoint(testRoomId);
        deleteCommandsByRoomId(testRoomId);
    }

    private void findPoint(final String testRoomId) throws SQLException {
        List<PointsVO> points = commandDAO.getCommandsByRoomId(testRoomId);
        assertThat(points).isEqualTo(commands);
    }

    void addPoints(final String roomId) {
        for (PointsVO points : commands) {
            commandDAO.addCommand(roomId, points.getStartPoint(), points.getEndPoint());
        }
    }

    @Test
    @DisplayName("roomId을 testId로 가진 것들을 삭제하면, 성공한다.")
    void deleteCommands() {
        deleteCommandsByRoomId(testRoomId);
    }

    void deleteCommandsByRoomId(final String roomId) {
        commandDAO.deleteCommandsByRoomId(roomId);
    }
}
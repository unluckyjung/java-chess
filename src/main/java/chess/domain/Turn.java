package chess.domain;

import static chess.domain.Team.BLACK;
import static chess.domain.Team.WHITE;

public class Turn {
    private Team team;

    private Turn() {
        this.team = WHITE;
    }

    public Turn(Team team) {
        this.team = team;
    }

    static Turn init() {
        return new Turn();
    }

    Turn turnChanged() {
        if (team == BLACK) {
            return changeTurn(WHITE);
        }
        return changeTurn(BLACK);
    }

    private Turn changeTurn(Team team) {
        this.team = team;
        return this;
    }

    public boolean isTurn(Team team) {
        return this.team == team;
    }

    public Team getTeam() {
        return this.team;
    }

    @Override
    public String toString() {
        return team.toString();
    }
}
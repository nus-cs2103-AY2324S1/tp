package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TeamBook;
import seedu.address.model.team.Team;

public class TypicalTeams {
    public static final Team TEAM1 = new TeamBuilder().withTeamLeader("1")
            .withTeamName("TEAM1").withDevelopers("2", "3", "4").build();

    public static final Team TEAM2 = new TeamBuilder().withTeamLeader("2")
            .withTeamName("TEAM2").withDevelopers("3").build();

    public static TeamBook getTypicalTeamBook() {
        TeamBook tb = new TeamBook();
        for (Team team : getTypicalTeams()) {
            tb.addTeam(team);
        }
        return tb;
    }

    public static List<Team> getTypicalTeams() {
        return new ArrayList<>(Arrays.asList(TEAM1, TEAM2));
    }

}






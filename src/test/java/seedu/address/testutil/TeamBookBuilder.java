package seedu.address.testutil;

import seedu.address.model.TeamBook;
import seedu.address.model.team.Team;

/**
 * A utility class to help with building Teambook objects.
 * Example usage: <br>
 *     {@code AddressBook ab = new TeamBookBuilder().withTeam("Team1", "Team2").build();}
 */
public class TeamBookBuilder {
    private TeamBook teamBook;

    /**
     * Instantiates a new Team book builder.
     */
    public TeamBookBuilder() {
        teamBook = new TeamBook();
    }

    /**
     * Adds teambook builder.
     *
     * @param team the team
     * @return the team book builder
     */
    public TeamBookBuilder withTeam(Team team) {
        teamBook.addTeam(team);
        return this;
    }

    /**
     * Builds team book.
     *
     * @return the team book
     */
    public TeamBook build() {
        return teamBook;
    }
}

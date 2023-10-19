package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.person.IdentityCode;
import seedu.address.model.team.Team;

/**
 * A utility class to help with building Team objects for testing.
 */
public class TeamBuilder {

    public static final String DEFAULT_TEAM_NAME = "Team Alpha";
    public static final String DEFAULT_LEADER_IDENTITY_CODE = "TL001";

    private IdentityCode leaderIdentityCode;
    private String teamName;
    private final Set<IdentityCode> developerIdentityCodes;

    /**
     * Creates a {@code TeamBuilder} with the default details.
     */
    public TeamBuilder() {
        leaderIdentityCode = new IdentityCode(DEFAULT_LEADER_IDENTITY_CODE);
        teamName = DEFAULT_TEAM_NAME;
        developerIdentityCodes = new HashSet<>();
    }

    /**
     * Initializes the TeamBuilder with the data of {@code teamToCopy}.
     */
    public TeamBuilder(Team teamToCopy) {
        leaderIdentityCode = teamToCopy.getTeamLeaderIdentityCode();
        teamName = teamToCopy.getTeamName();
        developerIdentityCodes = new HashSet<>(teamToCopy.getDeveloperIdentityCodes());
    }

    /**
     * Sets the {@code IdentityCode} of the TeamLeader of the {@code Team} that we are building.
     */
    public TeamBuilder withTeamLeader(String leaderId) {
        this.leaderIdentityCode = new IdentityCode(leaderId);
        return this;
    }

    /**
     * Sets the {@code teamName} of the {@code Team} that we are building.
     */
    public TeamBuilder withTeamName(String teamName) {
        this.teamName = teamName;
        return this;
    }

    /**
     * Adds the developers with the given {@code developerIds} to the {@code Team} that we are building.
     */
    public TeamBuilder withDevelopers(String... developerIds) {
        for (String devId : developerIds) {
            developerIdentityCodes.add(new IdentityCode(devId));
        }
        return this;
    }

    public Team build() {
        Team team = new Team(leaderIdentityCode, teamName);
        for (IdentityCode devId : developerIdentityCodes) {
            team.addDeveloper(devId);
        }
        return team;
    }
}

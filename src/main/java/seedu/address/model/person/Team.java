package seedu.address.model.person;

import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Represents a Team in the address book.
 * Each team consists of one TeamLeader and multiple Developers.
 */
public class Team {
    private IdentityCode leaderIdentityCode;
    private String teamName;
    private final Set<IdentityCode> developerIdentityCodes;

    /**
     * Constructs a new Team instance with the specified TeamLeader and team name.
     *
     * @param leaderIdentityCode The IdentityCode of the team leader.
     * @param teamName The name of the team.
     */
    public Team(IdentityCode leaderIdentityCode, String teamName) {
        this.leaderIdentityCode = leaderIdentityCode;
        this.teamName = teamName;
        this.developerIdentityCodes = new HashSet<>();
    }

    /**
     * Adds a Developer's IdentityCode to the team.
     *
     * @param developerIdentityCode The IdentityCode of the developer to be added.
     */
    public void addDeveloper(IdentityCode developerIdentityCode) {
        developerIdentityCodes.add(developerIdentityCode);
    }

    /**
     * Removes a Developer's IdentityCode from the team.
     *
     * @param developerIdentityCode The IdentityCode of the developer to be removed.
     * @return true if the developer was part of the team and was removed, false otherwise.
     */
    public boolean removeDeveloper(IdentityCode developerIdentityCode) {
        return developerIdentityCodes.remove(developerIdentityCode);
    }

    /**
     * Updates the TeamLeader of the team.
     *
     * @param leaderIdentityCode The new TeamLeader's IdentityCode.
     */
    public void setTeamLeader(IdentityCode leaderIdentityCode) {
        this.leaderIdentityCode = leaderIdentityCode;
    }

    /**
     * Retrieves the TeamLeader's IdentityCode of the team.
     *
     * @return The TeamLeader's IdentityCode.
     */
    public IdentityCode getTeamLeaderIdentityCode() {
        return this.leaderIdentityCode;
    }

    /**
     * Retrieves the set of Developers' IdentityCodes in the team.
     *
     * @return A set of Developers' IdentityCodes.
     */
    public Set<IdentityCode> getDeveloperIdentityCodes() {
        return developerIdentityCodes;
    }

    /**
     * Retrieves the name of the team.
     *
     * @return The team's name.
     */
    public String getTeamName() {
        return this.teamName;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Team Name", teamName)
                .add("Team Leader", leaderIdentityCode)
                .add("Developer List", developerIdentityCodes)
                .toString();
    }
}

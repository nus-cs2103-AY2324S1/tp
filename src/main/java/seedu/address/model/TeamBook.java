package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.model.person.IdentityCode;
import seedu.address.model.team.Team;
import seedu.address.model.team.UniqueTeamList;
import seedu.address.model.team.exceptions.TeamNotFoundException;

/**
 * Represents a TeamBook in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 * Duplicates are not allowed (by .isSameTeam comparison or some other logic you prefer).
 */
public class TeamBook implements ReadOnlyTeamBook {

    private final UniqueTeamList teams;

    {
        teams = new UniqueTeamList();
    }

    /**
     * Initializes an empty TeamBook.
     */
    public TeamBook() {}

    /**
     * Creates a TeamBook using the Teams in the {@code toBeCopied}
     *
     * @param toBeCopied ReadOnlyTeamBook that is to be copied into the new TeamBook.
     */
    public TeamBook(ReadOnlyTeamBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /**
     * Sets the list of teams in the TeamBook.
     *
     * @param teams List of teams to be set in the TeamBook.
     */
    public void setTeams(List<Team> teams) {
        this.teams.setTeams(teams);
    }

    /**
     * Resets the TeamBook data with the given new data.
     *
     * @param newData ReadOnlyTeamBook containing the new data to reset.
     */
    public void resetData(ReadOnlyTeamBook newData) {
        requireNonNull(newData);
        setTeams(newData.getTeamList());
    }

    /**
     * Checks if the TeamBook has a team with the specified team name.
     *
     * @param teamName The name of the team to be checked.
     * @return boolean indicating if the team exists.
     */
    public boolean hasTeam(String teamName) {
        requireNonNull(teamName);
        return teams.containsTeamByName(teamName);
    }

    /**
     * Checks if the TeamBook has a team with the specified team name.
     *
     * @param team The team to be checked.
     * @return boolean indicating if the team exists.
     */
    public boolean hasTeam(Team team) {
        requireNonNull(team);
        return teams.containsTeamByName(team.getTeamName());
    }

    /**
     * Retrieves the Team Leader Identity code of the team from the TeamBook by his team name.
     *
     * @param teamName The name of the team to retrieve its team leader Identity code.
     * @return The Team Leader Identity code if found, or null if the team does not exist.
     */
    public IdentityCode getTeamLeaderIdOfTeam(String teamName) {
        requireNonNull(teamName);
        return teams.getTeamLeaderIdOfTeam(teamName);
    }

    public void setTeamLeaderOfTeam(String teamName, IdentityCode newTeamLeaderID) {
        requireNonNull(teamName);
        requireNonNull(newTeamLeaderID);
        teams.setTeamLeaderOfTeam(teamName, newTeamLeaderID);

    }

    /**
     * Adds a new team to the TeamBook.
     *
     * @param team Team to be added.
     */
    public void addTeam(Team team) {
        teams.add(team);
    }

    /**
     * Removes the team with the given name from the TeamBook.
     *
     * @param teamName Name of the team to be removed.
     */
    public void removeTeamByName(String teamName) {
        teams.removeTeamByName(teamName);
    }

    /**
     * Remove developer from team.
     *
     * @param teamName              the team name
     * @param developerIdentityCode the developer identity code
     */
    public void removeDeveloperFromTeam(String teamName, IdentityCode developerIdentityCode) {
        if (teams.containsTeamByName(teamName)) {
            teams.removeDeveloperFromTeam(teamName, developerIdentityCode);
        } else {
            throw new TeamNotFoundException();
        }
    }
    /**
     * Checks if the given id is an id of team leader.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean isTeamLeader(IdentityCode id) {
        return teams.isTeamLeader(id);
    }

    public boolean removeDeveloperFromAllTeams(IdentityCode developerIdentityCode) {
        return teams.removeDeveloperFromAllTeams(developerIdentityCode);
    }


    /**
     * Checks if given person by identity code is in given team
     *
     * @param teamToAddTo          the team to add to
     * @param devToAddIdentityCode the dev to add identity code
     * @return the boolean
     */

    public boolean personAlreadyInTeam(String teamToAddTo, IdentityCode devToAddIdentityCode) {

        return teams.teamContainsPerson(teamToAddTo, devToAddIdentityCode);
    }


    /**
     * Checks if given team exists.
     *
     * @param teamToAddTo the team to add to
     * @return the boolean
     */
    public boolean invalidAddToTeam(String teamToAddTo) {
        requireNonNull(teamToAddTo);
        return teams.containsTeamByName(teamToAddTo);
    }

    public void addDevToTeam(String teamToAddTo, IdentityCode devToAddIdentityCode) {
        teams.addDevToTeam(teamToAddTo, devToAddIdentityCode);
    }

    public void editTeamName(String originalTeamName, String newTeamName) {
        teams.editTeamName(originalTeamName, newTeamName);
    }

    @Override
    public ObservableList<Team> getTeamList() {
        return teams.asUnmodifiableObservableList();
    }

    @Override
    public int hashCode() {
        return teams.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TeamBook teamBook = (TeamBook) obj;
        // Assume 'teams' is a List of Team objects in TeamBook
        // Also, make sure that Team class has an overridden equals method
        return teams.equals(teamBook.teams);
    }

}

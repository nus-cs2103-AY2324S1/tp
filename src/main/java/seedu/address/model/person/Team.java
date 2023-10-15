package seedu.address.model.person;


import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.exceptions.PersonNotFoundException;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;



/**
 * Represents a Team in the address book.
 * An instance of Team consists of a TeamLeader and multiple Developers.
 */
public class Team {

    private final int leaderHashCode;
    private String teamName = "unassigned";


    //todo: set will ignore duplicated values, do we need to notify the user that he added a duplicate value or not?
    //use the hash code for representing each developer. The hash code will be updated when there is a change
    //to the developer's information.
    private final List<Integer> developerHashCodes;

    /**
     * Constructs a new Team instance with the specified TeamLeader.
     *
     * @param leader The leader of the team.
     */
    public Team(TeamLeader leader) {
        this.leaderHashCode = leader.hashCode();
        this.developerHashCodes = new ArrayList<>();
    }

    /**
     * Constructs a new Team instance with the specified TeamLeader and team name.
     *
     * @param leader The leader of the team.
     * @param teamName The name of the team.
     */
    public Team(TeamLeader leader, String teamName) {
        this.leaderHashCode = leader.hashCode();
        this.developerHashCodes = new ArrayList<>();
        this.teamName = teamName;
    }


    public boolean hasDeveloper(int developerHashCode) {
        return this.developerHashCodes.contains(developerHashCode);
    }

    /**
     * Adds a Developer to the team.
     *
     * @param developerHashCode The developer hash code to be added.
     */
    public void addDeveloper(int developerHashCode) {
        if (hasDeveloper(developerHashCode)) {
            throw new DuplicatePersonException();
        } else {
            this.developerHashCodes.add(developerHashCode);
        }
    }

    /**
     * Adds a Developer to the team.
     *
     * @param developer The developer to be added.
     */
    public void addDeveloper(Developer developer) {
        addDeveloper(developer.hashCode());
    }



    /**
     * Removes a Developer to the team.
     *
     * @param developerHashCode The developer to be removed.
     */
    public void removeDeveloper(int developerHashCode) {
        if (hasDeveloper(developerHashCode)) {
            this.developerHashCodes.remove(developerHashCode);
        } else {
            throw new PersonNotFoundException();
        }
    }


    /**
     * Retrieves the TeamLeader of the team.
     *
     * @return The TeamLeader of the team.
     */
    public int getLeaderHashCode() {
        return leaderHashCode;
    }


    public boolean isSameTeam(Team team) {
        //return hasSameTeamName(team) && hasSameTeamLeader(team) && hasSameDevelopers(team);
        return hasSameTeamName(team);
    }

    public boolean hasSameTeamLeader(Team team) {
        requireAllNonNull(team);
        return leaderHashCode == team.getLeaderHashCode();
    }

    public boolean hasSameDevelopers(Team team) {
        requireAllNonNull(team);

        //todo: fix this warning
        return developerHashCodes.equals(team.getLeaderHashCode());
    }

    public boolean hasSameTeamName(Team team) {
        return this.teamName.equals(team.getTeamName());
    }

    /**
     * Retrieves the Set of Developers in the team.
     *
     * @return A Set of Developers.
     */
    public List<Integer> getDevelopers() {
        return developerHashCodes;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }
    public String getTeamName() {
        return teamName;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Team)) {
            return false;
        }

        Team otherTeam = (Team) other;
        return isSameTeam(otherTeam);
    }


}

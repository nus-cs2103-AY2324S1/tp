package seedu.address.model.team;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.team.exceptions.DuplicateTeamException;
import seedu.address.model.team.exceptions.TeamNotFoundException;

/**
 * A list of teams that enforces uniqueness between its elements and does not allow nulls.
 * A team is considered unique by comparing using {@code Team#equals(Object)}.
 *
 * Supports a minimal set of list operations.
 */
public class UniqueTeamList implements Iterable<Team> {

    private final ObservableList<Team> internalList = FXCollections.observableArrayList();
    private final ObservableList<Team> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains a team with the same name as the given argument.
     */
    public boolean containsTeamByName(String teamName) {
        requireNonNull(teamName);
        return internalList.stream().anyMatch(team -> team.getTeamName().equals(teamName));
    }

    /**
     * Adds a team to the list.
     * The team must not already exist in the list.
     */
    public void add(Team toAdd) {
        requireNonNull(toAdd);
        if (containsTeamByName(toAdd.getTeamName())) {
            throw new DuplicateTeamException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the team with the given name in the list with {@code editedTeam}.
     * The team with the given name must exist in the list.
     */
    public void setTeamByName(String teamName, Team editedTeam) {
        requireAllNonNull(teamName, editedTeam);

        int index = internalList.indexOf(new Team(null, teamName));
        if (index == -1) {
            throw new TeamNotFoundException();
        }

        if (containsTeamByName(editedTeam.getTeamName())) {
            throw new DuplicateTeamException();
        }

        internalList.set(index, editedTeam);
    }

    /**
     * Gets a team from the list by its name.
     *
     * @param teamName The name of the team to retrieve.
     * @return The Team object if found, or null if the team does not exist.
     */
    public Team getTeamByName(String teamName) {
        for (Team team : internalList) {
            if (team.getTeamName().equals(teamName)) {
                return team;
            }
        }
        throw new TeamNotFoundException();
    }


    /**
     * Removes the team with the given name from the list.
     * The team with the given name must exist in the list.
     */
    public void removeTeamByName(String teamName) {
        requireNonNull(teamName);

        if (!internalList.remove(this.getTeamByName(teamName))) {
            throw new TeamNotFoundException();
        }
    }

    public void setTeams(UniqueTeamList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code teams}.
     * {@code teams} must not contain duplicate teams.
     */
    public void setTeams(List<Team> teams) {
        requireAllNonNull(teams);
        if (!teamsAreUnique(teams)) {
            throw new DuplicateTeamException();
        }

        internalList.setAll(teams);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Team> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Team> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UniqueTeamList)) {
            return false;
        }

        UniqueTeamList otherUniqueTeamList = (UniqueTeamList) other;
        return internalList.equals(otherUniqueTeamList.internalList);
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    @Override
    public String toString() {
        return internalList.toString();
    }

    /**
     * Returns true if {@code teams} contains only unique teams.
     */
    private boolean teamsAreUnique(List<Team> teams) {
        for (int i = 0; i < teams.size() - 1; i++) {
            for (int j = i + 1; j < teams.size(); j++) {
                if (teams.get(i).getTeamName().equals(teams.get(j).getTeamName())) {
                    return false;
                }
            }
        }
        return true;
    }
}

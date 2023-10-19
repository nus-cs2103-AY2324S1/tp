package seedu.address.model.person;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;


import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Team;
import seedu.address.model.person.exceptions.DuplicateTeamException;
import seedu.address.model.person.exceptions.TeamNotFoundException;

/**
 * A list of Teams that enforces uniqueness between its elements and does not allow nulls.
 * A team is considered unique by comparing using {@code Team#isSameTeam(Team)}. As such, adding and updating of
 * Teams uses Team#isSameTeam(Team) for equality so as to ensure that the Team being added or updated is
 * unique in terms of identity in the UniqueTeamList. However, the removal of a Team uses Team#equals(Object) so
 * as to ensure that the Team with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Team#isSameTeam(Team)
 */
public class UniqueTeamList implements Iterable<Team> {

    private final ObservableList<Team> internalList = FXCollections.observableArrayList();
    private final ObservableList<Team> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Team as the given argument.
     */
    public boolean contains(Team toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameTeam);
    }

    /**
     * Adds a Team to the list.
     * The Team must not already exist in the list.
     */
    public void add(Team toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateTeamException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the Team {@code target} in the list with {@code editedTeam}.
     * {@code target} must exist in the list.
     * The Team identity of {@code editedTeam} must not be the same as another existing Team in the list.
     */
    public void setTeam(Team target, Team editedTeam) {
        requireAllNonNull(target, editedTeam);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new TeamNotFoundException();
        }

        if (!target.isSameTeam(editedTeam) && contains(editedTeam)) {
            throw new DuplicateTeamException();
        }

        internalList.set(index, editedTeam);
    }

    /**
     * Removes the equivalent Team from the list.
     * The Team must exist in the list.
     */
    public void remove(Team toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new TeamNotFoundException();
        }
    }

    public void setTeams(UniqueTeamList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code Teams}.
     * {@code Teams} must not contain duplicate Teams.
     */
    public void setTeams(List<Team> Teams) {
        requireAllNonNull(Teams);
        if (!TeamsAreUnique(Teams)) {
            throw new DuplicateTeamException();
        }

        internalList.setAll(Teams);
    }


    //todo: more data protection. Maybe have two functions. One for displaying and one for editing.
    public Team getTeamByName(String name) {
        Team foundTeam = null;
        for (Team t : internalList) {
            if (t.getTeamName().equals(name)) {
                foundTeam = t;
                break;
            }
        }

        if (foundTeam != null) {
            return foundTeam;
        } else {
            throw new TeamNotFoundException();
        }
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
     * Returns true if {@code Teams} contains only unique Teams.
     */
    private boolean TeamsAreUnique(List<Team> Teams) {
        for (int i = 0; i < Teams.size() - 1; i++) {
            for (int j = i + 1; j < Teams.size(); j++) {
                if (Teams.get(i).isSameTeam(Teams.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}


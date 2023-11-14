package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.team.Team;

/**
 * Unmodifiable view of a team book
 */
public interface ReadOnlyTeamBook {

    /**
     * Returns an unmodifiable view of the teams list.
     * This list will not contain any duplicate teams.
     */
    ObservableList<Team> getTeamList();

}

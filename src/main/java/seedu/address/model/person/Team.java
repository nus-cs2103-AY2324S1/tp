package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Team in the address book.
 * A Team consists of a TeamLeader and multiple Developers.
 */
public class Team {

    private final TeamLeader leader;
    private final List<Developer> developers;

    /**
     * Constructs a new Team instance with the specified TeamLeader.
     *
     * @param leader The leader of the team.
     */
    public Team(TeamLeader leader) {
        this.leader = leader;
        this.developers = new ArrayList<>();
    }

    /**
     * Adds a Developer to the team.
     *
     * @param developer The developer to be added.
     */
    public void addDeveloper(Developer developer) {
        this.developers.add(developer);
    }

    /**
     * Retrieves the TeamLeader of the team.
     *
     * @return The TeamLeader of the team.
     */
    public TeamLeader getLeader() {
        return leader;
    }

    /**
     * Retrieves the list of Developers in the team.
     *
     * @return A list of Developers.
     */
    public List<Developer> getDevelopers() {
        return developers;
    }
}

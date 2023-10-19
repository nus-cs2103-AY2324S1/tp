package seedu.address.model.person;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import seedu.address.model.tag.Tag;
import seedu.address.model.team.Team;

/**
 * Represents a Project Manager in the address book.
 * A Project Manager manages multiple teams.
 * Inherits from the Person class.
 */
public class ProjectManager extends Person {

    private final ArrayList<Team> teams;

    /**
     * Constructs a new ProjectManager instance.
     *
     * @param name    The name of the project manager.
     * @param phone   The phone number of the project manager.
     * @param email   The email of the project manager.
     * @param address The address of the project manager.
     * @param remark  Any remarks related to the project manager.
     * @param tags    Any tags associated with the project manager.
     */
    public ProjectManager(Name name, Phone phone, Email email, Address address, Remark remark, Set<Tag> tags) {
        super(name, phone, email, address, remark, tags);
        this.teams = new ArrayList<>();
    }

    /**
     * Adds a team under this project manager.
     *
     * @param team The team to be added.
     */
    public void addTeam(Team team) {
        this.teams.add(team);
    }

    /**
     * Retrieves the list of teams managed by this project manager.
     *
     * @return A list of teams.
     */
    public List<Team> getTeams() {
        return teams;
    }
}

package seedu.address.model.person;

//import java.util.ArrayList;
import java.util.HashSet;
//import java.util.List;
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
    private final Set<Integer> developerHashCode;

    /**
     * Constructs a new Team instance with the specified TeamLeader.
     *
     * @param leader The leader of the team.
     */
    public Team(TeamLeader leader) {
        this.leaderHashCode = leader.hashCode();
        this.developerHashCode = new HashSet<>();
    }

    /**
     * Adds a Developer to the team.
     *
     * @param developerHashCode The developer to be added.
     */
    public void addDeveloper(int developerHashCode) {
        this.developerHashCode.add(developerHashCode);
    }



    /**
     * Adds a Developer to the team.
     *
     * @param developerHashCode The developer to be removed.
     */
    public void removeDeveloper(int developerHashCode) {
        this.developerHashCode.remove(developerHashCode);
    }


    /**
     * Retrieves the TeamLeader of the team.
     *
     * @return The TeamLeader of the team.
     */
    public int getLeaderHashCode() {
        return leaderHashCode;
    }

    /**
     * Retrieves the list of Developers in the team.
     *
     * @return A list of Developers.
     */
    public Set<Integer> getDevelopers() {
        return developerHashCode;
    }

}

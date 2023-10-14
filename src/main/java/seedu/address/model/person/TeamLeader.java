package seedu.address.model.person;


/**
 * Represents a TeamLeader in the address book.
 * A TeamLeader is a specialized person who leads a team.
 */
public class TeamLeader extends Developer {
    private Developer developer;

    /**
     * Instantiates a new Team leader.
     *
     * @param developer the developer
     */
    //Team leader is a developer. When creating a team leader, choose the developer to be assigned as team leader.
    public TeamLeader(Developer developer) {
        this.developer = developer;
    }
}

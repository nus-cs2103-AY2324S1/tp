package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a TeamLeader in the address book.
 * A TeamLeader is a specialized person who leads a team.
 */
public class TeamLeader extends Person {

    /**
     * Constructs a new TeamLeader instance.
     *
     * @param name The name of the TeamLeader.
     * @param phone The phone number of the TeamLeader.
     * @param email The email address of the TeamLeader.
     * @param address The address of the TeamLeader.
     * @param remark Any remarks associated with the TeamLeader.
     * @param tags Tags associated with the TeamLeader.
     */
    public TeamLeader(Name name, Phone phone, Email email, Address address, Remark remark, Set<Tag> tags) {
        super(name, phone, email, address, remark, tags);
    }
}

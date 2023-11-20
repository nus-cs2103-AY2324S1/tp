package seedu.address.model.person;

import java.util.Optional;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Client in the address book.
 */
public class Client extends Person {
    public static final String TYPE_CLIENT = "client";

    /**
     * Every field must be present and not null.
     */
    public Client(Name name, Phone phone, Email email, Address address,
                  Optional<MeetingTime> meetingTime, Set<Tag> tags) {
        super(name, phone, email, new Type(TYPE_CLIENT), address, meetingTime, tags);
    }

    @Override
    public boolean isClient() {
        return true;
    }

    @Override
    public boolean isLead() {
        return false;
    }
}

package seedu.address.model.person;

import java.util.Optional;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Lead in the address book.
 */
public class Lead extends Person {
    public static final String TYPE_LEAD = "lead";
    private final KeyMilestone keyMilestone;

    /**
     * Every field must be present and not null.
     */
    public Lead(Name name, Phone phone, Email email, Address address,
                KeyMilestone keyMilestone, Optional<MeetingTime> meetingTime, Set<Tag> tags) {
        super(name, phone, email, new Type(TYPE_LEAD), address, meetingTime, tags);
        this.keyMilestone = keyMilestone;
    }

    public KeyMilestone getKeyMilestone() {
        return this.keyMilestone;
    }

    @Override
    public boolean isClient() {
        return false;
    }

    @Override
    public boolean isLead() {
        return true;
    }

    @Override
    public String toString() {
        //todo: fix the get
        return new ToStringBuilder(this)
                .add("name", super.getName())
                .add("phone", super.getPhone())
                .add("email", super.getEmail())
                .add("type", super.getType())
                .add("address", super.getAddress())
                .add("key milestone", this.keyMilestone)
                .add("meetingTime", super.getMeetingTime())
                .add("tags", super.getTags())
                .toString();
    }
}

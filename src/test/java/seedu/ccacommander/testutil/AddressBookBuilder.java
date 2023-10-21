package seedu.ccacommander.testutil;

import seedu.ccacommander.model.CcaCommander;
import seedu.ccacommander.model.event.Event;
import seedu.ccacommander.model.member.Member;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code CcaCommander ab = new AddressBookBuilder().withMember("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private CcaCommander ccaCommander;

    public AddressBookBuilder() {
        ccaCommander = new CcaCommander();
    }

    public AddressBookBuilder(CcaCommander ccaCommander) {
        this.ccaCommander = ccaCommander;
    }

    /**
     * Adds a new {@code Member} to the {@code CcaCommander} that we are building.
     */
    public AddressBookBuilder withMember(Member member) {
        ccaCommander.createMember(member);
        return this;
    }

    /**
     * Adds a new {@code Event} to the {@code CcaCommander} that we are building.
     */
    public AddressBookBuilder withEvent(Event event) {
        ccaCommander.createEvent(event);
        return this;
    }

    public CcaCommander build() {
        return ccaCommander;
    }
}

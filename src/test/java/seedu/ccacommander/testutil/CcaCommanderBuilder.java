package seedu.ccacommander.testutil;

import seedu.ccacommander.model.CcaCommander;
import seedu.ccacommander.model.event.Event;
import seedu.ccacommander.model.member.Member;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code CcaCommander ab = new CcaCommanderBuilder().withMember("John", "Doe").build();}
 */
public class CcaCommanderBuilder {

    private CcaCommander ccaCommander;

    public CcaCommanderBuilder() {
        ccaCommander = new CcaCommander();
    }

    public CcaCommanderBuilder(CcaCommander ccaCommander) {
        this.ccaCommander = ccaCommander;
    }

    /**
     * Adds a new {@code Member} to the {@code CcaCommander} that we are building.
     */
    public CcaCommanderBuilder withMember(Member member) {
        ccaCommander.createMember(member);
        return this;
    }

    /**
     * Adds a new {@code Event} to the {@code CcaCommander} that we are building.
     */
    public CcaCommanderBuilder withEvent(Event event) {
        ccaCommander.createEvent(event);
        return this;
    }

    public CcaCommander build() {
        return ccaCommander;
    }
}

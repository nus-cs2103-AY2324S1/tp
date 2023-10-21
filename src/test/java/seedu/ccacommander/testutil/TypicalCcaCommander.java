package seedu.ccacommander.testutil;

import static seedu.ccacommander.testutil.TypicalEvents.getTypicalEvents;
import static seedu.ccacommander.testutil.TypicalMembers.getTypicalMembers;

import seedu.ccacommander.model.CcaCommander;
import seedu.ccacommander.model.event.Event;
import seedu.ccacommander.model.member.Member;

/**
 * A utility class which returns an CcaCommander with all typical members and events.
 */
public class TypicalCcaCommander {
    /**
     * Returns an {@code CcaCommander} with only typical members.
     */
    public static CcaCommander getTypicalMemberAddressBook() {
        CcaCommander ab = new CcaCommander();
        for (Member member : getTypicalMembers()) {
            ab.createMember(member);
        }

        return ab;
    }

    /**
     * Returns an {@code CcaCommander} with only typical events.
     */
    public static CcaCommander getTypicalEventAddressBook() {
        CcaCommander ab = new CcaCommander();
        for (Event event: getTypicalEvents()) {
            ab.createEvent(event);
        }

        return ab;
    }

    /**
     * Returns an {@code CcaCommander} with all the typical members and events.
     */
    public static CcaCommander getTypicalAddressBook() {
        CcaCommander ab = new CcaCommander();
        for (Member member : getTypicalMembers()) {
            ab.createMember(member);
        }

        for (Event event: getTypicalEvents()) {
            ab.createEvent(event);
        }

        return ab;
    }
}

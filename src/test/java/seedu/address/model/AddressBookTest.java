package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_AURORA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BOXING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_AURORA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BOXING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalEvents.AURORA_BOREALIS;
import static seedu.address.testutil.TypicalMembers.ALICE;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.event.Event;
import seedu.address.model.event.exceptions.DuplicateEventException;
import seedu.address.model.member.Member;
import seedu.address.model.member.exceptions.DuplicateMemberException;
import seedu.address.testutil.EventBuilder;
import seedu.address.testutil.MemberBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getMemberList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        AddressBook newData = getTypicalAddressBook();
        addressBook.resetData(newData);
        assertEquals(newData, addressBook);
    }

    @Test
    public void resetData_withDuplicateMembers_throwsDuplicateMemberException() {
        // Two members with the same identity fields
        Member editedAlice = new MemberBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Member> newMembers = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newMembers, Arrays.asList());

        assertThrows(DuplicateMemberException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasMember_nullMember_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasMember(null));
    }

    @Test
    public void hasMember_memberNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasMember(ALICE));
    }

    @Test
    public void hasMember_memberInAddressBook_returnsTrue() {
        addressBook.createMember(ALICE);
        assertTrue(addressBook.hasMember(ALICE));
    }

    @Test
    public void hasMember_memberWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.createMember(ALICE);
        Member editedAlice = new MemberBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(addressBook.hasMember(editedAlice));
    }

    @Test
    public void getMemberList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getMemberList().remove(0));
    }
    @Test
    public void getEventList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getEventList().remove(0));
    }

    @Test
    public void resetData_withDuplicateEvents_throwsDuplicateEventException() {
        // Two events with the same identity fields
        Event editedAurora = new EventBuilder(AURORA_BOREALIS).withDate(VALID_DATE_AURORA)
                .withLocation(VALID_LOCATION_AURORA).build();
        List<Event> newEvents = Arrays.asList(AURORA_BOREALIS, editedAurora);
        AddressBookStub newData = new AddressBookStub(Arrays.asList(), newEvents);

        assertThrows(DuplicateEventException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasEvent_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasEvent(null));
    }

    @Test
    public void hasEvent_eventNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasEvent(AURORA_BOREALIS));
    }

    @Test
    public void hasEvent_eventInAddressBook_returnsTrue() {
        addressBook.createEvent(AURORA_BOREALIS);
        assertTrue(addressBook.hasEvent(AURORA_BOREALIS));
    }

    @Test
    public void hasEvent_eventWithSameNameInAddressBook_returnsFalse() {
        addressBook.createEvent(AURORA_BOREALIS);
        Event editedAurora = new EventBuilder(AURORA_BOREALIS).withDate(VALID_DATE_BOXING)
                .withLocation(VALID_LOCATION_BOXING)
                .build();
        assertFalse(addressBook.hasEvent(editedAurora));
    }
    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{members=" + addressBook.getMemberList()
                + ", events=" + addressBook.getEventList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose members list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Member> members = FXCollections.observableArrayList();
        private final ObservableList<Event> events = FXCollections.observableArrayList();

        AddressBookStub(Collection<Member> members, Collection<Event> events) {
            this.members.setAll(members);
            this.events.setAll(events);
        }

        @Override
        public ObservableList<Member> getMemberList() {
            return members;
        }
        @Override
        public ObservableList<Event> getEventList() {
            return events;
        }
    }

}

package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalRsvps.ALICE_FSC_CC;
import static seedu.address.testutil.TypicalRsvps.CARL_CODING_TBC;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.displayable.DisplayableListViewItem;
import seedu.address.model.event.Event;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.rsvp.Rsvp;
import seedu.address.model.vendor.Vendor;
import seedu.address.model.venue.Venue;
import seedu.address.testutil.PersonBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getPersonList());
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
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE)
                .build();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void hasRsvp_nullRsvp_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasRsvp(null));
    }

    @Test
    public void hasRsvp_rsvpNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasRsvp(CARL_CODING_TBC));
    }

    @Test
    public void hasRsvp_rsvpInAddressBook_returnsTrue() {
        addressBook.addRsvp(ALICE_FSC_CC);
        assertTrue(addressBook.hasRsvp(ALICE_FSC_CC));
    }

    @Test
    public void getRsvpList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getRsvpList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{persons=" + addressBook.getPersonList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Event> events = FXCollections.observableArrayList();
        private final ObservableList<Venue> venues = FXCollections.observableArrayList();
        private final ObservableList<Person> eventAttendees = FXCollections.observableArrayList();
        private final ObservableList<Vendor> eventVendors = FXCollections.observableArrayList();
        private final ObservableList<DisplayableListViewItem> displayableItems = FXCollections.observableArrayList();
        private final ObservableList<Rsvp> rsvps = FXCollections.observableArrayList();
        private final ObservableList<Vendor> vendors = FXCollections.observableArrayList();


        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }

        @Override
        public ObservableList<Event> getEventList() {
            return events;
        }

        @Override
        public ObservableList<Person> getEventAttendeesList() {
            return eventAttendees;
        }

        @Override
        public ObservableList<Vendor> getEventVendorsList() {
            return eventVendors;
        }

        @Override
        public ObservableList<DisplayableListViewItem> getDisplayableItemList() {
            return displayableItems;
        }

        @Override
        public ObservableList<Venue> getVenueList() {
            return venues;
        }

        @Override
        public ObservableList<Rsvp> getRsvpList() {
            return rsvps;
        }

        @Override
        public ObservableList<Vendor> getVendorList() {
            return vendors;
        }
    }

}

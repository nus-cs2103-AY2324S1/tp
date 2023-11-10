package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.event.Event;
import seedu.address.model.note.Note;
import seedu.address.model.note.NoteContent;
import seedu.address.model.note.NoteTitle;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.TypicalPersons;

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
        List<Person> newPersons = Arrays.asList(ALICE, ALICE);
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
    public void hasPerson_personWithDifferentIdentityFieldsInAddressBook_returnsFalse() {
        addressBook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertFalse(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getPersonList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{persons=" + addressBook.getPersonList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    @Test
    public void eventListToStringTest() {
        Event event1 = new Event("n", "2023-11-10 00:00", "2023-11-10 00:00", "loc1",
                "info1");
        Event event2 = new Event("n2", "2023-11-10 00:10", "2023-11-10 00:20", "loc2",
                "info2");
        Event event3 = new Event("n3", "2023-11-10 00:25", "2023-11-10 00:45", "loc3",
                "info3");
        Event event4 = new Event("n4", "2023-11-10 00:50", "2023-11-10 00:55", "loc4",
                "info4");
        Event event5 = new Event("n5", "2023-11-10 01:00", "2023-11-10 01:05", "loc5",
                "info5");
        Person owner = TypicalPersons.copyTypicalPerson(ALICE);
        addressBook.addPerson(owner);
        addressBook.addEvent(event1, owner);
        addressBook.addEvent(event2, owner);
        addressBook.addEvent(event3, owner);
        addressBook.addEvent(event4, owner);
        addressBook.addEvent(event5, owner);
        assertEquals(addressBook.eventListToString(),
                "[Alice Pauline] n\nStarts at: 2023-11-10 00:00:00\nLocation: loc1\nInformation: info1\n"
                        + "[Alice Pauline] n2\nStarts at: 2023-11-10 00:10:00\nEnds at: 2023-11-10 00:20:00\n"
                        + "Location: loc2\nInformation: info2\n[Alice Pauline] n3\nStarts at: 2023-11-10 00:25:00\n"
                        + "Ends at: 2023-11-10 00:45:00\nLocation: loc3\nInformation: info3\n[Alice Pauline] n4\n"
                        + "Starts at: 2023-11-10 00:50:00\nEnds at: 2023-11-10 00:55:00\nLocation: loc4\n"
                        + "Information: info4\n[Alice Pauline] n5\n"
                        + "Starts at: 2023-11-10 01:00:00\nEnds at: 2023-11-10 01:05:00\nLocation: loc5\n"
                        + "Information: info5\n");
    }

    @Test
    public void noteListToStringTest() {
        Note note1 = new Note(NoteTitle.fromString("title1"), NoteContent.fromString("content1"));
        Note note2 = new Note(NoteTitle.fromString("title2"), NoteContent.fromString("content2"));
        Note note3 = new Note(NoteTitle.fromString("title3"), NoteContent.fromString("content3"));
        Note note4 = new Note(NoteTitle.fromString("title4"), NoteContent.fromString("content4"));
        Note note5 = new Note(NoteTitle.fromString("title5"), NoteContent.fromString("content5"));
        Person owner = TypicalPersons.copyTypicalPerson(ALICE);
        addressBook.addPerson(owner);
        owner.addNote(note1);
        owner.addNote(note2);
        owner.addNote(note3);
        owner.addNote(note4);
        owner.addNote(note5);
        assertEquals(addressBook.noteListToString(),
                "[Alice Pauline]\n1. title1\ncontent1\n2. title2\ncontent2\n3. title3\ncontent3\n4. title4\n"
                        + "content4\n5. title5\ncontent5\n");
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();
        private final ObservableList<Event> events = FXCollections.observableArrayList();

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
        public String eventListToString() {
            return "";
        }

        @Override
        public String noteListToString() {
            return "";
        }
    }

}

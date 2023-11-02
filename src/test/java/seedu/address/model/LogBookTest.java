package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalLogBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class LogBookTest {

    private final LogBook logbook = new LogBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), logbook.getPersonList());
    }

    @Test
    public void constructor_withValidReadOnlyAddressBook() {
        LogBook newData = getTypicalLogBook();
        LogBook newLogBook = new LogBook(newData);
        assertEquals(newData, newLogBook);
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> logbook.resetData(null));
    }

    @Test
    public void resetDataInLogBook_withValidReadOnlyAddressBook_replacesData() {
        LogBook newData = getTypicalLogBook();
        logbook.resetData(newData);
        assertEquals(newData, logbook);
    }

    @Test
    public void resetDataInLogBook_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        LogBookStub newData = new LogBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> logbook.resetData(newData));
    }

    @Test
    public void hasPersonInLogBook_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> logbook.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInLogBook_returnsFalse() {
        assertFalse(logbook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInLogBook_returnsTrue() {
        logbook.addPerson(ALICE);
        assertTrue(logbook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInLogBook_returnsTrue() {
        logbook.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(logbook.hasPerson(editedAlice));
    }

    @Test
    public void getPersonListInLogBook_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logbook.getPersonList().remove(0));
    }

    @Test
    public void setPersons_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> logbook.setPersons(null));
    }

    @Test
    public void setPersons_withValidReadOnlyAddressBook_replacesData() {
        LogBook newData = getTypicalLogBook();
        logbook.setPersons(newData.getPersonList());
        assertEquals(newData, logbook);
    }

    @Test
    public void addPersons_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> logbook.addPerson(null));
    }

    @Test
    public void addPersons_withValidReadOnlyAddressBook_replacesData() {
        LogBook newData = getTypicalLogBook();
        logbook.setPersons(newData.getPersonList());
        assertThrows(DuplicatePersonException.class, () -> logbook.addPerson(newData.getPersonList().get(0)));
    }

    @Test
    public void removePersons_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> logbook.removePerson(null));
    }

    @Test
    public void removePersons_withValidReadOnlyAddressBook_replacesData() {
        LogBook newData = getTypicalLogBook();
        logbook.setPersons(newData.getPersonList());
        logbook.removePerson(newData.getPersonList().get(0));
        assertFalse(logbook.equals(newData));
    }

    @Test
    public void hashCodeMethod() {
        LogBook newData = getTypicalLogBook();
        logbook.setPersons(newData.getPersonList());
        assertEquals(newData.hashCode(), logbook.hashCode());
    }

    @Test
    public void toStringMethod() {
        String expected = LogBook.class.getCanonicalName() + "{persons=" + logbook.getPersonList() + "}";
        assertEquals(expected, logbook.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class LogBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        LogBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}

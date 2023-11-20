package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalClients.ALICE;
import static seedu.address.testutil.TypicalLeads.BOB;
import static seedu.address.testutil.TypicalLeads.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.person.Client;
import seedu.address.model.person.Lead;
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
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
    public void resetData_withDuplicateClients_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Client editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .buildClient();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void resetData_withDuplicateLeads_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Lead editedGeorge = new PersonBuilder(GEORGE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .buildLead();
        List<Person> newPersons = Arrays.asList(GEORGE, editedGeorge);
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
    public void hasPerson_clientInAddressBook_returnsTrue() {
        addressBook.addClient(ALICE);
        assertTrue(addressBook.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_leadInAddressBook_returnsTrue() {
        addressBook.addLead(GEORGE);
        assertTrue(addressBook.hasPerson(GEORGE));
    }
    @Test
    public void hasPerson_clientWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addClient(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .buildClient();
        assertTrue(addressBook.hasPerson(editedAlice));
    }

    @Test
    public void hasLead_leadWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addLead(GEORGE);
        Person editedGeorge = new PersonBuilder(GEORGE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .buildLead();
        assertTrue(addressBook.hasPerson(editedGeorge));
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
    public void equalsMethodSameObject() {
        AddressBook addressBook = new AddressBook();
        assertTrue(addressBook.equals(addressBook));
    }

    @Test
    public void equalsMethodSamePersonsList() {
        AddressBook addressBook1 = new AddressBook();
        AddressBook addressBook2 = new AddressBook();
        for (Person person : getTypicalPersons()) {
            addressBook1.addPerson(person);
            addressBook2.addPerson(person);
        }
        assertTrue(addressBook1.equals(addressBook2));
    }

    @Test
    public void equalsMethodDifferentPersonsList() {
        AddressBook addressBook1 = new AddressBook();
        AddressBook addressBook2 = new AddressBook();
        List<Person> persons1 = Arrays.asList(ALICE);
        List<Person> persons2 = Arrays.asList(BOB);
        addressBook1.setPersons(persons1);
        addressBook1.setPersons(persons2);
        assertFalse(addressBook1.equals(addressBook2));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        AddressBookStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}

package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_LOGISTIC;
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
import seedu.address.model.person.Person;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.testutil.PersonBuilder;

public class ManageHrTest {

    private final ManageHr manageHr = new ManageHr();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), manageHr.getPersonList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> manageHr.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        ManageHr newData = getTypicalAddressBook();
        manageHr.resetData(newData);
        assertEquals(newData, manageHr);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .withDepartments(VALID_DEPARTMENT_LOGISTIC)
                .build();
        List<Person> newPersons = Arrays.asList(ALICE, editedAlice);
        ManageHrStub newData = new ManageHrStub(newPersons);

        assertThrows(DuplicatePersonException.class, () -> manageHr.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> manageHr.hasPerson(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(manageHr.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        manageHr.addPerson(ALICE);
        assertTrue(manageHr.hasPerson(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        manageHr.addPerson(ALICE);
        Person editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .withDepartments(VALID_DEPARTMENT_LOGISTIC)
                .build();
        assertTrue(manageHr.hasPerson(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> manageHr.getPersonList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = ManageHr.class.getCanonicalName() + "{persons=" + manageHr.getPersonList() + "}";
        assertEquals(expected, manageHr.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class ManageHrStub implements ReadOnlyManageHr {
        private final ObservableList<Person> persons = FXCollections.observableArrayList();

        ManageHrStub(Collection<Person> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Person> getPersonList() {
            return persons;
        }
    }

}

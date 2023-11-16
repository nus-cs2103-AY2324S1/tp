package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_2_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalDevelopers.ALICE;
import static seedu.address.testutil.TypicalDevelopers.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.client.Client;
import seedu.address.model.developer.Developer;
import seedu.address.model.person.exceptions.DuplicateDeveloperException;
import seedu.address.model.project.Project;
import seedu.address.testutil.DeveloperBuilder;

public class AddressBookTest {

    private final AddressBook addressBook = new AddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), addressBook.getDeveloperList());
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
    public void resetData_withDuplicateDevelopers_throwsDuplicateDeveloperException() {
        // Two developers with the same identity fields
        Developer editedAlice = new DeveloperBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .withProjects(VALID_PROJECT_2_AMY).build();
        List<Developer> newDevelopers = Arrays.asList(ALICE, editedAlice);
        AddressBookStub newData = new AddressBookStub(newDevelopers, Collections.emptyList(), Collections.emptyList());

        assertThrows(DuplicateDeveloperException.class, () -> addressBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> addressBook.hasDeveloper(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(addressBook.hasDeveloper(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        addressBook.addDeveloper(ALICE);
        assertTrue(addressBook.hasDeveloper(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        addressBook.addDeveloper(ALICE);
        Developer editedAlice = new DeveloperBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .withProjects(VALID_PROJECT_2_AMY).build();
        assertTrue(addressBook.hasDeveloper(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> addressBook.getDeveloperList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = AddressBook.class.getCanonicalName() + "{developers=" + addressBook.getDeveloperList()
                + ", clients=" + addressBook.getClientList()
                + ", projects=" + addressBook.getProjectList() + "}";
        assertEquals(expected, addressBook.toString());
    }

    /**
     * A stub ReadOnlyAddressBook whose developers list can violate interface constraints.
     */
    private static class AddressBookStub implements ReadOnlyAddressBook {
        private final ObservableList<Developer> developers = FXCollections.observableArrayList();
        private final ObservableList<Client> clients = FXCollections.observableArrayList();
        private final ObservableList<Project> projects = FXCollections.observableArrayList();

        AddressBookStub(Collection<Developer> developers, Collection<Client> clients, Collection<Project> projects) {
            this.developers.setAll(developers);
            this.clients.setAll(clients);
            this.projects.setAll(projects);
        }

        @Override
        public ObservableList<Developer> getDeveloperList() {
            return developers;
        }

        @Override
        public ObservableList<Client> getClientList() {
            return clients;
        }

        @Override
        public ObservableList<Project> getProjectList() {
            return projects;
        }
    }

}

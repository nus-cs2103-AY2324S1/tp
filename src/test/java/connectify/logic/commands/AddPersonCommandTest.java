package connectify.logic.commands;

import static connectify.testutil.Assert.assertThrows;
import static connectify.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static connectify.testutil.TypicalIndexes.INDEX_SECOND_COMPANY;
import static connectify.testutil.TypicalPersons.ALICE;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import connectify.commons.core.GuiSettings;
import connectify.logic.Messages;
import connectify.logic.commands.exceptions.CommandException;
import connectify.model.AddressBook;
import connectify.model.Entity;
import connectify.model.Model;
import connectify.model.ReadOnlyAddressBook;
import connectify.model.ReadOnlyUserPrefs;
import connectify.model.company.Company;
import connectify.model.person.Person;
import connectify.testutil.PersonBuilder;
import connectify.testutil.TypicalCompanies;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;

public class AddPersonCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddPersonCommand(null, INDEX_FIRST_COMPANY));
    }

    @Test
    public void execute_addPersonToCompany_success() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().build();
        modelStub.addCompany(TypicalCompanies.DUMMY_COMPANY);

        CommandResult commandResult = new AddPersonCommand(validPerson, INDEX_FIRST_COMPANY).execute(modelStub);

        assertEquals(String.format(AddPersonCommand.MESSAGE_SUCCESS, Messages.format(validPerson)),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validPerson), modelStub.personsAdded);
    }

    @Test
    public void execute_invalidCompanyIndex_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddPersonCommand addPersonCommand = new AddPersonCommand(validPerson, INDEX_SECOND_COMPANY);
        ModelStub modelStub = new ModelStubAcceptingPersonAdded();
        modelStub.addCompany(TypicalCompanies.DUMMY_COMPANY);

        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX, () ->
                addPersonCommand.execute(modelStub));
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Person validPerson = new PersonBuilder().build();
        AddPersonCommand addPersonCommand = new AddPersonCommand(validPerson, INDEX_FIRST_COMPANY);
        ModelStub modelStub = new ModelStubWithPerson(validPerson);

        assertThrows(CommandException.class, AddPersonCommand.MESSAGE_DUPLICATE_PERSON, () ->
                     addPersonCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        AddPersonCommand addAliceCommand = new AddPersonCommand(alice, INDEX_FIRST_COMPANY);
        AddPersonCommand addBobCommand = new AddPersonCommand(bob, INDEX_FIRST_COMPANY);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddPersonCommand addAliceCommandCopy = new AddPersonCommand(alice, INDEX_FIRST_COMPANY);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddPersonCommand addPersonCommand = new AddPersonCommand(ALICE, INDEX_FIRST_COMPANY);
        String expected = AddPersonCommand.class.getCanonicalName() + "{toAdd=" + ALICE + "}";
        assertEquals(expected, addPersonCommand.toString());
    }

    /**
     * A default model stub that have all the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getAddressBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBookFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Adds the given company.
         * {@code company} must not already exist in the address book.
         *
         * @param company
         */
        @Override
        public void addCompany(Company company) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setAddressBook(ReadOnlyAddressBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasPerson(Person person) {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Returns true if a company with the same identity as {@code company} exists in the address book.
         *
         * @param company
         */
        @Override
        public boolean hasCompany(Company company) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteCompany(Company target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCompany(Company target, Company editedCompany) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedPersonList(Comparator<Person> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Company> getFilteredCompanyList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredCompanyList(Predicate<Company> predicate) {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public ObservableList<? extends Entity> getFilteredEntityList() {
            throw new AssertionError("This method should not be called.");
        };


        /**
         * Updates the filter of the filtered company and people list to filter by the given {@code predicate}.
         *
         * @param predicate
         * @throws NullPointerException if {@code predicate} is null.
         */
        @Override
        public void updateFilteredEntityList(Predicate<Entity> predicate) {
            throw new AssertionError("This method should not be called.");
        }


        @Override
        public Integer getNumberOfPeople() {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public Integer getNumberOfCompanies() {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public Integer getNumberOfEntities() {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public Integer getNumberOfAllEntities() {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public Boolean isEmpty() {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public String getCurrEntity() {
            throw new AssertionError("This method should not be called.");
        };

        @Override
        public void setCurrEntity(String currEntity) {
            throw new AssertionError("This method should not be called.");
        };
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Person person;

        ModelStubWithPerson(Person person) {
            requireNonNull(person);
            this.person = person;
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return this.person.isSamePerson(person);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();
        final AddressBook addressBook;
        final FilteredList<Company> filterCompanies;

        ModelStubAcceptingPersonAdded() {
            addressBook = new AddressBook();
            filterCompanies = new FilteredList<>(addressBook.getCompanyList());
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public void addCompany(Company company) {
            requireNonNull(company);
            addressBook.addCompany(company);
        }

        public void setCompany(Company target, Company editedCompany) {
            requireNonNull(editedCompany);
            addressBook.setCompany(target, editedCompany);
        }

        public void updateFilteredCompanyList(Predicate<Company> predicate) {
            requireNonNull(predicate);
            filterCompanies.setPredicate(predicate);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

        @Override
        public ObservableList<Company> getFilteredCompanyList() {
            return filterCompanies;
        }
    }

}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Client;
import seedu.address.model.person.Lead;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonMeetingTimeComparator;
import seedu.address.testutil.PersonBuilder;

public class ConvertLeadToClientCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ConvertLeadToClientCommand(null));
    }
    @Test
    public void executeLeadConvertToClientAddSuccessfully() throws CommandException {
        final String expectedOutput = "Converted Lead to Client: Amy Bee; Phone: 85355255; "
                + "Email: amy@gmail.com; Address: 123, Jurong West Ave 6, #08-111; Meeting Time: null; Tags: ";
        // Step 1: Set up the necessary test data and model stub.
        ModelStubAcceptingLeadAdded modelStub = new ModelStubAcceptingLeadAdded();
        Lead validPerson = new PersonBuilder().buildLead();
        modelStub.addLead(validPerson);
        Index index = Index.fromOneBased(1);
        ConvertLeadToClientCommand convertLeadToClientCommand = new ConvertLeadToClientCommand(index);

        // Step 2: Execute the ConvertLeadToClientCommand.
        CommandResult commandResult = convertLeadToClientCommand.execute(modelStub);

        // Step 3: Check the results.
        // You may want to assert that the model stub has been updated correctly, e.g., by checking the filteredPersons.
        // For example:
        if (!modelStub.getFilteredPersonList().isEmpty()) {
            assertTrue(modelStub.getFilteredPersonList().get(0).isClient());
        }
        // You should also check that the CommandResult is the expected one.
        // For example:
        assertEquals(expectedOutput, commandResult.getFeedbackToUser());
    }

    @Test
    public void executeLeadConvertToClientInvalidIndexAddFailure() {
        ModelStubAcceptingLeadAdded modelStub = new ModelStubAcceptingLeadAdded();
        Index invalidIndex = Index.fromOneBased(2); // An index that does not exist in the model
        ConvertLeadToClientCommand convertLeadToClientCommand = new ConvertLeadToClientCommand(invalidIndex);

        assertThrows(
                CommandException.class,
                "The person index provided is invalid", () -> convertLeadToClientCommand.execute(modelStub)
        );
    }

    @Test
    public void executeConvertClientToClientThrowException() {
        ModelStubAcceptingLeadAdded modelStub = new ModelStubAcceptingLeadAdded();
        Client validPerson = new PersonBuilder().buildClient();
        modelStub.addClient(validPerson);
        Index validIndex = Index.fromOneBased(1); // Assuming index 1 is valid, but there are no leads in the model
        ConvertLeadToClientCommand convertLeadToClientCommand = new ConvertLeadToClientCommand(validIndex);

        assertThrows(
                CommandException.class,
                "The person at the specified index is not a Lead.", () -> convertLeadToClientCommand.execute(modelStub)
        );
    }

    private abstract class ModelStub implements Model {
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

        @Override
        public void addClient(Client client) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addLead(Lead lead) {
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

        @Override
        public void deletePerson(Person target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
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
    }

    /**
     * A Model stub that contains a single person.
     */
    private abstract class ModelStubWithPerson extends ModelStub {
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
    private class ModelStubAcceptingLeadAdded extends ModelStub {

        final List<Person> leadsAdded = new ArrayList<>();
        private final ObservableList<Person> filteredPersons = FXCollections.observableArrayList(leadsAdded);
        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return leadsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addLead(Lead lead) {
            requireNonNull(lead);
            filteredPersons.add(lead);
        }

        @Override
        public void addClient(Client client) {
            requireNonNull(client);
            filteredPersons.add(client);
        }


        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return filteredPersons;
        }



        @Override
        public void updateFilteredPersonList(Predicate<Person> person) {
            requireNonNull(person);
            filteredPersons.setAll(
                    leadsAdded.stream().filter(person).collect(Collectors.toList())
            );
        }

        @Override
        public void sortFilteredPersonList() {
            Predicate<Person> meetingTimePredicate = person -> person.getMeetingTime().isPresent();

            filteredPersons.setAll(
                    leadsAdded.stream()
                            .filter(meetingTimePredicate)
                            .sorted(new PersonMeetingTimeComparator())
                            .collect(Collectors.toList())
            );
        }

        @Override
        public void setPerson(Person target, Person converted) {
            leadsAdded.remove(target);
            leadsAdded.add(converted);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

        @Override
        public void view(Person person) {
            requireNonNull(person);
        }
    }
}

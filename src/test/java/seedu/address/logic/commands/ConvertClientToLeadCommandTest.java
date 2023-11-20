package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.person.KeyMilestone.DATE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import seedu.address.testutil.PersonBuilder;

public class ConvertClientToLeadCommandTest {

    @Test
    public void constructorNullPersonThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ConvertClientToLeadCommand(null));
    }
    @Test
    public void executeClientConvertToLeadSuccess() throws CommandException {
        LocalDate date = LocalDate.from(LocalDateTime.now());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        String formattedDate = date.plusYears(1).format(formatter);

        final String expectedOutput = "Converted Client to Lead: Amy Bee; Phone: 85355255; Email: amy@gmail.com; "
                + "Address: 123, Jurong West Ave 6, #08-111; "
                + "Key Milestone: " + formattedDate + "; Meeting Time: null; Tags: ";

        // Step 1: Set up the necessary test data and model stub.
        ModelStubAcceptingClientAdded modelStub = new ModelStubAcceptingClientAdded();
        Client validPerson = new PersonBuilder().buildClient();
        modelStub.addClient(validPerson);
        Index index = Index.fromOneBased(1);
        ConvertClientToLeadCommand convertClientToLeadCommand = new ConvertClientToLeadCommand(index);

        // Step 2: Execute the ConvertClientToLeadCommand.
        CommandResult commandResult = convertClientToLeadCommand.execute(modelStub);

        // Step 3: Check the results.
        // You may want to assert that the model stub has been updated correctly, e.g., by checking the filteredPersons.
        // For example:
        if (!modelStub.getFilteredPersonList().isEmpty()) {
            assertTrue(modelStub.getFilteredPersonList().get(0).isLead());
        }
        // You should also check that the CommandResult is the expected one.
        // For example:
        assertEquals(expectedOutput, commandResult.getFeedbackToUser());
    }

    @Test
    public void executeClientConvertToLeadInvalidIndexThrowsCommandException() {
        ModelStubAcceptingClientAdded modelStub = new ModelStubAcceptingClientAdded();
        Index invalidIndex = Index.fromOneBased(2); // An index that does not exist in the model
        ConvertClientToLeadCommand convertClientToLeadCommand = new ConvertClientToLeadCommand(invalidIndex);

        assertThrows(
                CommandException.class,
                "The person index provided is invalid", ()
                        -> convertClientToLeadCommand.execute(modelStub)
        );
    }

    @Test
    public void executeConvertLeadToLeadThrowsException() {
        ModelStubAcceptingClientAdded modelStub = new ModelStubAcceptingClientAdded();
        Lead validPerson = new PersonBuilder().buildLead();
        modelStub.addLead(validPerson);
        Index validIndex = Index.fromOneBased(1); // Assuming index 1 is valid, but there are no clients in the model
        ConvertClientToLeadCommand convertClientToLeadCommand = new ConvertClientToLeadCommand(validIndex);

        assertThrows(
                CommandException.class, "The person at the specified index is not a Client.", ()
                        -> convertClientToLeadCommand.execute(modelStub));
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

        @Override
        public void sortFilteredPersonList() {
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
    private class ModelStubAcceptingClientAdded extends ModelStub {

        final List<Person> clientAdded = new ArrayList<>();
        private final ObservableList<Person> filteredPersons = FXCollections.observableArrayList(clientAdded);
        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return clientAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public void addClient(Client client) {
            requireNonNull(client);
            filteredPersons.add(client);
        }

        @Override
        public void addLead(Lead lead) {
            requireNonNull(lead);
            filteredPersons.add(lead);
        }


        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return filteredPersons;
        }



        @Override
        public void updateFilteredPersonList(Predicate<Person> person) {
            requireNonNull(person);
            filteredPersons.setAll(
                    clientAdded.stream().filter(person).collect(Collectors.toList())
            );
        }

        @Override
        public void setPerson(Person target, Person converted) {
            clientAdded.remove(target);
            clientAdded.add(converted);
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

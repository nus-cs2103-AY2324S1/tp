package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.Balance;
import seedu.address.model.person.Person;
import seedu.address.testutil.ModelStub;
import seedu.address.testutil.PersonBuilder;

class OweCommandTest {

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().withBalance(0).build();
        modelStub.addPerson(validPerson);
        CommandResult commandResult = new OweCommand(
                Index.fromZeroBased(0),
                new Balance(250)).execute(modelStub);

        assertEquals(String.format(OweCommand.MESSAGE_SUCCESS, "$2.50",
                Messages.format(validPerson)), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_personExceedsBalanceLimit_throwsCommandException() {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().withBalance(0).build();
        modelStub.addPerson(validPerson);
        assertThrows(CommandException.class, () ->
                new OweCommand(Index.fromZeroBased(0),
                        new Balance(1500000)).execute(modelStub));
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().withBalance(0).build();
        modelStub.addPerson(validPerson);
        Index invalidIndex = Index.fromZeroBased(modelStub.personsAdded.size() + 1);
        assertThrows(CommandException.class, () ->
                new OweCommand(invalidIndex, new Balance(250)).execute(modelStub));
    }

    @Test
    public void execute_newDebtWouldExceedBalanceLimit_throwsCommandException() {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Person validPerson = new PersonBuilder().withBalance(-999995).build(); // Current debt is 999995
        modelStub.addPerson(validPerson);
        assertThrows(CommandException.class, () ->
                new OweCommand(Index.fromZeroBased(0),
                        new Balance(10)).execute(modelStub)); // New debt would make it 1000005
    }

    @Test
    public void equals() {
        OweCommand oweFirstCommand = new OweCommand(Index.fromZeroBased(0), new Balance(250));
        OweCommand oweSecondCommand = new OweCommand(Index.fromZeroBased(1), new Balance(250));

        // same object -> returns true
        assertTrue(oweFirstCommand.equals(oweFirstCommand));

        // same values -> returns true
        OweCommand oweFirstCommandCopy = new OweCommand(Index.fromZeroBased(0), new Balance(250));
        assertTrue(oweFirstCommand.equals(oweFirstCommandCopy));

        // different types -> returns false
        assertFalse(oweFirstCommand.equals(1));

        // null -> returns false
        assertFalse(oweFirstCommand.equals(null));

        // different owe command -> returns false
        assertFalse(oweFirstCommand.equals(oweSecondCommand));
    }


    /**
     * A Model stub that always accepts the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireAllNonNull(person);
            return this.personsAdded.contains(person);
        }

        @Override
        public void addPerson(Person person) {
            requireAllNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return FXCollections.observableList(personsAdded);
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            List<Person> updatedList = new ArrayList<>(personsAdded);
            updatedList.set(updatedList.indexOf(target), editedPerson);
            personsAdded.set(0, editedPerson);
        }
    }
}

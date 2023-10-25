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

class PayCommandTest {

    @Test
    public void execute_personAcceptedByModel_paySuccessful() throws Exception {
        ModelStubAcceptingPersonPaid modelStub = new ModelStubAcceptingPersonPaid();
        Person validPerson = new PersonBuilder().withBalance(-250).build(); // Initial debt of 2.50
        modelStub.addPerson(validPerson);
        CommandResult commandResult = new PayCommand(
                Index.fromZeroBased(0),
                new Balance(250)).execute(modelStub);

        assertEquals(String.format(PayCommand.MESSAGE_SUCCESS, "$2.50",
                Messages.format(validPerson)), commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_personExceedsBalanceLimit_throwsCommandException() {
        ModelStubAcceptingPersonPaid modelStub = new ModelStubAcceptingPersonPaid();
        Person validPerson = new PersonBuilder().withBalance(0).build(); // No initial debt
        modelStub.addPerson(validPerson);
        assertThrows(CommandException.class, () ->
                new PayCommand(Index.fromZeroBased(0),
                        new Balance(1500000)).execute(modelStub)); // Trying to pay too much
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        ModelStubAcceptingPersonPaid modelStub = new ModelStubAcceptingPersonPaid();
        Person validPerson = new PersonBuilder().withBalance(0).build();
        modelStub.addPerson(validPerson);
        Index invalidIndex = Index.fromZeroBased(modelStub.personsPaid.size() + 1);
        assertThrows(CommandException.class, () ->
                new PayCommand(invalidIndex, new Balance(250)).execute(modelStub));
    }

    @Test
    public void equals() {
        PayCommand payFirstCommand = new PayCommand(Index.fromZeroBased(0), new Balance(250));
        PayCommand paySecondCommand = new PayCommand(Index.fromZeroBased(1), new Balance(250));

        // same object -> returns true
        assertTrue(payFirstCommand.equals(payFirstCommand));

        // same values -> returns true
        PayCommand payFirstCommandCopy = new PayCommand(Index.fromZeroBased(0), new Balance(250));
        assertTrue(payFirstCommand.equals(payFirstCommandCopy));

        // different types -> returns false
        assertFalse(payFirstCommand.equals(1));

        // null -> returns false
        assertFalse(payFirstCommand.equals(null));

        // different pay command -> returns false
        assertFalse(payFirstCommand.equals(paySecondCommand));
    }

    /**
     * A Model stub that always accepts the payment being added.
     */
    private class ModelStubAcceptingPersonPaid extends ModelStub {
        final ArrayList<Person> personsPaid = new ArrayList<>();

        @Override
        public boolean hasPerson(Person person) {
            requireAllNonNull(person);
            return this.personsPaid.contains(person);
        }

        @Override
        public void addPerson(Person person) {
            requireAllNonNull(person);
            personsPaid.add(person);
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return FXCollections.observableList(personsPaid);
        }

        @Override
        public void setPerson(Person target, Person editedPerson) {
            List<Person> updatedList = new ArrayList<>(personsPaid);
            updatedList.set(updatedList.indexOf(target), editedPerson);
            personsPaid.set(0, editedPerson);
        }
    }
}

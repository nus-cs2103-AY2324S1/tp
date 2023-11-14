package seedu.address.logic.commands.finance;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.testutil.TypicalFinances.COMMISSION_FROM_ALICE;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.contacts.AddContactCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.ModelStub;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.finance.Commission;
import seedu.address.model.person.Person;
import seedu.address.testutil.CommissionBuilder;
import seedu.address.testutil.PersonBuilder;

public class AddCommissionCommandTest {

    @Test
    public void execute_addSuccessful() throws Exception {
        ModelStubAcceptingCommissionAdded modelStub = new ModelStubAcceptingCommissionAdded();
        Person validPerson = new PersonBuilder().withName(CommissionBuilder.DEFAULT_NAME).build();
        // Add a new person into the model
        new AddContactCommand(validPerson).execute(modelStub);
        // Add a commission for that person
        Commission commission = new CommissionBuilder().build();
        CommandResult commandResult = new AddCommissionCommand(commission).execute(modelStub);
        // Assert that the correct command result is returned
        assertEquals(String.format(AddCommissionCommand.MESSAGE_SUCCESS, Messages.formatFinance(commission)),
                commandResult.getFeedbackToUser());
        // Assert that the commission has been added correctly
        assertEquals(Arrays.asList(commission), modelStub.commissionsAdded);
    }

    @Test
    public void execute_invalidClient_throwsException() {
        ModelStubAcceptingCommissionAdded modelStub = new ModelStubAcceptingCommissionAdded();
        Commission commission = new CommissionBuilder().build();
        assertThrows(CommandException.class, () -> new AddCommissionCommand(commission).execute(modelStub),
                Messages.MESSAGE_CLIENT_DOES_NOT_EXIST);
    }

    @Test
    public void equals() {
        Commission alice = new CommissionBuilder().withPerson("Alice").build();
        Commission bob = new CommissionBuilder().withPerson("Bob").build();
        AddCommissionCommand addAliceCommand = new AddCommissionCommand(alice);
        AddCommissionCommand addBobCommand = new AddCommissionCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommissionCommand addAliceCommandCopy = new AddCommissionCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different commission -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    @Test
    public void toStringMethod() {
        AddCommissionCommand addCommissionCommand = new AddCommissionCommand(COMMISSION_FROM_ALICE);
        String expected = AddCommissionCommand.class.getCanonicalName() + "{toAdd=" + COMMISSION_FROM_ALICE + "}";
        assertEquals(expected, addCommissionCommand.toString());
    }

    private class ModelStubAcceptingCommissionAdded extends ModelStub {
        final ArrayList<Commission> commissionsAdded = new ArrayList<>();
        final ArrayList<Person> personsAdded = new ArrayList<>();

        @Override
        public void addCommission(Commission commission) {
            requireNonNull(commission);
            commissionsAdded.add(commission);
        }

        @Override
        public boolean isValidClient(Person client) {
            requireAllNonNull(client);
            return personsAdded.stream().anyMatch(client::isSamePerson);
        }

        @Override
        public void addPerson(Person person) {
            requireNonNull(person);
            personsAdded.add(person);
        }

        @Override
        public boolean hasPerson(Person person) {
            requireNonNull(person);
            return personsAdded.stream().anyMatch(person::isSamePerson);
        }

        @Override
        public Person getMatchedClient(Person client) {
            for (Person p : personsAdded) {
                if (p.isSamePerson(client)) {
                    return p;
                }
            }
            return null;
        }

        @Override
        public ReadOnlyAddressBook getAddressBook() {
            return new AddressBook();
        }
    }

}

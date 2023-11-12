package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.CompleteCommand.MESSAGE_COMPLETE_SUCCESS;
import static seedu.address.logic.commands.CompleteCommand.MESSAGE_PERSON_NO_APPOINTMENT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class CompleteByIndexTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new CompleteByIndex(null));
    }

    @Test
    public void execute_indexSpecified_success() {
        Person editedPerson = new PersonBuilder(BENSON).withNullAppointment().build();
        String expectedMessage = MESSAGE_COMPLETE_SUCCESS;

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(model.getFilteredPersonList().get(1), editedPerson);
        CompleteByIndex completeCommand = new CompleteByIndex(INDEX_SECOND_PERSON);

        assertCommandSuccess(completeCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_personNoAppointment_failure() {
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        CompleteByIndex completeCommand = new CompleteByIndex(INDEX_FIRST_PERSON); //person has no appointment

        assertCommandFailure(completeCommand, expectedModel, MESSAGE_PERSON_NO_APPOINTMENT);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        CompleteByIndex completeByIndex = new CompleteByIndex(outOfBoundIndex);

        assertCommandFailure(completeByIndex, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        CompleteByIndex completeByIndex = new CompleteByIndex(outOfBoundIndex);

        assertCommandFailure(completeByIndex, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        CompleteByIndex completeCommand1 = new CompleteByIndex(INDEX_FIRST_PERSON);
        CompleteByIndex completeCommand2 = new CompleteByIndex(INDEX_SECOND_PERSON);

        //same object
        assertTrue(completeCommand1.equals(completeCommand1));

        //same index
        assertTrue(completeCommand1.equals(new CompleteByIndex(INDEX_FIRST_PERSON)));

        // null -> returns false
        assertFalse(completeCommand1.equals(null));

        // different types -> returns false
        assertFalse(completeCommand1.equals(new ClearCommand()));

        // diff index -> returns false
        assertFalse(completeCommand1.equals(completeCommand2));
    }
}

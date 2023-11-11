package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code PaidCommand}.
 */
public class PaidCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToPaid = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        PaidCommand paidCommand = new PaidCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(PaidCommand.MESSAGE_MARK_PERSON_PAID_SUCCESS,
                personToPaid.getPaid());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.markPersonPaid(personToPaid);

        assertCommandSuccess(paidCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        PaidCommand paidCommand = new PaidCommand(outOfBoundIndex);

        assertCommandFailure(paidCommand, model, Messages.MESSAGE_INVALID_TUTEE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        PaidCommand paidCommand = new PaidCommand(outOfBoundIndex);

        assertCommandFailure(paidCommand, model, Messages.MESSAGE_INVALID_TUTEE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        PaidCommand paidFirstCommand = new PaidCommand(INDEX_FIRST_PERSON);
        PaidCommand paidSecondCommand = new PaidCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(paidFirstCommand.equals(paidFirstCommand));

        // same values -> returns true
        PaidCommand paidFirstCommandCopy = new PaidCommand(INDEX_FIRST_PERSON);
        assertTrue(paidFirstCommand.equals(paidFirstCommandCopy));

        // different types -> returns false
        assertFalse(paidFirstCommand.equals(1));

        // null -> returns false
        assertFalse(paidFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(paidFirstCommand.equals(paidSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        PaidCommand paidCommand = new PaidCommand(targetIndex);
        String expected = PaidCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, paidCommand.toString());
    }
}

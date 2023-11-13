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
 * {@code IsPaidCommand}.
 */
public class IsPaidCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToCheck = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        IsPaidCommand ispaidCommand = new IsPaidCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(IsPaidCommand.MESSAGE_CHECK_PERSON_PAID,
                personToCheck.getPaid());

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.getPersonPaid(personToCheck);

        assertCommandSuccess(ispaidCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        IsPaidCommand ispaidCommand = new IsPaidCommand(outOfBoundIndex);

        assertCommandFailure(ispaidCommand, model, Messages.MESSAGE_INVALID_TUTEE_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        IsPaidCommand ispaidCommand = new IsPaidCommand(outOfBoundIndex);

        assertCommandFailure(ispaidCommand, model, Messages.MESSAGE_INVALID_TUTEE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        IsPaidCommand isPaidFirstCommand = new IsPaidCommand(INDEX_FIRST_PERSON);
        IsPaidCommand isPaidSecondCommand = new IsPaidCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(isPaidFirstCommand.equals(isPaidFirstCommand));

        // same values -> returns true
        IsPaidCommand isPaidFirstCommandCopy = new IsPaidCommand(INDEX_FIRST_PERSON);
        assertTrue(isPaidFirstCommand.equals(isPaidFirstCommandCopy));

        // different types -> returns false
        assertFalse(isPaidFirstCommand.equals(1));

        // null -> returns false
        assertFalse(isPaidFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(isPaidFirstCommand.equals(isPaidSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        IsPaidCommand ispaidCommand = new IsPaidCommand(targetIndex);
        String expected = IsPaidCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, ispaidCommand.toString());
    }
}

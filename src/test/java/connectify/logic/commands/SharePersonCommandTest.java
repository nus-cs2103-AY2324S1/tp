package connectify.logic.commands;

import static connectify.logic.commands.CommandTestUtil.assertCommandFailure;
import static connectify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static connectify.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static connectify.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static connectify.testutil.TypicalPersons.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import connectify.commons.core.index.Index;
import connectify.logic.Messages;
import connectify.model.Model;
import connectify.model.ModelManager;
import connectify.model.UserPrefs;
import connectify.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code SharePersonCommand}.
 */
public class SharePersonCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexesUnfilteredList_success() {
        Person personToShare = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        SharePersonCommand sharePersonCommand = new SharePersonCommand(INDEX_FIRST_COMPANY, INDEX_FIRST_PERSON);

        String expectedMessage = String.format(SharePersonCommand.MESSAGE_SHARE_PERSON_SUCCESS,
                SharePersonCommand.formatInput(Messages.format(personToShare)));

        assertCommandSuccess(sharePersonCommand, model, expectedMessage, model);

        // Additional checks if the person is still in the original list
        assertTrue(model.getFilteredPersonList().contains(personToShare));
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundPersonIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        SharePersonCommand sharePersonCommand = new SharePersonCommand(INDEX_FIRST_COMPANY, outOfBoundPersonIndex);

        assertCommandFailure(sharePersonCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        SharePersonCommand shareFirstCommand = new SharePersonCommand(INDEX_FIRST_COMPANY, INDEX_FIRST_PERSON);
        SharePersonCommand shareSecondCommand = new SharePersonCommand(INDEX_FIRST_COMPANY, INDEX_FIRST_PERSON);

        // same object -> returns true
        assertTrue(shareFirstCommand.equals(shareFirstCommand));

        // same values -> returns true
        SharePersonCommand shareFirstCommandCopy = new SharePersonCommand(INDEX_FIRST_COMPANY, INDEX_FIRST_PERSON);
        assertTrue(shareFirstCommand.equals(shareFirstCommandCopy));

        // different types -> returns false
        assertFalse(shareFirstCommand.equals(1));

        // null -> returns false
        assertFalse(shareFirstCommand.equals(null));
    }

    @Test
    public void toStringMethod() {
        SharePersonCommand sharePersonCommand = new SharePersonCommand(INDEX_FIRST_COMPANY, INDEX_FIRST_PERSON);
        String expected = SharePersonCommand.class.getCanonicalName() + "{companyIndex=" + INDEX_FIRST_COMPANY
                + ", personIndex=" + INDEX_FIRST_PERSON + "}";
        assertEquals(expected, sharePersonCommand.toString());
    }
}

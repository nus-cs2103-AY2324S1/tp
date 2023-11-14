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
 * {@code FileCommand}.
 */
public class FileCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToFile = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        FileCommand fileCommand = new FileCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(FileCommand.OPEN_FILE_PERSON_SUCCESS,
                Messages.format(personToFile));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(fileCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        FileCommand fileCommand = new FileCommand(outOfBoundIndex);

        assertCommandFailure(fileCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        FileCommand fileCommand = new FileCommand(outOfBoundIndex);

        assertCommandFailure(fileCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        FileCommand fileFirstCommand = new FileCommand(INDEX_FIRST_PERSON);
        FileCommand fileSecondCommand = new FileCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(fileFirstCommand.equals(fileFirstCommand));

        // same values -> returns true
        FileCommand fileFirstCommandCopy = new FileCommand(INDEX_FIRST_PERSON);
        assertTrue(fileFirstCommand.equals(fileFirstCommandCopy));

        // different types -> returns false
        assertFalse(fileFirstCommand.equals(1));

        // null -> returns false
        assertFalse(fileFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(fileFirstCommand.equals(fileSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        FileCommand fileCommand = new FileCommand(targetIndex);
        String expected = FileCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, fileCommand.toString());
    }

}

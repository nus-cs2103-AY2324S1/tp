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
 * {@code UnfavouriteCommand}.
 */
public class UnfavouriteCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToUnfavourite = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UnfavouriteCommand unfavouriteCommand = new UnfavouriteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(UnfavouriteCommand.MESSAGE_UNFAVOURITE_PERSON_SUCCESS,
                Messages.format(personToUnfavourite));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.unfavouritePerson(personToUnfavourite);

        assertCommandSuccess(unfavouriteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        UnfavouriteCommand unfavouriteCommand = new UnfavouriteCommand(outOfBoundIndex);

        assertCommandFailure(unfavouriteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToUnfavourite = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UnfavouriteCommand unfavouriteCommand = new UnfavouriteCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(UnfavouriteCommand.MESSAGE_UNFAVOURITE_PERSON_SUCCESS,
                Messages.format(personToUnfavourite));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.unfavouritePerson(personToUnfavourite);

        assertCommandSuccess(unfavouriteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        UnfavouriteCommand unfavouriteCommand = new UnfavouriteCommand(outOfBoundIndex);

        assertCommandFailure(unfavouriteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        UnfavouriteCommand unfavouriteFirstCommand = new UnfavouriteCommand(INDEX_FIRST_PERSON);
        UnfavouriteCommand unfavouriteSecondCommand = new UnfavouriteCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(unfavouriteFirstCommand.equals(unfavouriteFirstCommand));

        // same values -> returns true
        UnfavouriteCommand unfavouriteFirstCommandCopy = new UnfavouriteCommand(INDEX_FIRST_PERSON);
        assertTrue(unfavouriteFirstCommand.equals(unfavouriteFirstCommandCopy));

        // different types -> returns false
        assertFalse(unfavouriteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(unfavouriteFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(unfavouriteFirstCommand.equals(unfavouriteSecondCommand));
    }

    @Test
    public void toStringTest() {
        Index targetIndex = Index.fromOneBased(1);
        UnfavouriteCommand unfavouriteCommand = new UnfavouriteCommand(targetIndex);
        String expected = UnfavouriteCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, unfavouriteCommand.toString());
    }

}

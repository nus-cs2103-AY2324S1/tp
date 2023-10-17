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
 * {@code ViewContactCommand}.
 */
public class ViewContactCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToView = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        ViewContactCommand viewContactCommand = new ViewContactCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(Messages.MESSAGE_PERSON_VIEWED_OVERVIEW, personToView.getName());
        String expectedDisplayString = "Name: Alice Pauline\n"
                + "Phone: 94351253\n"
                + "Email: alice@example.com\n"
                + "Remark: \n";

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        assertCommandSuccess(viewContactCommand, model, expectedMessage, expectedModel);
        assertEquals(personToView.toDisplayString(), expectedDisplayString);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        ViewContactCommand viewContactCommand = new ViewContactCommand(outOfBoundIndex);

        assertCommandFailure(viewContactCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person personToView = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        ViewContactCommand viewContactCommand = new ViewContactCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(Messages.MESSAGE_PERSON_VIEWED_OVERVIEW, personToView.getName());
        String expectedDisplayString = "Name: Alice Pauline\n"
                + "Phone: 94351253\n"
                + "Email: alice@example.com\n"
                + "Remark: \n";

        // Model displaying filtered list should not change
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        showPersonAtIndex(expectedModel, INDEX_FIRST_PERSON);

        assertCommandSuccess(viewContactCommand, model, expectedMessage, expectedModel);
        assertEquals(personToView.toDisplayString(), expectedDisplayString);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        ViewContactCommand viewContactCommand = new ViewContactCommand(outOfBoundIndex);

        assertCommandFailure(viewContactCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ViewContactCommand viewContactFirstCommand = new ViewContactCommand(INDEX_FIRST_PERSON);
        ViewContactCommand viewContactSecondCommand = new ViewContactCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(viewContactFirstCommand.equals(viewContactFirstCommand));

        // same values -> returns true
        ViewContactCommand viewContactFirstCommandCopy = new ViewContactCommand(INDEX_FIRST_PERSON);
        assertTrue(viewContactFirstCommand.equals(viewContactFirstCommandCopy));

        // different types -> returns false
        assertFalse(viewContactFirstCommand.equals(1));

        // null -> returns false
        assertFalse(viewContactFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(viewContactFirstCommand.equals(viewContactSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        ViewContactCommand viewContactCommand = new ViewContactCommand(targetIndex);
        String expected = ViewContactCommand.class.getCanonicalName() + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, viewContactCommand.toString());
    }
}

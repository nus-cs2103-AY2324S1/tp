package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.logic.commands.UndoCommand.MESSAGE_UNDO_DELETE_FAILURE;
import static seedu.address.logic.commands.UndoCommand.MESSAGE_UNDO_DELETE_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;


public class UndoCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_undo_delete_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UndoCommand undoCommand = new UndoCommand();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        //CommandResult expectedCommandResult = new CommandResult(MESSAGE_UNDO_SUCCESS);
        String expectedResult = String.format(MESSAGE_UNDO_DELETE_SUCCESS, Messages.format(personToDelete));
        expectedModel.undoDelete();

        model.deletePerson(personToDelete);
        model.storePreviousUndoableCommand("delete");
        assertCommandSuccess(undoCommand, model, expectedResult, expectedModel);
    }

    //could be redundant.
    @Test
    public void execute_alreadyUndoneBefore_throwsCommandException() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Person personToDelete = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        UndoCommand undoCommand = new UndoCommand();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePerson(personToDelete);

        expectedModel.undoDelete();

        model.deletePerson(personToDelete);
        model.storePreviousUndoableCommand("delete");
        model.undoDelete();
        assertCommandFailure(undoCommand, model, MESSAGE_UNDO_DELETE_FAILURE);
    }

}

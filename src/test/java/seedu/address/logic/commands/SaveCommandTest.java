package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.EditCommand.MESSAGE_DUPLICATE_PERSON;
import static seedu.address.logic.commands.EditCommand.MESSAGE_EDIT_PERSON_SUCCESS;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class SaveCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_saveNewFosterer_success() throws CommandException {
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

        Person newFosterer = new PersonBuilder().build();
        expectedModel.addPerson(newFosterer);
        CommandResult expectedCommandResult = new CommandResult(
                String.format(SaveCommand.MESSAGE_ADD_SUCCESS, Messages.format(newFosterer)),
                null,
                null,
                CommandType.VIEW_EXIT,
                true
        );
        assertCommandSuccess(new SaveCommand(newFosterer), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_editAndSaveFosterer_success() throws CommandException {
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Person personToEdit = model.getFilteredPersonList().get(0);
        Person editedPerson = new PersonBuilder().build();

        expectedModel.setPerson(personToEdit, editedPerson);
        CommandResult expectedCommandResult = new CommandResult(
                String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPerson)),
                CommandType.SAVE
        );
        assertCommandSuccess(
                new SaveCommand(Index.fromZeroBased(0), editedPerson),
                model,
                expectedCommandResult,
                expectedModel
        );
    }

    @Test
    public void execute_addDuplicateFosterer_failure() throws CommandException {
        Person existingPerson = model.getFilteredPersonList().get(0);
        SaveCommand saveCommand = new SaveCommand(existingPerson);
        assertCommandFailure(saveCommand, model, MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_editedNameAlreadyExists_failure() throws CommandException {
        Person existingPerson = model.getFilteredPersonList().get(0);
        SaveCommand saveCommand = new SaveCommand(Index.fromZeroBased(1), existingPerson);
        assertCommandFailure(saveCommand, model, MESSAGE_DUPLICATE_PERSON);
    }

    @Test
    public void execute_noDetailsEdited_failure() throws CommandException {
        Person existingPerson = model.getFilteredPersonList().get(0);
        SaveCommand saveCommand = new SaveCommand(Index.fromZeroBased(0), existingPerson);
        assertCommandFailure(saveCommand, model, SaveCommand.MESSAGE_FOSTERER_NOT_EDITED);
    }

    @Test
    public void execute_notEveryDetailFilled_failure() throws CommandException {
        SaveCommand saveCommand = new SaveCommand(null);
        assertCommandFailure(saveCommand, model, SaveCommand.MESSAGE_DETAILS_NOT_FILLED);
    }
}

package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ViewExitCommand.MESSAGE_CONFIRM_EXIT;
import static seedu.address.logic.commands.ViewExitCommand.MESSAGE_CONFIRM_EXIT_WITHOUT_DETAILS;
import static seedu.address.logic.commands.ViewExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class ViewExitCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_viewExit_detailsNotFilledInEmptyProfile() {
        CommandResult expectedCommandResult = new CommandResult(
                MESSAGE_CONFIRM_EXIT_WITHOUT_DETAILS,
                null,
                null,
                CommandType.VIEW_EXIT,
                false
        );
        assertCommandSuccess(new ViewExitCommand(null), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_viewExit_detailsFilledInEmptyProfile() {
        Person newFosterer = new PersonBuilder().build();
        CommandResult expectedCommandResult = new CommandResult(
                MESSAGE_CONFIRM_EXIT,
                newFosterer,
                null,
                CommandType.VIEW_EXIT,
                false);
        assertCommandSuccess(new ViewExitCommand(newFosterer), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_viewExit_changesNotEdited() {
        Person p = model.getFilteredPersonList().get(0);
        CommandResult expectedCommandResult = new CommandResult(
                MESSAGE_EXIT_ACKNOWLEDGEMENT,
                p,
                null,
                CommandType.VIEW_EXIT,
                true);
        assertCommandSuccess(
                new ViewExitCommand(Index.fromZeroBased(0), p),
                model,
                expectedCommandResult,
                expectedModel
        );
    }

    @Test
    public void execute_viewExit_changesEdited() {
        Person fosterer = model.getFilteredPersonList().get(0);
        CommandResult expectedCommandResult = new CommandResult(
                MESSAGE_EXIT_ACKNOWLEDGEMENT,
                fosterer,
                null,
                CommandType.VIEW_EXIT,
                true);
        assertCommandSuccess(
                new ViewExitCommand(Index.fromZeroBased(0), fosterer),
                model,
                expectedCommandResult,
                expectedModel
        );
    }

    @Test
    public void execute_viewExit_changesEditedNotSaved() {
        Person newFosterer = new PersonBuilder().build();
        CommandResult expectedCommandResult = new CommandResult(
                MESSAGE_CONFIRM_EXIT,
                newFosterer,
                null,
                CommandType.VIEW_EXIT,
                false);
        assertCommandSuccess(
                new ViewExitCommand(Index.fromZeroBased(0), newFosterer),
                model,
                expectedCommandResult,
                expectedModel
        );
    }
}

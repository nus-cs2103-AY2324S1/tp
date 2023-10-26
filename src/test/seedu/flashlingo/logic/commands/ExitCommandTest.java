package flashlingo.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.flashlingo.logic.commands.CommandResult;
import seedu.flashlingo.logic.commands.ExitCommand;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;

import static flashlingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashlingo.logic.commands.ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT;


/**
 * Terminates the program.
 */
public class ExitCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_exit_success() {
        CommandResult expectedCommandResult = new CommandResult(MESSAGE_EXIT_ACKNOWLEDGEMENT, false
                , true, false);
        assertCommandSuccess(new ExitCommand(), model, expectedCommandResult, expectedModel);
    }
//    public static final String COMMAND_WORD = "exit";
//
//    public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Address Book as requested ...";
//
//    @Override
//    public CommandResultTest execute(Model model) {
//        return new CommandResultTest(MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true, false);
//    }
}

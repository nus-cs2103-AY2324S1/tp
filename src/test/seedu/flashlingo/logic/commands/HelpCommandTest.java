package flashlingo.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.flashlingo.logic.commands.CommandResult;
import seedu.flashlingo.logic.commands.HelpCommand;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;

import static flashlingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashlingo.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();

    @Test
    public void execute_help_success() {
        CommandResult expectedCommandResult = new CommandResult(SHOWING_HELP_MESSAGE, true, false,
                false);
        assertCommandSuccess(new HelpCommand(), model, expectedCommandResult, expectedModel);
    }

//    public static final String COMMAND_WORD = "help";
//
//    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
//            + "Example: " + COMMAND_WORD;
//
//    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";
//
//    @Override
//    public CommandResultTest execute(Model model) {
//        return new CommandResultTest(SHOWING_HELP_MESSAGE, true, false, false);
//    }
}

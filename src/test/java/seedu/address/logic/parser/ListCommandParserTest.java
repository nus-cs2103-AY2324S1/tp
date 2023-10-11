package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ListEventCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListCommandParserTest {

    private ListCommandParser parser = new ListCommandParser();


    @Test
    public void execute_correctCommand_success() throws CommandException {
        assertParseSuccessWithCommand(() -> parser.parse(" "
                + ListEventCommand.SECONDARY_COMMAND_WORD), ListEventCommand.class.getName());
    }

    @Test
    public void execute_commandNotFound_fails() throws CommandException {
        assertParseFailedWithError(() -> parser.parse(" "
                + " unknown_command 1 2 3"), MESSAGE_UNKNOWN_COMMAND);
    }


    private void assertParseSuccessWithCommand(ThrowingSupplier<Command> function, String commandClassName) {
        try {
            Command command = function.get();
            assertEquals(command.getClass().getName(), commandClassName);
        } catch (Throwable e) {
            throw new AssertionError("Execution of command should not fail.", e);
        }
    }

    private void assertParseFailedWithError(ThrowingSupplier<Command> function, String errResult) {
        try {
            function.get();
        } catch (Throwable e) {
            if (!(e instanceof ParseException)) {
                throw new AssertionError("Execution of parser failed but not due to ParseException.");
            }
            assertEquals(e.getMessage(), errResult);
            return;
        }
        throw new AssertionError("Execution of command should fail.");
    }
}

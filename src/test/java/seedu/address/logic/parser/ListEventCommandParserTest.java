package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_START_TIME_AFTER_END_TIME;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ListEventCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventTime;

public class ListEventCommandParserTest {

    private ListEventCommandParser parser = new ListEventCommandParser();


    @Test
    public void execute_correctCommandNoFilter_success() {
        assertParseSuccessWithCommand(() -> parser.parse(" "), ListEventCommand.class.getName());
    }

    @Test
    public void execute_correctCommandNoFilterDescending_success() {
        assertParseSuccessWithCommand(() -> parser.parse(" -descending"), ListEventCommand.class.getName());
    }

    @Test
        public void execute_correctCommandUseFilter_success() {
        assertParseSuccessWithCommand(() -> parser.parse(" -st 2023-11-02 -et 2023-11-03"),
                ListEventCommand.class.getName());
    }

    @Test
    public void execute_correctCommandUseFilterDescending_success() {
        assertParseSuccessWithCommand(() -> parser.parse(" -st 2023-11-02 -et 2023-11-03 -descending"),
                ListEventCommand.class.getName());
    }

    @Test
    public void execute_wrongCommandUseFilterFail_startTimeAfterEndTime() {
        assertParseFailedWithError(() -> parser.parse(" -st 2023-11-03 -et 2023-11-02"),
                String.format(MESSAGE_START_TIME_AFTER_END_TIME, "2023-11-03 00:00:00", "2023-11-02 00:00:00"));
    }

    @Test
    public void execute_wrongCommandUseFilterFail_onlyHasStartTime() {
        assertParseFailedWithError(() -> parser.parse(" -st 2023-11-03"),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListEventCommand.MESSAGE_USAGE));
    }

    @Test
    public void execute_wrongCommandUseFilterFail_onlyHasEndTime() {
        assertParseFailedWithError(() -> parser.parse(" -et 2023-11-03"),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListEventCommand.MESSAGE_USAGE));
    }

    @Test
    public void execute_wrongCommandUseFilterFail_emptyArguments() {
        assertParseFailedWithError(() -> parser.parse(" -st -et"),
                EventTime.MESSAGE_NON_EMPTY);
    }

    @Test
    public void execute_wrongCommandUseFilterDescendingFail_extraContentAfterDescendingPrefix() {
        assertParseFailedWithError(() -> parser.parse("  -descending xxx -st 2023-11-02 -et 2023-11-03"),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListEventCommand.MESSAGE_USAGE));
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

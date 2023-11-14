package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_START_TIME_AFTER_END_TIME;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.PERSON_ID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.ThrowingSupplier;

import seedu.address.logic.commands.AddEventCommand;
import seedu.address.logic.commands.AddTagCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventInformation;
import seedu.address.model.event.EventLocation;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;

public class AddEventCommandParserTest {

    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void execute_correctCommand_success() throws CommandException {
        assertParseSuccessWithCommand(() -> parser.parse(" "
                + AddEventCommand.SECONDARY_COMMAND_WORD + " -id 1 -en aa -st 00:01"), AddEventCommand.class.getName());
        assertParseSuccessWithCommand(() -> parser.parse(" " + AddTagCommand.SECONDARY_COMMAND_WORD + " "
                + PERSON_ID_DESC + TAG_DESC_HUSBAND), AddTagCommand.class.getName());
    }

    @Test
    public void execute_commandNotFound_fails() throws CommandException {
        assertParseFailedWithError(() -> parser.parse(" "
                + " unknown_command 1 2 3"), MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandFormatError_fails() throws CommandException {
        assertParseFailedWithError(() -> parser.parse(" "
                + AddEventCommand.SECONDARY_COMMAND_WORD + " -...."),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
        assertParseFailedWithError(() -> parser.parse(" "
                + AddTagCommand.SECONDARY_COMMAND_WORD + " -**"),
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTagCommand.MESSAGE_USAGE));
    }

    @Test
    public void execute_emptyStringArguments_fails() {
        assertParseFailedWithError(() -> parser.parse(" "
                + AddEventCommand.SECONDARY_COMMAND_WORD + " -id 1 -en -st 12:00"),
                EventName.MESSAGE_CONSTRAINTS);
        assertParseFailedWithError(() -> parser.parse(" "
                + AddEventCommand.SECONDARY_COMMAND_WORD + " -id 1 -en Sample -st"),
                EventTime.MESSAGE_NON_EMPTY);
        assertParseFailedWithError(() -> parser.parse(" "
                + AddEventCommand.SECONDARY_COMMAND_WORD + " -id 1 -en Sample -st 12:00 -et"),
                EventTime.MESSAGE_NON_EMPTY);
        assertParseFailedWithError(() -> parser.parse(" "
                + AddEventCommand.SECONDARY_COMMAND_WORD + " -id 1 -en Sample -st 12:00 -loc"),
                EventLocation.MESSAGE_CONSTRAINTS);
        assertParseFailedWithError(() -> parser.parse(" "
                + AddEventCommand.SECONDARY_COMMAND_WORD + " -id 1 -en Sample -st 12:00 -info"),
                EventInformation.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void execute_startTimeAfterEndTime_fails() {
        assertParseFailedWithError(() -> parser.parse(" "
                        + AddEventCommand.SECONDARY_COMMAND_WORD
                        + " -id 1 -en 2 -st 2023-11-02 12:00 -et 2023-11-01 23:00"),
                String.format(MESSAGE_START_TIME_AFTER_END_TIME, "2023-11-02 12:00:00", "2023-11-01 23:00:00"));
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

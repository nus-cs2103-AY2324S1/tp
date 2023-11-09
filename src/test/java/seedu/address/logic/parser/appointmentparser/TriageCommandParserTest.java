package seedu.address.logic.parser.appointmentparser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPOINTMENT_PRIORITY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.appointmentcommands.TriageCommand;
import seedu.address.model.tag.PriorityTag;

public class TriageCommandParserTest {
    private TriageCommandParser parser = new TriageCommandParser();
    @Test
    public void parse_validArgs_returnsTriageCommand() {
        Index targetIndex = Index.fromOneBased(1);
        String userInput = "1 " + PREFIX_APPOINTMENT_PRIORITY + "high";
        TriageCommand expectedCommand = new TriageCommand(targetIndex, new PriorityTag("high"));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // negative index
        assertParseFailure(parser, "-12 " + PREFIX_APPOINTMENT_PRIORITY + "high",
                MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);

        // zero index
        assertParseFailure(parser, "0 " + PREFIX_APPOINTMENT_PRIORITY + "high",
                MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);

        // missing index
        assertParseFailure(parser, PREFIX_APPOINTMENT_PRIORITY + "high",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TriageCommand.MESSAGE_USAGE));

        // missing priority
        assertParseFailure(parser, "1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, TriageCommand.MESSAGE_USAGE));

        // invalid priority
        assertParseFailure(parser, "1 " + PREFIX_APPOINTMENT_PRIORITY + "urgent",
                PriorityTag.MESSAGE_CONSTRAINTS);
    }
}

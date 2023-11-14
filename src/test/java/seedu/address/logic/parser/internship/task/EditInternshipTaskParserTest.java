package seedu.address.logic.parser.internship.task;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.internship.task.EditInternshipTaskCommand;
import seedu.address.model.fields.Outcome;
import seedu.address.model.fields.TaskOutcome;

public class EditInternshipTaskParserTest {
    private final EditInternshipTaskParser parser = new EditInternshipTaskParser();

    @Test
    public void parse_allValidFieldsPresent_success() {
        String validInput = " i/1 o/offered";
        EditInternshipTaskCommand editCmd = new EditInternshipTaskCommand(Index.fromOneBased(1),
                new TaskOutcome(Outcome.OFFERED));
        assertParseSuccess(parser, validInput, editCmd);
    }

    @Test
    public void parse_emptyFields_throwParseException() {
        String invalidInput = " i/1";
        assertParseFailure(parser, invalidInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditInternshipTaskCommand.MESSAGE_USAGE));
    }


    @Test
    public void parse_invalidInput_throwsParseException() {
        assertParseFailure(parser, " i/0 o/follow-up", "Index is not a non-zero unsigned integer.");
        assertParseFailure(parser, " i/1 o/invalid_cycle",
                "Enter a valid outcome: o/follow-up, ghosted, rejected, offered, awaiting");
    }

    @Test
    public void parse_indexNotPresent_throwsParseException() {
        String invalidInput = " o/ghosted";
        assertParseFailure(parser, invalidInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditInternshipTaskCommand.MESSAGE_USAGE));
    }
}

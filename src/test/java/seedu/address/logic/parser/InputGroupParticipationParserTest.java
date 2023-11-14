package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.InputGroupParticipationCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class InputGroupParticipationParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, InputGroupParticipationCommand.MESSAGE_USAGE);
    private InputGroupParticipationParser parser = new InputGroupParticipationParser();

    @Test
    public void parse_missingParts_failure() {
        // Test case 1: Missing group (preamble)
        String userInput1 = "t/1";
        assertParseFailure(parser, userInput1, MESSAGE_INVALID_FORMAT);

        // Test case 2: Missing week (tutorial)
        String userInput2 = "tut33";
        assertParseFailure(parser, userInput2, MESSAGE_INVALID_FORMAT);

        // Missing both group and tutorial
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidGroup_failure() {
        // Invalid group (not alphanumeric)
        assertParseFailure(parser, "t/1", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidTutorial_failure() {
        // Invalid tutorial (not a positive integer)
        assertParseFailure(parser, "Group1 t/a", MESSAGE_INVALID_FORMAT);

        // Invalid tutorial (0)
        assertParseFailure(parser, "Group1 t/0", MESSAGE_INVALID_FORMAT);

        // Invalid tutorial (negative)
        assertParseFailure(parser, "Group1 t/-5", MESSAGE_INVALID_FORMAT);

        // Invalid tutorial (greater than 12)
        assertParseFailure(parser, "Group1 t/13", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validInput_success() throws ParseException {
        // Valid input
        assertParseSuccess(parser, "Group1 t/1 pp/350",
                new InputGroupParticipationCommand(ParserUtil.parseGroup("Group1"),
                Index.fromOneBased(1), 350));

        // Valid input
        assertParseSuccess(parser, "Group1 t/12 pp/350",
                new InputGroupParticipationCommand(ParserUtil.parseGroup("Group1"),
                        Index.fromOneBased(12), 350));

        // Valid input with different indices and tutorial
        assertParseSuccess(parser, "Group2 t/5 pp/350",
                new InputGroupParticipationCommand(ParserUtil.parseGroup("Group2"),
                Index.fromOneBased(5), 350));
    }
}

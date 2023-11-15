package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOSAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UntakeCommand;
import seedu.address.model.prescription.Dosage;

public class UntakeCommandParserTest {

    private UntakeCommandParser parser = new UntakeCommandParser();

    @Test
    public void parse_validArgs_success() {
        // Valid arguments with a valid Index and dosage
        assertParseSuccess(parser, "1 " + PREFIX_DOSAGE + "2",
                new UntakeCommand(Index.fromOneBased(1), new Dosage("2")));
    }

    @Test
    public void parse_notIntegerDosage_failure() {
        // Invalid dosage (not an integer)
        assertParseFailure(parser, "1"
                + PREFIX_DOSAGE + "abc", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UntakeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_negativeIntegerDosage_failure() {
        // Invalid dosage (not an integer)
        assertParseFailure(parser, "1"
                + PREFIX_DOSAGE + "-1", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UntakeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyPreamble_failure() {
        // Empty input
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UntakeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_whitespacePreamble_failure() {
        // Whitespace input
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UntakeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingName_failure() {
        // Missing Name
        assertParseFailure(parser, " " + PREFIX_DOSAGE + "2", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UntakeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingDosage_failure() {
        // Missing dosage
        assertParseFailure(parser, " " + PREFIX_NAME + "Aspirin", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                UntakeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_extraValues_failure() {
        // Random values
        assertParseFailure(parser, "ABCDEFGH",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        UntakeCommand.MESSAGE_USAGE));

        // Random prefixes
        assertParseFailure(parser, "mn/ABCD d/2",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        UntakeCommand.MESSAGE_USAGE));
    }
}

package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.UngroupPersonCommand;

public class UngroupPersonCommandParserTest {
    private UngroupPersonCommandParser parser = new UngroupPersonCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + NAME_DESC_CS2103T,
                new UngroupPersonCommand(VALID_NAME_AMY, VALID_GROUP_CS2103T));
    }

    @Test
    public void parse_compulsoryFieldsMissing_failure() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE + VALID_NAME_AMY + VALID_GROUP_CS2103T,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UngroupPersonCommand.MESSAGE_USAGE));

        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + VALID_GROUP_CS2103T,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UngroupPersonCommand.MESSAGE_USAGE));

        assertParseFailure(parser, PREAMBLE_WHITESPACE + VALID_NAME_AMY + NAME_DESC_CS2103T,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, UngroupPersonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_duplicatePrefix_failure() {
        // duplicate remark prefix
        assertParseFailure(parser, NAME_DESC_AMY + NAME_DESC_AMY + NAME_DESC_CS2103T,
                "Multiple values specified for the following single-valued field(s): " + PREFIX_NAME);

        // duplicate group prefix
        assertParseFailure(parser, NAME_DESC_AMY + NAME_DESC_CS2103T + NAME_DESC_CS2103T,
                "Multiple values specified for the following single-valued field(s): " + PREFIX_GROUPTAG);
    }
}

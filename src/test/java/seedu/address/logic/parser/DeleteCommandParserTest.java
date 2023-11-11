package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CS;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_CS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteGroupCommand;
import seedu.address.logic.commands.DeletePersonCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        // valid command format for delete person
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY,
                new DeletePersonCommand(VALID_NAME_AMY));

        // valid command format for delete group
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_CS,
                new DeleteGroupCommand(VALID_GROUP_CS));
    }

    @Test
    public void parse_compulsoryFieldMissingPrefix_failure() {
        // empty arguments
        assertParseFailure(parser, PREAMBLE_WHITESPACE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));

        // missing name prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + VALID_NAME_BOB,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));

        // missing group prefix
        assertParseFailure(parser, PREAMBLE_WHITESPACE + VALID_GROUP_CS,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        // multiple name prefixes
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + NAME_DESC_BOB,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // multiple group prefixes
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_CS + NAME_DESC_CS2103T,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_GROUPTAG));

        // multiple name and group prefixes
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + NAME_DESC_BOB
                        + NAME_DESC_CS + NAME_DESC_CS2103T,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_GROUPTAG));

        // name and group
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + NAME_DESC_CS,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_TWO_PARAMETERS));
    }
}

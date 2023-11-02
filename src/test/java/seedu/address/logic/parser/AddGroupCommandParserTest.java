package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddGroupCommand;
import seedu.address.model.group.Group;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class AddGroupCommandParserTest {
    private AddGroupCommandParser parser = new AddGroupCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Group expectedGroup = new Group("CS2103T");

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_CS2103T, new AddGroupCommand(expectedGroup));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedGroupString = NAME_DESC_CS;

        // multiple group names
        assertParseFailure(parser, NAME_DESC_CS2103T + validExpectedGroupString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_GROUPTAG));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGroupCommand.MESSAGE_USAGE);

        // missing group prefix
        assertParseFailure(parser, VALID_GROUP_CS2103T, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, INVALID_GROUP_DESC_BLANK, Group.MESSAGE_CONSTRAINTS);

        // invalid name
        assertParseFailure(parser, INVALID_GROUP_DESC, Group.MESSAGE_CONSTRAINTS);
    }
}

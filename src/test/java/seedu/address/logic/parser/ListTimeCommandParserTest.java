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

import seedu.address.logic.commands.ListTimeGroupCommand;
import seedu.address.logic.commands.ListTimePersonCommand;
import seedu.address.model.group.Group;
import seedu.address.model.person.Name;

public class ListTimeCommandParserTest {

    @Test
    public void parse_listTimePerson_success() {
        ListTimeCommandParser parser = new ListTimeCommandParser();

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY,
                new ListTimePersonCommand(new Name(VALID_NAME_AMY)));
    }

    @Test
    public void parse_listTimeGroup_success() {
        ListTimeCommandParser parser = new ListTimeCommandParser();

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_CS2103T,
                new ListTimeGroupCommand(new Group(VALID_GROUP_CS2103T)));
    }

    @Test
    public void parse_compulsoryFieldsMissing_failure() {
        ListTimeCommandParser parser = new ListTimeCommandParser();

        assertParseFailure(parser, PREAMBLE_WHITESPACE + VALID_NAME_AMY,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListTimePersonCommand.MESSAGE_USAGE));

        assertParseFailure(parser, PREAMBLE_WHITESPACE + VALID_GROUP_CS2103T,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListTimeGroupCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_duplicateFields_failure() {
        ListTimeCommandParser parser = new ListTimeCommandParser();

        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_AMY + NAME_DESC_AMY,
                "Multiple values specified for the following single-valued field(s): " + PREFIX_NAME);

        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_CS2103T + NAME_DESC_CS2103T,
                "Multiple values specified for the following single-valued field(s): " + PREFIX_GROUPTAG);
    }
}

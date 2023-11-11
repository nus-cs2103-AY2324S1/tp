package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.GROUP_REMARK_DESC;
import static seedu.address.logic.commands.CommandTestUtil.GROUP_REMARK_OTHERS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.GROUP_REMARK_SPECIAL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.GROUP_REMARK_UNICODE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CS;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CS2103T;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_CS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_REMARK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_REMARK_OTHERS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_REMARK_SPECIAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_REMARK_UNICODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPREMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPTAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.GroupRemarkCommand;
import seedu.address.model.group.Group;
import seedu.address.model.group.GroupRemark;

public class GroupRemarkCommandParserTest {

    private GroupRemarkCommandParser parser = new GroupRemarkCommandParser();

    @Test
    public void parse_validArgs_returnsGroupRemarkCommand() {
        Group expectedGroup = new Group("CS2103T");

        // valid group and remark
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_CS2103T + GROUP_REMARK_DESC,
                new GroupRemarkCommand(expectedGroup.getGroupName(), new GroupRemark(VALID_GROUP_REMARK)));

        // valid group and alphanumeric remark
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_CS2103T + GROUP_REMARK_SPECIAL_DESC,
                new GroupRemarkCommand(expectedGroup.getGroupName(), new GroupRemark(VALID_GROUP_REMARK_SPECIAL)));

        // valid group and unicode remark
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_CS2103T + GROUP_REMARK_UNICODE_DESC,
                new GroupRemarkCommand(expectedGroup.getGroupName(), new GroupRemark(VALID_GROUP_REMARK_UNICODE)));

        // valid group and other remark
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_CS2103T + GROUP_REMARK_OTHERS_DESC,
                new GroupRemarkCommand(expectedGroup.getGroupName(), new GroupRemark(VALID_GROUP_REMARK_OTHERS)));
    }

    @Test
    public void parse_compulsoryFieldMissingPrefix_failure() {
        // missing name prefix
        assertParseFailure(parser, VALID_GROUP_CS + GROUP_REMARK_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupRemarkCommand.MESSAGE_USAGE));

        // missing group prefix
        assertParseFailure(parser, NAME_DESC_CS + VALID_GROUP_REMARK,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupRemarkCommand.MESSAGE_USAGE));

        // missing time prefix
        assertParseFailure(parser, VALID_GROUP_CS + VALID_GROUP_REMARK,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupRemarkCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_duplicatePrefix_failure() {
        // duplicate remark prefix
        assertParseFailure(parser, NAME_DESC_CS + GROUP_REMARK_DESC + GROUP_REMARK_DESC,
                "Multiple values specified for the following single-valued field(s): " + PREFIX_GROUPREMARK);

        // duplicate group prefix
        assertParseFailure(parser, NAME_DESC_CS + NAME_DESC_CS + GROUP_REMARK_DESC,
                "Multiple values specified for the following single-valued field(s): " + PREFIX_GROUPTAG);
    }
}

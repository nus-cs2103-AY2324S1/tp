package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.AddGroupMeetingTimeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.TimeInterval;
import seedu.address.model.group.Group;

import java.util.ArrayList;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.*;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_CS2103T;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUPTAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

public class AddGroupMeetingTimeCommandParserTest {
    AddGroupMeetingTimeCommandParser parser = new AddGroupMeetingTimeCommandParser();

    @Test
    public void parse_addGroupMeetingTime_success() throws ParseException {
        ArrayList<TimeInterval> list = new ArrayList<>();
        TimeInterval validTimeMon = ParserUtil.parseEachInterval(VALID_TIME_MON);
        list.add(validTimeMon);

        assertParseSuccess(parser, PREAMBLE_WHITESPACE + NAME_DESC_CS2103T + VALID_TIME_DESC_MON,
                new AddGroupMeetingTimeCommand(new Group(VALID_GROUP_CS2103T), list));
    }

    @Test
    public void parse_addMultipleGroupMeetingTime_success() throws ParseException {
        ArrayList<TimeInterval> list = new ArrayList<>();
        TimeInterval validTimeMon = ParserUtil.parseEachInterval(VALID_TIME_MON);
        TimeInterval validTimeTue = ParserUtil.parseEachInterval(VALID_TIME_TUE);
        TimeInterval validTimeWed = ParserUtil.parseEachInterval(VALID_TIME_WED);
        list.add(validTimeMon);
        list.add(validTimeTue);
        list.add(validTimeWed);

        assertParseSuccess(parser,
                PREAMBLE_WHITESPACE + NAME_DESC_CS2103T + VALID_TIME_DESC_MON
                        + VALID_TIME_DESC_TUE + VALID_TIME_DESC_WED,
                new AddGroupMeetingTimeCommand(new Group(VALID_GROUP_CS2103T), list));
    }

    @Test
    public void parse_addClashingGroupMeetingTime_failure() throws ParseException {
        ArrayList<TimeInterval> list = new ArrayList<>();
        TimeInterval validTimeMon = ParserUtil.parseEachInterval(VALID_TIME_MON);
        TimeInterval validTimeMon2 = ParserUtil.parseEachInterval(VALID_TIME_MON_2);
        list.add(validTimeMon);
        list.add(validTimeMon2);

        assertParseFailure(parser,
                PREAMBLE_WHITESPACE + NAME_DESC_CS2103T + VALID_TIME_DESC_MON + VALID_TIME_DESC_MON_2,
                "No overlap is allowed in your interval. \n" +
                        " t/mon 1200 - mon 1600 t/mon 1400 - mon 1800 is not allowed. Write it as mon 1200 - mon 1800");
    }

    @Test
    public void parse_compulsoryFieldsMissing_failure() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE + VALID_GROUP_CS2103T + VALID_TIME_DESC_MON,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGroupMeetingTimeCommand.MESSAGE_USAGE));

        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_CS2103T + VALID_TIME_MON,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddGroupMeetingTimeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_duplicateFields_failure() {
        assertParseFailure(parser, PREAMBLE_WHITESPACE + NAME_DESC_CS2103T + NAME_DESC_CS + VALID_TIME_DESC_MON,
                "Multiple values specified for the following single-valued field(s): " + PREFIX_GROUPTAG);
    }
}

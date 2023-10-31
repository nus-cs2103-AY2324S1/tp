package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import seedu.address.logic.commands.EditEventCommand;
import seedu.address.model.event.EventName;
import seedu.address.model.event.EventTime;

public class EditMeetingCommandParserTest {

    private EditMeetingCommandParser parser = new EditMeetingCommandParser();

    @Test
    public void parse_missingParts_failure() {


        // no index specified
        assertParseFailure(parser, VALID_EVENT_NAME, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditEventCommand.MESSAGE_USAGE));

        // no field specified
        assertParseFailure(parser, "1", EditEventCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditEventCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_EVENT_NAME, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditEventCommand.MESSAGE_USAGE));

        // zero index
        assertParseFailure(parser, "0" + VALID_EVENT_NAME, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditEventCommand.MESSAGE_USAGE));

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditEventCommand.MESSAGE_USAGE));

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/string", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EditEventCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid name
        assertParseFailure(parser, "1" + " " + PREFIX_EVENT_NAME + " ", EventName.MESSAGE_CONSTRAINTS);

        // invalid date
        assertParseFailure(parser, "1" + " " + PREFIX_EVENT_NAME + " " + VALID_EVENT_NAME + " " + PREFIX_DATE + " ",
                "Date should be in YYYY-mm-dd format, i.e. 2023-09-30, and it should not be blank");

        // invalid start time
        assertParseFailure(
                parser, "1" + " " + PREFIX_EVENT_NAME + " " + VALID_EVENT_NAME + " " + PREFIX_START_TIME + "1",
                EventTime.MESSAGE_CONSTRAINTS);

        // invalid end time
        assertParseFailure(parser, "1" + " " + PREFIX_EVENT_NAME + " " + VALID_EVENT_NAME + " " + PREFIX_START_TIME
                + "1400" + PREFIX_END_TIME + " ", EventTime.MESSAGE_CONSTRAINTS);

        // invalid group
        assertParseFailure(parser, "1" + " " + PREFIX_EVENT_NAME + " " + VALID_EVENT_NAME + " " + PREFIX_GROUP
                + " ", "Group name should not be blank");

        // invalid unassign group
        assertParseFailure(parser, "1" + " " + PREFIX_EVENT_NAME + " " + VALID_EVENT_NAME + " " + PREFIX_UNASSIGN_GROUPS
                + " ", "Group name should not be blank");

        // invalid unassign person
        assertParseFailure(parser, "1" + " " + PREFIX_EVENT_NAME + " " + VALID_EVENT_NAME + " " + PREFIX_UNASSIGN_PERSONS
                + " ", "Person name should not be blank");
    }

}

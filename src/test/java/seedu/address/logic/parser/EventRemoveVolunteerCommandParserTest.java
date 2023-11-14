package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.EVENTID_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LOCATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VOLUNTEERID_DESC;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.eventvolunteercommands.EventRemoveVolunteerCommand;
import seedu.address.logic.parser.eventvolunteercommandparsers.EventRemoveVolunteerCommandParser;

public class EventRemoveVolunteerCommandParserTest {
    private EventRemoveVolunteerCommandParser parser = new EventRemoveVolunteerCommandParser();
    @Test
    public void parse_allFieldsPresent_success() {
        EventRemoveVolunteerCommand expectedCommand = new EventRemoveVolunteerCommand(INDEX_FIRST, INDEX_FIRST);
        assertParseSuccess(parser, " eid/1 vid/1", expectedCommand);
    }
    @Test
    public void parse_missingArguments_parseUnsuccessful() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EventRemoveVolunteerCommand.MESSAGE_USAGE);

        // missing eid argument
        assertParseFailure(parser, EVENTID_DESC + VOLUNTEERID_DESC + INDEX_FIRST,
                expectedMessage);

        // missing vid argument
        assertParseFailure(parser, EVENTID_DESC + INDEX_FIRST + VOLUNTEERID_DESC,
                expectedMessage);

        // both eid and vid arguments missing
        assertParseFailure(parser, EVENTID_DESC + VOLUNTEERID_DESC,
                expectedMessage);
    }
    @Test
    public void parse_invalidArguments_parseUnsuccessful() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                EventRemoveVolunteerCommand.MESSAGE_USAGE);

        // invalid eid argument
        assertParseFailure(parser, EVENTID_DESC + INVALID_LOCATION_DESC + VOLUNTEERID_DESC + INDEX_FIRST,
                expectedMessage);

        // invalid vid argument
        assertParseFailure(parser, EVENTID_DESC + INDEX_FIRST + VOLUNTEERID_DESC + INVALID_LOCATION_DESC,
                expectedMessage);

        // both eid and vid arguments invalid
        assertParseFailure(parser, EVENTID_DESC + INVALID_LOCATION_DESC + VOLUNTEERID_DESC
                        + INVALID_LOCATION_DESC,
                expectedMessage);
    }
}

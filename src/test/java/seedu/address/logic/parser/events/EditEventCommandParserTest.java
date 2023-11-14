package seedu.address.logic.parser.events;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC_BIRTHDAY;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_NAME_DESC_BIRTHDAY;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LOCATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TIME_START_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_BIRTHDAY;
import static seedu.address.logic.commands.CommandTestUtil.TIME_END_DESC_BIRTHDAY;
import static seedu.address.logic.commands.CommandTestUtil.TIME_START_DESC_BIRTHDAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BIRTHDAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_BIRTHDAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EVENT_NAME_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BIRTHDAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_MEETING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_END_BIRTHDAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TIME_START_BIRTHDAY;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.events.EditEventCommand;
import seedu.address.logic.commands.events.EditEventCommand.EditEventDescriptor;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.event.EventDescription;
import seedu.address.model.event.EventName;
import seedu.address.model.event.Location;
import seedu.address.testutil.EditEventDescriptorBuilder;

public class EditEventCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditEventCommand.MESSAGE_USAGE);

    private EditEventCommandParser parser = new EditEventCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_EVENT_NAME_MEETING, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditEventCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_DESCRIPTION_MEETING, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_DESCRIPTION_MEETING, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_EVENT_NAME_DESC + createMoreThanAllowedString(),
                EventName.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_LOCATION_DESC + createMoreThanAllowedString(),
                Location.MESSAGE_CONSTRAINTS); // invalid location
        assertParseFailure(parser, "1" + INVALID_DESCRIPTION_DESC + createMoreThanAllowedString(),
                EventDescription.MESSAGE_CONSTRAINTS); // invalid description

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_EVENT_NAME_DESC + createMoreThanAllowedString()
                        + INVALID_TIME_START_DESC
                        + VALID_LOCATION_MEETING + createMoreThanAllowedString(),
                EventName.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() throws ParseException {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + EVENT_NAME_DESC_BIRTHDAY
                + TIME_START_DESC_BIRTHDAY + TIME_END_DESC_BIRTHDAY + LOCATION_DESC_BIRTHDAY
                + DESCRIPTION_DESC_BIRTHDAY;

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withEventName(VALID_EVENT_NAME_BIRTHDAY)
                .withTimeStart(VALID_TIME_START_BIRTHDAY).withTimeEnd(VALID_TIME_END_BIRTHDAY)
                .withDescription(VALID_DESCRIPTION_BIRTHDAY).withLocation(VALID_LOCATION_BIRTHDAY).build();

        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() throws ParseException {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + " " + EVENT_NAME_DESC_BIRTHDAY
                + TIME_START_DESC_BIRTHDAY + TIME_END_DESC_BIRTHDAY;

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withEventName(VALID_EVENT_NAME_BIRTHDAY)
                .withTimeStart(VALID_TIME_START_BIRTHDAY).withTimeEnd(VALID_TIME_END_BIRTHDAY)
                .build();

        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    private String createMoreThanAllowedString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 257; i++) {
            sb.append("a");
        }
        return sb.toString();
    }
}

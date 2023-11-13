package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INTEGER_OVERFLOW;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.END_DATE_DESC_EARLIER;
import static seedu.address.logic.commands.CommandTestUtil.EVENT_DESC_SLEEP;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EVENT;
import static seedu.address.logic.commands.CommandTestUtil.START_DATE_DESC_EARLIER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_DATE_EARLIER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_EARLIER;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddContactEventCommand;
import seedu.address.model.event.Event;
import seedu.address.testutil.EventBuilder;

public class AddContactEventCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddContactEventCommand.MESSAGE_USAGE);
    private static final String VALID_EVENT = EVENT_DESC_SLEEP + START_DATE_DESC_EARLIER + END_DATE_DESC_EARLIER;

    private AddContactEventCommandParser parser = new AddContactEventCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_EVENT, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_EVENT, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_EVENT, MESSAGE_INVALID_FORMAT);

        // int overflow
        assertParseFailure(parser, "2147483648" + VALID_EVENT, MESSAGE_INTEGER_OVERFLOW);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_EVENT, MESSAGE_INVALID_FORMAT); // invalid event
    }

    @Test
    public void parse_validEvent_success() {
        Index targetIndex = INDEX_SECOND_PERSON;
        String userInput = targetIndex.getOneBased() + VALID_EVENT;

        Event event = new EventBuilder()
                .withDescription(VALID_DESCRIPTION)
                .withStartEndDate(VALID_START_DATE_EARLIER, VALID_END_DATE_EARLIER)
                .build();
        AddContactEventCommand expectedCommand = new AddContactEventCommand(targetIndex, event);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

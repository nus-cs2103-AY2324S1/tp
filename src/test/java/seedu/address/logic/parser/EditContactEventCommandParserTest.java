package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_DATE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_EARLIER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_DATE_LATER;
import static seedu.address.logic.commands.EditContactEventCommand.MESSAGE_NOT_EDITED;
import static seedu.address.logic.commands.EditContactEventCommand.MESSAGE_WRONG_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_END_DATE_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EVENT_START_DATE_TIME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditContactEventCommand;
import seedu.address.model.event.Event;
import seedu.address.model.event.EventDescription;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class EditContactEventCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditContactEventCommand.MESSAGE_USAGE);

    private EditContactEventCommandParser parser = new EditContactEventCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AMY, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5 1 " + VALID_START_DATE_EARLIER, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0 1 " + VALID_START_DATE_EARLIER, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_missingParameters_failure() {
        // Only 1 index
        assertParseFailure(parser, "1 " + PREFIX_EVENT_START_DATE_TIME
                + VALID_START_DATE_EARLIER, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1 1 " + PREFIX_EVENT_START_DATE_TIME
                + INVALID_START_DATE, MESSAGE_WRONG_TIME);
    }

    @Test
    public void parse_invalidTimeEnd_failure2() {
        assertParseFailure(parser, "1 1 " + PREFIX_EVENT_START_DATE_TIME, MESSAGE_WRONG_TIME);
    }

    @Test
    public void parse_invalidTimeEnd_failure3() {
        assertParseFailure(parser, "1 1", MESSAGE_NOT_EDITED);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index personIndex = INDEX_FIRST_PERSON;
        Index eventIndex = INDEX_FIRST_EVENT;
        String userInput = personIndex.getOneBased() + " " + eventIndex.getOneBased() + " " + PREFIX_EVENT_DESCRIPTION
                + "Webinar";

        Person person = new PersonBuilder().withCalendar().build();
        Event event = person.getCalendar().getEventList().get(INDEX_FIRST_EVENT.getOneBased());
        EventDescription expectedEventDescription = event.getDescription();
        EditContactEventCommand.EditEventDescriptor descriptor = new EditContactEventCommand.EditEventDescriptor();
        descriptor.setEventDescription(expectedEventDescription);
        ArrayList<Index> expectedIndexArray = new ArrayList<>();
        expectedIndexArray.add(INDEX_FIRST_PERSON);
        expectedIndexArray.add(INDEX_FIRST_EVENT);
        EditContactEventCommand expectedCommand = new EditContactEventCommand(expectedIndexArray, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_allFieldsSpecified_success2() {
        Index personIndex = INDEX_FIRST_PERSON;
        Index eventIndex = INDEX_FIRST_EVENT;
        String userInput = personIndex.getOneBased() + " " + eventIndex.getOneBased() + " "
                + PREFIX_EVENT_START_DATE_TIME + VALID_START_DATE_EARLIER + " "
                + PREFIX_EVENT_END_DATE_TIME + VALID_START_DATE_LATER;

        Person person = new PersonBuilder().withCalendar().build();
        Event event = person.getCalendar().getEventList().get(INDEX_FIRST_EVENT.getOneBased());
        EventDescription expectedEventDescription = event.getDescription();
        EditContactEventCommand.EditEventDescriptor descriptor = new EditContactEventCommand.EditEventDescriptor();
        descriptor.setStart(VALID_START_DATE_EARLIER);
        descriptor.setEnd(VALID_START_DATE_LATER);
        ArrayList<Index> expectedIndexArray = new ArrayList<>();
        expectedIndexArray.add(INDEX_FIRST_PERSON);
        expectedIndexArray.add(INDEX_FIRST_EVENT);
        EditContactEventCommand expectedCommand = new EditContactEventCommand(expectedIndexArray, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

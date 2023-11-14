package seedu.ccacommander.logic.parser;

import static seedu.ccacommander.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.ccacommander.logic.commands.CommandTestUtil.DATE_DESC_AURORA;
import static seedu.ccacommander.logic.commands.CommandTestUtil.DATE_DESC_BOXING;
import static seedu.ccacommander.logic.commands.CommandTestUtil.INVALID_DATE_DESC;
import static seedu.ccacommander.logic.commands.CommandTestUtil.INVALID_LOCATION_DESC;
import static seedu.ccacommander.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.ccacommander.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.ccacommander.logic.commands.CommandTestUtil.LOCATION_DESC_AURORA;
import static seedu.ccacommander.logic.commands.CommandTestUtil.LOCATION_DESC_BOXING;
import static seedu.ccacommander.logic.commands.CommandTestUtil.NAME_DESC_AURORA;
import static seedu.ccacommander.logic.commands.CommandTestUtil.TAGS_DESC_AURORA;
import static seedu.ccacommander.logic.commands.CommandTestUtil.TAGS_DESC_BOXING;
import static seedu.ccacommander.logic.commands.CommandTestUtil.VALID_DATE_AURORA;
import static seedu.ccacommander.logic.commands.CommandTestUtil.VALID_LOCATION_AURORA;
import static seedu.ccacommander.logic.commands.CommandTestUtil.VALID_LOCATION_BOXING;
import static seedu.ccacommander.logic.commands.CommandTestUtil.VALID_NAME_AURORA;
import static seedu.ccacommander.logic.commands.CommandTestUtil.VALID_TAG_AURORA;
import static seedu.ccacommander.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.ccacommander.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.ccacommander.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.ccacommander.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.ccacommander.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.ccacommander.testutil.TypicalIndexes.INDEX_FIRST_EVENT;
import static seedu.ccacommander.testutil.TypicalIndexes.INDEX_SECOND_EVENT;
import static seedu.ccacommander.testutil.TypicalIndexes.INDEX_THIRD_EVENT;

import org.junit.jupiter.api.Test;

import seedu.ccacommander.commons.core.index.Index;
import seedu.ccacommander.logic.Messages;
import seedu.ccacommander.logic.commands.EditEventCommand;
import seedu.ccacommander.logic.commands.EditEventCommand.EditEventDescriptor;
import seedu.ccacommander.model.event.EventDate;
import seedu.ccacommander.model.event.Location;
import seedu.ccacommander.model.shared.Name;
import seedu.ccacommander.model.tag.Tag;
import seedu.ccacommander.testutil.EditEventDescriptorBuilder;

public class EditEventCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditEventCommand.MESSAGE_USAGE);

    private EditEventCommandParser parser = new EditEventCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_AURORA, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", EditEventCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + NAME_DESC_AURORA, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + NAME_DESC_AURORA, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name
        assertParseFailure(parser, "1" + INVALID_DATE_DESC, EventDate.MESSAGE_CONSTRAINTS); // invalid date
        assertParseFailure(parser, "1" + INVALID_LOCATION_DESC, Location.MESSAGE_CONSTRAINTS); // invalid Location
        assertParseFailure(parser, "1" + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS); // invalid tag

        // invalid location followed by valid date
        assertParseFailure(parser, "1" + INVALID_LOCATION_DESC + DATE_DESC_AURORA, Location.MESSAGE_CONSTRAINTS);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code Event} being edited,
        // parsing it together with a valid tag results in error
        assertParseFailure(parser, "1" + TAGS_DESC_AURORA + TAGS_DESC_BOXING + TAG_EMPTY, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAGS_DESC_AURORA + TAG_EMPTY + TAGS_DESC_BOXING, Tag.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAGS_DESC_AURORA + TAGS_DESC_BOXING, Tag.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_NAME_DESC + INVALID_DATE_DESC
                + VALID_LOCATION_AURORA + VALID_DATE_AURORA, Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_EVENT;
        String userInput = targetIndex.getOneBased() + DATE_DESC_AURORA
                + LOCATION_DESC_AURORA + NAME_DESC_AURORA + TAGS_DESC_AURORA;

        EditEventCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_NAME_AURORA)
                .withDate(VALID_DATE_AURORA).withLocation(VALID_LOCATION_AURORA)
                .withTags(VALID_TAG_AURORA).build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + LOCATION_DESC_BOXING + DATE_DESC_AURORA;

        EditEventCommand.EditEventDescriptor descriptor = new EditEventDescriptorBuilder()
                .withLocation(VALID_LOCATION_BOXING)
                .withDate(VALID_DATE_AURORA).build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_EVENT;
        String userInput = targetIndex.getOneBased() + NAME_DESC_AURORA;
        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withName(VALID_NAME_AURORA).build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // location
        userInput = targetIndex.getOneBased() + LOCATION_DESC_AURORA;
        descriptor = new EditEventDescriptorBuilder().withLocation(VALID_LOCATION_AURORA).build();
        expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // date
        userInput = targetIndex.getOneBased() + DATE_DESC_AURORA;
        descriptor = new EditEventDescriptorBuilder().withDate(VALID_DATE_AURORA).build();
        expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAGS_DESC_AURORA;
        descriptor = new EditEventDescriptorBuilder().withTags(VALID_TAG_AURORA).build();
        expectedCommand = new EditEventCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // CreateEventCommandParserTest#parse_repeatedNonTagValue_failure()

        // invalid followed by valid
        Index targetIndex = INDEX_FIRST_EVENT;
        String userInput = targetIndex.getOneBased() + INVALID_LOCATION_DESC + LOCATION_DESC_BOXING;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_LOCATION));

        // valid followed by invalid
        userInput = targetIndex.getOneBased() + LOCATION_DESC_BOXING + INVALID_LOCATION_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_LOCATION));

        // multiple valid fields repeated
        userInput = targetIndex.getOneBased() + LOCATION_DESC_AURORA + DATE_DESC_AURORA
                + TAGS_DESC_AURORA + LOCATION_DESC_AURORA + DATE_DESC_AURORA + DATE_DESC_AURORA + TAGS_DESC_AURORA
                + LOCATION_DESC_BOXING + DATE_DESC_BOXING + TAGS_DESC_BOXING;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_LOCATION, PREFIX_DATE));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + INVALID_LOCATION_DESC + INVALID_DATE_DESC
                + INVALID_LOCATION_DESC + INVALID_DATE_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_DATE, PREFIX_LOCATION));
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_EVENT;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditEventDescriptor descriptor = new EditEventDescriptorBuilder().withTags().build();
        EditEventCommand expectedCommand = new EditEventCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

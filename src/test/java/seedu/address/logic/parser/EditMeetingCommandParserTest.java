package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_FIELDS;
import static seedu.address.logic.Messages.MESSAGE_INVALID_INDEX_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_MISSING_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.END_DESC_MEETING1;
import static seedu.address.logic.commands.CommandTestUtil.END_DESC_MEETING2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_END_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LOCATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_START_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_MEETING1;
import static seedu.address.logic.commands.CommandTestUtil.START_DESC_MEETING1;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_WORK;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_MEETING1;
import static seedu.address.logic.commands.CommandTestUtil.TITLE_DESC_MEETING2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_MEETING1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_MEETING2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_MEETING1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_MEETING1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_WORK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TITLE_MEETING1;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_MEETING;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_MEETING;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditMeetingCommand;
import seedu.address.logic.commands.EditMeetingCommand.EditMeetingDescriptor;
import seedu.address.model.meeting.Location;
import seedu.address.model.meeting.MeetingTime;
import seedu.address.model.meeting.Title;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.EditMeetingDescriptorBuilder;

public class EditMeetingCommandParserTest {

    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private EditMeetingCommandParser parser = new EditMeetingCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, TITLE_DESC_MEETING1,
                MESSAGE_MISSING_INDEX + "\n" + EditMeetingCommand.MESSAGE_USAGE);

        // no field specified
        assertParseFailure(parser, "1",
                EditMeetingCommand.MESSAGE_NOT_EDITED + "\n" + EditMeetingCommand.MESSAGE_USAGE);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_MISSING_INDEX + "\n" + EditMeetingCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + TITLE_DESC_MEETING1,
                MESSAGE_INVALID_INDEX_FORMAT + "\n" + EditMeetingCommand.MESSAGE_USAGE);

        // zero index
        assertParseFailure(parser, "0" + TITLE_DESC_MEETING1,
                MESSAGE_INVALID_INDEX_FORMAT + "\n" + EditMeetingCommand.MESSAGE_USAGE);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "general kenobi :)",
                MESSAGE_INVALID_INDEX_FORMAT + "\n" + EditMeetingCommand.MESSAGE_USAGE);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ nonono",
                MESSAGE_INVALID_FIELDS + "\n" + EditMeetingCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid title
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC,
                Title.MESSAGE_CONSTRAINTS + "\n" + EditMeetingCommand.MESSAGE_USAGE);
        // invalid location
        assertParseFailure(parser, "1" + INVALID_LOCATION_DESC,
                Location.MESSAGE_CONSTRAINTS + "\n" + EditMeetingCommand.MESSAGE_USAGE);
        // invalid meeting time
        assertParseFailure(parser, "1" + INVALID_START_DESC + INVALID_END_DESC,
                MeetingTime.MESSAGE_CONSTRAINTS + "\n" + EditMeetingCommand.MESSAGE_USAGE);
        // invalid tag
        assertParseFailure(parser, "1" + INVALID_TAG_DESC,
                Tag.MESSAGE_CONSTRAINTS + "\n" + EditMeetingCommand.MESSAGE_USAGE);

        // invalid title followed by valid location
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC + LOCATION_DESC_MEETING1,
                Title.MESSAGE_CONSTRAINTS + "\n" + EditMeetingCommand.MESSAGE_USAGE);

        // while parsing {@code PREFIX_TAG} alone will reset the tags of the {@code
        // Meeting} being edited,
        // parsing it together with a valid tag results in an error
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_DESC_WORK + TAG_EMPTY,
                Tag.MESSAGE_CONSTRAINTS + "\n" + EditMeetingCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "1" + TAG_DESC_FRIEND + TAG_EMPTY + TAG_DESC_WORK,
                Tag.MESSAGE_CONSTRAINTS + "\n" + EditMeetingCommand.MESSAGE_USAGE);
        assertParseFailure(parser, "1" + TAG_EMPTY + TAG_DESC_FRIEND + TAG_DESC_WORK,
                Tag.MESSAGE_CONSTRAINTS + "\n" + EditMeetingCommand.MESSAGE_USAGE);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_TITLE_DESC + INVALID_LOCATION_DESC + VALID_START_MEETING1,
                Title.MESSAGE_CONSTRAINTS + "\n" + EditMeetingCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_MEETING;
        String userInput = targetIndex.getOneBased() + LOCATION_DESC_MEETING1 + END_DESC_MEETING2;

        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withLocation(VALID_LOCATION_MEETING1)
                .withEnd(VALID_END_MEETING2).build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // title
        Index targetIndex = INDEX_THIRD_MEETING;
        String userInput = targetIndex.getOneBased() + TITLE_DESC_MEETING1;
        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withTitle(VALID_TITLE_MEETING1).build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // location
        userInput = targetIndex.getOneBased() + LOCATION_DESC_MEETING1;
        descriptor = new EditMeetingDescriptorBuilder().withLocation(VALID_LOCATION_MEETING1).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // start
        userInput = targetIndex.getOneBased() + START_DESC_MEETING1;
        descriptor = new EditMeetingDescriptorBuilder().withStart(VALID_START_MEETING1).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // end
        userInput = targetIndex.getOneBased() + END_DESC_MEETING1;
        descriptor = new EditMeetingDescriptorBuilder().withEnd(VALID_END_MEETING1).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // tags
        userInput = targetIndex.getOneBased() + TAG_DESC_WORK;
        descriptor = new EditMeetingDescriptorBuilder().withTags(VALID_TAG_WORK).build();
        expectedCommand = new EditMeetingCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatredFields_failure() {
        // valid followed by invalid
        Index targetIndex = INDEX_FIRST_MEETING;
        String userInput = targetIndex.getOneBased() + INVALID_LOCATION_DESC + LOCATION_DESC_MEETING1;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_LOCATION) + "\n"
                + EditMeetingCommand.MESSAGE_USAGE);

        // invalid followed by valid
        userInput = targetIndex.getOneBased() + LOCATION_DESC_MEETING1 + INVALID_LOCATION_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_LOCATION) + "\n"
                + EditMeetingCommand.MESSAGE_USAGE);

        // multiple valid fields repeated
        userInput = targetIndex.getOneBased() + TITLE_DESC_MEETING1 + TITLE_DESC_MEETING2 + LOCATION_DESC_MEETING1
                + END_DESC_MEETING1 + END_DESC_MEETING2;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TITLE, PREFIX_END)
                + "\n" + EditMeetingCommand.MESSAGE_USAGE);

        // multiple invalid values
        userInput = targetIndex.getOneBased() + INVALID_LOCATION_DESC + INVALID_TITLE_DESC + INVALID_LOCATION_DESC
                + INVALID_TITLE_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_TITLE, PREFIX_LOCATION) + "\n"
                        + EditMeetingCommand.MESSAGE_USAGE);
    }

    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_SECOND_MEETING;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditMeetingDescriptor descriptor = new EditMeetingDescriptorBuilder().withTags().build();
        EditMeetingCommand expectedCommand = new EditMeetingCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }
}

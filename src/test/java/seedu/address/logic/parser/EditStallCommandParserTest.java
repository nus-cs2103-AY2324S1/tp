package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX;
import static seedu.address.logic.commands.CommandTestUtil.DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_LOCATION_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_RATING_DESC;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_ASIAN;
import static seedu.address.logic.commands.CommandTestUtil.LOCATION_DESC_BRITISH;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_ASIAN;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BRITISH;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.RATING_DESC;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESCRIPTION;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_ASIAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_ASIAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_RATING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STALL_INDEX_DESC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RATING;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STALL;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STALL;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_STALL;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_STALL;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditStallCommand;
import seedu.address.model.review.Description;
import seedu.address.model.review.Rating;
import seedu.address.model.stall.Location;
import seedu.address.model.stall.Name;
import seedu.address.testutil.EditStallDescriptorBuilder;

public class EditStallCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditStallCommand.MESSAGE_USAGE);

    private EditStallCommandParser parser = new EditStallCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_NAME_ASIAN, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, VALID_STALL_INDEX_DESC, EditStallCommand.MESSAGE_NOT_EDITED);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidStallIndex_failure() {
        // EP: larger than max int
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "2147483648" // boundary value
                + NAME_DESC_ASIAN, MESSAGE_INVALID_STALL_DISPLAYED_INDEX);

        // EP: negative integer
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "-1" + NAME_DESC_ASIAN, MESSAGE_INVALID_STALL_DISPLAYED_INDEX); //boundary value
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "-2147483647" // boundary value
                + NAME_DESC_ASIAN, MESSAGE_INVALID_STALL_DISPLAYED_INDEX);

        // EP: zero
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "0" + NAME_DESC_ASIAN, MESSAGE_INVALID_STALL_DISPLAYED_INDEX); // boundary value

        // EP: non-integer
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "some random string", MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "1.5", MESSAGE_INVALID_STALL_DISPLAYED_INDEX);

        // EP: empty strings
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "", MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "    ", MESSAGE_INVALID_STALL_DISPLAYED_INDEX);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "1 i/ string", MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
    }

    @Test
    public void parse_invalidValue_failure() {
        // EP: empty string
        assertParseFailure(parser, VALID_STALL_INDEX_DESC
                + INVALID_NAME_DESC, Name.MESSAGE_CONSTRAINTS); // invalid name

        // EP: empty string
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "1"
                + INVALID_LOCATION_DESC, Location.MESSAGE_CONSTRAINTS); // invalid address

        // EP: empty string
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "1"
                + INVALID_DESCRIPTION_DESC, Description.MESSAGE_CONSTRAINTS); // invalid description

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, VALID_STALL_INDEX_DESC + INVALID_NAME_DESC + INVALID_LOCATION_DESC
                + INVALID_RATING_DESC + INVALID_DESCRIPTION_DESC,
                Name.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidRating_failure() {
        // EP: non-integer
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "1"
                + INVALID_RATING_DESC, Rating.MESSAGE_CONSTRAINTS); // invalid rating
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "1"
                + " " + PREFIX_RATING + "1.5", Rating.MESSAGE_CONSTRAINTS);

        // EP: Integer larger than 5
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "1"
                + " " + PREFIX_RATING + "6", Rating.MESSAGE_CONSTRAINTS); // boundary value

        // EP: empty string
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "1"
                + " " + PREFIX_RATING + "", Rating.MESSAGE_CONSTRAINTS);
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + "1"
                + " " + PREFIX_RATING + "    ", Rating.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST_STALL;

        // name and location specified
        String userInput = PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + targetIndex.getOneBased() + NAME_DESC_ASIAN + LOCATION_DESC_ASIAN;
        EditStallCommand.EditStallDescriptor descriptor = new EditStallDescriptorBuilder()
                .withName(VALID_NAME_ASIAN)
                .withLocation(VALID_LOCATION_ASIAN).build();
        EditStallCommand expectedCommand = new EditStallCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // rating and description specified
        userInput = PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + targetIndex.getOneBased() + RATING_DESC + DESCRIPTION_DESC;
        descriptor = new EditStallDescriptorBuilder().withRating(VALID_RATING)
                .withDescription(VALID_DESCRIPTION).build();
        expectedCommand = new EditStallCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_STALL;
        String userInput = PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL
                + targetIndex.getOneBased() + NAME_DESC_ASIAN + LOCATION_DESC_ASIAN
                + RATING_DESC + DESCRIPTION_DESC;

        EditStallCommand.EditStallDescriptor descriptor = new EditStallDescriptorBuilder().withName(VALID_NAME_ASIAN)
                .withLocation(VALID_LOCATION_ASIAN).withRating(VALID_RATING).withDescription(VALID_DESCRIPTION)
                .build();
        EditStallCommand expectedCommand = new EditStallCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }


    @Test
    public void parse_oneFieldSpecified_success() {
        // name
        Index targetIndex = INDEX_THIRD_STALL;
        String userInput = PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + targetIndex.getOneBased() + NAME_DESC_ASIAN;
        EditStallCommand.EditStallDescriptor descriptor = new EditStallDescriptorBuilder()
                .withName(VALID_NAME_ASIAN).build();
        EditStallCommand expectedCommand = new EditStallCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // location
        userInput = PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + targetIndex.getOneBased() + LOCATION_DESC_ASIAN;
        descriptor = new EditStallDescriptorBuilder().withLocation(VALID_LOCATION_ASIAN).build();
        expectedCommand = new EditStallCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // rating
        userInput = PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + targetIndex.getOneBased() + RATING_DESC;
        descriptor = new EditStallDescriptorBuilder().withRating(VALID_RATING).build();
        expectedCommand = new EditStallCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // description
        userInput = PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + targetIndex.getOneBased() + DESCRIPTION_DESC;
        descriptor = new EditStallDescriptorBuilder().withDescription(VALID_DESCRIPTION).build();
        expectedCommand = new EditStallCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // More extensive testing of duplicate parameter detections is done in
        // AddCommandParserTest#parse_repeatedNonTagValue_failure()

        // invalid followed by valid
        Index targetIndex = INDEX_FIRST_STALL;
        String userInput = PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL
                + targetIndex.getOneBased() + INVALID_NAME_DESC + NAME_DESC_ASIAN;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME));

        // valid followed by invalid
        userInput = PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL
                + targetIndex.getOneBased() + LOCATION_DESC_ASIAN + INVALID_LOCATION_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_LOCATION));

        // mulltiple valid fields repeated
        userInput = PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL + targetIndex.getOneBased() + NAME_DESC_ASIAN + LOCATION_DESC_ASIAN
                + NAME_DESC_ASIAN + LOCATION_DESC_ASIAN + NAME_DESC_BRITISH + LOCATION_DESC_BRITISH;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_LOCATION));

        // multiple invalid values
        userInput = PREAMBLE_WHITESPACE + " "
                + PREFIX_STALL
                + targetIndex.getOneBased() + INVALID_NAME_DESC + INVALID_LOCATION_DESC
                + INVALID_NAME_DESC + INVALID_LOCATION_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_NAME, PREFIX_LOCATION));
    }
}

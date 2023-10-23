package seedu.staffsnap.logic.parser;

import static seedu.staffsnap.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.staffsnap.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.INVALID_RATING_DESC;
import static seedu.staffsnap.logic.commands.CommandTestUtil.INVALID_TYPE_DESC;
import static seedu.staffsnap.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.POSITION_DESC_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.TYPE_DESC_BEHAVIORAL;
import static seedu.staffsnap.logic.commands.CommandTestUtil.TYPE_DESC_TECHNICAL;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_TYPE_TECHNICAL;
import static seedu.staffsnap.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.staffsnap.logic.commands.AddInterviewCommand;
import seedu.staffsnap.model.interview.Interview;
import seedu.staffsnap.model.interview.Rating;

class AddInterviewCommandParserTest {
    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddInterviewCommand.MESSAGE_USAGE);

    private AddInterviewCommandParser parser = new AddInterviewCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_TYPE_TECHNICAL, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_multipleInterviewValue_failure() {
        String validExpectedApplicantString = NAME_DESC_BOB + PHONE_DESC_BOB + EMAIL_DESC_BOB
                + POSITION_DESC_BOB + TYPE_DESC_TECHNICAL;

        // multiple types
        assertParseFailure(parser, TYPE_DESC_BEHAVIORAL + validExpectedApplicantString,
                MESSAGE_INVALID_FORMAT);

        // multiple ratings
        assertParseFailure(parser, TYPE_DESC_BEHAVIORAL + validExpectedApplicantString,
                MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddInterviewCommand.MESSAGE_USAGE);

        // missing index
        assertParseFailure(parser, TYPE_DESC_BEHAVIORAL, expectedMessage);

        // missing interview type
        String index = "1 ";
        assertParseFailure(parser, index, expectedMessage);

        // missing interview prefix
        assertParseFailure(parser, index + VALID_TYPE_TECHNICAL, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid index
        assertParseFailure(parser, "a" + TYPE_DESC_TECHNICAL, MESSAGE_INVALID_FORMAT);

        // invalid type
        assertParseFailure(parser, "1" + INVALID_TYPE_DESC, Interview.MESSAGE_CONSTRAINTS);

        // invalid rating
        assertParseFailure(parser, "1" + TYPE_DESC_TECHNICAL + INVALID_RATING_DESC,
                Rating.MESSAGE_CONSTRAINTS);
    }
}

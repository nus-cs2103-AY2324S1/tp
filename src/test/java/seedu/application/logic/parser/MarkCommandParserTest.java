package seedu.application.logic.parser;

import static seedu.application.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.commands.CommandTestUtil.*;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.application.testutil.TypicalIndexes.INDEX_SECOND_JOB;

import org.junit.jupiter.api.Test;

import seedu.application.commons.core.index.Index;
import seedu.application.logic.commands.MarkCommand;
import seedu.application.model.job.Status;

public class MarkCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE);

    private MarkCommandParser parser = new MarkCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_STATUS_CHEF, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1", MESSAGE_INVALID_FORMAT);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + STATUS_DESC_CHEF, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + STATUS_DESC_CHEF, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        assertParseFailure(parser, "1" + INVALID_STATUS_DESC, Status.MESSAGE_CONSTRAINTS); // invalid company

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1" + INVALID_STATUS_DESC + INVALID_COMPANY_DESC,
                Status.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND_JOB;
        String userInput = targetIndex.getOneBased() + STATUS_DESC_CLEANER;

        MarkCommand expectedCommand = new MarkCommand(targetIndex, new Status(VALID_STATUS_CLEANER));

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    //    TO BE IMPLEMENTED
    //    @Test
    //    public void parse_multipleRepeatedFields_failure() {
    //        // More extensive testing of duplicate parameter detections is done in
    //        // AddCommandParserTest#parse_repeatedNonTagValue_failure()
    //
    //        // invalid
    //        Index targetIndex = INDEX_THIRD_JOB;
    //        String userInput = targetIndex.getOneBased() + INVALID_STATUS_DESC;
    //
    //        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STATUS));
    //
    //        // multiple valid fields repeated
    //        userInput = targetIndex.getOneBased() + STATUS_DESC_CHEF + STATUS_DESC_CHEF;
    //
    //        assertParseFailure(parser, userInput,
    //                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STATUS));
    //
    //        // multiple invalid values
    //        userInput = targetIndex.getOneBased() + INVALID_STATUS_DESC + INVALID_STATUS_DESC;
    //
    //        assertParseFailure(parser, userInput,
    //                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_STATUS));
    //    }

}

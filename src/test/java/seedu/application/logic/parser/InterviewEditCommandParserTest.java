package seedu.application.logic.parser;

import static seedu.application.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.application.logic.commands.CommandTestUtil.*;
import static seedu.application.logic.parser.CliSyntax.*;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.application.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.application.testutil.TypicalIndexes.*;

import org.junit.jupiter.api.Test;

import seedu.application.commons.core.index.Index;
import seedu.application.logic.Messages;
import seedu.application.logic.commands.InterviewEditCommand;
import seedu.application.model.job.interview.InterviewType;
import seedu.application.testutil.EditInterviewDescriptorBuilder;

class InterviewEditCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, InterviewEditCommand.MESSAGE_USAGE);

    private final InterviewEditCommandParser parser = new InterviewEditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no job index specified
        assertParseFailure(parser, "3 from/ " + INTERVIEW_DATETIME_DESC_CLEANER, MESSAGE_INVALID_FORMAT);

        // no interview index specified
        assertParseFailure(parser, "from/2 " + INTERVIEW_TYPE_DESC_CLEANER, MESSAGE_INVALID_FORMAT);

        // no prefix specified
        assertParseFailure(parser, "3 from2 t/Online", MESSAGE_INVALID_FORMAT);
        // no field specified
        assertParseFailure(parser, "3 from/2", InterviewEditCommand.MESSAGE_NOT_EDITED);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + INTERVIEW_ADDRESS_DESC_CLEANER, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + INTERVIEW_TYPE_DESC_CHEF, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "i know i know", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "2 to/ 5" + INTERVIEW_TYPE_DESC_CLEANER, MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid interview type
        assertParseFailure(parser, "1 from/2" + INVALID_INTERVIEW_TYPE_DESC, InterviewType.MESSAGE_CONSTRAINTS);

        // invalid interview type followed by valid interview address
        assertParseFailure(parser, "1 from/4" + INVALID_INTERVIEW_TYPE_DESC + INTERVIEW_DATETIME_DESC_CLEANER,
                InterviewType.MESSAGE_CONSTRAINTS);

        // multiple invalid values, but only the first invalid value is captured
        assertParseFailure(parser, "1 from/2" + INVALID_INTERVIEW_TYPE_DESC + INVALID_INTERVIEW_DATETIME_DESC,
                InterviewType.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        Index targetIndex = INDEX_SECOND;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_JOB_SOURCE + targetIndex.getOneBased()
                + INTERVIEW_TYPE_DESC_CHEF + INTERVIEW_ADDRESS_DESC_CHEF + INTERVIEW_DATETIME_DESC_CLEANER;

        InterviewEditCommand.EditInterviewDescriptor descriptor = new EditInterviewDescriptorBuilder()
                .withType(VALID_INTERVIEW_TYPE_CHEF).withDateTime(VALID_INTERVIEW_DATETIME_CLEANER)
                .withAddress(VALID_INTERVIEW_ADDRESS_CHEF).build();
        InterviewEditCommand expectedCommand = new InterviewEditCommand(targetIndex, targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }
    @Test
    public void parse_someFieldsSpecified_success() {
        Index targetIndex = INDEX_FIRST;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_JOB_SOURCE + targetIndex.getOneBased()
                + INTERVIEW_TYPE_DESC_CHEF + INTERVIEW_ADDRESS_DESC_CHEF;
        InterviewEditCommand.EditInterviewDescriptor descriptor = new EditInterviewDescriptorBuilder()
                .withType(VALID_INTERVIEW_TYPE_CHEF).withAddress(VALID_INTERVIEW_ADDRESS_CHEF).build();
        InterviewEditCommand expectedCommand = new InterviewEditCommand(targetIndex, targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // interview type
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_JOB_SOURCE + targetIndex.getOneBased()
                + INTERVIEW_TYPE_DESC_CHEF;
        InterviewEditCommand.EditInterviewDescriptor descriptor = new EditInterviewDescriptorBuilder()
                .withType(VALID_INTERVIEW_TYPE_CHEF).build();
        InterviewEditCommand expectedCommand = new InterviewEditCommand(targetIndex, targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // interview address
        userInput = targetIndex.getOneBased() + " " + PREFIX_JOB_SOURCE + targetIndex.getOneBased()
                + INTERVIEW_ADDRESS_DESC_CHEF;
        descriptor = new EditInterviewDescriptorBuilder().withAddress(VALID_INTERVIEW_ADDRESS_CHEF).build();
        expectedCommand = new InterviewEditCommand(targetIndex, targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // interview date time
        userInput = targetIndex.getOneBased() + " " + PREFIX_JOB_SOURCE + targetIndex.getOneBased()
                + INTERVIEW_DATETIME_DESC_CLEANER;
        descriptor = new EditInterviewDescriptorBuilder().withDateTime(VALID_INTERVIEW_DATETIME_CLEANER).build();
        expectedCommand = new InterviewEditCommand(targetIndex, targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_multipleRepeatedFields_failure() {
        // invalid followed by valid
        Index targetIndex = INDEX_THIRD;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_JOB_SOURCE + targetIndex.getOneBased()
                + INVALID_INTERVIEW_DATETIME_DESC + INTERVIEW_DATETIME_DESC_CHEF;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_INTERVIEW_DATETIME));

        // valid followed by invalid
        userInput = targetIndex.getOneBased() + " " + PREFIX_JOB_SOURCE + targetIndex.getOneBased()
                + INTERVIEW_TYPE_DESC_CLEANER + INVALID_INTERVIEW_TYPE_DESC;

        assertParseFailure(parser, userInput, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_INTERVIEW_TYPE));

        // multiple valid fields repeated
        userInput = targetIndex.getOneBased() + " " + PREFIX_JOB_SOURCE + targetIndex.getOneBased()
                + INTERVIEW_ADDRESS_DESC_CLEANER + INTERVIEW_ADDRESS_DESC_CLEANER + INTERVIEW_ADDRESS_DESC_CHEF;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_INTERVIEW_ADDRESS));

        // multiple invalid values
        userInput = targetIndex.getOneBased() + " " + PREFIX_JOB_SOURCE + targetIndex.getOneBased()
                + INVALID_INTERVIEW_DATETIME_DESC + INVALID_INTERVIEW_DATETIME_DESC;

        assertParseFailure(parser, userInput,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_INTERVIEW_DATETIME));
    }
}

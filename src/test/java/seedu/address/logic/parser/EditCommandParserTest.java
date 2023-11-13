package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_CS1101S;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HINT_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_CS1101S;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SINGULAR_TAG_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CS2100;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HINT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_CARD;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_CARD;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_CARD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditCommand;
import seedu.address.testutil.EditCardDescriptorBuilder;

public class EditCommandParserTest {

    // @@author GSgiansen-reused
    // Inspired from AB-3 EditCommandParserTest
    private static final String TAG_EMPTY = " " + PREFIX_TAG;

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE);

    private EditCommandParser parser = new EditCommandParser();

    @Test
    public void parse_missingParts_failure() {
        // no index specified
        assertParseFailure(parser, VALID_QUESTION_CS1101S, MESSAGE_INVALID_FORMAT);

        // no field specified
        assertParseFailure(parser, "1",
                EditCommand.MESSAGE_NOT_EDITED + "\n" + EditCommand.MESSAGE_USAGE);

        // no index and no field specified
        assertParseFailure(parser, "", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_invalidPreamble_failure() {
        // negative index
        assertParseFailure(parser, "-5" + VALID_QUESTION_CS2100, MESSAGE_INVALID_FORMAT);

        // zero index
        assertParseFailure(parser, "0" + VALID_QUESTION_CS2100, MESSAGE_INVALID_FORMAT);

        // invalid arguments being parsed as preamble
        assertParseFailure(parser, "1 some random string", MESSAGE_INVALID_FORMAT);

        // invalid prefix being parsed as preamble
        assertParseFailure(parser, "1 i/ string", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        // With tag
        Index targetIndex = INDEX_SECOND_CARD;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_QUESTION + VALID_QUESTION_CS2100
                + " " + PREFIX_ANSWER + VALID_ANSWER_CS1101S + " " + PREFIX_TAG + VALID_SINGULAR_TAG_CS2100
                + " " + PREFIX_HINT + VALID_HINT_CS2100;
        EditCommand.EditCardDescriptor descriptor = new EditCardDescriptorBuilder().withQuestion(VALID_QUESTION_CS2100)
                .withAnswer(VALID_ANSWER_CS1101S).withTags(VALID_TAG_CS2100).withHint(VALID_HINT_CS2100).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_someFieldsSpecified_success() {
        // With no tag
        Index targetIndex = INDEX_FIRST_CARD;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_QUESTION + VALID_QUESTION_CS2100
                + " " + PREFIX_ANSWER + VALID_ANSWER_CS1101S;
        EditCommand.EditCardDescriptor descriptor = new EditCardDescriptorBuilder().withQuestion(VALID_QUESTION_CS2100)
                .withAnswer(VALID_ANSWER_CS1101S).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_oneFieldSpecified_success() {
        // Question
        Index targetIndex = INDEX_THIRD_CARD;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_QUESTION + VALID_QUESTION_CS2100;
        EditCommand.EditCardDescriptor descriptor = new EditCardDescriptorBuilder()
                .withQuestion(VALID_QUESTION_CS2100).build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // Answer
        userInput = targetIndex.getOneBased() + " " + PREFIX_ANSWER + VALID_ANSWER_CS1101S;
        descriptor = new EditCardDescriptorBuilder().withAnswer(VALID_ANSWER_CS1101S).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);

        // Tags
        userInput = targetIndex.getOneBased() + " " + PREFIX_TAG + VALID_SINGULAR_TAG_CS2100;
        descriptor = new EditCardDescriptorBuilder().withTags(VALID_TAG_CS2100).build();
        expectedCommand = new EditCommand(targetIndex, descriptor);
        assertParseSuccess(parser, userInput, expectedCommand);
    }


    @Test
    public void parse_resetTags_success() {
        Index targetIndex = INDEX_THIRD_CARD;
        String userInput = targetIndex.getOneBased() + TAG_EMPTY;

        EditCommand.EditCardDescriptor descriptor = new EditCardDescriptorBuilder().withTags().build();
        EditCommand expectedCommand = new EditCommand(targetIndex, descriptor);

        assertParseSuccess(parser, userInput, expectedCommand);

    }
}

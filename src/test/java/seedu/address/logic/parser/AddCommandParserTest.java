package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_CS1101S;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_CS2100_R;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_CS1101S;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUESTION_CS2100_R;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SINGULAR_TAG_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CS2100;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANSWER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUESTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalCards.CS1101S;
import static seedu.address.testutil.TypicalCards.CS2100;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.AddCommand;
import seedu.address.model.card.Card;
import seedu.address.testutil.CardBuilder;

public class AddCommandParserTest {

    // @@author GSgiansen-reused
    // Inspired from AB-3 AddCommandParserTest
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Card expectedCard = new CardBuilder(CS2100).withTags(VALID_TAG_CS2100).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_QUESTION + VALID_QUESTION_CS2100_R + " "
                + PREFIX_ANSWER + VALID_ANSWER_CS2100_R + " "
                + PREFIX_TAG + VALID_SINGULAR_TAG_CS2100, new AddCommand(expectedCard));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedCardString = PREFIX_QUESTION + VALID_QUESTION_CS2100_R + " "
                + PREFIX_ANSWER + VALID_ANSWER_CS2100_R + " "
                + PREFIX_TAG + VALID_SINGULAR_TAG_CS2100;

        // multiple names
        assertParseFailure(parser, PREAMBLE_WHITESPACE + " " + PREFIX_QUESTION + VALID_QUESTION_CS2100_R + " "
                        + validExpectedCardString, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_QUESTION));

        assertParseFailure(parser, PREAMBLE_WHITESPACE + " " + PREFIX_ANSWER + VALID_ANSWER_CS2100 + " "
                + validExpectedCardString, Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ANSWER));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Card expectedCard = new CardBuilder(CS1101S).withTags().build();
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + " "
                + PREFIX_QUESTION + VALID_QUESTION_CS1101S + " "
                + PREFIX_ANSWER + VALID_ANSWER_CS1101S,
                new AddCommand(expectedCard));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing answer prefix
        assertParseFailure(parser, PREFIX_QUESTION + VALID_QUESTION_CS2100_R ,
                expectedMessage);

        // missing question prefix
        assertParseFailure(parser, PREFIX_ANSWER + VALID_ANSWER_CS2100_R ,
                expectedMessage);
    }
}

package seedu.flashlingo.logic.parser;

import static seedu.flashlingo.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.flashlingo.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.flashlingo.testutil.TypicalFlashCards.BOB;
import static seedu.flashlingo.testutil.TypicalFlashCards.TRANSLATION_DESC_BOB;
import static seedu.flashlingo.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_ORIGINAL_WORD;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.testutil.FlashCardBuilder;
import seedu.flashlingo.logic.Messages;
import seedu.flashlingo.logic.commands.AddCommand;
import seedu.flashlingo.logic.commands.Command;
import seedu.flashlingo.logic.commands.CommandTestUtil;
import seedu.flashlingo.model.flashcard.FlashCard;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        FlashCard expectedFlashCard = new FlashCardBuilder(BOB).build();

        // whitespace only preamble
        assertParseSuccess(parser, CommandTestUtil.PREAMBLE_WHITESPACE + CommandTestUtil.WORD_DESC_BOB + TRANSLATION_DESC_BOB,
                new AddCommand(expectedFlashCard));
    }

    @Test
    public void parse_repeatedNonTagValue_failure() {
        String validExpectedPersonString = CommandTestUtil.WORD_DESC_BOB + TRANSLATION_DESC_BOB;

        // multiple Word
        assertParseFailure((Parser<? extends Command>) parser, CommandTestUtil.WORD_DESC_AMY + validExpectedPersonString,
                Messages.getErrorMessageForDuplicatePrefixes(PREFIX_ORIGINAL_WORD));

    }
    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing Word prefix
        assertParseFailure((Parser<? extends Command>) parser, CommandTestUtil.VALID_ORIGINAL_WORD_BOB + CommandTestUtil.VALID_TRANSLATION_BOB,
                expectedMessage);

        // missing Translation prefix
        assertParseFailure((Parser<? extends Command>) parser, CommandTestUtil.WORD_DESC_BOB + CommandTestUtil.VALID_TRANSLATION_BOB,
                expectedMessage);
    }
}

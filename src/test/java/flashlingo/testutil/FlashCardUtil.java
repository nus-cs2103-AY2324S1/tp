package flashlingo.testutil;

import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_ORIGINAL_WORD;
import static seedu.flashlingo.logic.parser.CliSyntax.PREFIX_TRANSLATED_WORD;

import seedu.flashlingo.logic.commands.AddCommand;
import seedu.flashlingo.model.flashcard.FlashCard;

/**
 * A utility class for FlashCard.
 */
public class FlashCardUtil {

    /**
     * Returns an add command string for adding the {@code FlashCard}.
     */
    public static String getAddCommand(FlashCard flashCard) {
        return AddCommand.COMMAND_WORD + " " + getFlashCardDetails(flashCard);
    }

    /**
     * Returns the part of command string for the given {@code FlashCard}'s details.
     */
    public static String getFlashCardDetails(FlashCard flashCard) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_ORIGINAL_WORD + flashCard.getOriginalWord().getWord() + " ");
        sb.append(PREFIX_TRANSLATED_WORD + flashCard.getTranslatedWord().getWord() + " ");
        return sb.toString();
    }

}

package flashlingo.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.flashlingo.commons.util.ToStringBuilder;
import seedu.flashlingo.logic.commands.AddCommand;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.model.flashcard.ProficiencyLevel;
import seedu.flashlingo.model.flashcard.words.OriginalWord;
import seedu.flashlingo.model.flashcard.words.TranslatedWord;

import java.util.Date;

import static flashlingo.testutil.Assert.assertThrows;
import static java.util.Objects.requireNonNull;
import static seedu.flashlingo.logic.parser.CliSyntax.*;

/**
 * Adds a flashcard to Flashlingo.
 */
public class AddCommandTest {
    @Test
    public void constructor_nullFlashCard_throwsNullPointerException() {
        // Check that the constructor throws a NullPointerException when a null FlashCard is provided.
        OriginalWord original = new OriginalWord("hello", "English");
        TranslatedWord translation = new TranslatedWord("你好", "Mandarin");
        assertThrows(NullPointerException.class, () -> new AddCommand(null, translation));
        assertThrows(NullPointerException.class, () -> new AddCommand(original, null));
        assertThrows(NullPointerException.class, () -> new AddCommand(null, null));
    }
//    public static final String COMMAND_WORD = "add";
//
//    // For help function
//    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a flashcard to Flashlingo.\n"
//            + "Parameters: "
//            + PREFIX_ORIGINAL_WORD + "ORIGINAL WORD "
//            + PREFIX_ORIGINAL_WORD_LANGUAGE + "ORIGINAL WORD LANGUAGE "
//            + PREFIX_TRANSLATED_WORD + "TRANSLATION "
//            + PREFIX_TRANSLATED_WORD_LANGUAGE + "TRANSLATED WORD LANGUAGE \n"
//            + "Example: " + COMMAND_WORD + " "
//            + PREFIX_ORIGINAL_WORD + "hello "
//            + PREFIX_ORIGINAL_WORD_LANGUAGE + "English"
//            + PREFIX_TRANSLATED_WORD + "你好 "
//            + PREFIX_TRANSLATED_WORD_LANGUAGE + "Chinese";
//
//
//    public static final String MESSAGE_SUCCESS = "New flashcard added: %s - %s";
//    public static final String MESSAGE_DUPLICATE_CARD = "This flashcard already exists";
//
//    private static final ProficiencyLevel level = new ProficiencyLevel(1);
//    private final FlashCard toAdd;
//    private OriginalWord original;
//    private TranslatedWord translated;
//
//    /**
//     * Creates an AddCommandTest to add the specified {@code FlashCard}
//     */
//    public AddCommandTest(OriginalWord original, TranslatedWord translated) {
//        this.original = original;
//        this.translated = translated;
//        this.toAdd = new FlashCard(original, translated, new Date(), level);
//    }
//
//    /**
//     * Creates an AddCommandTest to add the specified {@code FlashCard}
//     */
//    public AddCommandTest(FlashCard flashCard) {
//        requireNonNull(flashCard);
//        toAdd = flashCard;
//    }
//    @Override
//    public CommandResultTest execute(Model model) throws CommandExceptionTest {
//        requireNonNull(model);
//
//        if (model.hasFlashCard(toAdd)) {
//            throw new CommandExceptionTest(MESSAGE_DUPLICATE_CARD);
//        }
//        model.addFlashCard(toAdd);
//        return new CommandResultTest(String.format(MESSAGE_SUCCESS, this.original, this.translated));
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        if (other == this) {
//            return true;
//        }
//
//        // instanceof handles nulls
//        if (!(other instanceof AddCommandTest)) {
//            return false;
//        }
//
//        AddCommandTest otherAddCommand = (AddCommandTest) other;
//        return toAdd.equals(otherAddCommand.toAdd);
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this)
//                .add("toAdd", toAdd)
//                .toString();
//    }
}

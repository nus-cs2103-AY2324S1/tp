package flashlingo.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.flashlingo.commons.util.ToStringBuilder;
import seedu.flashlingo.logic.Messages;
import seedu.flashlingo.logic.commands.FindCommand;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;
import seedu.flashlingo.model.UserPrefs;
import seedu.flashlingo.model.flashcard.WordContainsKeywordsPredicate;

import java.util.Arrays;
import java.util.Collections;

import static flashlingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static flashlingo.testutil.TypicalFlashCards.*;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.flashlingo.logic.Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW;

/**
 * Finds and lists all flashcards in flashlingo whose original words contains any of the argument keywords.
 */
public class FindCommandTest {

    private Model model = new ModelManager(getTypicalFlashlingo(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalFlashlingo(), new UserPrefs());

    @Test
    public void equals() {
        WordContainsKeywordsPredicate firstPredicate =
                new WordContainsKeywordsPredicate(Collections.singletonList("first"));
        WordContainsKeywordsPredicate secondPredicate =
                new WordContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different FlashCard -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noFlashCardFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 0);
        WordContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredFlashCardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredFlashCardList());
    }

    @Test
    public void execute_multipleKeywords_multipleFlashCardsFound() {
        String expectedMessage = String.format(MESSAGE_FLASHCARDS_LISTED_OVERVIEW, 3);
        WordContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredFlashCardList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredFlashCardList());
    }

    @Test
    public void toStringMethod() {
        WordContainsKeywordsPredicate predicate = new WordContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindCommand findCommand = new FindCommand(predicate);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code WordContainsKeywordsPredicate}.
     */
    private WordContainsKeywordsPredicate preparePredicate(String userInput) {
        return new WordContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
//    public static final String COMMAND_WORD = "find";
//
//    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all flashcards whose words contain any of "
//            + "the specified keywords and displays them as a list with index numbers.\n"
//            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
//            + "Example: " + COMMAND_WORD + " shark";
//
//    private final WordContainsKeywordsPredicate predicate;
//
//    public FindCommandTest(WordContainsKeywordsPredicate predicate) {
//        this.predicate = predicate;
//    }
//    @Override
//    public CommandResultTest execute(Model model) {
//        requireNonNull(model);
//        model.updateFilteredFlashCardList(predicate);
//        return new CommandResultTest(
//                String.format(Messages.MESSAGE_FLASHCARDS_LISTED_OVERVIEW + "\n"
//                    + model.getFilteredFlashCardList(),
//                  model.getFilteredFlashCardList().size()));
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        if (other == this) {
//            return true;
//        }
//
//        // instanceof handles nulls
//        if (!(other instanceof FindCommandTest)) {
//            return false;
//        }
//
//        FindCommandTest otherFindCommand = (FindCommandTest) other;
//        return predicate.equals(otherFindCommand.predicate);
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this)
//                .add("predicate", predicate)
//                .toString();
//    }
}

package seedu.flashlingo.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.commons.core.index.Index;
import seedu.flashlingo.logic.Messages;
import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;
import seedu.flashlingo.model.UserPrefs;
import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.model.flashcard.ProficiencyLevel;
import seedu.flashlingo.model.flashcard.words.OriginalWord;
import seedu.flashlingo.model.flashcard.words.TranslatedWord;
import seedu.flashlingo.testutil.FlashCardBuilder;
import seedu.flashlingo.testutil.TypicalFlashCards;
import seedu.flashlingo.testutil.TypicalIndexes;

/**
 * Edits the details of an existing flashcard in Flashlingo.
 */
public class EditCommandTest {
    private Model model = new ModelManager(TypicalFlashCards.getTypicalFlashlingo(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        FlashCard editedFlashcard = new FlashCardBuilder().withOriginalWord("waar", "dutch")
                .withTranslatedWord("True", "English")
                .withLevel(1)
                .build();
        OriginalWord editedOriginalWord = editedFlashcard.getOriginalWord();
        TranslatedWord editedTranslatedWord = editedFlashcard.getTranslatedWord();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_FLASHCARD,
                new String[]{
                        editedOriginalWord.getWord(), editedOriginalWord.getLanguage(),
                        editedTranslatedWord.getWord(), editedTranslatedWord.getLanguage()
                });
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FLASHCARD_SUCCESS,
                Messages.format(editedFlashcard));

        Model expectedModel = new ModelManager(TypicalFlashCards.getTypicalFlashlingo(), new UserPrefs());
        expectedModel.setFlashCard(model.getFilteredFlashCardList().get(0), editedFlashcard);
        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastFlashCard = Index.fromOneBased(model.getFilteredFlashCardList().size());
        FlashCard lastFlashCard = model.getFilteredFlashCardList().get(indexLastFlashCard.getZeroBased());

        FlashCardBuilder flashCardInList = new FlashCardBuilder(lastFlashCard);
        FlashCard editedFlashCard = flashCardInList.withOriginalWord(CommandTestUtil.VALID_ORIGINAL_WORD_BOB, "")
                .withTranslatedWord(CommandTestUtil.VALID_TRANSLATION_BOB, "")
                .withWhenToReview(new GregorianCalendar(2023, Calendar.DECEMBER, 23).getTime())
                .withLevel(1).build();
        OriginalWord editedOriginalWord = editedFlashCard.getOriginalWord();
        TranslatedWord editedTranslatedWord = editedFlashCard.getTranslatedWord();
        EditCommand editCommand = new EditCommand(indexLastFlashCard, new String[]{ editedOriginalWord.getWord(),
                editedOriginalWord.getLanguage(), editedTranslatedWord.getWord(), editedTranslatedWord.getLanguage()
        });

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FLASHCARD_SUCCESS,
                Messages.format(editedFlashCard));

        Model expectedModel = new ModelManager(new Flashlingo(model.getFlashlingo()), new UserPrefs());
        expectedModel.setFlashCard(lastFlashCard, editedFlashCard);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        CommandTestUtil.showFlashCardAtIndex(model, TypicalIndexes.INDEX_FIRST_FLASHCARD);

        FlashCard flashCardInFilteredList = model.getFilteredFlashCardList().get(
                TypicalIndexes.INDEX_FIRST_FLASHCARD.getZeroBased());
        FlashCard editedFlashCard = new FlashCardBuilder(flashCardInFilteredList)
                .withOriginalWord(CommandTestUtil.VALID_ORIGINAL_WORD_BOB, "").build();
        OriginalWord editedOriginalWord = editedFlashCard.getOriginalWord();
        TranslatedWord editedTranslatedWord = editedFlashCard.getTranslatedWord();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_FLASHCARD,
                new String[]{
                        editedOriginalWord.getWord(), editedOriginalWord.getLanguage(),
                        editedTranslatedWord.getWord(), editedTranslatedWord.getLanguage()
                });

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FLASHCARD_SUCCESS,
                Messages.format(editedFlashCard));

        Model expectedModel = new ModelManager(new Flashlingo(model.getFlashlingo()), new UserPrefs());
        expectedModel.setFlashCard(model.getFilteredFlashCardList().get(0), editedFlashCard);

        CommandTestUtil.assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateFlashCardUnfilteredList_failure() {
        FlashCard firstFlashCard = model.getFilteredFlashCardList().get(
                TypicalIndexes.INDEX_FIRST_FLASHCARD.getZeroBased());
        OriginalWord firstOriginalWord = firstFlashCard.getOriginalWord();
        TranslatedWord firstTranslatedWord = firstFlashCard.getTranslatedWord();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_SECOND_FLASHCARD, new String[]{
                firstOriginalWord.getWord(), firstOriginalWord.getLanguage(),
                firstTranslatedWord.getWord(), firstTranslatedWord.getLanguage()
        });

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_DUPLICATE_FLASHCARD);
    }

    @Test
    public void execute_duplicateFlashCardFilteredList_failure() {
        CommandTestUtil.showFlashCardAtIndex(model, TypicalIndexes.INDEX_FIRST_FLASHCARD);
        // edit FlashCard in filtered list into a duplicate in address book
        FlashCard flashCardInList = model.getFlashlingo().getFlashCardList().get(
                TypicalIndexes.INDEX_SECOND_FLASHCARD.getZeroBased());
        OriginalWord originalWord = flashCardInList.getOriginalWord();
        TranslatedWord translatedWord = flashCardInList.getTranslatedWord();
        EditCommand editCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_FLASHCARD,
                new String[]{originalWord.getWord(), originalWord.getLanguage(),
                        translatedWord.getWord(), translatedWord.getLanguage()});

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_DUPLICATE_FLASHCARD);
    }

    @Test
    public void execute_invalidFlashCardIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFlashCardList().size() + 1);
        FlashCard editedFlashCard = new FlashCardBuilder().withOriginalWord(
                        CommandTestUtil.VALID_TRANSLATED_WORD_THANKS, "")
                .build();
        OriginalWord editedOriginalWord = editedFlashCard.getOriginalWord();
        TranslatedWord editedTranslatedWord = editedFlashCard.getTranslatedWord();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, new String[]{editedOriginalWord.getWord(),
                editedOriginalWord.getLanguage(), editedTranslatedWord.getWord(), editedTranslatedWord.getLanguage()
        });

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidFlashCardIndexFilteredList_failure() {
        CommandTestUtil.showFlashCardAtIndex(model, TypicalIndexes.INDEX_FIRST_FLASHCARD);
        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_FLASHCARD;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFlashlingo().getFlashCardList().size());
        FlashCard editedFlashCard = new FlashCardBuilder().withOriginalWord(
                        CommandTestUtil.VALID_ORIGINAL_WORD_WELCOME, "")
                .build();
        OriginalWord editedOriginalWord = editedFlashCard.getOriginalWord();
        TranslatedWord editedTranslatedWord = editedFlashCard.getTranslatedWord();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, new String[]{editedOriginalWord.getWord(),
                editedOriginalWord.getLanguage(), editedTranslatedWord.getWord(), editedTranslatedWord.getLanguage()
        });

        CommandTestUtil.assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final FlashCard standardFlashCard = new FlashCard(
                new OriginalWord(CommandTestUtil.VALID_ORIGINAL_WORD_PLEASE, ""),
                new TranslatedWord(CommandTestUtil.VALID_TRANSLATED_WORD_PLEASE, ""),
                new GregorianCalendar(2023, Calendar.DECEMBER, 24).getTime(),
                new ProficiencyLevel(1));
        OriginalWord standardOriginalWord = standardFlashCard.getOriginalWord();
        TranslatedWord standardTranslatedWord = standardFlashCard.getTranslatedWord();
        final EditCommand standardCommand = new EditCommand(TypicalIndexes.INDEX_FIRST_FLASHCARD,
                new String[]{standardOriginalWord.getWord(), standardOriginalWord.getLanguage(),
                        standardTranslatedWord.getWord(), standardTranslatedWord.getLanguage()});

        // same values -> returns true
        FlashCard copyFlashCard = new FlashCardBuilder().withOriginalWord(
                        CommandTestUtil.VALID_ORIGINAL_WORD_PLEASE, "")
                .withTranslatedWord(CommandTestUtil.VALID_TRANSLATED_WORD_PLEASE, "")
                .withWhenToReview(new GregorianCalendar(2023, Calendar.DECEMBER, 24).getTime())
                .withLevel(1).build();

        OriginalWord copyOriginalWord = copyFlashCard.getOriginalWord();
        TranslatedWord copyTranslatedWord = copyFlashCard.getTranslatedWord();

        EditCommand commandWithSameValues = new EditCommand(TypicalIndexes.INDEX_FIRST_FLASHCARD,
                new String[]{copyOriginalWord.getWord(), copyOriginalWord.getLanguage(),
                        copyTranslatedWord.getWord(), copyTranslatedWord.getLanguage()});

        // assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ExitCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(TypicalIndexes.INDEX_SECOND_FLASHCARD,
                new String[]{standardOriginalWord.getWord(), standardOriginalWord.getLanguage(),
                        standardTranslatedWord.getWord(), standardTranslatedWord.getLanguage()})));

        FlashCard differentFlashCard = new FlashCardBuilder().withOriginalWord(
                        CommandTestUtil.VALID_ORIGINAL_WORD_THANKS, "")
                .withTranslatedWord(CommandTestUtil.VALID_TRANSLATED_WORD_THANKS, "")
                .withWhenToReview(new GregorianCalendar(2023, Calendar.DECEMBER, 24).getTime())
                .withLevel(1).build();
        OriginalWord differentOriginalWord = differentFlashCard.getOriginalWord();
        TranslatedWord differentTranslatedWord = differentFlashCard.getTranslatedWord();
        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(TypicalIndexes.INDEX_FIRST_FLASHCARD,
                new String[]{differentOriginalWord.getWord(), differentOriginalWord.getLanguage(),
                        differentTranslatedWord.getWord(), differentTranslatedWord.getLanguage()})));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        FlashCard editedFlashCard = new FlashCardBuilder().build();
        OriginalWord originalWord = editedFlashCard.getOriginalWord();
        TranslatedWord translatedWord = editedFlashCard.getTranslatedWord();
        String[] changes = new String[]{originalWord.getWord(),
                originalWord.getLanguage(), translatedWord.getWord(), translatedWord.getLanguage()};
        EditCommand editCommand = new EditCommand(index, changes);
        String expected = EditCommand.class.getCanonicalName() + "{index=" + index + ", changes="
                + changes.toString() + "}";
        assertEquals(expected, editCommand.toString());
    }
}

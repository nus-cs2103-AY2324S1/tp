package seedu.flashlingo.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashlingo.logic.Messages.MESSAGE_DUPLICATE_FLASHCARD;
import static seedu.flashlingo.logic.Messages.MESSAGE_SAME_WORD;
import static seedu.flashlingo.logic.commands.CommandTestUtil.VALID_ORIGINAL_WORD_BOB;
import static seedu.flashlingo.logic.commands.CommandTestUtil.VALID_ORIGINAL_WORD_LANGUAGE;
import static seedu.flashlingo.logic.commands.CommandTestUtil.VALID_TRANSLATION_BOB;
import static seedu.flashlingo.logic.commands.CommandTestUtil.VALID_TRANSLATION_LANGUAGE;
import static seedu.flashlingo.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.flashlingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashlingo.logic.commands.CommandTestUtil.showFlashCardAtIndex;
import static seedu.flashlingo.testutil.TypicalFlashCards.getTypicalFlashlingo;
import static seedu.flashlingo.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static seedu.flashlingo.testutil.TypicalIndexes.INDEX_SECOND_FLASHCARD;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.commons.core.index.Index;
import seedu.flashlingo.logic.Messages;
import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;
import seedu.flashlingo.model.UserPrefs;
import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.testutil.FlashCardBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for EditCommand.
 */
public class EditCommandTest {

    private Model model = new ModelManager(getTypicalFlashlingo(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        FlashCard editedFlashcard = new FlashCardBuilder().build();
        FlashCard lastFlashcard = model.getFilteredFlashCardList().get(0);
        System.out.println(lastFlashcard.toString());
        String[] changes = new String[] {
                editedFlashcard.getOriginalWord().getWord(),
                editedFlashcard.getOriginalWord().getLanguage(),
                editedFlashcard.getTranslatedWord().getWord(),
                editedFlashcard.getTranslatedWord().getLanguage()};
        System.out.println(Arrays.toString(changes));
        EditCommand editCommand = new EditCommand(INDEX_FIRST_FLASHCARD, changes);
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FLASHCARD_SUCCESS,
                Messages.format(editedFlashcard));

        Model expectedModel = new ModelManager(new Flashlingo(model.getFlashlingo()), new UserPrefs());
        expectedModel.setFlashCard(lastFlashcard, editedFlashcard);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_changeWordLanguageOnly_success() {
        FlashCard editedFlashcard = new FlashCardBuilder().build();
        FlashCard lastFlashcard = model.getFilteredFlashCardList().get(0);
        String[] changes = new String[] {"", editedFlashcard.getOriginalWord().getLanguage(), "", ""};
        EditCommand editCommand = new EditCommand(INDEX_FIRST_FLASHCARD, changes);
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FLASHCARD_SUCCESS,
                Messages.format(lastFlashcard.editFlashCard(changes)));

        Model expectedModel = new ModelManager(new Flashlingo(model.getFlashlingo()), new UserPrefs());
        expectedModel.setFlashCard(lastFlashcard, lastFlashcard.editFlashCard(changes));

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_changeTranslationWordOnly_success() {
        FlashCard editedFlashcard = new FlashCardBuilder().build();
        FlashCard lastFlashcard = model.getFilteredFlashCardList().get(0);
        String[] changes = new String[] {"", "", editedFlashcard.getTranslatedWord().getWord(), ""};
        EditCommand editCommand = new EditCommand(INDEX_FIRST_FLASHCARD, changes);
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FLASHCARD_SUCCESS,
                Messages.format(lastFlashcard.editFlashCard(changes)));

        Model expectedModel = new ModelManager(new Flashlingo(model.getFlashlingo()), new UserPrefs());
        expectedModel.setFlashCard(lastFlashcard, lastFlashcard.editFlashCard(changes));

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_changeTranslationToBeSameAsWord_failure() {
        FlashCard lastFlashcard = model.getFilteredFlashCardList().get(0);
        String[] changes = new String[] {"", "", lastFlashcard.getOriginalWord().getWord(), ""};
        EditCommand editCommand = new EditCommand(INDEX_FIRST_FLASHCARD, changes);

        assertCommandFailure(editCommand, model, MESSAGE_SAME_WORD);
    }

    @Test
    public void execute_changeWordToUpperCase_failure() {
        FlashCard lastFlashcard = model.getFilteredFlashCardList().get(0);
        String[] changes = new String[] {lastFlashcard.getOriginalWord().getWord().toUpperCase(), "", "", ""};
        EditCommand editCommand = new EditCommand(INDEX_FIRST_FLASHCARD, changes);

        assertCommandFailure(editCommand, model, MESSAGE_DUPLICATE_FLASHCARD);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastFlashcard = Index.fromOneBased(model.getFilteredFlashCardList().size());
        FlashCard lastFlashcard = model.getFilteredFlashCardList().get(indexLastFlashcard.getZeroBased());

        FlashCardBuilder flashcardInList = new FlashCardBuilder(lastFlashcard);
        FlashCard editedFlashcard = flashcardInList.withOriginalWord(VALID_ORIGINAL_WORD_BOB,
                        VALID_ORIGINAL_WORD_LANGUAGE)
                .withTranslatedWord(VALID_TRANSLATION_BOB, VALID_TRANSLATION_LANGUAGE)
                .build();

        String[] changes = new String[]{editedFlashcard.getOriginalWord().getWord(), "",
                editedFlashcard.getTranslatedWord().getWord(), ""};
        EditCommand editCommand = new EditCommand(indexLastFlashcard, changes);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FLASHCARD_SUCCESS,
                Messages.format(editedFlashcard));

        Model expectedModel = new ModelManager(new Flashlingo(model.getFlashlingo()), new UserPrefs());
        expectedModel.setFlashCard(lastFlashcard, editedFlashcard);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }


    @Test
    public void execute_duplicatePersonUnfilteredList_failure() {
        FlashCard firstFlashcard = model.getFilteredFlashCardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
        FlashCard descriptor = new FlashCardBuilder(firstFlashcard).build();
        String[] changes = new String[] {descriptor.getOriginalWord().getWord(),
                descriptor.getOriginalWord().getLanguage(),
                descriptor.getTranslatedWord().getWord(),
                descriptor.getTranslatedWord().getLanguage()};
        EditCommand editCommand = new EditCommand(INDEX_SECOND_FLASHCARD, changes);

        assertCommandFailure(editCommand, model, MESSAGE_DUPLICATE_FLASHCARD);
    }

    @Test
    public void execute_duplicatePersonFilteredList_failure() {
        showFlashCardAtIndex(model, INDEX_FIRST_FLASHCARD);

        // edit person in filtered list into a duplicate in address book
        FlashCard flashCardInList = model.getFlashlingo().getFlashCardList().get(INDEX_SECOND_FLASHCARD.getZeroBased());
        FlashCard editedFlashcard = new FlashCardBuilder(flashCardInList).build();
        String[] changes = new String[]{editedFlashcard.getOriginalWord().getWord(), "",
                editedFlashcard.getTranslatedWord().getWord(), ""};
        EditCommand editCommand = new EditCommand(INDEX_FIRST_FLASHCARD, changes);

        assertCommandFailure(editCommand, model, MESSAGE_DUPLICATE_FLASHCARD);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFlashCardList().size() + 1);
        FlashCard descriptor = new FlashCardBuilder().withOriginalWord(VALID_ORIGINAL_WORD_BOB,
                VALID_ORIGINAL_WORD_LANGUAGE).build();
        String[] changes = new String[] {descriptor.getOriginalWord().getWord(), "",
                descriptor.getTranslatedWord().getWord()};
        EditCommand editCommand = new EditCommand(outOfBoundIndex, changes);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showFlashCardAtIndex(model, INDEX_FIRST_FLASHCARD);
        Index outOfBoundIndex = INDEX_SECOND_FLASHCARD;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFlashlingo().getFlashCardList().size());

        FlashCard editedFlashcard = new FlashCardBuilder()
                .withOriginalWord(VALID_ORIGINAL_WORD_BOB, VALID_TRANSLATION_LANGUAGE).build();
        String[] changes = new String[]{editedFlashcard.getOriginalWord().getWord(), "",
                editedFlashcard.getTranslatedWord().getWord(), ""};
        EditCommand editCommand = new EditCommand(outOfBoundIndex, changes);

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }


    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        FlashCard flashCard = new FlashCardBuilder().build();
        String editWord = flashCard.getOriginalWord().getWord();
        String editTranslation = flashCard.getTranslatedWord().getWord();
        String editWordLanguage = flashCard.getOriginalWord().getLanguage();
        String editTranslationLanguage = flashCard.getTranslatedWord().getLanguage();
        String[] changes = new String[] {editWord, editWordLanguage, editTranslation, editTranslationLanguage};
        EditCommand editCommand = new EditCommand(index, changes);
        String expected = EditCommand.class.getCanonicalName()
                + "{index=" + index + ", changes=" + Arrays.toString(changes) + "}";
        assertEquals(expected, editCommand.toString());
    }

}

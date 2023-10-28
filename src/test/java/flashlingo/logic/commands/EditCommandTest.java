package flashlingo.logic.commands;


import org.junit.jupiter.api.Test;
import seedu.flashlingo.commons.core.index.Index;
import seedu.flashlingo.logic.Messages;
import seedu.flashlingo.logic.commands.EditCommand;
import seedu.flashlingo.logic.commands.ExitCommand;
import seedu.flashlingo.model.Flashlingo;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;
import seedu.flashlingo.model.UserPrefs;
import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.model.flashcard.ProficiencyLevel;
import seedu.flashlingo.model.flashcard.words.OriginalWord;
import seedu.flashlingo.model.flashcard.words.TranslatedWord;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static flashlingo.logic.commands.CommandTestUtil.*;
import static flashlingo.testutil.TypicalFlashCards.*;
import static flashlingo.testutil.TypicalIndexes.INDEX_FIRST_FLASHCARD;
import static flashlingo.testutil.TypicalIndexes.INDEX_SECOND_FLASHCARD;
import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Edits the details of an existing flashcard in Flashlingo.
 */
public class EditCommandTest {
    private Model model = new ModelManager(getTypicalFlashlingo(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        FlashCard editedFlashcard = new FlashCardBuilder().withOriginalWord("waar", "dutch")
                .withTranslatedWord("True","English")
                .withLevel(1)
                .build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_FLASHCARD,
                editedFlashcard.getOriginalWord().getWord(), editedFlashcard.getTranslatedWord().getWord());
        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FLASHCARD_SUCCESS,
                Messages.format(editedFlashcard));

        Model expectedModel = new ModelManager(getTypicalFlashlingo(),new UserPrefs());
        expectedModel.setFlashCard(model.getFilteredFlashCardList().get(0), editedFlashcard);
        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Index indexLastFlashCard = Index.fromOneBased(model.getFilteredFlashCardList().size());
        FlashCard lastFlashCard = model.getFilteredFlashCardList().get(indexLastFlashCard.getZeroBased());

        FlashCardBuilder FlashCardInList = new FlashCardBuilder(lastFlashCard);
        FlashCard editedFlashCard = FlashCardInList.withOriginalWord(VALID_ORIGINAL_WORD_BOB,"")
                .withTranslatedWord(VALID_TRANSLATION_BOB,"")
                .withWhenToReview(new GregorianCalendar(2023, Calendar.DECEMBER, 23).getTime())
                        .withLevel(1).build();

        EditCommand editCommand = new EditCommand(indexLastFlashCard, editedFlashCard.getOriginalWord().getWord(),
                editedFlashCard.getTranslatedWord().getWord() );

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FLASHCARD_SUCCESS
                , Messages.format(editedFlashCard));

        Model expectedModel = new ModelManager(new Flashlingo(model.getFlashlingo()), new UserPrefs());
        expectedModel.setFlashCard(lastFlashCard, editedFlashCard);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showFlashCardAtIndex(model, INDEX_FIRST_FLASHCARD);

        FlashCard FlashCardInFilteredList = model.getFilteredFlashCardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
        FlashCard editedFlashCard = new FlashCardBuilder(FlashCardInFilteredList)
                .withOriginalWord(VALID_ORIGINAL_WORD_BOB,"").build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_FLASHCARD,
                editedFlashCard.getOriginalWord().getWord(), editedFlashCard.getTranslatedWord().getWord());

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_FLASHCARD_SUCCESS, Messages.format(editedFlashCard));

        Model expectedModel = new ModelManager(new Flashlingo(model.getFlashlingo()), new UserPrefs());
        expectedModel.setFlashCard(model.getFilteredFlashCardList().get(0), editedFlashCard);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateFlashCardUnfilteredList_failure() {
        FlashCard firstFlashCard = model.getFilteredFlashCardList().get(INDEX_FIRST_FLASHCARD.getZeroBased());
//        EditFlashCardDescriptor descriptor = new EditFlashCardDescriptorBuilder(firstFlashCard).build();
        EditCommand editCommand = new EditCommand(INDEX_SECOND_FLASHCARD, firstFlashCard.getOriginalWord().getWord(),
                firstFlashCard.getTranslatedWord().getWord());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_FLASHCARD);
    }

    @Test
    public void execute_duplicateFlashCardFilteredList_failure() {
        showFlashCardAtIndex(model, INDEX_FIRST_FLASHCARD);

        // edit FlashCard in filtered list into a duplicate in address book
        FlashCard FlashCardInList = model.getFlashlingo().getFlashCardList().get(INDEX_SECOND_FLASHCARD.getZeroBased());
        EditCommand editCommand = new EditCommand(INDEX_FIRST_FLASHCARD,
                FlashCardInList.getOriginalWord().getWord(), FlashCardInList.getTranslatedWord().getWord());

        assertCommandFailure(editCommand, model, EditCommand.MESSAGE_DUPLICATE_FLASHCARD);
    }

    @Test
    public void execute_invalidFlashCardIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredFlashCardList().size() + 1);
        FlashCard editedFlashCard = new FlashCardBuilder().withOriginalWord(VALID_TRANSLATED_WORD_THANKS,"").build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, editedFlashCard.getOriginalWord().getWord(),
                editedFlashCard.getTranslatedWord().getWord());

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of address book
     */
    @Test
    public void execute_invalidFlashCardIndexFilteredList_failure() {
        showFlashCardAtIndex(model, INDEX_FIRST_FLASHCARD);
        Index outOfBoundIndex = INDEX_SECOND_FLASHCARD;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getFlashlingo().getFlashCardList().size());

        FlashCard editedFlashCard = new FlashCardBuilder().withOriginalWord(VALID_ORIGINAL_WORD_WELCOME,"").build();
        EditCommand editCommand = new EditCommand(outOfBoundIndex, editedFlashCard.getOriginalWord().getWord(),
                editedFlashCard.getTranslatedWord().getWord()
                );

        assertCommandFailure(editCommand, model, Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        final FlashCard standardFlashCard = new FlashCard(
                new OriginalWord(VALID_ORIGINAL_WORD_PLEASE,""),
                new TranslatedWord(VALID_TRANSLATED_WORD_PLEASE, ""),
                new GregorianCalendar(2023, Calendar.DECEMBER, 24).getTime(),
        new ProficiencyLevel(1));
        final EditCommand standardCommand = new EditCommand(INDEX_FIRST_FLASHCARD
                , standardFlashCard.getOriginalWord().getWord(),
                standardFlashCard.getTranslatedWord().getWord());

        // same values -> returns true
        FlashCard copyFlashCard = new FlashCardBuilder().withOriginalWord(VALID_ORIGINAL_WORD_PLEASE,"")
                .withTranslatedWord(VALID_TRANSLATED_WORD_PLEASE,"")
                .withWhenToReview(new GregorianCalendar(2023, Calendar.DECEMBER, 24).getTime())
                .withLevel(1).build();
        EditCommand commandWithSameValues = new EditCommand(INDEX_FIRST_FLASHCARD,
                copyFlashCard.getOriginalWord().getWord(), copyFlashCard.getTranslatedWord().getWord());
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ExitCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_SECOND_FLASHCARD,
                standardFlashCard.getOriginalWord().getWord(), standardFlashCard.getTranslatedWord().getWord())));

        FlashCard differentFlashCard = new FlashCardBuilder().withOriginalWord(VALID_ORIGINAL_WORD_THANKS,"")
                .withTranslatedWord(VALID_TRANSLATED_WORD_THANKS,"")
                .withWhenToReview(new GregorianCalendar(2023, Calendar.DECEMBER, 24).getTime())
                .withLevel(1).build();
        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditCommand(INDEX_FIRST_FLASHCARD,
                differentFlashCard.getOriginalWord().getWord(), differentFlashCard.getTranslatedWord().getWord())));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        FlashCard editedFlashCard = new FlashCardBuilder().build();
        EditCommand editCommand = new EditCommand(index, editedFlashCard.getOriginalWord().getWord(),
                editedFlashCard.getTranslatedWord().getWord());
        String expected = EditCommand.class.getCanonicalName() + "{index=" + index + ", replacedWord="
                + editedFlashCard.getOriginalWord().getWord() + ", replacedTranslation="
                + editedFlashCard.getTranslatedWord().getWord() + "}";
        assertEquals(expected, editCommand.toString());
    }

//    public static final String COMMAND_WORD = "edit";
//    public static final String MESSAGE_EDIT_FLASHCARD_SUCCESS = "Edited Flashcard: %1$s";
//    public static final String MESSAGE_DUPLICATE_FLASHCARD = "This flashcard already exists in Flashlingo";
//
//    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the flashcard identified "
//          + "by the index number used in the displayed flashcard list. "
//          + "Existing values will be overwritten by the input values.\n"
//          + "Parameters: INDEX (must be a positive integer) "
//          + "[" + PREFIX_ORIGINAL_WORD + "ORIGINAL WORD] "
//          + "[" + PREFIX_TRANSLATED_WORD + "TRANSLATION] \n"
//          + "Example: " + COMMAND_WORD + " 1 "
//          + PREFIX_ORIGINAL_WORD + "bye "
//          + PREFIX_TRANSLATED_WORD + "再见";
//
//    private final Index index;
//    private final FlashCard replacedFlashCard;
//    /**
//     * @param index of the flashcard in the list to edit
//     * @param replacedFlashCard details to edit the flashcard with
//     */
//    public EditCommandTest(Index index, FlashCard replacedFlashCard) {
//        requireNonNull(index);
//        requireNonNull(replacedFlashCard);
//
//        this.index = index;
//        this.replacedFlashCard = replacedFlashCard;
//    }
//    @Override
//    public CommandResultTest execute(Model model) throws CommandExceptionTest {
//        requireNonNull(model);
//        List<FlashCard> lastShownList = model.getFilteredFlashCardList();
//
//        if (index.getZeroBased() >= lastShownList.size()) {
//            throw new CommandExceptionTest(Messages.MESSAGE_INVALID_FLASHCARD_DISPLAYED_INDEX);
//        }
//
//        FlashCard flashCardToEdit = lastShownList.get(index.getZeroBased());
//        FlashCard editedFlashCard = this.replacedFlashCard;
//
//        if (!flashCardToEdit.isSameFlashCard(editedFlashCard) && model.hasFlashCard(editedFlashCard)) {
//            throw new CommandExceptionTest(Messages.MESSAGE_DUPLICATE_FLASHCARD);
//        }
//        System.out.println("here");
//        model.setFlashCard(flashCardToEdit, editedFlashCard);
//        model.updateFilteredFlashCardList(PREDICATE_SHOW_ALL_FLASHCARDS);
//        return new CommandResultTest(String.format(MESSAGE_EDIT_FLASHCARD_SUCCESS, Messages.format(editedFlashCard)));
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        if (other == this) {
//            return true;
//        }
//
//        // instanceof handles nulls
//        if (!(other instanceof EditCommandTest)) {
//            return false;
//        }
//
//        EditCommandTest otherEditCommand = (EditCommandTest) other;
//        return index.equals(otherEditCommand.index)
//            && replacedFlashCard.equals(otherEditCommand.replacedFlashCard);
//    }
//
//    @Override
//    public String toString() {
//        return new ToStringBuilder(this)
//          .add("index", index)
//          .add("replacedFlashCard", replacedFlashCard)
//          .toString();
//    }
}

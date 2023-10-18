package seedu.flashlingo.logic.commands;

import static seedu.flashlingo.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.flashlingo.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.flashlingo.testutil.TypicalFlashCards.getTypicalFlashlingo;
import static seedu.flashlingo.logic.Messages.MESSAGE_DUPLICATE_FLASHCARD;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.flashlingo.logic.Messages;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;
import seedu.flashlingo.model.UserPrefs;
import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.testutil.FlashcardBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFlashlingo(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        FlashCard flashCard = new FlashcardBuilder().build();

        Model expectedModel = new ModelManager(model.getFlashlingo(), new UserPrefs());
        expectedModel.addFlashCard(flashCard);

        assertCommandSuccess(new AddCommand(flashCard), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(flashCard)),
                expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        FlashCard flashcardList = model.getFlashlingo().getFlashCardList().get(0);
        assertCommandFailure(new AddCommand(flashcardList), model,
                MESSAGE_DUPLICATE_FLASHCARD);
    }

}

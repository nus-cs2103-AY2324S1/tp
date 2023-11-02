package seedu.flashlingo.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.flashlingo.testutil.TypicalFlashCards.getTypicalFlashlingo;
import static seedu.flashlingo.testutil.TypicalFlashCards.getTypicalFlashlingoWithOneFlashCard;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;
import seedu.flashlingo.model.UserPrefs;
import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.session.SessionManager;

public class EndCommandTest {
    private Model model = new ModelManager(getTypicalFlashlingo(), new UserPrefs());

    private SessionManager sessionManager = SessionManager.getInstance();

    @Test
    public void execute_startSession_success() {
        try {
            model.endSession();
            assertEquals(sessionManager.isReviewSession(), false);
        } catch (Exception e) {
            fail("An exception occurred: " + e.getMessage());
        }
    }
}

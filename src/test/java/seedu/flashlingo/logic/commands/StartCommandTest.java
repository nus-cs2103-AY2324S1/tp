package seedu.flashlingo.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.flashlingo.testutil.TypicalFlashCards.getTypicalFlashlingoWithOneFlashCard;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;
import seedu.flashlingo.model.UserPrefs;
import seedu.flashlingo.logic.session.SessionManager;

public class StartCommandTest {

    private SessionManager sessionManager = SessionManager.getInstance();

    @Test
    public void execute_startSession_success() {
        Model model = new ModelManager(getTypicalFlashlingoWithOneFlashCard(), new UserPrefs());
        try {
            model.startSession();
            assertEquals(sessionManager.isReviewSession(), true);
        } catch (Exception e) {
            System.out.println(("An exception occurred: " + e.getMessage()));
        }
    }

    @Test
    public void equals() {
        StartCommand startFirstCommand = new StartCommand();
        assertTrue(startFirstCommand.equals(startFirstCommand));
        StartCommand startFirstCommandCopy = new StartCommand();
        assertTrue(startFirstCommand.equals(startFirstCommandCopy));
        assertFalse(startFirstCommand.equals(1));
        assertFalse(startFirstCommand.equals(null));
    }

}

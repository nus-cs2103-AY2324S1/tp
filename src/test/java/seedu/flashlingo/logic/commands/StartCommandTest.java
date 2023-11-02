package seedu.flashlingo.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.flashlingo.testutil.TypicalFlashCards.getTypicalFlashlingo;

import org.junit.jupiter.api.Test;

import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;
import seedu.flashlingo.model.UserPrefs;
import seedu.flashlingo.session.SessionManager;

public class StartCommandTest {
    private Model model = new ModelManager(getTypicalFlashlingo(), new UserPrefs());

    private SessionManager sessionManager = SessionManager.getInstance();

    @Test
    public void execute_startSession_success() {
        try {
            model.startSession();
            assertEquals(sessionManager.isReviewSession(), true);
        } catch (Exception e) {
            fail("An exception occurred: " + e.getMessage());
        }
    }
}

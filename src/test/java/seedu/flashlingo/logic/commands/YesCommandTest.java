package seedu.flashlingo.logic.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.flashlingo.model.Model;
import seedu.flashlingo.model.ModelManager;
import seedu.flashlingo.model.UserPrefs;
import seedu.flashlingo.model.flashcard.FlashCard;
import seedu.flashlingo.model.flashcard.ProficiencyLevel;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.flashlingo.testutil.TypicalFlashCards.*;

public class YesCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();
    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalFlashlingoWithOneFlashCard(), new UserPrefs());
        expectedModel = new ModelManager(model.getFlashlingo(), new UserPrefs());
    }
    @Test
    public void execute_yes_getNextReviewWord_success() {
        try {
            FlashCard result = model.nextReviewWord();
            assertNotNull(result);
        } catch (Exception e) {
            fail("An exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void execute_yes_updateDate_failure() {
        try {
            FlashCard result = model.nextReviewWord();
            ProficiencyLevel previousLevel = result.getProficiencyLevel();

            result.updateLevel(false);

            ProficiencyLevel currentLevel = result.getProficiencyLevel();
            assertEquals(previousLevel.getLevel(), currentLevel.getLevel());
        } catch (Exception e) {
            fail("An exception occurred: " + e.getMessage());
        }

    }

}

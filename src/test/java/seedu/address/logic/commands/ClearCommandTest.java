package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCards.getEmptyDeck;
import static seedu.address.testutil.TypicalCards.getTypicalDeck;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

class ClearCommandTest {

    private final Model model = new ModelManager(getTypicalDeck(), new UserPrefs());

    @Test
    public void execute_invalidModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClearCommand().execute(null));
    }

    @Test
    void execute_clearSuccessful() {
        String expectedMessage = String.format(ClearCommand.MESSAGE_SUCCESS);
        Model expectedModel = new ModelManager(getEmptyDeck(), new UserPrefs());

        assertCommandSuccess(new ClearCommand(), model, expectedMessage, expectedModel);
    }
}

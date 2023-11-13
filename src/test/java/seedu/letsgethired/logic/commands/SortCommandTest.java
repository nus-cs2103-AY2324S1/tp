package seedu.letsgethired.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.letsgethired.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.letsgethired.model.application.InternApplicationComparator.COMPANY_COMPARATOR_ASCENDING;
import static seedu.letsgethired.model.application.InternApplicationComparator.COMPANY_COMPARATOR_DESCENDING;
import static seedu.letsgethired.testutil.TypicalInternApplications.getTypicalInternTracker;

import org.junit.jupiter.api.Test;

import seedu.letsgethired.model.Model;
import seedu.letsgethired.model.ModelManager;
import seedu.letsgethired.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for SortCommand.
 */
public class SortCommandTest {
    private Model model = new ModelManager(getTypicalInternTracker(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalInternTracker(), new UserPrefs());

    @Test
    public void execute_oneFieldSpecifiedSort_success() {
        SortCommand sortCommand = new SortCommand(COMPANY_COMPARATOR_ASCENDING);
        String expectedMessage = SortCommand.MESSAGE_SUCCESS;
        expectedModel.updateFilteredSortedInternApplicationList(COMPANY_COMPARATOR_ASCENDING);

        assertCommandSuccess(sortCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        SortCommand sortFirstCommand = new SortCommand(COMPANY_COMPARATOR_ASCENDING);
        SortCommand sortSecondCommand = new SortCommand(COMPANY_COMPARATOR_ASCENDING);

        // Same object -> returns true
        assertEquals(sortFirstCommand, sortFirstCommand);

        // Same comparator -> returns true
        assertEquals(sortFirstCommand, sortSecondCommand);

        Object notSortCommand = new Object();
        // Different command or other object -> returns false
        assertNotEquals(sortFirstCommand, notSortCommand);

        // null -> returns false
        assertNotEquals(sortFirstCommand, null);

        // Different comparator -> returns false
        SortCommand sortThirdCommand = new SortCommand(COMPANY_COMPARATOR_DESCENDING);
        assertNotEquals(sortFirstCommand, sortThirdCommand);
    }

    @Test
    void toStringMethod() {
        SortCommand sortCommand = new SortCommand(COMPANY_COMPARATOR_ASCENDING);
        String expectedString =
                SortCommand.class.getCanonicalName() + "{comparator=" + COMPANY_COMPARATOR_ASCENDING + "}";
        assertEquals(sortCommand.toString(), expectedString);
    }
}

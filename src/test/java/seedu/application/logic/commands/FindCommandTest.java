package seedu.application.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.logic.Messages.MESSAGE_JOBS_LISTED_OVERVIEW;
import static seedu.application.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.application.testutil.TypicalJobs.FRUIT_SELLER;
import static seedu.application.testutil.TypicalJobs.GRASS_CUTTER;
import static seedu.application.testutil.TypicalJobs.POLICE_OFFICER;
import static seedu.application.testutil.TypicalJobs.getTypicalApplicationBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.application.model.Model;
import seedu.application.model.ModelManager;
import seedu.application.model.UserPrefs;
import seedu.application.model.job.RoleContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalApplicationBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalApplicationBook(), new UserPrefs());

    @Test
    public void equals() {
        RoleContainsKeywordsPredicate firstPredicate =
                new RoleContainsKeywordsPredicate(Collections.singletonList("first"));
        RoleContainsKeywordsPredicate secondPredicate =
                new RoleContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different job -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noJobFound() {
        String expectedMessage = String.format(MESSAGE_JOBS_LISTED_OVERVIEW, 0);
        RoleContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredJobList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredJobList());
    }

    @Test
    public void execute_multipleKeywords_multipleJobsFound() {
        String expectedMessage = String.format(MESSAGE_JOBS_LISTED_OVERVIEW, 3);
        RoleContainsKeywordsPredicate predicate = preparePredicate("Grass Seller Police");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredJobList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(POLICE_OFFICER, FRUIT_SELLER, GRASS_CUTTER), model.getFilteredJobList());
    }

    @Test
    public void toStringMethod() {
        RoleContainsKeywordsPredicate predicate = new RoleContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindCommand findCommand = new FindCommand(predicate);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private RoleContainsKeywordsPredicate preparePredicate(String userInput) {
        return new RoleContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

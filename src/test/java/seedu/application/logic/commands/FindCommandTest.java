package seedu.application.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.application.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.application.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.application.testutil.TypicalJobs.getTypicalApplicationBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.application.logic.Messages;
import seedu.application.model.Model;
import seedu.application.model.ModelManager;
import seedu.application.model.UserPrefs;
import seedu.application.model.job.CombinedPredicate;
import seedu.application.model.job.FieldContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private final Model model = new ModelManager(getTypicalApplicationBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalApplicationBook(), new UserPrefs());

    @Test
    public void equals() {
        CombinedPredicate firstPredicate =
            new CombinedPredicate(Arrays.asList(
                new FieldContainsKeywordsPredicate(PREFIX_ROLE, Collections.singletonList("first"))));
        CombinedPredicate secondPredicate =
            new CombinedPredicate(Arrays.asList(
                new FieldContainsKeywordsPredicate(PREFIX_ROLE, Collections.singletonList("second"))));

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(findFirstCommand, 5.0f);

        // null -> returns false
        assertNotEquals(findFirstCommand, null);

        // different job -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    @Test
    public void execute_multipleKeywords_noJobsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_JOBS_LISTED_OVERVIEW, 0);
        CombinedPredicate combinedPredicate = new CombinedPredicate(
            Arrays.asList(preparePredicate(PREFIX_ROLE + " Grass Seller Police")));
        FindCommand command = new FindCommand(combinedPredicate);
        expectedModel.updateFilteredJobList(combinedPredicate);
        assertCommandSuccess(command, model,
            expectedMessage, FindCommand.CLEARS_DETAILS_PANEL, expectedModel);
        assertEquals(Arrays.asList(), model.getFilteredJobList());
    }

    @Test
    public void toStringMethod() {
        CombinedPredicate predicate = new CombinedPredicate(Arrays.asList(
            new FieldContainsKeywordsPredicate(PREFIX_ROLE, Arrays.asList("keyword"))));
        FindCommand findCommand = new FindCommand(predicate);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code FieldContainsKeywordsPredicate}.
     */
    private FieldContainsKeywordsPredicate preparePredicate(String userInput) {
        String[] specifierAndKeywords = userInput.split("\\s+");
        ArrayList<String> keywords = new ArrayList<>(Arrays.asList(specifierAndKeywords)
                                                         .subList(1, specifierAndKeywords.length));
        return new FieldContainsKeywordsPredicate(PREFIX_ROLE, keywords);
    }
}

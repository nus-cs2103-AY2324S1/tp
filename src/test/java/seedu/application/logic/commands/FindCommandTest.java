package seedu.application.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.logic.Messages.MESSAGE_JOBS_LISTED_OVERVIEW;
import static seedu.application.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.application.model.job.Role.ROLE_SPECIFIER;
import static seedu.application.testutil.TypicalJobs.FRUIT_SELLER;
import static seedu.application.testutil.TypicalJobs.GRASS_CUTTER;
import static seedu.application.testutil.TypicalJobs.POLICE_OFFICER;
import static seedu.application.testutil.TypicalJobs.getTypicalApplicationBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.application.model.Model;
import seedu.application.model.ModelManager;
import seedu.application.model.UserPrefs;
import seedu.application.model.job.FieldContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalApplicationBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalApplicationBook(), new UserPrefs());

    @Test
    public void equals() {
        FieldContainsKeywordsPredicate firstPredicate =
                new FieldContainsKeywordsPredicate(ROLE_SPECIFIER, Collections.singletonList("first"));
        FieldContainsKeywordsPredicate secondPredicate =
                new FieldContainsKeywordsPredicate(ROLE_SPECIFIER, Collections.singletonList("second"));

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
        FieldContainsKeywordsPredicate predicate = preparePredicate(ROLE_SPECIFIER + " ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredJobList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredJobList());
    }

    @Test
    public void execute_multipleKeywords_multipleJobsFound() {
        String expectedMessage = String.format(MESSAGE_JOBS_LISTED_OVERVIEW, 3);
        FieldContainsKeywordsPredicate predicate = preparePredicate(ROLE_SPECIFIER + " Grass Seller Police");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredJobList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(POLICE_OFFICER, FRUIT_SELLER, GRASS_CUTTER), model.getFilteredJobList());
    }

    @Test
    public void toStringMethod() {
        FieldContainsKeywordsPredicate predicate =
                new FieldContainsKeywordsPredicate(ROLE_SPECIFIER, Arrays.asList("keyword"));
        FindCommand findCommand = new FindCommand(predicate);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code FieldContainsKeywordsPredicate}.
     */
    private FieldContainsKeywordsPredicate preparePredicate(String userInput) {
        String[] specifierAndKeywords = userInput.split("\\s+");
        String specifier = specifierAndKeywords[0];
        ArrayList<String> keywords = new ArrayList<>();
        for (int i = 1; i < specifierAndKeywords.length; i++) {
            keywords.add(specifierAndKeywords[i]);
        }
        return new FieldContainsKeywordsPredicate(specifier, keywords);
    }
}

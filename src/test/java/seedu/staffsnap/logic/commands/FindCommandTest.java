package seedu.staffsnap.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.staffsnap.logic.Messages.MESSAGE_APPLICANTS_LISTED_OVERVIEW;
import static seedu.staffsnap.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.staffsnap.testutil.TypicalApplicants.BENSON;
import static seedu.staffsnap.testutil.TypicalApplicants.CARL;
import static seedu.staffsnap.testutil.TypicalApplicants.DANIEL;
import static seedu.staffsnap.testutil.TypicalApplicants.ELLE;
import static seedu.staffsnap.testutil.TypicalApplicants.FIONA;
import static seedu.staffsnap.testutil.TypicalApplicants.getTypicalApplicantBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.staffsnap.model.Model;
import seedu.staffsnap.model.ModelManager;
import seedu.staffsnap.model.UserPrefs;
import seedu.staffsnap.model.applicant.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalApplicantBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalApplicantBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

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

        // different applicant -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noApplicantFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredApplicantList());
    }

    @Test
    public void execute_multipleKeywords_multipleApplicantsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredApplicantList());
    }

    @Test
    public void execute_multipleKeywords_singleApplicantFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate predicate = preparePredicate("Fiona Fluorescence");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA), model.getFilteredApplicantList());
    }

    @Test
    public void execute_singleIncompleteKeyword_multipleApplicantsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 2);
        NameContainsKeywordsPredicate predicate = preparePredicate("Mei");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        System.out.println(model.getFilteredApplicantList());
        assertEquals(Arrays.asList(BENSON, DANIEL), model.getFilteredApplicantList());
    }


    @Test
    public void toStringMethod() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindCommand findCommand = new FindCommand(predicate);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

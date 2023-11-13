package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_APPLICANTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalApplicants.CARL_APPLICANT;
import static seedu.address.testutil.TypicalApplicants.ELLE_APPLICANT;
import static seedu.address.testutil.TypicalApplicants.FIONA_APPLICANT;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBookWithMembersApplicants;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ApplicantContainsKeywordsPredicate;

public class FindApplicantCommandTest {
    private Model model = new ModelManager(getTypicalAddressBookWithMembersApplicants(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBookWithMembersApplicants(), new UserPrefs());

    @Test
    public void equals() {
        ApplicantContainsKeywordsPredicate firstPredicate =
                new ApplicantContainsKeywordsPredicate(Collections.singletonList("Alice"));
        ApplicantContainsKeywordsPredicate secondPredicate =
                new ApplicantContainsKeywordsPredicate(Collections.singletonList("Benson"));

        FindApplicantCommand findFirstCommand = new FindApplicantCommand(firstPredicate);
        FindApplicantCommand findSecondCommand = new FindApplicantCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindApplicantCommand findFirstCommandCopy = new FindApplicantCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }
    @Test
   public void execute_zeroKeywords_noApplicantFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 0);
        ApplicantContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindApplicantCommand command = new FindApplicantCommand(predicate);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredApplicantList());
    }
    @Test
    public void execute_multipleKeywords_multipleApplicantFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 3);
        ApplicantContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindApplicantCommand command = new FindApplicantCommand(predicate);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL_APPLICANT, ELLE_APPLICANT, FIONA_APPLICANT), model.getFilteredApplicantList());
    }

    @Test
    public void toStringMethod() {
        ApplicantContainsKeywordsPredicate predicate = new ApplicantContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindApplicantCommand findCommand = new FindApplicantCommand(predicate);
        String expected = FindApplicantCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code ApplicantContainsKeywordsPredicate}.
     */
    private ApplicantContainsKeywordsPredicate preparePredicate(String userInput) {
        return new ApplicantContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

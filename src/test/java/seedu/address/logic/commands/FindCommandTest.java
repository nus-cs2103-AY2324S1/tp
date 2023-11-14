package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_APPLICANTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalApplicants.ALICE;
import static seedu.address.testutil.TypicalApplicants.BENSON;
import static seedu.address.testutil.TypicalApplicants.CARL;
import static seedu.address.testutil.TypicalApplicants.DANIEL;
import static seedu.address.testutil.TypicalApplicants.ELLE;
import static seedu.address.testutil.TypicalApplicants.FIONA;
import static seedu.address.testutil.TypicalApplicants.GEORGE;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.applicant.predicate.AddressContainsKeywordsPredicate;
import seedu.address.model.applicant.predicate.ApplicantPredicate;
import seedu.address.model.applicant.predicate.EmailContainsKeywordsPredicate;
import seedu.address.model.applicant.predicate.NameContainsKeywordsPredicate;
import seedu.address.model.applicant.predicate.PhoneContainsNumberPredicate;
import seedu.address.model.applicant.predicate.TagsContainKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        ApplicantPredicate firstCombinedPredicate = new ApplicantPredicate(Arrays.asList(firstNamePredicate));
        ApplicantPredicate secondCombinedPredicate = new ApplicantPredicate(Arrays.asList(secondNamePredicate));

        FindCommand findFirstCommand = new FindCommand(firstCombinedPredicate);
        FindCommand findSecondCommand = new FindCommand(secondCombinedPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstCombinedPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different applicant -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_invalidNameKeywords_noApplicantFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 0);
        ApplicantPredicate predicate =
                new ApplicantPredicate(Arrays.asList(prepareNamePredicate("!@#$% ")));
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredApplicantList());
    }

    @Test
    public void execute_multipleNameKeywords_multipleApplicantsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 3);
        ApplicantPredicate predicate =
                new ApplicantPredicate(Arrays.asList(prepareNamePredicate("Kurz Elle Kunz")));
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredApplicantList());
    }

    @Test
    public void execute_validNumber_multipleApplicantsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 3);
        ApplicantPredicate predicate =
                new ApplicantPredicate(Arrays.asList(new PhoneContainsNumberPredicate("948")));
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE, FIONA, GEORGE), model.getFilteredApplicantList());
    }

    @Test
    public void execute_invalidNumber_zeroApplicantsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 0);
        ApplicantPredicate predicate =
                new ApplicantPredicate(Arrays.asList(new PhoneContainsNumberPredicate("not a number!")));
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList() , model.getFilteredApplicantList());
    }

    @Test
    public void execute_multipleEmailKeywords_multipleApplicantsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 3);
        ApplicantPredicate predicate =
                new ApplicantPredicate(Arrays.asList(
                        prepareEmailPredicate("heinz werner@example.com example1.com")));
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredApplicantList());
    }

    @Test
    public void execute_multipleAddressKeywords_multipleApplicantsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 3);
        ApplicantPredicate predicate =
                new ApplicantPredicate(Arrays.asList(
                        prepareAddressPredicate("wall michegan tokyo")));
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredApplicantList());
    }

    @Test
    public void execute_multipleTagKeywords_multipleApplicantsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 3);
        ApplicantPredicate predicate =
                new ApplicantPredicate(Arrays.asList(
                        prepareTagPredicate("friends owesmoney")));
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredApplicantList());
    }

    @Test
    public void execute_multipleFields_multipleApplicantsFound() {
        String expectedMessage = String.format(MESSAGE_APPLICANTS_LISTED_OVERVIEW, 6);
        ApplicantPredicate predicate =
                new ApplicantPredicate(Arrays.asList(
                        prepareNamePredicate("alice fiona"),
                        new PhoneContainsNumberPredicate("95352563"),
                        prepareEmailPredicate("cornelia example1.com"),
                        prepareAddressPredicate("wall 4th"),
                        prepareTagPredicate("friends owesmoney")
                        ));
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredApplicantList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, FIONA, GEORGE), model.getFilteredApplicantList());
    }

    @Test
    public void toStringMethod() {
        NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(Arrays.asList("keyword"));
        ApplicantPredicate predicate = new ApplicantPredicate(Arrays.asList(namePredicate));
        FindCommand findCommand = new FindCommand(predicate);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code EmailContainsKeywordsPredicate}.
     */
    private EmailContainsKeywordsPredicate prepareEmailPredicate(String userInput) {
        return new EmailContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code AddressContainsKeywordsPredicate}.
     */
    private AddressContainsKeywordsPredicate prepareAddressPredicate(String userInput) {
        return new AddressContainsKeywordsPredicate((Arrays.asList(userInput.split("\\s+"))));
    }

    /**
     * Parses {@code userInput} into a {@code TagContainsKeywordsPredicate}.
     */
    private TagsContainKeywordsPredicate prepareTagPredicate(String userInput) {
        return new TagsContainKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

}

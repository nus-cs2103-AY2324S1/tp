package seedu.address.logic.commands.find;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalDevelopers.ALICE;
import static seedu.address.testutil.TypicalDevelopers.BENSON;
import static seedu.address.testutil.TypicalDevelopers.CARL;
import static seedu.address.testutil.TypicalDevelopers.ELLE;
import static seedu.address.testutil.TypicalDevelopers.FIONA;
import static seedu.address.testutil.TypicalDevelopers.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.developer.NameDeveloperContainsKeywordsPredicate;
import seedu.address.model.developer.RatingContainsKeywordsPredicate;
import seedu.address.model.developer.SalaryContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindDeveloperCommand}.
 */
public class FindDeveloperCommandTest {
    private final Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameDeveloperContainsKeywordsPredicate firstPredicate =
                new NameDeveloperContainsKeywordsPredicate(Collections.singletonList("first"));
        NameDeveloperContainsKeywordsPredicate secondPredicate =
                new NameDeveloperContainsKeywordsPredicate(Collections.singletonList("second"));

        FindDeveloperCommand findFirstCommand = new FindDeveloperCommand(firstPredicate);
        FindDeveloperCommand findSecondCommand = new FindDeveloperCommand(secondPredicate);

        // same object -> returns true
        assertEquals(findFirstCommand, findFirstCommand);

        // same values -> returns true
        FindDeveloperCommand findFirstCommandCopy = new FindDeveloperCommand(firstPredicate);
        assertEquals(findFirstCommand, findFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, findFirstCommand);

        // null -> returns false
        assertNotEquals(null, findFirstCommand);

        // different developer -> returns false
        assertNotEquals(findFirstCommand, findSecondCommand);
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        //String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        String expectedMessage = "There are no developers with matching information.";
        NameDeveloperContainsKeywordsPredicate predicate = prepareNamePredicate("hii");
        FindDeveloperCommand command = new FindDeveloperCommand(predicate);
        expectedModel.updateFilteredDeveloperList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredDeveloperList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        //String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        String expectedMessage = "These are the 3 developers with matching information.";
        NameDeveloperContainsKeywordsPredicate predicate = prepareNamePredicate("Kurz Elle Kunz");
        FindDeveloperCommand command = new FindDeveloperCommand(predicate);
        expectedModel.updateFilteredDeveloperList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredDeveloperList());
    }

    @Test
    public void toStringMethod() {
        NameDeveloperContainsKeywordsPredicate predicate =
                new NameDeveloperContainsKeywordsPredicate(List.of("keyword"));
        FindDeveloperCommand findDeveloperCommand = new FindDeveloperCommand(predicate);
        String expected = FindDeveloperCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findDeveloperCommand.toString());
    }

    @Test
    public void execute_caseInsensitiveSearch_personFound() {
        String expectedMessage = "This is the 1 developer with matching information.";
        NameDeveloperContainsKeywordsPredicate predicate = prepareNamePredicate("ElLe"); // using mixed case
        FindDeveloperCommand command = new FindDeveloperCommand(predicate);
        expectedModel.updateFilteredDeveloperList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE), model.getFilteredDeveloperList());
    }

    @Test
    public void execute_salarySearch_personFound() {
        String expectedMessage = "This is the 1 developer with matching information.";
        SalaryContainsKeywordsPredicate predicate = prepareSalaryPredicate("40000"); // using mixed case
        FindDeveloperCommand command = new FindDeveloperCommand(predicate);
        expectedModel.updateFilteredDeveloperList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredDeveloperList());
    }

    @Test
    public void execute_ratingSearch_personFound() {
        String expectedMessage = "These are the 3 developers with matching information.";
        RatingContainsKeywordsPredicate predicate = prepareRatingPredicate("4.0");
        FindDeveloperCommand command = new FindDeveloperCommand(predicate);
        expectedModel.updateFilteredDeveloperList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, ELLE, FIONA), model.getFilteredDeveloperList());
    }

    @Test
    public void execute_partialName_personFound() {
        String expectedMessage = "This is the 1 developer with matching information.";
        NameDeveloperContainsKeywordsPredicate predicate = prepareNamePredicate("Ell");
        FindDeveloperCommand command = new FindDeveloperCommand(predicate);
        expectedModel.updateFilteredDeveloperList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE), model.getFilteredDeveloperList());
    }

    @Test
    public void execute_partialSalary_noPersonFound() {
        String expectedMessage = "There are no developers with matching information.";
        SalaryContainsKeywordsPredicate predicate = prepareSalaryPredicate("4000");
        FindDeveloperCommand command = new FindDeveloperCommand(predicate);
        expectedModel.updateFilteredDeveloperList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredDeveloperList());
    }

    @Test
    public void execute_partialRating_noPersonFound() {
        String expectedMessage = "There are no developers with matching information.";
        RatingContainsKeywordsPredicate predicate = prepareRatingPredicate("4");
        FindDeveloperCommand command = new FindDeveloperCommand(predicate);
        expectedModel.updateFilteredDeveloperList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredDeveloperList());
    }

    @Test
    public void execute_noMatchingPersons_noPersonFound() {
        String expectedMessage = "There are no developers with matching information.";
        NameDeveloperContainsKeywordsPredicate predicate = prepareNamePredicate("NonExistentDeveloper");
        FindDeveloperCommand command = new FindDeveloperCommand(predicate);
        expectedModel.updateFilteredDeveloperList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredDeveloperList());
    }

    @Test
    public void execute_noMatchingSalary_noPersonFound() {
        String expectedMessage = "There are no developers with matching information.";
        SalaryContainsKeywordsPredicate predicate = prepareSalaryPredicate("30000");
        FindDeveloperCommand command = new FindDeveloperCommand(predicate);
        expectedModel.updateFilteredDeveloperList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredDeveloperList());
    }

    @Test
    public void execute_noMatchingRating_noPersonFound() {
        String expectedMessage = "There are no developers with matching information.";
        RatingContainsKeywordsPredicate predicate = prepareRatingPredicate("2.5");
        FindDeveloperCommand command = new FindDeveloperCommand(predicate);
        expectedModel.updateFilteredDeveloperList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredDeveloperList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameDeveloperContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameDeveloperContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private SalaryContainsKeywordsPredicate prepareSalaryPredicate(String userInput) {
        return new SalaryContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    private RatingContainsKeywordsPredicate prepareRatingPredicate(String userInput) {
        return new RatingContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

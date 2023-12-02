package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_COMPANIES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.testdata.FindCommandTestCompanies.APPLESTORE;
import static seedu.address.logic.commands.testdata.FindCommandTestCompanies.APPLE_STORES;
import static seedu.address.logic.commands.testdata.FindCommandTestCompanies.B;
import static seedu.address.logic.commands.testdata.FindCommandTestCompanies.BANANASTORE;
import static seedu.address.logic.commands.testdata.FindCommandTestCompanies.getFindCommandTestAddressBook;
import static seedu.address.testutil.TypicalCompanies.ACCENTURE;
import static seedu.address.testutil.TypicalCompanies.APPLE;
import static seedu.address.testutil.TypicalCompanies.MICROSOFT;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.company.predicates.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getFindCommandTestAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getFindCommandTestAddressBook(), new UserPrefs());

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

        // different company -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noCompanyFound() {
        String expectedMessage = String.format(MESSAGE_COMPANIES_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.findCompanies(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredCompanyList());
    }

    @Test
    public void execute_multipleKeywords_multipleCompaniesFound() {
        String expectedMessage = String.format(MESSAGE_COMPANIES_LISTED_OVERVIEW, 2);
        NameContainsKeywordsPredicate predicate = preparePredicate("Microsoft Accenture");
        FindCommand command = new FindCommand(predicate);
        expectedModel.findCompanies(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(MICROSOFT, ACCENTURE), model.getFilteredCompanyList());
    }

    @Test
    public void toStringMethod() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindCommand findCommand = new FindCommand(predicate);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    @Test
    public void execute_findA_multipleCompaniesFound() {
        String expectedMessage = String.format(MESSAGE_COMPANIES_LISTED_OVERVIEW, 5);
        NameContainsKeywordsPredicate predicate = preparePredicate("A");
        FindCommand command = new FindCommand(predicate);
        expectedModel.findCompanies(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(APPLE, APPLESTORE, APPLE_STORES, BANANASTORE, ACCENTURE),
                model.getFilteredCompanyList());
    }

    @Test
    public void execute_findAp_multipleCompaniesFound() {
        String expectedMessage = String.format(MESSAGE_COMPANIES_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Ap");
        FindCommand command = new FindCommand(predicate);
        expectedModel.findCompanies(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(APPLE, APPLESTORE, APPLE_STORES), model.getFilteredCompanyList());
    }

    @Test
    public void execute_findAppleStore_singleCompanyFound() {
        String expectedMessage = String.format(MESSAGE_COMPANIES_LISTED_OVERVIEW, 1);
        NameContainsKeywordsPredicate predicate = preparePredicate("AppleStore");
        FindCommand command = new FindCommand(predicate);
        expectedModel.findCompanies(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.singletonList(APPLESTORE), model.getFilteredCompanyList());
    }

    @Test
    public void execute_findAppleStoreWithSpace_multipleCompaniesFound() {
        String expectedMessage = String.format(MESSAGE_COMPANIES_LISTED_OVERVIEW, 4);
        NameContainsKeywordsPredicate predicate = preparePredicate("Apple Store");
        FindCommand command = new FindCommand(predicate);
        expectedModel.findCompanies(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(APPLE, APPLESTORE, APPLE_STORES, BANANASTORE), model.getFilteredCompanyList());
    }

    @Test
    public void execute_findB_multipleCompaniesFound() {
        String expectedMessage = String.format(MESSAGE_COMPANIES_LISTED_OVERVIEW, 2);
        NameContainsKeywordsPredicate predicate = preparePredicate("B");
        FindCommand command = new FindCommand(predicate);
        expectedModel.findCompanies(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BANANASTORE, B), model.getFilteredCompanyList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

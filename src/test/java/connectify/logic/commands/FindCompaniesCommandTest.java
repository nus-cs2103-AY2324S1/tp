package connectify.logic.commands;

import static connectify.logic.Messages.MESSAGE_COMPANIES_LISTED_OVERVIEW;
import static connectify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static connectify.testutil.TypicalCompanies.COMPANY_1;
import static connectify.testutil.TypicalCompanies.COMPANY_2;
import static connectify.testutil.TypicalPersons.ALICE;
import static connectify.testutil.TypicalPersons.BENSON;
import static connectify.testutil.TypicalPersons.CARL;
import static connectify.testutil.TypicalPersons.DANIEL;
import static connectify.testutil.TypicalPersons.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import connectify.model.Model;
import connectify.model.ModelManager;
import connectify.model.UserPrefs;
import connectify.model.company.Company;
import connectify.model.company.CompanyNameContainsKeywordsPredicate;


/**
 * Contains integration tests (interaction with the Model) for {@code FindCompaniesCommand}.
 */
public class FindCompaniesCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        CompanyNameContainsKeywordsPredicate firstPredicate =
                new CompanyNameContainsKeywordsPredicate(Collections.singletonList("first"));
        CompanyNameContainsKeywordsPredicate secondPredicate =
                new CompanyNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCompaniesCommand findFirstCommand = new FindCompaniesCommand(firstPredicate);
        FindCompaniesCommand findSecondCommand = new FindCompaniesCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCompaniesCommand findFirstCommandCopy = new FindCompaniesCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different company -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    /**
     * Tests the scenario where no companies are found with zero keywords.
     */
    @Test
    public void execute_zeroKeywords_noCompanyFound() {
        String expectedMessage = String.format(MESSAGE_COMPANIES_LISTED_OVERVIEW, 0);
        CompanyNameContainsKeywordsPredicate predicate = createCompanyNamePredicate(" ");
        FindCompaniesCommand command = new FindCompaniesCommand(predicate);
        expectedModel.updateFilteredCompanyList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredCompanyList());
    }

    /**
     * Tests the scenario where multiple companies are found with multiple keywords.
     */
    @Test
    public void execute_multipleKeywords_multipleCompaniesFound() {
        String expectedMessage = String.format(MESSAGE_COMPANIES_LISTED_OVERVIEW, 2);
        CompanyNameContainsKeywordsPredicate predicate = createCompanyNamePredicate("Company1 Company2");
        FindCompaniesCommand command = new FindCompaniesCommand(predicate);
        expectedModel.updateFilteredCompanyList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        Company expectedCompany1 = COMPANY_1;
        expectedCompany1 = expectedCompany1.addPersonToCompany(ALICE);
        expectedCompany1 = expectedCompany1.addPersonToCompany(BENSON);

        Company expectedCompany2 = COMPANY_2;
        expectedCompany2 = expectedCompany2.addPersonToCompany(CARL);
        expectedCompany2 = expectedCompany2.addPersonToCompany(DANIEL);

        List<Company> expectedCompanies = Arrays.asList(expectedCompany1, expectedCompany2);
        assertEquals(expectedCompanies, model.getFilteredCompanyList());
    }

    /**
     * Tests the scenario where a single company is found with a single keyword.
     */
    @Test
    public void execute_singleKeyword_singleCompanyFound() {
        String expectedMessage = String.format(MESSAGE_COMPANIES_LISTED_OVERVIEW, 1);
        CompanyNameContainsKeywordsPredicate predicate = createCompanyNamePredicate("Company1");
        FindCompaniesCommand command = new FindCompaniesCommand(predicate);
        expectedModel.updateFilteredCompanyList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        Company expectedCompany = COMPANY_1;
        expectedCompany = expectedCompany.addPersonToCompany(ALICE);
        expectedCompany = expectedCompany.addPersonToCompany(BENSON);
        List<Company> expectedCompanies = Collections.singletonList(expectedCompany);
        assertEquals(expectedCompanies, model.getFilteredCompanyList());
    }

    /**
     * Tests the scenario where the user provides keywords in a mixture of upper and lower case letters.
     * The search should be case-insensitive, and the method should return companies that match
     * the keywords regardless of their case.
     */
    @Test
    public void execute_caseInsensitiveSearch() {
        String expectedMessage = String.format(MESSAGE_COMPANIES_LISTED_OVERVIEW, 1);
        CompanyNameContainsKeywordsPredicate predicate = createCompanyNamePredicate("COMPANY1");
        FindCompaniesCommand command = new FindCompaniesCommand(predicate);
        expectedModel.updateFilteredCompanyList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        Company expectedCompany = COMPANY_1;
        expectedCompany = expectedCompany.addPersonToCompany(ALICE);
        expectedCompany = expectedCompany.addPersonToCompany(BENSON);
        List<Company> expectedCompanies = Collections.singletonList(expectedCompany);
        assertEquals(expectedCompanies, model.getFilteredCompanyList());
    }

    /**
     * Tests the scenario where the user provides multiple keywords for searching,
     * and only some of them match the companies in the list.
     */
    @Test
    public void execute_multipleKeywords_someMatching() {
        String expectedMessage = String.format(MESSAGE_COMPANIES_LISTED_OVERVIEW, 1);
        CompanyNameContainsKeywordsPredicate predicate = createCompanyNamePredicate("Company1 Nonexistent");
        FindCompaniesCommand command = new FindCompaniesCommand(predicate);
        expectedModel.updateFilteredCompanyList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        Company expectedCompany = COMPANY_1;
        expectedCompany = expectedCompany.addPersonToCompany(ALICE);
        expectedCompany = expectedCompany.addPersonToCompany(BENSON);
        List<Company> expectedCompanies = Collections.singletonList(expectedCompany);
        assertEquals(expectedCompanies, model.getFilteredCompanyList());
    }

    /**
     * Parses {@code userInput} into a {@code CompanyNameContainsKeywordsPredicate}.
     *
     * @param userInput The user's input to be parsed.
     * @return A predicate that checks if a company name contains the given keywords.
     */
    private CompanyNameContainsKeywordsPredicate createCompanyNamePredicate(String userInput) {
        return new CompanyNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}

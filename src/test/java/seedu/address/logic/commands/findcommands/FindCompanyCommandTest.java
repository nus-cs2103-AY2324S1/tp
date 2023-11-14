package seedu.address.logic.commands.findcommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_COMPANIES_LISTED_OVERVIEW;
import static seedu.address.testutil.TypicalCompanies.APPLE;
import static seedu.address.testutil.TypicalCompanies.GOOGLE;
import static seedu.address.testutil.TypicalCompanies.MICROSOFT;
import static seedu.address.testutil.TypicalCompanies.getTypicalAddressBook;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.company.CompanyNameAndTagContainKeywordsPredicate;

public class FindCompanyCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        CompanyNameAndTagContainKeywordsPredicate firstPredicate =
            new CompanyNameAndTagContainKeywordsPredicate(Arrays.asList("Apple"), Arrays.asList("tech"));
        CompanyNameAndTagContainKeywordsPredicate secondPredicate =
            new CompanyNameAndTagContainKeywordsPredicate(Arrays.asList("Microsoft"), Arrays.asList("windows"));

        FindCompanyCommand findFirstCommand = new FindCompanyCommand(firstPredicate);
        FindCompanyCommand findSecondCommand = new FindCompanyCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCompanyCommand findFirstCommandCopy = new FindCompanyCommand(firstPredicate);
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
        CompanyNameAndTagContainKeywordsPredicate predicate = preparePredicate(" ", " ");
        FindCompanyCommand command = new FindCompanyCommand(predicate);
        expectedModel.updateFilteredCompanyList(predicate);
        CommandTestUtil.assertRegularCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(0, model.getFilteredCompanyList().size());
    }

    @Test
    public void execute_multipleNameKeywords_multipleCompaniesFound() {
        String expectedMessage = String.format(MESSAGE_COMPANIES_LISTED_OVERVIEW, 2); // Apple and Microsoft match
        CompanyNameAndTagContainKeywordsPredicate predicate = preparePredicate("Apple Microsoft", " ");
        FindCompanyCommand command = new FindCompanyCommand(predicate);
        expectedModel.updateFilteredCompanyList(predicate);
        CommandTestUtil.assertRegularCommandSuccess(command, model, expectedMessage, expectedModel);

        // Check that the actual companies returned match Apple and Microsoft
        assertEquals(Arrays.asList(APPLE, MICROSOFT), model.getFilteredCompanyList());
    }

    @Test
    public void execute_multipleTagKeywords_multipleCompaniesFound() {
        String expectedMessage = String.format(MESSAGE_COMPANIES_LISTED_OVERVIEW, 3);
        // Apple, Microsoft, and Google have "tech" tag
        CompanyNameAndTagContainKeywordsPredicate predicate = preparePredicate(" ", "tech");
        FindCompanyCommand command = new FindCompanyCommand(predicate);
        expectedModel.updateFilteredCompanyList(predicate);
        CommandTestUtil.assertRegularCommandSuccess(command, model, expectedMessage, expectedModel);

        // Check that the actual companies returned have the "tech" tag
        assertEquals(Arrays.asList(APPLE, MICROSOFT, GOOGLE), model.getFilteredCompanyList());
    }

    /**
     * Parses {@code nameInput} and {@code tagInput} into a {@code CompanyNameAndTagContainKeywordsPredicate}.
     */
    private CompanyNameAndTagContainKeywordsPredicate preparePredicate(String nameInput, String tagInput) {
        return new CompanyNameAndTagContainKeywordsPredicate(
            Arrays.asList(nameInput.split("\\s+")),
            Arrays.asList(tagInput.split("\\s+"))
        );
    }
}

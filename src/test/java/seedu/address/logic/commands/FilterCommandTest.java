package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCompanies.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.company.ApplicationStatus;
import seedu.address.model.company.predicates.ApplicationStatusPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for FilterCommand.
 */
public class FilterCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validStatusUnfilteredList_success() {
        ApplicationStatus status = new ApplicationStatus("pa");
        String expectedMessage = String.format(FilterCommand.MESSAGE_SUCCESS, status);
        ApplicationStatusPredicate predicate = new ApplicationStatusPredicate(status);
        FilterCommand filterCommand = new FilterCommand(status, predicate);
        expectedModel.updateFilteredCompanyList(predicate);
        assertCommandSuccess(filterCommand, model, expectedMessage, expectedModel);
        assertEquals(model.getFilteredCompanyList(), expectedModel.getFilteredCompanyList());
    }

    @Test
    public void equals() {
        ApplicationStatus firstStatus = new ApplicationStatus("pa");
        ApplicationStatus secondStatus = new ApplicationStatus("pi");
        ApplicationStatusPredicate firstPredicate = new ApplicationStatusPredicate(firstStatus);
        ApplicationStatusPredicate secondPredicate = new ApplicationStatusPredicate(secondStatus);

        FilterCommand filterFirstCommand = new FilterCommand(firstStatus, firstPredicate);
        FilterCommand filterSecondCommand = new FilterCommand(secondStatus, secondPredicate);

        // same object -> returns true
        assertTrue(filterFirstCommand.equals(filterFirstCommand));

        // same values -> returns true
        FilterCommand findFirstCommandCopy = new FilterCommand(firstStatus, firstPredicate);
        assertTrue(filterFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterFirstCommand.equals(null));

        // different company -> returns false
        assertFalse(filterFirstCommand.equals(filterSecondCommand));
    }

    @Test
    public void toStringMethod() {
        ApplicationStatus status = new ApplicationStatus("pa");
        ApplicationStatusPredicate predicate = new ApplicationStatusPredicate(status);
        FilterCommand filterCommand = new FilterCommand(status, predicate);
        String expected = FilterCommand.class.getCanonicalName()
                + "{status=" + status + ", predicate=" + predicate + "}";
        assertEquals(expected, filterCommand.toString());
    }
}

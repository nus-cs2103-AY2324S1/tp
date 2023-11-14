package seedu.address.logic.commands.viewcommands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCompanyCommandFailure;
import static seedu.address.testutil.TypicalCompanies.getTypicalAddressBook;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.CommandTestUtil;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.company.Company;

/**
 * Contains integration tests (interaction with the Model) and unit tests for {@code ViewPersonCommand}.
 */
public class ViewCompanyCommandIntegrationTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_viewCompanyAtIndex1_success() {
        List<Company> listToCheck = expectedModel.getFilteredCompanyList();
        Company companyToView = listToCheck.get(0);
        CommandTestUtil.assertDisplayableCommandSuccess(new ViewCompanyCommand(Index.fromOneBased(1)), model,
                String.format(ViewCompanyCommand.MESSAGE_SUCCESS, 1), expectedModel, companyToView);
    }

    @Test
    public void execute_viewCompanyAtIndexExceedsListSize_exceptionThrown() {
        assertCompanyCommandFailure(new ViewCompanyCommand(Index.fromOneBased(99)), model,
                Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_viewCompanyAtIndexOutOfBounds_exceptionThrown() {
        int outOfBoundsIndex = model.getFilteredCompanyList().size() + 1;
        assertCompanyCommandFailure(new ViewCompanyCommand(Index.fromOneBased(outOfBoundsIndex)), model,
                Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_viewCompanyCommandWithNullModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ViewCompanyCommand(Index.fromOneBased(1)).execute(null));
    }

    @Test
    public void execute_viewCompanyCommandWithNullTargetIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ViewCompanyCommand(null).execute(model));
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        ViewCompanyCommand viewCompanyCommand = new ViewCompanyCommand(Index.fromOneBased(1));
        assertTrue(viewCompanyCommand.equals(viewCompanyCommand));
    }

    @Test
    public void equals_nullObject_returnsFalse() {
        ViewCompanyCommand viewCompanyCommand = new ViewCompanyCommand(Index.fromOneBased(1));
        assertFalse(viewCompanyCommand.equals(null));
    }

    @Test
    public void equals_differentTargetIndex_returnsFalse() {
        ViewCompanyCommand viewCompanyCommand1 = new ViewCompanyCommand(Index.fromOneBased(1));
        ViewCompanyCommand viewCompanyCommand2 = new ViewCompanyCommand(Index.fromOneBased(2));
        assertFalse(viewCompanyCommand1.equals(viewCompanyCommand2));
    }

    @Test
    public void equals_sameTargetIndex_returnsTrue() {
        ViewCompanyCommand viewCompanyCommand1 = new ViewCompanyCommand(Index.fromOneBased(1));
        ViewCompanyCommand viewCompanyCommand2 = new ViewCompanyCommand(Index.fromOneBased(1));
        assertTrue(viewCompanyCommand1.equals(viewCompanyCommand2));
    }

}

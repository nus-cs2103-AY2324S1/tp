package connectify.logic.commands;

import static connectify.logic.commands.CommandTestUtil.assertCommandSuccess;
import static connectify.logic.commands.CommandTestUtil.showCompanyAtIndex;
import static connectify.testutil.TypicalIndexes.INDEX_FIRST_COMPANY;
import static connectify.testutil.TypicalPersons.getTypicalAddressBook;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import connectify.commons.core.index.Index;
import connectify.logic.Messages;
import connectify.model.Model;
import connectify.model.ModelManager;
import connectify.model.UserPrefs;
import connectify.model.company.Company;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ShareCompanyCommand.
 */
public class ShareCompanyCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_validIndexUnfiltered_showsShareSuccessMessage() {
        Index index = INDEX_FIRST_COMPANY;
        Company companyToShare = model.getFilteredCompanyList().get(index.getZeroBased());

        ShareCompanyCommand shareCommand = new ShareCompanyCommand(index);
        String expectedMessage = String.format(ShareCompanyCommand.MESSAGE_SHARE_COMPANY_SUCCESS,
                ShareCompanyCommand.formatInput(Messages.format(companyToShare)));

        assertCommandSuccess(shareCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfiltered_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCompanyList().size() + 1);
        ShareCompanyCommand shareCommand = new ShareCompanyCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(shareCommand, model,
                Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFiltered_showsShareSuccessMessage() {
        showCompanyAtIndex(model, INDEX_FIRST_COMPANY);

        Index index = INDEX_FIRST_COMPANY;
        Company companyToShare = model.getFilteredCompanyList().get(index.getZeroBased());

        ShareCompanyCommand shareCommand = new ShareCompanyCommand(index);
        String expectedMessage = String.format(ShareCompanyCommand.MESSAGE_SHARE_COMPANY_SUCCESS,
                ShareCompanyCommand.formatInput(Messages.format(companyToShare)));

        assertCommandSuccess(shareCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFiltered_throwsCommandException() {
        showCompanyAtIndex(model, INDEX_FIRST_COMPANY);

        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredCompanyList().size() + 1);
        ShareCompanyCommand shareCommand = new ShareCompanyCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(shareCommand, model,
                Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        ShareCompanyCommand shareCommand1 = new ShareCompanyCommand(INDEX_FIRST_COMPANY);
        ShareCompanyCommand shareCommand2 = new ShareCompanyCommand(INDEX_FIRST_COMPANY);

        // same object -> returns true
        assertTrue(shareCommand1.equals(shareCommand1));

        // same values -> returns true
        assertTrue(shareCommand1.equals(shareCommand2));

        // different types -> returns false
        assertFalse(shareCommand1.equals(1));

        // null -> returns false
        assertFalse(shareCommand1.equals(null));

        // different index -> returns false
        ShareCompanyCommand differentCommand = new ShareCompanyCommand(Index.fromOneBased(2));
        assertFalse(shareCommand1.equals(differentCommand));
    }
}

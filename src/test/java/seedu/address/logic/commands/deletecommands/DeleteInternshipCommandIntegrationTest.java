package seedu.address.logic.commands.deletecommands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalCompanies.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON_INTERNSHIP_OR_COMPANY;

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
import seedu.address.model.company.internship.InternshipName;
import seedu.address.testutil.CompanyBuilder;

public class DeleteInternshipCommandIntegrationTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_deleteInternshipCompanyNoMatchedInternships_failure() {
        DeleteInternshipCommand deleteInternshipCommand = new DeleteInternshipCommand(
                INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY, Index.fromZeroBased(10)
        );
        CommandTestUtil.assertInternshipCommandFailure(
                deleteInternshipCommand, model,
                Messages.MESSAGE_INVALID_INTERNSHIP_DISPLAYED_INDEX);
    }

    @Test
    public void execute_deleteInternshipCompanyWithMatchedInternships_success() {
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        List<Company> lastShownList = expectedModel.getFilteredCompanyList();
        Company targetCompany =
                lastShownList.get(INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY.getZeroBased());

        // Make copies of the targetCompany at the index 2.
        Company companyToDeleteFrom = new CompanyBuilder(targetCompany).build();
        Company updatedCompany = new CompanyBuilder(targetCompany).build();
        updatedCompany.removeInternship(updatedCompany.getInternshipAtIndex(INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY));

        // Set to copy of the company, so that it does not affect the actual company.
        model.setCompany(targetCompany, companyToDeleteFrom);
        expectedModel.setCompany(companyToDeleteFrom, updatedCompany);

        DeleteInternshipCommand deleteInternshipCommand = new DeleteInternshipCommand(
                INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY, Index.fromZeroBased(0)
        );

        CommandTestUtil.assertDisplayableCommandSuccess(
                deleteInternshipCommand, model, String.format(DeleteInternshipCommand.MESSAGE_SUCCESS, 1,
                        updatedCompany.getCompanyName()),
                expectedModel, updatedCompany);
    }

    @Test
    public void execute_deleteInternshipCompanyOutOfIndexScope_failure() {
        DeleteInternshipCommand deleteInternshipCommand = new DeleteInternshipCommand(
                Index.fromZeroBased(100), Index.fromZeroBased(0)
        );
        CommandTestUtil.assertInternshipCommandFailure(
                deleteInternshipCommand, model,
                Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_deleteInternshipWithNullModel_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteInternshipCommand(
                        Index.fromOneBased(1),
                        Index.fromZeroBased(0)).execute(null));
    }

    @Test
    public void execute_deleteInternshipWithNullIndex_throwsNullPointerException() {
        InternshipName internshipName = new InternshipName("Software Engineer");
        assertThrows(NullPointerException.class, () -> new DeleteInternshipCommand(
                        null,
                        Index.fromZeroBased(0)).execute(model));
    }

    @Test
    public void execute_deleteInternshipWithNullInternshipName_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteInternshipCommand(
                        Index.fromOneBased(1),
                        null).execute(model));
    }

    @Test
    public void equals() {
        DeleteInternshipCommand deleteFirstCommand = new DeleteInternshipCommand(
                INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY, Index.fromZeroBased(0)
        );
        DeleteInternshipCommand deleteSecondCommand = new DeleteInternshipCommand(
                INDEX_SECOND_PERSON_INTERNSHIP_OR_COMPANY, Index.fromZeroBased(0)
        );
        DeleteInternshipCommand anotherDeleteFirstCommand = new DeleteInternshipCommand(
                INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY, Index.fromZeroBased(1)
        );

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));
        assertTrue(deleteSecondCommand.equals(deleteSecondCommand));

        // same values -> returns true
        DeleteInternshipCommand deleteFirstCommandCopy = new DeleteInternshipCommand(
                INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY, Index.fromZeroBased(0)
        );
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different Company -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));

        // different internship index for same company -> returns False
        assertFalse(deleteFirstCommand.equals(anotherDeleteFirstCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetCompanyIndex = Index.fromZeroBased(0);
        Index targetInternshipIndex = Index.fromZeroBased(0);
        DeleteInternshipCommand deleteInternshipCommand = new DeleteInternshipCommand(
                targetCompanyIndex, targetInternshipIndex
        );
        String expected = DeleteInternshipCommand.class.getCanonicalName()
                + "{targetCompanyIndex=" + targetCompanyIndex + ", "
                + "targetInternshipIndex=" + targetInternshipIndex + "}";
        assertEquals(expected, deleteInternshipCommand.toString());
    }
}

package seedu.address.logic.commands.clearcommands;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalCompanies.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY;

import java.util.ArrayList;
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
import seedu.address.model.company.internship.Internship;
import seedu.address.testutil.CompanyBuilder;

public class ClearInternshipCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_clearInternshipCompany_success() {
        ClearInternshipCommand clearInternshipCommand =
                new ClearInternshipCommand(INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY);

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        List<Company> lastShownList = expectedModel.getFilteredCompanyList();
        Company targetCompany = lastShownList.get(INDEX_FIRST_PERSON_INTERNSHIP_OR_COMPANY.getZeroBased());
        List<Internship> internshipsToKeep = new ArrayList<>();
        Company updatedCompany = new CompanyBuilder(targetCompany).withInternships(internshipsToKeep).build();
        expectedModel.setCompany(targetCompany, updatedCompany);

        CommandTestUtil.assertRegularCommandSuccess(clearInternshipCommand, model,
                String.format(clearInternshipCommand.MESSAGE_SUCCESS, targetCompany.getCompanyName()), expectedModel
        );
    }

    @Test
    public void execute_clearInternshipCompanyOutOfIndexScope_failure() {
        ClearInternshipCommand clearInternshipCommand = new ClearInternshipCommand(
                Index.fromZeroBased(100)
        );
        CommandTestUtil.assertInternshipCommandFailure(
                clearInternshipCommand, model,
                Messages.MESSAGE_INVALID_COMPANY_DISPLAYED_INDEX);
    }

    @Test
    public void execute_clearInternshipWithNullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClearInternshipCommand(
                null).execute(model));
    }
}

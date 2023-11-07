package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalApplicants.getTypicalAddressBookWithApplicants;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Applicant;
import seedu.address.testutil.ApplicantBuilder;

public class AddApplicantCommandIntegrationTest {
    private Model model;
    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBookWithApplicants(), new UserPrefs());
    }

    @Test
    public void execute_newApplicant_success() {
        Applicant validApplicant = new ApplicantBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addApplicant(validApplicant);

        assertCommandSuccess(new AddApplicantCommand(validApplicant), model,
                String.format(AddApplicantCommand.MESSAGE_SUCCESS, Messages.format(validApplicant)),
                expectedModel);
    }

    @Test
    public void execute_duplicateApplicant_throwsCommandException() {
        Applicant applicantInList = model.getAddressBook().getApplicantList().get(0);
        assertCommandFailure(new AddApplicantCommand(applicantInList), model,
                AddApplicantCommand.MESSAGE_DUPLICATE_APPLICANT);
    }
}

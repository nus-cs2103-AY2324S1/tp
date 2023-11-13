package seedu.staffsnap.logic.commands;

import static seedu.staffsnap.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.staffsnap.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.staffsnap.testutil.TypicalApplicants.getTypicalApplicantBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.staffsnap.logic.Messages;
import seedu.staffsnap.model.Model;
import seedu.staffsnap.model.ModelManager;
import seedu.staffsnap.model.UserPrefs;
import seedu.staffsnap.model.applicant.Applicant;
import seedu.staffsnap.testutil.ApplicantBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalApplicantBook(), new UserPrefs());
    }

    @Test
    public void execute_newApplicant_success() {
        Applicant validApplicant = new ApplicantBuilder().build();

        Model expectedModel = new ModelManager(model.getApplicantBook(), new UserPrefs());
        expectedModel.addApplicant(validApplicant);

        assertCommandSuccess(new AddCommand(validApplicant), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validApplicant)),
                expectedModel);
    }

    @Test
    public void execute_duplicateApplicant_throwsCommandException() {
        Applicant applicantInList = model.getApplicantBook().getApplicantList().get(0);
        assertCommandFailure(new AddCommand(applicantInList), model,
                AddCommand.MESSAGE_DUPLICATE_APPLICANT);
    }

}

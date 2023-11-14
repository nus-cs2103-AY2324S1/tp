package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.applicant.Applicant;
import seedu.address.testutil.ApplicantBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newApplicant_success() {
        Applicant validApplicant = new ApplicantBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addApplicant(validApplicant);

        assertCommandSuccess(new AddCommand(validApplicant), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.formatApplicant(validApplicant)),
                expectedModel);
    }

    @Test
    public void execute_duplicateApplicant_throwsCommandException() {
        Applicant applicantInList = model.getAddressBook().getApplicantList().get(0);
        assertCommandFailure(new AddCommand(applicantInList), model,
                AddCommand.MESSAGE_DUPLICATE_APPLICANT);
    }

}

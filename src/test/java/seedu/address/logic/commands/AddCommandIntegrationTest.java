package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalJobs.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.job.Job;
import seedu.address.testutil.JobBuilder;

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
    public void execute_newJob_success() {
        Job validJob = new JobBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addJob(validJob);

        assertCommandSuccess(new AddCommand(validJob), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validJob)),
                expectedModel);
    }

    @Test
    public void execute_duplicateJob_throwsCommandException() {
        Job jobInList = model.getAddressBook().getJobList().get(0);
        assertCommandFailure(new AddCommand(jobInList), model,
                AddCommand.MESSAGE_DUPLICATE_JOB);
    }

}

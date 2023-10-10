package seedu.application.logic.commands;

import static seedu.application.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.application.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.application.testutil.TypicalJobs.getTypicalApplicationBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.application.logic.Messages;
import seedu.application.model.Model;
import seedu.application.model.ModelManager;
import seedu.application.model.UserPrefs;
import seedu.application.model.job.Job;
import seedu.application.testutil.JobBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalApplicationBook(), new UserPrefs());
    }

    @Test
    public void execute_newJob_success() {
        Job validJob = new JobBuilder().build();

        Model expectedModel = new ModelManager(model.getApplicationBook(), new UserPrefs());
        expectedModel.addJob(validJob);

        assertCommandSuccess(new AddCommand(validJob), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validJob)),
                expectedModel);
    }

    @Test
    public void execute_duplicateJob_throwsCommandException() {
        Job jobInList = model.getApplicationBook().getJobList().get(0);
        assertCommandFailure(new AddCommand(jobInList), model,
                AddCommand.MESSAGE_DUPLICATE_JOB);
    }

}

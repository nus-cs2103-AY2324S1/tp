package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalInterviews.STANDARD_INTERVIEW;
import static seedu.address.testutil.TypicalInterviews.STANDARD_INTERVIEW_2;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.interview.Interview;

/**
 * Contains integration tests (interaction with the Model) for {@code AddInterviewCommand}.
 * Adapted from AB3 AddCommandIntegrationTest
 */
public class AddInterviewCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_twoNewInterview_success() {
        Interview validInterview = STANDARD_INTERVIEW;
        Interview validInterview2 = STANDARD_INTERVIEW_2;

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addInterview(validInterview);

        assertCommandSuccess(new AddInterviewCommand(validInterview), model,
                String.format(AddInterviewCommand.MESSAGE_SUCCESS, Messages.formatInterview(validInterview)),
                expectedModel);

        expectedModel.addInterview(validInterview2);
        assertCommandSuccess(new AddInterviewCommand(validInterview2), model,
                String.format(AddInterviewCommand.MESSAGE_SUCCESS, Messages.formatInterview(validInterview2)),
                expectedModel);
    }

    @Test
    public void execute_duplicateInterview_throwsCommandException() {
        assert(!model.getAddressBook().getInterviewList().isEmpty());
        Interview interviewInList = model.getAddressBook().getInterviewList().get(0);
        assertCommandFailure(new AddInterviewCommand(interviewInList), model,
                AddInterviewCommand.MESSAGE_DUPLICATE_INTERVIEW);
    }

}

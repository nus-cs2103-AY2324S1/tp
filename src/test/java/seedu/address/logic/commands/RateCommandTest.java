package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.interview.Interview;
import seedu.address.model.interview.Rating;

//@@author jonyxzx
public class RateCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Rating testRating = new Rating("2.0");

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Interview interviewToRate = model.getFilteredInterviewList().get(INDEX_THIRD.getZeroBased());
        RateCommand rateCommand = new RateCommand(INDEX_THIRD, testRating);

        String expectedMessage = String.format(RateCommand.MESSAGE_RATE_INTERVIEW_SUCCESS,
                Messages.formatInterview(interviewToRate));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        Interview ratedInterview = new Interview(
                interviewToRate.getInterviewApplicant(),
                interviewToRate.getJobRole(),
                testRating,
                interviewToRate.getInterviewStartTime(),
                interviewToRate.getInterviewEndTime(),
                interviewToRate.isDone());
        expectedModel.setInterview(interviewToRate, ratedInterview);

        assertCommandSuccess(rateCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredApplicantList().size() + 1);
        RateCommand rateCommand = new RateCommand(outOfBoundIndex, testRating);

        assertCommandFailure(rateCommand, model, Messages.MESSAGE_INVALID_INTERVIEW_DISPLAYED_INDEX);
    }

    @Test
    public void execute_interviewNotYetDone_throwsCommandException() {
        Interview interviewToRate = model.getFilteredInterviewList().get(INDEX_FIRST.getZeroBased());
        RateCommand rateCommand = new RateCommand(INDEX_FIRST, testRating);

        String expectedMessage = String.format(RateCommand.MESSAGE_NOT_DONE,
                Messages.formatInterview(interviewToRate));

        assertCommandFailure(rateCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        RateCommand rateFirstCommand = new RateCommand(INDEX_FIRST, testRating);
        RateCommand rateSecondCommand = new RateCommand(INDEX_SECOND, testRating);

        // same object -> returns true
        assertTrue(rateFirstCommand.equals(rateFirstCommand));

        // same values -> returns true
        RateCommand rateFirstCommandCopy = new RateCommand(INDEX_FIRST, testRating);
        assertTrue(rateFirstCommand.equals(rateFirstCommandCopy));

        // different types -> returns false
        assertFalse(rateFirstCommand.equals(1));

        // null -> returns false
        assertFalse(rateFirstCommand.equals(null));

        // different applicant -> returns false
        assertFalse(rateFirstCommand.equals(rateSecondCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        Rating newRating = new Rating("1.0");
        RateCommand rateCommand = new RateCommand(targetIndex, newRating);
        String expected = RateCommand.class.getCanonicalName()
                + "{targetIndex=" + targetIndex + ", "
                + "newRating=" + newRating + "}";
        assertEquals(expected, rateCommand.toString());
    }
}

package seedu.staffsnap.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.staffsnap.testutil.TypicalApplicants.getUnsortedApplicantBook;

import org.junit.jupiter.api.Test;

import seedu.staffsnap.commons.core.index.Index;
import seedu.staffsnap.logic.commands.exceptions.CommandException;
import seedu.staffsnap.model.Model;
import seedu.staffsnap.model.ModelManager;
import seedu.staffsnap.model.UserPrefs;
import seedu.staffsnap.model.applicant.Status;



class StatusCommandTest {

    @Test
    void execute_setStatus_offered() {
        Model model = new ModelManager(getUnsortedApplicantBook(), new UserPrefs());
        try {
            new StatusCommand(Index.fromOneBased(1), Status.OFFERED).execute(model);
        } catch (CommandException e) {
            throw new RuntimeException(e);
        }
        assertTrue(model.getFilteredApplicantList().get(0).getStatus() == Status.OFFERED);
    }

    @Test
    void execute_setStatus_rejected() {
        Model model = new ModelManager(getUnsortedApplicantBook(), new UserPrefs());
        try {
            new StatusCommand(Index.fromOneBased(1), Status.REJECTED).execute(model);
        } catch (CommandException e) {
            throw new RuntimeException(e);
        }
        assertTrue(model.getFilteredApplicantList().get(0).getStatus() == Status.REJECTED);
    }

    @Test
    void execute_testEquals_sameParams() {
        StatusCommand command1 = new StatusCommand(Index.fromOneBased(1), Status.OFFERED);
        StatusCommand command2 = new StatusCommand(Index.fromOneBased(1), Status.OFFERED);
        assertTrue(command1.equals(command2));
    }

    @Test
    void execute_testEquals_differentParams() {
        StatusCommand command1 = new StatusCommand(Index.fromOneBased(1), Status.OFFERED);
        StatusCommand command2 = new StatusCommand(Index.fromOneBased(2), Status.REJECTED);
        assertFalse(command1.equals(command2));
    }

    @Test
    void testToString() {
        StatusCommand command = new StatusCommand(Index.fromOneBased(1), Status.OFFERED);
        System.out.println(command);
        assertTrue(command.toString().equals(
                "StatusCommand{index=seedu.staffsnap.commons.core.index.Index{zeroBasedIndex=0}, status=OFFERED}"));
    }
}

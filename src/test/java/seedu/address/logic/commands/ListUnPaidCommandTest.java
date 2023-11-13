package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.PaidPredicate;

public class ListUnPaidCommandTest {

    @Test
    public void equals() {
        PaidPredicate firstPredicate = new PaidPredicate(false);
        PaidPredicate secondPredicate = new PaidPredicate(true);

        ListUnPaidCommand listUnPaidFirstCommand = new ListUnPaidCommand(firstPredicate);
        ListUnPaidCommand listUnPaidSecondCommand = new ListUnPaidCommand(secondPredicate);

        // same object -> returns true
        assertTrue(listUnPaidFirstCommand.equals(listUnPaidFirstCommand));

        // same values -> returns true
        ListUnPaidCommand listUnPaidFirstCommandCopy = new ListUnPaidCommand(firstPredicate);
        assertTrue(listUnPaidFirstCommand.equals(listUnPaidFirstCommandCopy));

        // different types -> returns false
        assertFalse(listUnPaidFirstCommand.equals(1));

        // null -> returns false
        assertFalse(listUnPaidFirstCommand.equals(null));

        // different command -> returns false
        assertFalse(listUnPaidFirstCommand.equals(listUnPaidSecondCommand));
    }
}

package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalIndexes;
import seedu.address.testutil.TypicalPersons;

public class UnflagCommandTest {
    private Model model =
            new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_outOfBoundsIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBookingList().size() + 1);
        UnflagCommand unflagCommand = new UnflagCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(unflagCommand, model, Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredLIst_throwsCommandException() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_PERSON;

        assertTrue(outOfBoundIndex.getZeroBased() < model.getBookingsBook().getPersonList().size());

        UnflagCommand unflagCommand = new UnflagCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(unflagCommand, model, Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX);

    }

    @Test
    public void execute_flagAFlaggedBooking_throwsCommandException() {
        UnflagCommand unflagCommand = new UnflagCommand(TypicalIndexes.INDEX_SECOND_PERSON);
        CommandTestUtil.assertCommandFailure(unflagCommand, model, UnflagCommand.MESSAGE_NOT_FLAGGED);
    }

    @Test
    public void equals() {
        UnflagCommand unflagCommandOne = new UnflagCommand(TypicalIndexes.INDEX_FIRST_PERSON);
        UnflagCommand unflagCommandTwo = new UnflagCommand(TypicalIndexes.INDEX_SECOND_PERSON);

        assertEquals(unflagCommandOne, unflagCommandOne);

        assertNotEquals(unflagCommandOne, unflagCommandTwo);

        UnflagCommand unflagCommandOneDuplicate = new UnflagCommand(TypicalIndexes.INDEX_FIRST_PERSON);
        assertEquals(unflagCommandOne, unflagCommandOneDuplicate);

        assertNotEquals(1, unflagCommandOne);

        assertNotEquals(null, unflagCommandOne);
    }
}


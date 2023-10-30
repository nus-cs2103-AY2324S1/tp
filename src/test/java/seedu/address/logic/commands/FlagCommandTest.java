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

public class FlagCommandTest {
    private Model model =
            new ModelManager(TypicalPersons.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_outOfBoundsIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredBookingList().size() + 1);
        FlagCommand flagCommand = new FlagCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(flagCommand, model, Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexFilteredLIst_throwsCommandException() {
        CommandTestUtil.showPersonAtIndex(model, TypicalIndexes.INDEX_FIRST_PERSON);

        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_PERSON;

        assertTrue(outOfBoundIndex.getZeroBased() < model.getBookingsBook().getPersonList().size());

        FlagCommand flagCommand = new FlagCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(flagCommand, model, Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX);

    }

    @Test
    public void execute_flagAFlaggedBooking_throwsCommandException() {
        FlagCommand flagCommand = new FlagCommand(TypicalIndexes.INDEX_FIRST_PERSON);
        CommandTestUtil.assertCommandFailure(flagCommand, model, FlagCommand.MESSAGE_ALREADY_FLAGGED);
    }

    @Test
    public void equals() {
        FlagCommand flagCommandOne = new FlagCommand(TypicalIndexes.INDEX_FIRST_PERSON);
        FlagCommand flagCommandTwo = new FlagCommand(TypicalIndexes.INDEX_SECOND_PERSON);

        assertEquals(flagCommandOne, flagCommandOne);

        assertNotEquals(flagCommandOne, flagCommandTwo);

        FlagCommand flagCommandOneDuplicate = new FlagCommand(TypicalIndexes.INDEX_FIRST_PERSON);
        assertEquals(flagCommandOne, flagCommandOneDuplicate);

        assertNotEquals(1, flagCommandOne);

        assertNotEquals(null, flagCommandOne);
    }
}

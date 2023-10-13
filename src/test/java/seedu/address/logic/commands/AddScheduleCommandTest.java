package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_END_TIME_TWO;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_ONE;
import static seedu.address.logic.commands.CommandTestUtil.VALID_START_TIME_TWO;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import seedu.address.commons.core.index.Index;
import seedu.address.model.schedule.EndTime;
import seedu.address.model.schedule.StartTime;

class AddScheduleCommandTest {

    @Test
    void execute() {
    }

    @Test
    public void equals() {
        final StartTime standardStartTime = new StartTime(LocalDateTime.parse(VALID_START_TIME_ONE));
        final EndTime standardEndTime = new EndTime(LocalDateTime.parse(VALID_END_TIME_ONE));
        final AddScheduleCommand standardCommand = new AddScheduleCommand(INDEX_FIRST_PERSON, standardStartTime,
                standardEndTime);

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // same values -> returns true
        StartTime copyStartTime = new StartTime(LocalDateTime.parse(VALID_START_TIME_ONE));
        EndTime copyEndTime = new EndTime(LocalDateTime.parse(VALID_END_TIME_ONE));
        AddScheduleCommand addScheduleCommandCopy =
                new AddScheduleCommand(INDEX_FIRST_PERSON, copyStartTime, copyEndTime);
        assertTrue(standardCommand.equals(addScheduleCommandCopy));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different start time -> returns false
        StartTime differentStartTime = new StartTime(LocalDateTime.parse(VALID_START_TIME_TWO));
        assertFalse(standardCommand.equals(new AddScheduleCommand(INDEX_FIRST_PERSON, differentStartTime, standardEndTime)));

        // different end time -> returns false
        EndTime differentEndTime = new EndTime(LocalDateTime.parse(VALID_END_TIME_TWO));
        assertFalse(standardCommand.equals(new AddScheduleCommand(INDEX_FIRST_PERSON, standardStartTime, differentEndTime)));
    }

    @Test
    public void toStringMethod() {
        Index index = Index.fromOneBased(1);
        StartTime startTime = new StartTime(LocalDateTime.parse(VALID_START_TIME_ONE));
        EndTime endTime = new EndTime(LocalDateTime.parse(VALID_END_TIME_ONE));
        AddScheduleCommand addScheduleCommand = new AddScheduleCommand(index, startTime, endTime);

        String expected = AddScheduleCommand.class.getCanonicalName() + "{index=" + index + ", startTime="
                + startTime +", endTime="
                + endTime + "}";
        assertEquals(expected, addScheduleCommand.toString());
    }
}
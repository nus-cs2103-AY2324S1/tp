package seedu.address.model.employee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class LeaveListTest {
    private ArrayList validList = new ArrayList<>(
            List.of(new Leave(LocalDate.parse("2023-11-11", DateTimeFormatter.ISO_LOCAL_DATE))));

    @Test
    public void equals() {
        LeaveList emptyLeaveList = new LeaveList();
        LeaveList nonEmptyLeaveList = new LeaveList(validList);

        // same values -> returns true
        assertTrue(emptyLeaveList.equals(new LeaveList()));

        // same object -> returns true
        assertTrue(emptyLeaveList.equals(emptyLeaveList));

        // null -> returns false
        assertFalse(emptyLeaveList.equals(null));

        // different types -> returns false
        assertFalse(emptyLeaveList.equals(5.0f));

        // different values -> returns false
        assertFalse(emptyLeaveList.equals(nonEmptyLeaveList));
    }

    @Test
    public void toStringMethod() {
        LeaveList leaveList = new LeaveList(validList);
        String expectedString = "1. " + leaveList.getLeave(0) + "\n";
        String actualString = leaveList.toString();
        assertTrue(actualString.equals(expectedString));
    }
}

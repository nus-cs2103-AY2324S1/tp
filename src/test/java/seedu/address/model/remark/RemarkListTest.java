package seedu.address.model.remark;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class RemarkListTest {
    private ArrayList validList = new ArrayList<>(List.of(new Remark("good worker")));

    @Test
    public void equals() {
        RemarkList emptyRemarkList = new RemarkList();
        RemarkList nonEmptyRemarkList = new RemarkList(validList);

        // same values -> returns true
        assertTrue(emptyRemarkList.equals(new RemarkList()));

        // same object -> returns true
        assertTrue(emptyRemarkList.equals(emptyRemarkList));

        // null -> returns false
        assertFalse(emptyRemarkList.equals(null));

        // different types -> returns false
        assertFalse(emptyRemarkList.equals(5.0f));

        // different values -> returns false
        assertFalse(emptyRemarkList.equals(nonEmptyRemarkList));
    }

    @Test
    public void toStringMethod() {
        RemarkList remarkList = new RemarkList(validList);
        String expectedString = "1. " + remarkList.getRemark(0) + "\n";
        String actualString = remarkList.toString();
        assertTrue(actualString.equals(expectedString));
    }
}

package seedu.address.model.attendance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_BOXING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HOURS_CLIMBING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_LOCATION_BOXING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOXING;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_CLIMBING;
import static seedu.address.testutil.TypicalAttendances.ALICE_AURORA;
import static seedu.address.testutil.TypicalAttendances.BENSON_BOXING;
import static seedu.address.testutil.TypicalEvents.AURORA_BOREALIS;
import static seedu.address.testutil.TypicalEvents.BOXING_DAY;

import org.junit.jupiter.api.Test;

import seedu.address.model.event.Event;
import seedu.address.testutil.AttendanceBuilder;
import seedu.address.testutil.EventBuilder;

public class AttendanceTest {

    @Test
    public void isSameEvent() {
        // same object -> returns true
        assertTrue(AURORA_BOREALIS.isSameEvent(AURORA_BOREALIS));

        // null -> returns false
        assertFalse(AURORA_BOREALIS.isSameEvent(null));

        // same name, all other attributes different -> returns false
        Event editedAurora = new EventBuilder(AURORA_BOREALIS).withDate(VALID_DATE_BOXING)
                .withLocation(VALID_LOCATION_BOXING).build();
        assertFalse(AURORA_BOREALIS.isSameEvent(editedAurora));

        // different name, all other attributes same -> returns false
        editedAurora = new EventBuilder(AURORA_BOREALIS).withName(VALID_NAME_BOXING).build();
        assertFalse(AURORA_BOREALIS.isSameEvent(editedAurora));

        // name differs in case, all other attributes same -> returns false
        Event editedBoxing = new EventBuilder(BOXING_DAY).withName(VALID_NAME_BOXING.toLowerCase()).build();
        assertFalse(BOXING_DAY.isSameEvent(editedBoxing));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOXING + " ";
        editedBoxing = new EventBuilder(BOXING_DAY).withName(nameWithTrailingSpaces).build();
        assertFalse(BOXING_DAY.isSameEvent(editedBoxing));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Attendance aliceAuroraCopy = new AttendanceBuilder(ALICE_AURORA).build();
        assertTrue(aliceAuroraCopy.equals(ALICE_AURORA));

        // same object -> returns true
        assertTrue(ALICE_AURORA.equals(ALICE_AURORA));

        // null -> returns false
        assertFalse(ALICE_AURORA.equals(null));

        // different type -> returns false
        assertFalse(ALICE_AURORA.equals(5));

        // different attendance -> returns false
        assertFalse(ALICE_AURORA.equals(BENSON_BOXING));

        // different member name -> returns false
        Attendance editedMemberName = new AttendanceBuilder(ALICE_AURORA).withMemberName(VALID_NAME_AMY).build();
        assertFalse(ALICE_AURORA.equals(editedMemberName));

        // different event name -> returns false
        Attendance editedEventName = new AttendanceBuilder(ALICE_AURORA).withEventName(VALID_NAME_BOXING).build();
        assertFalse(ALICE_AURORA.equals(editedEventName));

        // different hours -> returns false
        Attendance editedHours = new AttendanceBuilder(ALICE_AURORA).withHours(VALID_HOURS_CLIMBING).build();
        assertFalse(ALICE_AURORA.equals(editedHours));

        // different remark -> returns false
        Attendance editedRemark = new AttendanceBuilder(ALICE_AURORA).withRemark(VALID_REMARK_CLIMBING).build();
        assertFalse(ALICE_AURORA.equals(editedRemark));

    }
    @Test
    public void test_equalObjectsHaveSameHashCode() {
        Attendance auroraCopy1 = new AttendanceBuilder(ALICE_AURORA).build();
        Attendance auroraCopy2 = new AttendanceBuilder(ALICE_AURORA).build();
        assertEquals(auroraCopy1.hashCode(), auroraCopy1.hashCode());
        assertEquals(auroraCopy1.hashCode(), auroraCopy2.hashCode());
    }
    @Test
    public void toStringMethod() {
        String expected = Attendance.class.getCanonicalName() + "{member name=" + ALICE_AURORA.getMemberName()
                + ", event name=" + ALICE_AURORA.getEventName() + ", hours=" + ALICE_AURORA.getHours()
                + ", remark=" + ALICE_AURORA.getRemark() + "}";
        assertEquals(expected, ALICE_AURORA.toString());
    }
}

package seedu.ccacommander.model.enrolment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.ccacommander.logic.commands.CommandTestUtil.VALID_HOURS_AURORA;
import static seedu.ccacommander.logic.commands.CommandTestUtil.VALID_HOURS_BOXING;
import static seedu.ccacommander.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.ccacommander.logic.commands.CommandTestUtil.VALID_NAME_AURORA;
import static seedu.ccacommander.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.ccacommander.logic.commands.CommandTestUtil.VALID_NAME_BOXING;
import static seedu.ccacommander.logic.commands.CommandTestUtil.VALID_REMARK_AURORA;
import static seedu.ccacommander.logic.commands.CommandTestUtil.VALID_REMARK_BOXING;
import static seedu.ccacommander.testutil.TypicalEnrolments.ALICE_AURORA;
import static seedu.ccacommander.testutil.TypicalEnrolments.BENSON_BOXING;

import org.junit.jupiter.api.Test;

import seedu.ccacommander.testutil.EnrolmentBuilder;

public class EnrolmentTest {

    @Test
    public void isSameEnrolment() {
        // same object -> returns true
        assertTrue(ALICE_AURORA.isSameEnrolment(ALICE_AURORA));

        // null -> returns false
        assertFalse(ALICE_AURORA.isSameEnrolment(null));

        // same name, all other attributes different -> returns true
        Enrolment editedEnrolment = new EnrolmentBuilder(ALICE_AURORA).withHours(VALID_HOURS_AURORA)
                .withRemark(VALID_REMARK_AURORA).build();
        assertTrue(ALICE_AURORA.isSameEnrolment(editedEnrolment));

        // different name, all other attributes same -> returns false
        editedEnrolment = new EnrolmentBuilder(ALICE_AURORA).withEventName(VALID_NAME_BOXING)
                .withMemberName(VALID_NAME_BOB).build();
        assertFalse(ALICE_AURORA.isSameEnrolment(editedEnrolment));

        // name differs in case, all other attributes same -> returns false
        editedEnrolment = new EnrolmentBuilder(ALICE_AURORA).withEventName(VALID_NAME_AURORA.toLowerCase())
                .build();
        assertFalse(ALICE_AURORA.isSameEnrolment(editedEnrolment));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_AURORA + " ";
        editedEnrolment = new EnrolmentBuilder(ALICE_AURORA).withEventName(nameWithTrailingSpaces)
                .build();
        assertFalse(ALICE_AURORA.isSameEnrolment(editedEnrolment));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Enrolment aliceAuroraCopy = new EnrolmentBuilder(ALICE_AURORA).build();
        assertTrue(aliceAuroraCopy.equals(ALICE_AURORA));

        // same object -> returns true
        assertTrue(ALICE_AURORA.equals(ALICE_AURORA));

        // null -> returns false
        assertFalse(ALICE_AURORA.equals(null));

        // different type -> returns false
        assertFalse(ALICE_AURORA.equals(5));

        // different enrolment -> returns false
        assertFalse(ALICE_AURORA.equals(BENSON_BOXING));

        // different member name -> returns false
        Enrolment editedMemberName = new EnrolmentBuilder(ALICE_AURORA).withMemberName(VALID_NAME_AMY).build();
        assertFalse(ALICE_AURORA.equals(editedMemberName));

        // different event name -> returns false
        Enrolment editedEventName = new EnrolmentBuilder(ALICE_AURORA).withEventName(VALID_NAME_BOXING).build();
        assertFalse(ALICE_AURORA.equals(editedEventName));

        // different hours -> returns false
        Enrolment editedHours = new EnrolmentBuilder(ALICE_AURORA).withHours(VALID_HOURS_BOXING).build();
        assertFalse(ALICE_AURORA.equals(editedHours));

        // different remark -> returns false
        Enrolment editedRemark = new EnrolmentBuilder(ALICE_AURORA).withRemark(VALID_REMARK_BOXING).build();
        assertFalse(ALICE_AURORA.equals(editedRemark));

    }
    @Test
    public void test_equalObjectsHaveSameHashCode() {
        Enrolment auroraCopy1 = new EnrolmentBuilder(ALICE_AURORA).build();
        Enrolment auroraCopy2 = new EnrolmentBuilder(ALICE_AURORA).build();
        assertEquals(auroraCopy1.hashCode(), auroraCopy1.hashCode());
        assertEquals(auroraCopy1.hashCode(), auroraCopy2.hashCode());
    }
    @Test
    public void toStringMethod() {
        String expected = Enrolment.class.getCanonicalName() + "{member name=" + ALICE_AURORA.getMemberName()
                + ", event name=" + ALICE_AURORA.getEventName() + ", hours=" + ALICE_AURORA.getHours()
                + ", remark=" + ALICE_AURORA.getRemark() + "}";
        assertEquals(expected, ALICE_AURORA.toString());
    }
}

package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICATIONSTATUS_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REQUIREMENT_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STARTDATE_OPTIVER;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.JANESTREET;
import static seedu.address.testutil.TypicalInternships.OPTIVER;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.InternshipBuilder;

class InternshipTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Internship internship = new InternshipBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> internship.getRequirements().remove(0));
    }

    @Test
    public void isSameInternship() {
        // same object -> returns true
        assertTrue(JANESTREET.isSameInternship(JANESTREET));

        // null -> returns false
        assertFalse(JANESTREET.isSameInternship(null));

        // same companyName and same role, all other attributes different -> returns true
        Internship editedJaneStreet = new InternshipBuilder(JANESTREET)
                .withApplicationStatus(VALID_APPLICATIONSTATUS_OPTIVER)
                .withDeadline(VALID_DEADLINE_OPTIVER, VALID_STARTDATE_OPTIVER)
                .withStartDate(VALID_STARTDATE_OPTIVER)
                .withDuration(VALID_DURATION_OPTIVER).withRequirements(VALID_REQUIREMENT_OPTIVER)
                .build();
        assertTrue(JANESTREET.isSameInternship(editedJaneStreet));

        // different companyName, all other attributes same -> returns false
        editedJaneStreet = new InternshipBuilder(JANESTREET).withCompanyName(VALID_COMPANY_NAME_OPTIVER).build();
        assertFalse(JANESTREET.isSameInternship(editedJaneStreet));

        // companyName differs in case, all other attributes same -> returns true
        Internship editedOptiver = new InternshipBuilder(OPTIVER)
                .withCompanyName(VALID_COMPANY_NAME_OPTIVER.toLowerCase()).build();
        assertTrue(OPTIVER.isSameInternship(editedOptiver));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Internship janeStreetCopy = new InternshipBuilder(JANESTREET).build();
        assertTrue(JANESTREET.equals(janeStreetCopy));

        // same object -> returns true
        assertTrue(JANESTREET.equals(JANESTREET));

        // null -> returns false
        assertFalse(JANESTREET.equals(null));

        // different type -> returns false
        assertFalse(JANESTREET.equals(5));

        // different internship -> returns false
        assertFalse(JANESTREET.equals(OPTIVER));

        // different companyName -> returns false
        Internship editedJaneStreet = new InternshipBuilder(JANESTREET)
                .withCompanyName(VALID_COMPANY_NAME_OPTIVER).build();
        assertFalse(JANESTREET.equals(editedJaneStreet));

        // different role -> returns false
        editedJaneStreet = new InternshipBuilder(JANESTREET).withRole(VALID_ROLE_OPTIVER).build();
        assertFalse(JANESTREET.equals(editedJaneStreet));

        // different application status -> returns false
        editedJaneStreet = new InternshipBuilder(JANESTREET)
                .withApplicationStatus(VALID_APPLICATIONSTATUS_OPTIVER).build();
        assertFalse(JANESTREET.equals(editedJaneStreet));

        // different deadline -> returns false
        editedJaneStreet = new InternshipBuilder(JANESTREET)
                .withDeadline(VALID_DEADLINE_OPTIVER, VALID_STARTDATE_OPTIVER).build();
        assertFalse(JANESTREET.equals(editedJaneStreet));

        // different startDate -> returns false
        editedJaneStreet = new InternshipBuilder(JANESTREET).withStartDate(VALID_STARTDATE_OPTIVER).build();
        assertFalse(JANESTREET.equals(editedJaneStreet));

        // different duration -> returns false
        editedJaneStreet = new InternshipBuilder(JANESTREET).withDuration(VALID_DURATION_OPTIVER).build();
        assertFalse(JANESTREET.equals(editedJaneStreet));

        // same companyName, different role -> returns false
        editedJaneStreet = new InternshipBuilder(JANESTREET).withRole(VALID_ROLE_OPTIVER).build();
        assertFalse(JANESTREET.equals(editedJaneStreet));
    }

    @Test
    public void toStringMethod() {
        String expected = Internship.class.getCanonicalName() + "{Company Name=" + JANESTREET.getCompanyName()
                + ", Role=" + JANESTREET.getRole() + ", Application Status=" + JANESTREET.getApplicationStatus()
                + ", Deadline=" + JANESTREET.getDeadline() + ", Start Date=" + JANESTREET.getStartDate()
                + ", Duration=" + JANESTREET.getDuration() + ", Requirements=" + JANESTREET.getRequirements() + "}";
        assertEquals(expected, JANESTREET.toString());
    }

    @Test
    public void hashCode_sameInternship_sameHashCode() {
        Internship editedJaneStreet1 = new InternshipBuilder(JANESTREET).withCompanyName(VALID_COMPANY_NAME_OPTIVER)
                .build();
        Internship editedJaneStreet2 = new InternshipBuilder(JANESTREET).withCompanyName(VALID_COMPANY_NAME_OPTIVER)
                .build();
        assertEquals(editedJaneStreet1.hashCode(), editedJaneStreet2.hashCode());
    }
}

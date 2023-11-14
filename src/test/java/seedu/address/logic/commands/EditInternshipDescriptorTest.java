package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_JANESTREET;
import static seedu.address.logic.commands.CommandTestUtil.DESC_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_APPLICATIONSTATUS_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEADLINE_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DURATION_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REQUIREMENT_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_OPTIVER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STARTDATE_OPTIVER;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditInternshipDescriptorBuilder;

public class EditInternshipDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        ModifyCommand.EditInternshipDescriptor descriptorWithSameValues =
                new ModifyCommand.EditInternshipDescriptor(DESC_JANESTREET);
        assertTrue(DESC_JANESTREET.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_JANESTREET.equals(DESC_JANESTREET));

        // null -> returns false
        assertFalse(DESC_JANESTREET.equals(null));

        // different types -> returns false
        assertFalse(DESC_JANESTREET.equals(5));

        // different values -> returns false
        assertFalse(DESC_JANESTREET.equals(DESC_OPTIVER));

        // different company name -> returns false
        ModifyCommand.EditInternshipDescriptor editedJanestreet =
                new EditInternshipDescriptorBuilder(DESC_JANESTREET)
                        .withCompanyName(VALID_COMPANY_NAME_OPTIVER).build();
        assertFalse(DESC_JANESTREET.equals(editedJanestreet));

        // different role -> returns false
        editedJanestreet =
                new EditInternshipDescriptorBuilder(DESC_JANESTREET)
                        .withRole(VALID_ROLE_OPTIVER).build();
        assertFalse(DESC_JANESTREET.equals(editedJanestreet));

        // different application status -> returns false
        editedJanestreet =
                new EditInternshipDescriptorBuilder(DESC_JANESTREET)
                        .withApplicationStatus(VALID_APPLICATIONSTATUS_OPTIVER).build();
        assertFalse(DESC_JANESTREET.equals(editedJanestreet));

        // different deadline -> returns false
        editedJanestreet =
                new EditInternshipDescriptorBuilder(DESC_JANESTREET)
                        .withDeadline(VALID_DEADLINE_OPTIVER, VALID_STARTDATE_OPTIVER).build();
        assertFalse(DESC_JANESTREET.equals(editedJanestreet));

        // different start date -> returns false
        editedJanestreet =
                new EditInternshipDescriptorBuilder(DESC_JANESTREET)
                        .withStartDate(VALID_STARTDATE_OPTIVER).build();
        assertFalse(DESC_JANESTREET.equals(editedJanestreet));

        // different duration -> returns false
        editedJanestreet =
                new EditInternshipDescriptorBuilder(DESC_JANESTREET)
                        .withDuration(VALID_DURATION_OPTIVER).build();
        assertFalse(DESC_JANESTREET.equals(editedJanestreet));

        // different requirements -> returns false
        editedJanestreet =
                new EditInternshipDescriptorBuilder(DESC_JANESTREET)
                        .withRequirements(VALID_REQUIREMENT_OPTIVER).build();
        assertFalse(DESC_JANESTREET.equals(editedJanestreet));
    }

    @Test
    public void toStringMethod() {
        ModifyCommand.EditInternshipDescriptor editInternshipDescriptor = new ModifyCommand.EditInternshipDescriptor();
        String expected = ModifyCommand.EditInternshipDescriptor.class.getCanonicalName() + "{companyName="
                + editInternshipDescriptor.getCompanyName().orElse(null) + ", role="
                + editInternshipDescriptor.getRole().orElse(null) + ", applicationStatus="
                + editInternshipDescriptor.getApplicationStatus().orElse(null) + ", deadline="
                + editInternshipDescriptor.getDeadline().orElse(null) + ", startDate="
                + editInternshipDescriptor.getStartDate().orElse(null) + ", duration="
                + editInternshipDescriptor.getDuration().orElse(null) + ", requirements="
                + editInternshipDescriptor.getRequirements().orElse(null) + "}";
        assertEquals(expected, editInternshipDescriptor.toString());
    }
}

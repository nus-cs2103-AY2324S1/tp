package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_PROFESSOR;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditPersonDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditPersonDescriptor descriptorWithSameValues = new EditPersonDescriptor(DESC_AMY);
        assertTrue(DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY.equals(DESC_AMY));

        // null -> returns false
        assertFalse(DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY.equals(DESC_BOB));

        // different name -> returns false
        EditPersonDescriptor editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different role -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withRoles(VALID_ROLE_PROFESSOR).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different contact -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withContacts(VALID_CONTACT_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different course -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_AMY).withCourses(VALID_COURSE_3.courseName).build();
        assertFalse(DESC_AMY.equals(editedAmy));

    }

    @Test
    public void toStringMethod() {
        EditPersonDescriptor editPersonDescriptor = new EditPersonDescriptor();
        String expected = EditPersonDescriptor.class.getCanonicalName() + "{name="
                + editPersonDescriptor.getName().orElse(null) + ", roles="
                + editPersonDescriptor.getRoles().orElse(null) + ", contacts="
                + editPersonDescriptor.getContacts().orElse(null) + ", courses="
                + editPersonDescriptor.getCourses().orElse(null) + ", tutorials="
                + editPersonDescriptor.getTutorials().orElse(null) + "}";
        assertEquals(expected, editPersonDescriptor.toString());
    }
}

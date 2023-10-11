package seedu.staffsnap.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
<<<<<<< HEAD:src/test/java/seedu/staffsnap/logic/commands/EditEmployeeDescriptorTest.java
import static seedu.staffsnap.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.staffsnap.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_JOB_TITLE_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.staffsnap.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
=======
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DEPARTMENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
>>>>>>> 81681bbbe6672d8647326ca44cf820b987267d7b:src/test/java/seedu/address/logic/commands/EditEmployeeDescriptorTest.java

import org.junit.jupiter.api.Test;

import seedu.staffsnap.logic.commands.EditCommand.EditEmployeeDescriptor;
import seedu.staffsnap.testutil.EditEmployeeDescriptorBuilder;

public class EditEmployeeDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditEmployeeDescriptor descriptorWithSameValues = new EditEmployeeDescriptor(DESC_AMY);
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
        EditEmployeeDescriptor editedAmy = new EditEmployeeDescriptorBuilder(DESC_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditEmployeeDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different department -> returns false
        editedAmy = new EditEmployeeDescriptorBuilder(DESC_AMY).withDepartment(VALID_DEPARTMENT_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different jobTitle -> returns false
        editedAmy = new EditEmployeeDescriptorBuilder(DESC_AMY).withJobTitle(VALID_JOB_TITLE_BOB).build();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditEmployeeDescriptorBuilder(DESC_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditEmployeeDescriptor editEmployeeDescriptor = new EditEmployeeDescriptor();
        String expected = EditEmployeeDescriptor.class.getCanonicalName() + "{name="
                + editEmployeeDescriptor.getName().orElse(null) + ", phone="
<<<<<<< HEAD:src/test/java/seedu/staffsnap/logic/commands/EditEmployeeDescriptorTest.java
                + editEmployeeDescriptor.getPhone().orElse(null) + ", email="
                + editEmployeeDescriptor.getEmail().orElse(null) + ", jobTitle="
                + editEmployeeDescriptor.getJobTitle().orElse(null) + ", tags="
=======
                + editEmployeeDescriptor.getPhone().orElse(null) + ", department="
                + editEmployeeDescriptor.getDepartment().orElse(null) + ", address="
                + editEmployeeDescriptor.getAddress().orElse(null) + ", tags="
>>>>>>> 81681bbbe6672d8647326ca44cf820b987267d7b:src/test/java/seedu/address/logic/commands/EditEmployeeDescriptorTest.java
                + editEmployeeDescriptor.getTags().orElse(null) + "}";
        assertEquals(expected, editEmployeeDescriptor.toString());
    }
}

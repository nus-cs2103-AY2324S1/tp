package seedu.address.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.DESC_CALEB;
import static seedu.address.logic.commands.CommandTestUtil.DESC_DAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_DAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_DAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_DAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_DAN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_1_DAN;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditClientDescriptorBuilder;

public class EditClientDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditClientCommand.EditClientDescriptor descriptorWithSameValues =
                new EditClientCommand.EditClientDescriptor(DESC_CALEB);
        assertEquals(DESC_CALEB, descriptorWithSameValues);

        // same object -> returns true
        assertEquals(DESC_CALEB, DESC_CALEB);

        // null -> returns false
        assertNotEquals(null, DESC_CALEB);

        // different types -> returns false
        assertNotEquals(5, DESC_CALEB);

        // different values -> returns false
        assertNotEquals(DESC_CALEB, DESC_DAN);

        // different name -> returns false
        EditClientCommand.EditClientDescriptor editedCaleb = new EditClientDescriptorBuilder(DESC_CALEB)
                .withName(VALID_NAME_DAN).build();
        assertNotEquals(DESC_CALEB, editedCaleb);

        // different phone -> returns false
        editedCaleb = new EditClientDescriptorBuilder(DESC_CALEB).withPhone(VALID_PHONE_DAN).build();
        assertNotEquals(DESC_CALEB, editedCaleb);

        // different email -> returns false
        editedCaleb = new EditClientDescriptorBuilder(DESC_CALEB).withEmail(VALID_EMAIL_DAN).build();
        assertNotEquals(DESC_CALEB, editedCaleb);

        // different address -> returns false
        editedCaleb = new EditClientDescriptorBuilder(DESC_CALEB).withAddress(VALID_ADDRESS_DAN).build();
        assertNotEquals(DESC_CALEB, editedCaleb);

        // different tags -> returns false
        editedCaleb = new EditClientDescriptorBuilder(DESC_CALEB).withProjects(VALID_PROJECT_1_DAN).build();
        assertNotEquals(DESC_CALEB, editedCaleb);
    }

    @Test
    public void toStringMethod() {
        EditClientCommand.EditClientDescriptor editClientDescriptor =
                new EditClientDescriptorBuilder().build();
        String expected = EditClientCommand.EditClientDescriptor.class.getCanonicalName() + "{name="
                + editClientDescriptor.getName().orElse(null) + ", phone="
                + editClientDescriptor.getPhone().orElse(null) + ", email="
                + editClientDescriptor.getEmail().orElse(null) + ", address="
                + editClientDescriptor.getAddress().orElse(null) + ", projects="
                + editClientDescriptor.getProjects().orElse(null) + ", role="
                + editClientDescriptor.getRole().orElse(null) + ", organisation="
                + editClientDescriptor.getOrganisation().orElse(null) + ", document="
                + editClientDescriptor.getDocument().orElse(null) + "}";

        assertEquals(expected, editClientDescriptor.toString());
    }
}

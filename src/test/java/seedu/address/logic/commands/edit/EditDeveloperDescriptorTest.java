package seedu.address.logic.commands.edit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PROJECT_1_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.EditDeveloperDescriptorBuilder;

public class EditDeveloperDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditDeveloperCommand.EditDeveloperDescriptor descriptorWithSameValues =
                new EditDeveloperCommand.EditDeveloperDescriptor(DESC_AMY);
        assertEquals(DESC_AMY, descriptorWithSameValues);

        // same object -> returns true
        assertEquals(DESC_AMY, DESC_AMY);

        // null -> returns false
        assertNotEquals(null, DESC_AMY);

        // different types -> returns false
        assertNotEquals(5, DESC_AMY);

        // different values -> returns false
        assertNotEquals(DESC_AMY, DESC_BOB);

        // different name -> returns false
        EditDeveloperCommand.EditDeveloperDescriptor editedAmy = new EditDeveloperDescriptorBuilder(DESC_AMY)
                .withName(VALID_NAME_BOB).build();
        assertNotEquals(DESC_AMY, editedAmy);

        // different phone -> returns false
        editedAmy = new EditDeveloperDescriptorBuilder(DESC_AMY).withPhone(VALID_PHONE_BOB).build();
        assertNotEquals(DESC_AMY, editedAmy);

        // different email -> returns false
        editedAmy = new EditDeveloperDescriptorBuilder(DESC_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertNotEquals(DESC_AMY, editedAmy);

        // different address -> returns false
        editedAmy = new EditDeveloperDescriptorBuilder(DESC_AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertNotEquals(DESC_AMY, editedAmy);

        // different tags -> returns false
        editedAmy = new EditDeveloperDescriptorBuilder(DESC_AMY).withProjects(VALID_PROJECT_1_BOB).build();
        assertNotEquals(DESC_AMY, editedAmy);
    }

    @Test
    public void toStringMethod() {
        EditDeveloperCommand.EditDeveloperDescriptor editDeveloperDescriptor =
                new EditDeveloperDescriptorBuilder().build();
        String expected = EditDeveloperCommand.EditDeveloperDescriptor.class.getCanonicalName() + "{name="
                + editDeveloperDescriptor.getName().orElse(null) + ", phone="
                + editDeveloperDescriptor.getPhone().orElse(null) + ", email="
                + editDeveloperDescriptor.getEmail().orElse(null) + ", address="
                + editDeveloperDescriptor.getAddress().orElse(null) + ", projects="
                + editDeveloperDescriptor.getProjects().orElse(null) + ", dateJoined="
                + editDeveloperDescriptor.getDateJoined().orElse(null) + ", role="
                + editDeveloperDescriptor.getRole().orElse(null) + ", salary="
                + editDeveloperDescriptor.getSalary().orElse(null) + ", githubId="
                + editDeveloperDescriptor.getGithubId().orElse(null) + ", rating="
                + editDeveloperDescriptor.getRating().orElse(null) + "}";

        assertEquals(expected, editDeveloperDescriptor.toString());
    }
}

package seedu.address.logic.commands.user;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AARON;
import static seedu.address.logic.commands.CommandTestUtil.DESC_FOODBEAR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ANSWER_FOODBEAR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PASSWORD_FOODBEAR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SECRET_QUESTION_FOODBEAR;
import static seedu.address.logic.commands.CommandTestUtil.VALID_USERNAME_FOODBEAR;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.user.UserUpdateCommand.UserUpdateDescriptor;
import seedu.address.testutil.UpdateUserDescriptorBuilder;

public class UserUpdateDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        UserUpdateDescriptor descriptorWithSameValues = new UserUpdateDescriptor(DESC_AARON);
        assertTrue(DESC_AARON.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AARON.equals(DESC_AARON));

        // null -> returns false
        assertFalse(DESC_AARON.equals(null));

        // different types -> returns false
        assertFalse(DESC_AARON.equals(5));

        // different values -> returns false
        assertFalse(DESC_AARON.equals(DESC_FOODBEAR));

        // different username -> returns false
        UserUpdateDescriptor editedAaron = new UpdateUserDescriptorBuilder(DESC_AARON)
                .withUsername(VALID_USERNAME_FOODBEAR).build();
        assertFalse(DESC_AARON.equals(editedAaron));

        // different password -> returns false
        editedAaron = new UpdateUserDescriptorBuilder(DESC_AARON).withPassword(VALID_PASSWORD_FOODBEAR).build();
        assertFalse(DESC_AARON.equals(editedAaron));

        // different secret question -> returns false
        editedAaron = new UpdateUserDescriptorBuilder(DESC_AARON)
                .withSecretQuestion(VALID_SECRET_QUESTION_FOODBEAR).build();
        assertFalse(DESC_AARON.equals(editedAaron));

        // different answer -> returns false
        editedAaron = new UpdateUserDescriptorBuilder(DESC_AARON).withAnswer(VALID_ANSWER_FOODBEAR).build();
        assertFalse(DESC_AARON.equals(editedAaron));
    }

    @Test
    public void toStringMethod() {
        UserUpdateDescriptor userUpdateDescriptor = new UserUpdateDescriptor();
        String expected = UserUpdateDescriptor.class.getCanonicalName() + "{username="
                + userUpdateDescriptor.getUsername().orElse(null) + ", secretQuestion="
                + userUpdateDescriptor.getSecretQuestion().orElse(null) + ", answer="
                + userUpdateDescriptor.getAnswer().orElse(null) + "}";
        assertEquals(expected, userUpdateDescriptor.toString());
    }
}

package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY_MEMBER;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB_MEMBER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEGRAM_BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditMemberCommand.EditMemberDescriptor;
import seedu.address.testutil.EditMemberDescriptorBuilder;

public class EditMemberDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditMemberDescriptor descriptorWithSameValues = new EditMemberDescriptor(DESC_AMY_MEMBER);
        assertTrue(DESC_AMY_MEMBER.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_AMY_MEMBER.equals(DESC_AMY_MEMBER));

        // null -> returns false
        assertFalse(DESC_AMY_MEMBER.equals(null));

        // different types -> returns false
        assertFalse(DESC_AMY_MEMBER.equals(5));

        // different values -> returns false
        assertFalse(DESC_AMY_MEMBER.equals(DESC_BOB_MEMBER));

        // different name -> returns false
        EditMemberDescriptor editedAmy = new EditMemberDescriptorBuilder(DESC_AMY_MEMBER)
                .withName(VALID_NAME_BOB).build();
        assertFalse(DESC_AMY_MEMBER.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditMemberDescriptorBuilder(DESC_AMY_MEMBER).withPhone(VALID_PHONE_BOB).build();
        assertFalse(DESC_AMY_MEMBER.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditMemberDescriptorBuilder(DESC_AMY_MEMBER).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(DESC_AMY_MEMBER.equals(editedAmy));

        // different address -> returns false
        editedAmy = new EditMemberDescriptorBuilder(DESC_AMY_MEMBER).withTelegram(VALID_TELEGRAM_BOB).build();
        assertFalse(DESC_AMY_MEMBER.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditMemberDescriptorBuilder(DESC_AMY_MEMBER).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(DESC_AMY_MEMBER.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditMemberDescriptor editMemberDescriptor = new EditMemberDescriptor();
        String expected = EditMemberDescriptor.class.getCanonicalName() + "{name="
                + editMemberDescriptor.getName().orElse(null) + ", phone="
                + editMemberDescriptor.getPhone().orElse(null) + ", email="
                + editMemberDescriptor.getEmail().orElse(null) + ", telegram="
                + editMemberDescriptor.getTelegram().orElse(null) + ", tags="
                + editMemberDescriptor.getTags().orElse(null) + "}";
        assertEquals(expected, editMemberDescriptor.toString());
    }
}

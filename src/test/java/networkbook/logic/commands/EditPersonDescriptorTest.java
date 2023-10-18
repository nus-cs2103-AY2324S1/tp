package networkbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import networkbook.testutil.EditPersonDescriptorBuilder;

public class EditPersonDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditCommand.EditPersonDescriptor descriptorWithSameValues =
                new EditCommand.EditPersonDescriptor(CommandTestUtil.DESC_AMY);
        assertTrue(CommandTestUtil.DESC_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(CommandTestUtil.DESC_AMY.equals(CommandTestUtil.DESC_AMY));

        // null -> returns false
        assertFalse(CommandTestUtil.DESC_AMY.equals(null));

        // different types -> returns false
        assertFalse(CommandTestUtil.DESC_AMY.equals(5));

        // different values -> returns false
        assertFalse(CommandTestUtil.DESC_AMY.equals(CommandTestUtil.DESC_BOB));

        // different name -> returns false
        EditCommand.EditPersonDescriptor editedAmy =
                new EditPersonDescriptorBuilder(CommandTestUtil.DESC_AMY)
                        .withName(CommandTestUtil.VALID_NAME_BOB)
                        .build();
        assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditPersonDescriptorBuilder(CommandTestUtil.DESC_AMY)
                .withPhone(CommandTestUtil.VALID_PHONE_BOB)
                .build();
        assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditPersonDescriptorBuilder(CommandTestUtil.DESC_AMY)
                .withEmail(CommandTestUtil.VALID_EMAIL_BOB)
                .build();
        assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));

        // different link -> returns false
        editedAmy = new EditPersonDescriptorBuilder(CommandTestUtil.DESC_AMY)
                .withLink(CommandTestUtil.VALID_LINK_BOB)
                .build();
        assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));

        // different graduating year -> returns false
        editedAmy = new EditPersonDescriptorBuilder(CommandTestUtil.DESC_AMY)
                .withGraduatingYear(CommandTestUtil.VALID_GRADUATING_YEAR_BOB)
                .build();
        assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));

        // different course -> returns false
        editedAmy = new EditPersonDescriptorBuilder(CommandTestUtil.DESC_AMY)
                .withCourse(CommandTestUtil.VALID_COURSE_BOB)
                .build();
        assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));

        // different specialisation -> returns false
        editedAmy = new EditPersonDescriptorBuilder(CommandTestUtil.DESC_AMY)
                .withSpecialisation(CommandTestUtil.VALID_SPECIALISATION_BOB)
                .build();
        assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditPersonDescriptorBuilder(CommandTestUtil.DESC_AMY)
                .withTags(CommandTestUtil.VALID_TAG_HUSBAND)
                .build();
        assertFalse(CommandTestUtil.DESC_AMY.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        EditCommand.EditPersonDescriptor editPersonDescriptor = new EditCommand.EditPersonDescriptor();
        String expected = EditCommand.EditPersonDescriptor.class.getCanonicalName() + "{name="
                + editPersonDescriptor.getName().orElse(null) + ", phones="
                + editPersonDescriptor.getPhones().orElse(null) + ", emails="
                + editPersonDescriptor.getEmails().orElse(null) + ", links="
                + editPersonDescriptor.getLinks().orElse(null) + ", graduating year="
                + editPersonDescriptor.getGraduatingYear().orElse(null) + ", course="
                + editPersonDescriptor.getCourse().orElse(null) + ", specialisation="
                + editPersonDescriptor.getSpecialisation().orElse(null) + ", tags="
                + editPersonDescriptor.getTags().orElse(null) + "}";
        assertEquals(expected, editPersonDescriptor.toString());
    }
}

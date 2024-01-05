package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.RemoveFromConsultCommand.RemoveFromConsultationDescriptor;
import seedu.address.testutil.RemoveFromConsultationDescriptorBuilder;
import seedu.address.testutil.TypicalConsultations;

public class RemoveFromConsultationDescriptorTest {
    @Test
    public void isEmptyStudents_nullDescriptor_returnsTrue() {
        RemoveFromConsultationDescriptor nullDescriptor = new RemoveFromConsultationDescriptorBuilder().build();
        assertTrue(nullDescriptor.isEmptyStudents());
    }

    @Test
    public void isEmptyStudents_nonEmptyDescriptor_returnsFalse() {
        RemoveFromConsultationDescriptor descriptor = new RemoveFromConsultationDescriptorBuilder(
                TypicalConsultations.CONSULTATION1).build();
        assertFalse(descriptor.isEmptyStudents());
    }

    @Test
    void equals() {
        RemoveFromConsultationDescriptor descriptor = new RemoveFromConsultationDescriptorBuilder(
                TypicalConsultations.CONSULTATION1).build();
        RemoveFromConsultationDescriptor otherDescriptor = new RemoveFromConsultationDescriptorBuilder(
                TypicalConsultations.CONSULTATION2).build();
        RemoveFromConsultationDescriptor descriptorWithSameValue = new RemoveFromConsultationDescriptor(descriptor);
        assertTrue(descriptor.equals(descriptorWithSameValue));

        // same object -> returns true
        assertTrue(descriptor.equals(descriptor));

        // null -> returns false
        assertFalse(descriptor.equals(null));

        // different types -> returns false
        assertFalse(descriptor.equals(5));

        // different values -> returns false
        assertFalse(descriptor.equals(otherDescriptor));
    }

    @Test
    public void toStringMethod() {
        RemoveFromConsultationDescriptor descriptor = new RemoveFromConsultationDescriptor();
        String expected = RemoveFromConsultationDescriptor.class.getCanonicalName() + "{names="
                + descriptor.getNames().orElse(null) + "}";

        assertEquals(expected, descriptor.toString());
    }
}

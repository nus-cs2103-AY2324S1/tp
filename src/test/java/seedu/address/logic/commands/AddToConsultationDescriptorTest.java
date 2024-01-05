package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddToConsultCommand.AddToConsultationDescriptor;
import seedu.address.testutil.AddToConsultationDescriptorBuilder;
import seedu.address.testutil.TypicalConsultations;

/**
 * A utility class to help with building AddToConsultationDescriptor objects.
 */
public class AddToConsultationDescriptorTest {
    @Test
    void equals() {
        AddToConsultationDescriptor descriptor = new AddToConsultationDescriptorBuilder(
                TypicalConsultations.CONSULTATION1).build();
        AddToConsultationDescriptor otherDescriptor = new AddToConsultationDescriptorBuilder(
                TypicalConsultations.CONSULTATION2).build();
        AddToConsultationDescriptor descriptorWithSameValue = new AddToConsultationDescriptor(descriptor);
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
        AddToConsultationDescriptor descriptor = new AddToConsultationDescriptor();
        String expected = AddToConsultationDescriptor.class.getCanonicalName() + "{names="
                + descriptor.getNames().orElse(null) + "}";

        assertEquals(expected, descriptor.toString());
    }
}

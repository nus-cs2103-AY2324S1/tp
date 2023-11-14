package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.FILTER_PROPERTY_DESCRIPTOR_HELIX;
import static seedu.address.logic.commands.CommandTestUtil.FILTER_PROPERTY_DESCRIPTOR_LIGHT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRICE_HELIX;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BIG;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterPropertyCommand.FilterPropertyDescriptor;
import seedu.address.testutil.FilterPropertyDescriptorBuilder;

public class FilterPropertyDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        FilterPropertyDescriptor descriptorWithSameValues =
                new FilterPropertyDescriptor(FILTER_PROPERTY_DESCRIPTOR_LIGHT);
        assertTrue(FILTER_PROPERTY_DESCRIPTOR_LIGHT.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(FILTER_PROPERTY_DESCRIPTOR_LIGHT.equals(FILTER_PROPERTY_DESCRIPTOR_LIGHT));

        // null -> returns false
        assertFalse(FILTER_PROPERTY_DESCRIPTOR_LIGHT.equals(null));

        // different types -> returns false
        assertFalse(FILTER_PROPERTY_DESCRIPTOR_LIGHT.equals(5));

        // different values -> returns false
        assertFalse(FILTER_PROPERTY_DESCRIPTOR_LIGHT.equals(FILTER_PROPERTY_DESCRIPTOR_HELIX));

        // different price -> returns false
        FilterPropertyDescriptor editedLight = new FilterPropertyDescriptorBuilder(FILTER_PROPERTY_DESCRIPTOR_LIGHT)
                .withPrice(VALID_PRICE_HELIX).build();
        assertFalse(FILTER_PROPERTY_DESCRIPTOR_LIGHT.equals(editedLight));

        // different tags -> returns false
        editedLight = new FilterPropertyDescriptorBuilder(FILTER_PROPERTY_DESCRIPTOR_LIGHT)
                .withTags(VALID_TAG_BIG).build();
        assertFalse(FILTER_PROPERTY_DESCRIPTOR_LIGHT.equals(editedLight));
    }

    @Test
    public void toStringMethod() {
        FilterPropertyDescriptor filterPropertyDescriptor = new FilterPropertyDescriptor();
        String expected = FilterPropertyDescriptor.class.getCanonicalName() + "{price="
                + filterPropertyDescriptor.getPrice().orElse(null) + ", tags="
                + filterPropertyDescriptor.getTags().orElse(null) + "}";
        assertEquals(expected, filterPropertyDescriptor.toString());
    }
}

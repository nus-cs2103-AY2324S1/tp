package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.FILTER_CUSTOMER_DESCRIPTOR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.FILTER_CUSTOMER_DESCRIPTOR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BUDGET_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BIG;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCustomerCommand.FilterCustomerDescriptor;
import seedu.address.testutil.FilterCustomerDescriptorBuilder;

public class FilterCustomerDescriptorTest {
    @Test
    public void equals() {
        // same values -> returns true
        FilterCustomerDescriptor descriptorWithSameValues =
                new FilterCustomerDescriptor(FILTER_CUSTOMER_DESCRIPTOR_AMY);
        assertTrue(FILTER_CUSTOMER_DESCRIPTOR_AMY.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(FILTER_CUSTOMER_DESCRIPTOR_AMY.equals(FILTER_CUSTOMER_DESCRIPTOR_AMY));

        // null -> returns false
        assertFalse(FILTER_CUSTOMER_DESCRIPTOR_AMY.equals(null));

        // different types -> returns false
        assertFalse(FILTER_CUSTOMER_DESCRIPTOR_AMY.equals(5));

        // different values -> returns false
        assertFalse(FILTER_CUSTOMER_DESCRIPTOR_AMY.equals(FILTER_CUSTOMER_DESCRIPTOR_BOB));

        // different price -> returns false
        FilterCustomerDescriptor editedAmy = new FilterCustomerDescriptorBuilder(FILTER_CUSTOMER_DESCRIPTOR_AMY)
                .withBudget(VALID_BUDGET_BOB).build();
        assertFalse(FILTER_CUSTOMER_DESCRIPTOR_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new FilterCustomerDescriptorBuilder(FILTER_CUSTOMER_DESCRIPTOR_AMY)
                .withTags(VALID_TAG_BIG).build();
        assertFalse(FILTER_CUSTOMER_DESCRIPTOR_AMY.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        FilterCustomerDescriptor filterPropertyDescriptor = new FilterCustomerDescriptor();
        String expected = FilterCustomerDescriptor.class.getCanonicalName() + "{budget="
                + filterPropertyDescriptor.getBudget().orElse(null) + ", tags="
                + filterPropertyDescriptor.getTags().orElse(null) + "}";
        assertEquals(expected, filterPropertyDescriptor.toString());
    }
}

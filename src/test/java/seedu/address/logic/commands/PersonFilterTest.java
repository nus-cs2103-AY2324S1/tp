package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.FILTER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.FILTER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FilterCommand.PersonFilter;
import seedu.address.testutil.PersonFilterBuilder;

class PersonFilterTest {
    @Test
    public void equals() {
        // same object -> returns true
        assertTrue(FILTER_AMY.equals(FILTER_AMY));

        // null -> returns false
        assertFalse(FILTER_AMY.equals(null));

        // different types -> returns false
        assertFalse(FILTER_AMY.equals(5));

        // different values -> returns false
        assertFalse(FILTER_AMY.equals(FILTER_BOB));

        // different name -> returns false
        PersonFilter editedAmy = new PersonFilterBuilder(FILTER_AMY).withName(VALID_NAME_BOB).build();
        assertFalse(FILTER_AMY.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new PersonFilterBuilder(FILTER_AMY).withPhone(VALID_PHONE_BOB).build();
        assertFalse(FILTER_AMY.equals(editedAmy));

        // different email -> returns false
        editedAmy = new PersonFilterBuilder(FILTER_AMY).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(FILTER_AMY.equals(editedAmy));

        // different address -> returns false
        editedAmy = new PersonFilterBuilder(FILTER_AMY).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(FILTER_AMY.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new PersonFilterBuilder(FILTER_AMY).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(FILTER_AMY.equals(editedAmy));
    }

    @Test
    public void toStringMethod() {
        PersonFilter personFilter = new PersonFilter();
        String expected = PersonFilter.class.getCanonicalName() + "{name="
                + personFilter.getName() + ", phone="
                + personFilter.getPhone() + ", email="
                + personFilter.getEmail() + ", address="
                + personFilter.getAddress() + ", tags="
                + personFilter.getTags() + "}";
        assertEquals(expected, personFilter.toString());
    }
}

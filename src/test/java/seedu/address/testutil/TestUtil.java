package seedu.address.testutil;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.contact.Contact;



/**
 * A utility class for test cases.
 */
public class TestUtil {
    /**
     * Returns the middle index of the contact in the {@code model}'s contact list.
     */
    public static Index getMidIndex(Model model) {
        return Index.fromOneBased(model.getFilteredContactList().size() / 2);
    }

    /**
     * Returns the last index of the contact in the {@code model}'s contact list.
     */
    public static Index getLastIndex(Model model) {
        return Index.fromOneBased(model.getFilteredContactList().size());
    }

    /**
     * Returns the contact in the {@code model}'s contact list at {@code index}.
     */
    public static Contact getContact(Model model, Index index) {
        return model.getFilteredContactList().get(index.getZeroBased());
    }
}

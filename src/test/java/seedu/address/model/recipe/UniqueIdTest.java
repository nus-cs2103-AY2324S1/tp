package seedu.address.model.recipe;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class UniqueIdTest {
    @Test
    public void equals() {
        UniqueId uuid1 = new UniqueId(1);
        UniqueId uuid2 = new UniqueId(2);

        // same object -> returns true
        assertTrue(uuid1.equals(uuid1));

        // same uuid value -> returns true;
        UniqueId uuid1Copy = new UniqueId(1);
        assertTrue(uuid1.equals(uuid1Copy));

        // different types -> returns false
        assertFalse(uuid1.equals(1));

        // null -> returns false
        assertFalse(uuid1.equals(null));

        // different values -> returns false
        assertFalse(uuid1.equals(uuid2));
    }

    @Test
    public void incrementUuidConstructor_returnsDifferentUuid() {
        UniqueId uuid1 = new UniqueId();
        UniqueId uuid2 = new UniqueId();

        // uuids should be different in value
        assertFalse(uuid1.equals(uuid2));

        // second uuid should have a value incremented by 1
        int uuid1Value = uuid1.getId();
        int uuid2Value = uuid2.getId();
        assertTrue(uuid1Value + 1 == uuid2Value);
    }
}

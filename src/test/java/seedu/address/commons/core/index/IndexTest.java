package seedu.address.commons.core.index;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class IndexTest {

    @Test
    public void createFromZeroBased_validIndex_success() {
        Index index = Index.fromZeroBased(0);
        assertEquals(0, index.getZeroBased());

        Index index2 = Index.fromZeroBased(5);
        assertEquals(5, index2.getZeroBased());
    }

    @Test
    public void createFromOneBased_validIndex_success() {
        Index index = Index.fromOneBased(1);
        assertEquals(0, index.getZeroBased());

        Index index2 = Index.fromOneBased(6);
        assertEquals(5, index2.getZeroBased());
    }

    @Test
    public void createFromZeroBased_invalidIndex_throwsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> Index.fromZeroBased(-1));
    }

    @Test
    public void createFromOneBased_invalidIndex_throwsException() {
        assertThrows(IndexOutOfBoundsException.class, () -> Index.fromOneBased(0));
    }

    @Test
    public void getOneBased_checkConversion_success() {
        Index index = Index.fromZeroBased(0);
        assertEquals(1, index.getOneBased());

        Index index2 = Index.fromZeroBased(5);
        assertEquals(6, index2.getOneBased());
    }

    @Test
    public void equals_sameIndex_returnsTrue() {
        Index index1 = Index.fromZeroBased(5);
        Index index2 = Index.fromZeroBased(5);

        assertEquals(index1, index2);
    }

    @Test
    public void equals_differentIndex_returnsFalse() {
        Index index1 = Index.fromZeroBased(5);
        Index index2 = Index.fromZeroBased(6);

        assertNotEquals(index1, index2);
    }

    @Test
    public void equals_differentObject_returnsFalse() {
        Index index = Index.fromZeroBased(5);
        String notAnIndex = "Not an Index";

        assertNotEquals(index, notAnIndex);
    }

}

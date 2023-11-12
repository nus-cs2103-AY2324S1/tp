package transact.model.transaction.info;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TransactionIdTest {

    // Resetting the used IDs before each test
    @BeforeEach
    public void setUp() {
        for (int i = 0; i < 7; i++) {
            TransactionId.freeUsedTransactionIds(i);

        }
    }

    // Test default constructor
    @Test
    public void testDefaultConstructor() {
        TransactionId id = new TransactionId();
        assertNotNull(id);
    }

    // Test construction with valid Integer ID
    @Test
    public void testConstructionWithValidIntegerId() {
        Integer validId = 1;
        TransactionId id = new TransactionId(validId);
        assertEquals(validId, id.getValue());
    }

    // Test construction with invalid Integer ID (null or used)
    @Test
    public void testConstructionWithInvalidIntegerId() {
        Integer usedId = 1;
        new TransactionId(usedId); // First use

        // Attempting to use the same ID again
        assertThrows(IllegalArgumentException.class, () -> new TransactionId(usedId));

        // Null ID
        assertThrows(NullPointerException.class, () -> new TransactionId((Integer) null));
    }

    // Test construction with valid String ID
    @Test
    public void testConstructionWithValidStringId() {
        String validId = "2";
        TransactionId id = new TransactionId(validId);
        assertEquals(Integer.valueOf(validId), id.getValue());
    }

    // Test construction with invalid String ID (non-numeric or used)
    @Test
    public void testConstructionWithInvalidStringId() {
        String nonNumericId = "abc";
        assertThrows(IllegalArgumentException.class, () -> new TransactionId(nonNumericId));

        String usedId = "1";
        new TransactionId(usedId); // First use

        // Attempting to use the same ID again
        assertThrows(IllegalArgumentException.class, () -> new TransactionId(usedId));
    }

    // Test isValidTransactionId Method
    @Test
    public void testIsValidTransactionId() {
        Integer validId = 3;
        assertTrue(TransactionId.isValidTransactionId(validId));

        Integer usedId = 1;
        new TransactionId(usedId); // First use
        assertFalse(TransactionId.isValidTransactionId(usedId));
    }

    // Test toString Method
    @Test
    public void testToStringMethod() {
        Integer validId = 4;
        TransactionId id = new TransactionId(validId);
        assertEquals(validId.toString(), id.toString());
    }

    // Test equals Method
    @Test
    public void testEqualsMethod() {
        TransactionId id1 = new TransactionId(5);
        TransactionId id2 = new TransactionId(6);

        assertNotEquals(id1, id2);
    }

    // Test compareTo Method
    @Test
    public void testCompareToMethod() {
        TransactionId id1 = new TransactionId(7);
        TransactionId id2 = new TransactionId(8);

        assertTrue(id1.compareTo(id2) < 0);
        assertTrue(id2.compareTo(id1) > 0);
    }
}

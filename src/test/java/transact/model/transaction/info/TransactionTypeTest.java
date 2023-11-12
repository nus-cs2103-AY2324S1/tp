package transact.model.transaction.info;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TransactionTypeTest {

    // Test isValidType method with valid types
    @Test
    public void testIsValidTypeWithValidTypes() {
        assertTrue(TransactionType.isValidType("Revenue"));
        assertTrue(TransactionType.isValidType("R"));
        assertTrue(TransactionType.isValidType("r"));
        assertTrue(TransactionType.isValidType("Expense"));
        assertTrue(TransactionType.isValidType("E"));
        assertTrue(TransactionType.isValidType("e"));
    }

    // Test isValidType method with invalid type
    @Test
    public void testIsValidTypeWithInvalidType() {
        assertFalse(TransactionType.isValidType("InvalidType"));
        assertFalse(TransactionType.isValidType(null));
        assertFalse(TransactionType.isValidType(""));
    }

    // Test getType method with valid types
    @Test
    public void testGetTypeWithValidTypes() {
        assertEquals(TransactionType.REVENUE, TransactionType.getType("Revenue"));
        assertEquals(TransactionType.REVENUE, TransactionType.getType("R"));
        assertEquals(TransactionType.REVENUE, TransactionType.getType("r"));
        assertEquals(TransactionType.EXPENSE, TransactionType.getType("Expense"));
        assertEquals(TransactionType.EXPENSE, TransactionType.getType("E"));
        assertEquals(TransactionType.EXPENSE, TransactionType.getType("e"));
    }

    // Test getType method with invalid type
    @Test
    public void testGetTypeWithInvalidType() {
        assertNull(TransactionType.getType("InvalidType"));
        assertThrows(AssertionError.class, () -> TransactionType.getType(null));
        assertNull(TransactionType.getType(""));
    }

    // Test toString method
    @Test
    public void testToStringMethod() {
        assertEquals("Revenue", TransactionType.REVENUE.toString());
        assertEquals("Expense", TransactionType.EXPENSE.toString());
    }
}

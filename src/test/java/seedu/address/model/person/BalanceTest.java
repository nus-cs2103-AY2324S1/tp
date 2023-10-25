package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BalanceTest {

    private Balance defaultBalance;

    @BeforeEach
    void setUp() {
        defaultBalance = new Balance(0);
    }

    @Test
    void constructor_invalidAmount_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Balance(Balance.TRANSACTION_LIMIT + 1));
        assertThrows(IllegalArgumentException.class, () -> new Balance(-Balance.TRANSACTION_LIMIT - 1));
    }

    @Test
    void constructor_validAmount_success() {
        assertDoesNotThrow(() -> new Balance(500));
    }

    @Test
    void isWithinLimits_inputBalanceWithinLimits_returnTrue() {
        assertTrue(Balance.isWithinLimits(0));
        assertTrue(Balance.isWithinLimits(Balance.MAX_VALUE));
        assertTrue(Balance.isWithinLimits(Balance.MIN_VALUE));
    }
    @Test
    void isWithinLimits_inputBalanceExceedLimits_returnFalse() {
        assertFalse(Balance.isWithinLimits(Balance.MAX_VALUE + 1));
        assertFalse(Balance.isWithinLimits(Balance.MIN_VALUE - 1));
    }

    @Test
    void isValidDollarString_withDollarSign_success() {
        assertTrue(Balance.isValidDollarString("$1000.00"));
        assertTrue(Balance.isValidDollarString("$0.5"));
        assertTrue(Balance.isValidDollarString("$129.75"));
        assertTrue(Balance.isValidDollarString("$1000"));
    }

    @Test
    void isValidDollarString_withoutDollarSign_success() {
        assertTrue(Balance.isValidDollarString("1000.00"));
        assertTrue(Balance.isValidDollarString("0.5"));
        assertTrue(Balance.isValidDollarString("129.75"));
        assertTrue(Balance.isValidDollarString("1000"));
    }

    @Test
    void isValidDollarString_invalidInput_returnFalse() {
        // too many decimal places
        assertFalse(Balance.isValidDollarString("1000.000"));
        assertFalse(Balance.isValidDollarString("0.555"));

        // negative amount
        assertFalse(Balance.isValidDollarString("-1000.00"));
        assertFalse(Balance.isValidDollarString("-0.5"));

        // invalid characters
        assertFalse(Balance.isValidDollarString("ABC"));
        assertFalse(Balance.isValidDollarString("123ABC"));

        // empty dollar amount
        assertFalse(Balance.isValidDollarString(""));
        assertFalse(Balance.isValidDollarString(".55"));
        assertFalse(Balance.isValidDollarString("$.3"));
        assertFalse(Balance.isValidDollarString("$"));

        // empty cents amount
        assertFalse(Balance.isValidDollarString("1000."));
        assertFalse(Balance.isValidDollarString("0."));
        assertFalse(Balance.isValidDollarString("129."));
        assertFalse(Balance.isValidDollarString("$1000."));

        // empty dollar and cents amount
        assertFalse(Balance.isValidDollarString("."));
        assertFalse(Balance.isValidDollarString("$."));
        assertFalse(Balance.isValidDollarString(""));
    }

    @Test
    void toDollarString() {
        assertEquals("$0.00", defaultBalance.toAbsoluteDollarString());
        assertEquals("$10.00", new Balance(1000).toAbsoluteDollarString());
        assertEquals("$10.50", new Balance(1050).toAbsoluteDollarString());
    }

    @Test
    void toUiMessage() {
        assertEquals("They owe you: $0.00", defaultBalance.toUiMessage());
        assertEquals("You owe them: $10.00", new Balance(-1000).toUiMessage());
        assertEquals("They owe you: $10.00", new Balance(1000).toUiMessage());
    }

    @Test
    void add() {
        Balance addedBalance = defaultBalance.add(new Balance(500));
        assertEquals(new Balance(500), addedBalance);

        addedBalance = defaultBalance.add(new Balance(-500));
        assertEquals(new Balance(-500), addedBalance);
    }

    @Test
    void wouldExceedBalanceLimit() {
        assertFalse(defaultBalance.wouldExceedBalanceLimit(new Balance(500)));
        System.out.println(defaultBalance.value);
        assertTrue(defaultBalance.wouldExceedBalanceLimit(new Balance(Balance.MAX_VALUE + 1)));
        assertTrue(new Balance(Balance.MAX_VALUE - 500).wouldExceedBalanceLimit(new Balance(501)));
    }

    @Test
    void testToString() {
        assertEquals("0", defaultBalance.toString());
        assertEquals("1000", new Balance(1000).toString());
    }

    @Test
    void equals() {
        assertTrue(defaultBalance.equals(new Balance(0)));
        assertFalse(defaultBalance.equals(new Balance(1000)));
        assertFalse(defaultBalance.equals(null));
        assertFalse(defaultBalance.equals("SomeString"));
    }

    @Test
    void testHashCode() {
        Balance balance1 = new Balance(1000);
        Balance balance2 = new Balance(1000);
        assertEquals(balance1.hashCode(), balance2.hashCode());
    }
}

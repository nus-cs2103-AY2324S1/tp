package seedu.address.model.finance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.CommissionBuilder;
class ClientNameExactMatchPredicateTest {
    @Test
    public void testConstructor_validKeywords_success() {
        String clientName = "John Doe";
        ClientNameExactMatchPredicate predicate = new ClientNameExactMatchPredicate(clientName);
        assertNotNull(predicate);
    }

    @Test
    public void testTest_financeClientIsNull_returnsFalse() {
        Finance finance = new Expense(
                new Amount("10"),
                null,
                new Description("Test"),
                new TimeDue(LocalDateTime.now())
        );
        String clientName = "John Doe";
        ClientNameExactMatchPredicate predicate = new ClientNameExactMatchPredicate(clientName);
        assertFalse(predicate.test(finance));
    }

    @Test
    public void testTest_clientNameEqual_returnsTrue() {
        Finance finance = new CommissionBuilder().withPerson("John Doe").build();
        String clientName = "John Doe";
        ClientNameExactMatchPredicate predicate = new ClientNameExactMatchPredicate(clientName);
        assertTrue(predicate.test(finance));
    }

    @Test
    public void testTest_clientNameNotEquals_returnsFalse() {
        Finance finance = new CommissionBuilder().withPerson("John Doe").build();
        String clientName = "Adam";
        ClientNameExactMatchPredicate predicate = new ClientNameExactMatchPredicate(clientName);
        assertFalse(predicate.test(finance));
    }

    @Test
    public void testEquals_equalPredicates_returnsTrue() {
        String clientName = "John Doe";
        ClientNameExactMatchPredicate predicate1 = new ClientNameExactMatchPredicate(clientName);
        ClientNameExactMatchPredicate predicate2 = new ClientNameExactMatchPredicate(clientName);
        assertEquals(predicate1, predicate2);
    }

    @Test
    public void testEquals_unequalPredicates_returnsFalse() {
        String clientName1 = "John Doe";
        String clientName2 = "John";
        ClientNameExactMatchPredicate predicate1 = new ClientNameExactMatchPredicate(clientName1);
        ClientNameExactMatchPredicate predicate2 = new ClientNameExactMatchPredicate(clientName2);
        assertNotEquals(predicate1, predicate2);
    }

    @Test
    public void testToString_validClientName_returnsExpectedString() {
        String clientName = "John Doe";
        ClientNameExactMatchPredicate predicate = new ClientNameExactMatchPredicate(clientName);
        String expectedString = ClientNameExactMatchPredicate.class.getCanonicalName()
                + "{clientName=John Doe}";
        assertEquals(expectedString, predicate.toString());
    }

}


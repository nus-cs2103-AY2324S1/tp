package transact.model.transaction;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static transact.testutil.TypicalTransactions.APPLES;

import org.junit.jupiter.api.Test;

import transact.testutil.TransactionBuilder;

public class TransactionTest {

    @Test
    public void isSameTransaction() {
        // same object -> returns true
        assertTrue(APPLES.isSameEntry(APPLES));

        // null -> returns false
        assertFalse(APPLES.isSameEntry(null));

        // same ID, all other attributes different -> returns true
        Transaction editedApples = new TransactionBuilder(APPLES).withAmount(19f).withDescription("Apple 2").build();
        assertTrue(APPLES.isSameEntry(editedApples));

        // same ID but different TransactionID object -> returns true
        Transaction newApple = new TransactionBuilder().withId(APPLES.getTransactionId().value)
                .withDescription("Apple 2").withAmount(19f).build();
        assertTrue(APPLES.isSameEntry(newApple));

        // different ID, all other attributes same -> returns false
        editedApples = new TransactionBuilder(APPLES).withId("APPLEAAA").build();
        assertFalse(APPLES.isSameEntry(editedApples));
    }
}

package seedu.address.logic.commands.finance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.finance.ClientNameContainsKeywordsPredicate;

class FilterClientNameCommandTest {
    @Test
    public void equals() {
        ClientNameContainsKeywordsPredicate predicate1 =
                new ClientNameContainsKeywordsPredicate(Arrays.asList("meeting"));
        ClientNameContainsKeywordsPredicate predicate2 =
                new ClientNameContainsKeywordsPredicate(Arrays.asList("conference"));

        FilterClientNameCommand command1 = new FilterClientNameCommand(predicate1);
        FilterClientNameCommand command2 = new FilterClientNameCommand(predicate1);
        FilterClientNameCommand command3 = new FilterClientNameCommand(predicate2);

        // same object -> returns true
        assertTrue(command1.equals(command1));

        // same values -> returns true
        assertTrue(command1.equals(command2));

        // different types -> returns false
        assertFalse(command1.equals(1));

        // null -> returns false
        assertFalse(command1.equals(null));

        // different predicate -> returns false
        assertFalse(command1.equals(command3));
    }

    @Test
    public void toStringMethod() {
        ClientNameContainsKeywordsPredicate predicate = new ClientNameContainsKeywordsPredicate(
                Arrays.asList("meeting"));
        FilterClientNameCommand command = new FilterClientNameCommand(predicate);
        String expected = FilterClientNameCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, command.toString());
    }

}


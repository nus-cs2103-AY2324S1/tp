package networkbook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import networkbook.model.person.PersonSortComparator;
import networkbook.model.person.PersonSortComparator.SortField;
import networkbook.model.person.PersonSortComparator.SortOrder;


public class SortCommandTest {
    @Test
    public void equals() {
        PersonSortComparator firstComparator = new PersonSortComparator(SortField.NAME, SortOrder.DESCENDING);
        PersonSortComparator secondComparator = new PersonSortComparator(SortField.NONE, SortOrder.ASCENDING);

        SortCommand sortFirstCommand = new SortCommand(firstComparator);
        SortCommand sortSecondCommand = new SortCommand(secondComparator);

        // same object -> returns true
        assertTrue(sortFirstCommand.equals(sortFirstCommand));

        // same values -> returns true
        SortCommand sortFirstCommandCopy = new SortCommand(firstComparator);
        assertTrue(sortFirstCommand.equals(sortFirstCommandCopy));

        // different types -> returns false
        assertFalse(sortFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortFirstCommand.equals(null));

        // different commans -> returns false
        assertFalse(sortFirstCommand.equals(sortSecondCommand));
    }

    @Test
    public void toStringMethod() {
        PersonSortComparator comparator = new PersonSortComparator(SortField.NONE, SortOrder.DESCENDING);
        SortCommand sortCommand = new SortCommand(comparator);
        String expected = SortCommand.class.getCanonicalName() + "{comparator=" + comparator + "}";
        assertEquals(expected, sortCommand.toString());
    }

}

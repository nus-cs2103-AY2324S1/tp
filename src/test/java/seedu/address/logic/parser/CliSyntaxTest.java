package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

/**
 * Checks that the relevant prefixes are present in the class
 */
public class CliSyntaxTest {
    @Test
    public void descriptionPrefix_exists() {
        assertNotNull(CliSyntax.PREFIX_DESCRIPTION);
        assertEquals(CliSyntax.PREFIX_DESCRIPTION.getPrefix(), "t/");
    }

    @Test
    public void memberPrefix_exists() {
        assertNotNull(CliSyntax.PREFIX_MEMBER);
        assertEquals(CliSyntax.PREFIX_MEMBER.getPrefix(), "m/");
    }
    @Test
    public void priorityPrefix_exists() {
        assertNotNull(CliSyntax.PREFIX_PRIORITY);
        assertEquals(CliSyntax.PREFIX_PRIORITY.getPrefix(), "p/");
    }

    @Test
    public void notePrefix_exists() {
        assertNotNull(CliSyntax.PREFIX_NOTE);
        assertEquals(CliSyntax.PREFIX_NOTE.getPrefix(), "n/");
    }

    @Test
    public void deadlinePrefix_exists() {
        assertNotNull(CliSyntax.PREFIX_DEADLINE);
        assertEquals(CliSyntax.PREFIX_DEADLINE.getPrefix(), "d/");
    }

    @Test
    public void sortOrderPrefix_exists() {
        assertNotNull(CliSyntax.PREFIX_SORT_ORDER);
        assertEquals(CliSyntax.PREFIX_SORT_ORDER.getPrefix(), "o/");
    }

    @Test
    public void sortTypePrefix_exists() {
        assertNotNull(CliSyntax.PREFIX_SORT_TYPE);
        assertEquals(CliSyntax.PREFIX_SORT_TYPE.getPrefix(), "ty/");
    }
}

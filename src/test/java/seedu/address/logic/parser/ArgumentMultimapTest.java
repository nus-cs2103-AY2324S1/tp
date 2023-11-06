package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ArgumentMultimapTest {

    @Test
    public void testIsAnyPrefixPresent() {
        // Create an ArgumentMultimap with some values
        ArgumentMultimap argumentMultimap = new ArgumentMultimap();
        argumentMultimap.put(new Prefix("phone"), "123456789");
        argumentMultimap.put(new Prefix("email"), "john@example.com");

        // Test with prefixes that exist in the ArgumentMultimap
        boolean result1 = ArgumentMultimap.isAnyPrefixPresent(argumentMultimap, new Prefix("phone"),
                new Prefix("email"));
        assertTrue(result1);

        // Test with prefixes that do not exist in the ArgumentMultimap
        boolean result2 = ArgumentMultimap.isAnyPrefixPresent(argumentMultimap, new Prefix("address"),
                new Prefix("tags"));
        assertFalse(result2);

        // Test with a mix of existing and non-existing prefixes
        boolean result3 = ArgumentMultimap.isAnyPrefixPresent(argumentMultimap, new Prefix("phone"),
                new Prefix("address"));
        assertTrue(result3);

        // Test with no prefixes
        boolean result4 = ArgumentMultimap.isAnyPrefixPresent(argumentMultimap);
        assertFalse(result4);
    }
}

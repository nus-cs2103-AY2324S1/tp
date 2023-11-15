package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.parser.exceptions.ParseException;

public class ArgumentMultimapTest {
    @Test
    public void testParseExceptionWithDuplicatePrefixes() {
        ArgumentMultimap argumentMultimap = new ArgumentMultimap();
        Prefix prefix1 = new Prefix("p1");
        Prefix prefix2 = new Prefix("p2");
        Prefix prefix3 = new Prefix("p3");

        argumentMultimap.put(prefix1, "value1");
        argumentMultimap.put(prefix2, "value2");
        argumentMultimap.put(prefix1, "value3");

        Prefix[] duplicatedPrefixes = { prefix1 };

        ParseException exception = assertThrows(ParseException.class, () -> {
            argumentMultimap.verifyNoDuplicatePrefixesFor(prefix1, prefix2, prefix3);
        });

        String expectedErrorMessage = Messages.getErrorMessageForDuplicatePrefixes(duplicatedPrefixes);
        assertEquals(expectedErrorMessage, exception.getMessage());
    }
}

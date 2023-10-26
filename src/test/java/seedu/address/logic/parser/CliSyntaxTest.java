package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class CliSyntaxTest {

    @Test
    public void cliSyntaxInstance_creation_success() {
        CliSyntax cliSyntax = new CliSyntax();
        assertNotNull(cliSyntax);
    }

    @Test
    public void testPrefixDefinitions() {
        assertEquals("n/", CliSyntax.PREFIX_NAME.getPrefix());
        assertEquals("p/", CliSyntax.PREFIX_PHONE.getPrefix());
        assertEquals("e/", CliSyntax.PREFIX_EMAIL.getPrefix());

        assertEquals("st/", CliSyntax.PREFIX_START_TIME.getPrefix());
        assertEquals("et/", CliSyntax.PREFIX_END_TIME.getPrefix());

        assertEquals("m/", CliSyntax.PREFIX_STATUS.getPrefix());
    }
}

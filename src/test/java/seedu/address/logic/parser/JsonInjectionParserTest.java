package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class JsonInjectionParserTest {
    private static final JsonInjectionParser parser = new JsonInjectionParser();

    @Test
    public void parse_singleInvalidInput_throwsException() {
        String testString = "This is a te'st input";
        Exception exception = assertThrows(ParseException.class, () -> {
            parser.parse(testString);
        });
        String expectedMessage = "Please do not input JSON like content";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void parse_multipleInvalidInputs_throwsException() {
        String testString = "This \\x0 is a te'st inp:ut()";
        Exception exception = assertThrows(ParseException.class, () -> {
            parser.parse(testString);
        });
        String expectedMessage = "Please do not input JSON like content";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}

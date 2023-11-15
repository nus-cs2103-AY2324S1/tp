package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class FindFilterStringTokenizerTest {

    @Test
    public void tokenToString_validInput_success() {
        assertEquals(new FindFilterStringTokenizer.Token(
                FindFilterStringTokenizer.Token.Type.AND, "Alice").toString(), "AND:Alice");
        assertEquals(new FindFilterStringTokenizer.Token(
                FindFilterStringTokenizer.Token.Type.OR, "Alice").toString(), "OR:Alice");
        assertEquals(new FindFilterStringTokenizer.Token(
                FindFilterStringTokenizer.Token.Type.NOT, "Alice").toString(), "NOT:Alice");
        assertEquals(new FindFilterStringTokenizer.Token(
                FindFilterStringTokenizer.Token.Type.LPAREN, "Alice").toString(), "LPAREN:Alice");
        assertEquals(new FindFilterStringTokenizer.Token(
                FindFilterStringTokenizer.Token.Type.RPAREN, "Alice").toString(), "RPAREN:Alice");
    }

    @Test
    public void tokenize_simpleValidInput_correctTokens() throws ParseException {
        assertEquals(new FindFilterStringTokenizer("&&").tokenize().get(0).type,
                FindFilterStringTokenizer.Token.Type.AND);

        assertEquals(new FindFilterStringTokenizer("&&").tokenize().get(0).text,
                "&&");

        assertEquals(new FindFilterStringTokenizer("||").tokenize().get(0).type,
                FindFilterStringTokenizer.Token.Type.OR);

        assertEquals(new FindFilterStringTokenizer("||").tokenize().get(0).text,
                "||");

        assertEquals(new FindFilterStringTokenizer("!").tokenize().get(0).type,
                FindFilterStringTokenizer.Token.Type.NOT);

        assertEquals(new FindFilterStringTokenizer("!").tokenize().get(0).text,
                "!");

        assertEquals(new FindFilterStringTokenizer("(").tokenize().get(0).type,
                FindFilterStringTokenizer.Token.Type.LPAREN);

        assertEquals(new FindFilterStringTokenizer("(").tokenize().get(0).text,
                "(");

        assertEquals(new FindFilterStringTokenizer(")").tokenize().get(0).type,
                FindFilterStringTokenizer.Token.Type.RPAREN);

        assertEquals(new FindFilterStringTokenizer(")").tokenize().get(0).text,
                ")");

        // basic condition

        assertEquals(new FindFilterStringTokenizer("n/Alice").tokenize().get(0).type,
                FindFilterStringTokenizer.Token.Type.CONDITION);

        assertEquals(new FindFilterStringTokenizer("n/Alice").tokenize().get(0).text,
                "n/Alice");
    }

    @Test
    public void tokenize_conditionWithCorrectlyQuotedString_success() {
        assertDoesNotThrow(() -> new FindFilterStringTokenizer("n/\"Alice\"").tokenize());

        // quotes with spaces
        assertDoesNotThrow(() -> new FindFilterStringTokenizer("n/\"Alice Bob\"").tokenize());

        // quoted condition in parentheses
        assertDoesNotThrow(() -> new FindFilterStringTokenizer("(n/\"Alice Bob\")").tokenize());

        // quoted condition negated
        assertDoesNotThrow(() -> new FindFilterStringTokenizer("!n/\"Alice Bob\"").tokenize());
    }

    @Test
    public void tokenize_conditionWithBrokenQuotedString_throwsParseException() throws ParseException {
        // missing closing quote
        assertThrows(ParseException.class, () -> new FindFilterStringTokenizer("n/\"Alice").tokenize());

        // three quotes
        assertThrows(ParseException.class, () -> new FindFilterStringTokenizer("n/\"Alice\"\"").tokenize());

        // closing quote not end of condition
        assertThrows(ParseException.class, () -> new FindFilterStringTokenizer("n/\"Alice\"Bob").tokenize());
    }
}

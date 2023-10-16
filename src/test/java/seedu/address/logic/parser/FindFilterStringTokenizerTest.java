package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

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
}

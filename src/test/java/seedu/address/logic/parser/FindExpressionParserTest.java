package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class FindExpressionParserTest {

    private final FindExpressionParser parser = new FindExpressionParser();

    @Test
    public void fromPrefix_invalidPrefix_throwsParseException() {
        assertThrows(ParseException.class, () -> FindExpressionParser.FindSupportedField.createFromPrefix(" "));
        assertThrows(ParseException.class, () -> FindExpressionParser.FindSupportedField.createFromPrefix("asdf"));

        // existent but unsupported fields
        assertThrows(ParseException.class, () -> FindExpressionParser.FindSupportedField.createFromPrefix("tg/"));
        assertThrows(ParseException.class, () -> FindExpressionParser.FindSupportedField.createFromPrefix("e2/"));
        assertThrows(ParseException.class, () -> FindExpressionParser.FindSupportedField.createFromPrefix("li/"));
    }

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parseToPredicate(new ArrayList<>()));
    }

    @Test
    public void conditionNodeToPredicate_validField_returnsPredicate() throws ParseException {
        assertTrue(new FindExpressionParser.ConditionNode(
                FindExpressionParser.FindSupportedField.NAME, "Alice").toPredicate()
                instanceof Predicate);

        assertTrue(new FindExpressionParser.ConditionNode(
                FindExpressionParser.FindSupportedField.PHONE, "12345").toPredicate()
                instanceof Predicate);

        assertTrue(new FindExpressionParser.ConditionNode(
                FindExpressionParser.FindSupportedField.EMAIL, "haha@haha.com").toPredicate()
                instanceof Predicate);

        assertTrue(new FindExpressionParser.ConditionNode(
                FindExpressionParser.FindSupportedField.ADDRESS, "123, haha street").toPredicate()
                instanceof Predicate);

        assertTrue(new FindExpressionParser.ConditionNode(
                FindExpressionParser.FindSupportedField.TAG, "friends").toPredicate()
                instanceof Predicate);

    }
}

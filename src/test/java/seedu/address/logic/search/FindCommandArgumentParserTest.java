package seedu.address.logic.search;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

class FindCommandArgumentParserTest {

    private static final Person TEST_PERSON;

    static {
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("hasADogIGuess"));
        tags.add(new Tag("wouldReallyLikeASnake"));
        TEST_PERSON = new Person(
                new Name("Tom ABC Whee"),
                new Phone("95728888"),
                new Email("twee.123@company.org"),
                new Address("123 Dawn Park"),
                tags
        );
    }

    @Test
    void test_basicQuery() throws ParseException {
        SearchPredicate queryA = new FindCommandArgumentParser().parse("Tom");
        assertTrue(queryA.test(TEST_PERSON));
        SearchPredicate queryB = new FindCommandArgumentParser().parse("Sam");
        assertFalse(queryB.test(TEST_PERSON));
    }

    @Test
    void test_implicitAndQuery() throws ParseException {
        SearchPredicate queryA = new FindCommandArgumentParser().parse("Tom Harry");
        assertFalse(queryA.test(TEST_PERSON));
        SearchPredicate queryB = new FindCommandArgumentParser().parse("whee twee");
        assertTrue(queryB.test(TEST_PERSON));
    }

    @Test
    void test_explicitOrQuery() throws ParseException {
        SearchPredicate queryA = new FindCommandArgumentParser().parse("Tom | Harry");
        assertTrue(queryA.test(TEST_PERSON));
        SearchPredicate queryB = new FindCommandArgumentParser().parse("Cat | Boy");
        assertFalse(queryB.test(TEST_PERSON));
    }

    @Test
    void test_explicitAndQuery() throws ParseException {
        SearchPredicate queryA = new FindCommandArgumentParser().parse("Tom & Harry");
        assertFalse(queryA.test(TEST_PERSON));
        SearchPredicate queryB = new FindCommandArgumentParser().parse("whee & twee");
        assertTrue(queryB.test(TEST_PERSON));
        SearchPredicate queryC = new FindCommandArgumentParser().parse("ree & whee");
        assertFalse(queryC.test(TEST_PERSON));
    }

    @Test
    void test_operatorPrecedence() throws ParseException {
        // TRUE: Tom whee twee
        // FALSE: Harry Cat ree
        SearchPredicate query;
        query = new FindCommandArgumentParser().parse("Harry Tom | whee twee");
        assertTrue(query.test(TEST_PERSON));
        query = new FindCommandArgumentParser().parse("Tom | whee Harry twee");
        assertTrue(query.test(TEST_PERSON));
        query = new FindCommandArgumentParser().parse("ree & whee | twee");
        assertFalse(query.test(TEST_PERSON));
        query = new FindCommandArgumentParser().parse("whee & ree | twee");
        assertTrue(query.test(TEST_PERSON));
        query = new FindCommandArgumentParser().parse("Tom whee & ree | twee");
        assertTrue(query.test(TEST_PERSON));
        query = new FindCommandArgumentParser().parse("twee | ree & whee Tom");
        assertTrue(query.test(TEST_PERSON));
        query = new FindCommandArgumentParser().parse("twee | whee & Tom ree");
        assertFalse(query.test(TEST_PERSON));
    }

    @Test
    void test_shortCircuitAnd() {
        SearchMatcher errorPredicate = new SearchMatcher() {
            @Override
            FieldRanges test(Map<String, String> p) {
                assert false;
                return null;
            }
        };
        SearchPredicate query = new SearchPredicate(
                new SingleTextSearchMatcher("Jerry")
                        .and(errorPredicate)
        );
        assertFalse(query.test(TEST_PERSON));
    }

    @Test
    void test_nonShortCircuitOr() {
        SearchMatcher errorPredicate = new SearchMatcher() {
            @Override
            FieldRanges test(Map<String, String> p) {
                assert false;
                return null;
            }
        };
        SearchPredicate query = new SearchPredicate(
                new SingleTextSearchMatcher("Tom")
                        .or(errorPredicate)
        );
        assertThrows(AssertionError.class, () -> query.test(TEST_PERSON));
    }

    @Test
    void test_parentheses() throws ParseException {
        // TRUE: Tom whee twee
        // FALSE: Harry Cat ree
        SearchPredicate query;
        query = new FindCommandArgumentParser().parse("Harry (Tom | whee) twee");
        assertFalse(query.test(TEST_PERSON));
        query = new FindCommandArgumentParser().parse("(Tom | whee) Harry twee");
        assertFalse(query.test(TEST_PERSON));
        query = new FindCommandArgumentParser().parse("(ree & whee) | twee");
        assertTrue(query.test(TEST_PERSON));
        query = new FindCommandArgumentParser().parse("twee | (whee & Tom) ree");
        assertTrue(query.test(TEST_PERSON));
    }

    @Test
    void test_nonStandardAcceptableInputs() throws ParseException {
        SearchPredicate query;
        query = new FindCommandArgumentParser().parse("#");
        assertFalse(query.test(TEST_PERSON));
        query = new FindCommandArgumentParser().parse(";");
        assertFalse(query.test(TEST_PERSON));
        query = new FindCommandArgumentParser().parse("\\");
        assertFalse(query.test(TEST_PERSON));
        query = new FindCommandArgumentParser().parse("-");
        assertFalse(query.test(TEST_PERSON));
        query = new FindCommandArgumentParser().parse("abc&123");
        assertTrue(query.test(TEST_PERSON));
    }

    @Test
    void test_nullConstructor() {
        assertDoesNotThrow(() -> new FindCommandArgumentParser().parse(null));
    }

    @Test
    void test_consecutiveJoiners_throw() {
        assertThrows(ParseException.class, () -> new FindCommandArgumentParser().parse("&&"));
    }

}

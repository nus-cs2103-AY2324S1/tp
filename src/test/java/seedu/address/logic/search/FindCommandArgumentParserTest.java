package seedu.address.logic.search;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.*;
import seedu.address.model.tag.Tag;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FindCommandArgumentParserTest {

    static Person testPerson;

    static {
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("hasADogIGuess"));
        tags.add(new Tag("wouldReallyLikeASnake"));
        testPerson = new Person(
                new Name("Tom ABC Whee"),
                new Phone("95728888"),
                new Email("twee.123@company.org"),
                new Address("123 Dawn Park"),
                tags
        );
    }

    @Test
    void test_basicQuery() {
        SearchTest queryA = new FindCommandArgumentParser().parse("Tom");
        assertTrue(queryA.test(testPerson));
        SearchTest queryB = new FindCommandArgumentParser().parse("Sam");
        assertFalse(queryB.test(testPerson));
    }

    @Test
    void test_implicitAndQuery() {
        SearchTest queryA = new FindCommandArgumentParser().parse("Tom Harry");
        assertFalse(queryA.test(testPerson));
        SearchTest queryB = new FindCommandArgumentParser().parse("whee twee");
        assertTrue(queryB.test(testPerson));
    }

    @Test
    void test_explicitOrQuery() {
        SearchTest queryA = new FindCommandArgumentParser().parse("Tom | Harry");
        assertTrue(queryA.test(testPerson));
        SearchTest queryB = new FindCommandArgumentParser().parse("Cat | Boy");
        assertFalse(queryB.test(testPerson));
    }

    @Test
    void test_explicitAndQuery() {
        SearchTest queryA = new FindCommandArgumentParser().parse("Tom & Harry");
        assertFalse(queryA.test(testPerson));
        SearchTest queryB = new FindCommandArgumentParser().parse("whee & twee");
        assertTrue(queryB.test(testPerson));
        SearchTest queryC = new FindCommandArgumentParser().parse("ree & whee");
        assertFalse(queryC.test(testPerson));
    }

    @Test
    void test_operatorPrecedence() {
        // TRUE: Tom whee twee
        // FALSE: Harry Cat ree
        SearchTest query;
        query = new FindCommandArgumentParser().parse("Harry Tom | whee twee");
        assertTrue(query.test(testPerson));
        query = new FindCommandArgumentParser().parse("Tom | whee Harry twee");
        assertTrue(query.test(testPerson));
        query = new FindCommandArgumentParser().parse("ree & whee | twee");
        assertFalse(query.test(testPerson));
        query = new FindCommandArgumentParser().parse("whee & ree | twee");
        assertTrue(query.test(testPerson));
        query = new FindCommandArgumentParser().parse("Tom whee & ree | twee");
        assertTrue(query.test(testPerson));
        query = new FindCommandArgumentParser().parse("twee | ree & whee Tom");
        assertTrue(query.test(testPerson));
        query = new FindCommandArgumentParser().parse("twee | whee & Tom ree");
        assertFalse(query.test(testPerson));
    }

    @Test
    void test_shortCircuitAnd() {
        SearchPredicate errorPredicate = new SearchPredicate() {
            @Override
            FieldRanges test(Map<String, String> p) {
                assert false;
                return null;
            }
        };
        SearchTest query = new SearchTest(
                new SingleTextSearchPredicate("Jerry")
                        .and(errorPredicate)
        );
        assertFalse(query.test(testPerson));
    }

    @Test
    void test_nonShortCircuitOr() {
        SearchPredicate errorPredicate = new SearchPredicate() {
            @Override
            FieldRanges test(Map<String, String> p) {
                assert false;
                return null;
            }
        };
        SearchTest query = new SearchTest(
                new SingleTextSearchPredicate("Tom")
                        .or(errorPredicate)
        );
        assertThrows(AssertionError.class, () -> query.test(testPerson));
    }

    @Test
    void test_parentheses() {
        // TRUE: Tom whee twee
        // FALSE: Harry Cat ree
        SearchTest query;
        query = new FindCommandArgumentParser().parse("Harry (Tom | whee) twee");
        assertFalse(query.test(testPerson));
        query = new FindCommandArgumentParser().parse("(Tom | whee) Harry twee");
        assertFalse(query.test(testPerson));
        query = new FindCommandArgumentParser().parse("(ree & whee) | twee");
        assertTrue(query.test(testPerson));
        query = new FindCommandArgumentParser().parse("twee | (whee & Tom) ree");
        assertTrue(query.test(testPerson));
    }

}
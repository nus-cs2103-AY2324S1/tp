package seedu.address.model.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

public class IdContainsKeywordsPredicateTest {
    @Test
    public void create() {
        List<String> keywords = List.of("keyword1", "keyword2");
        IdContainsKeywordsPredicate predicate = IdContainsKeywordsPredicate.create(keywords);

        IdContainsKeywordsPredicate expected = new IdContainsKeywordsPredicate(keywords);
        assertEquals(expected, predicate);
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        IdContainsKeywordsPredicate predicate = new IdContainsKeywordsPredicate(keywords);

        assertEquals("ID Filter: " + keywords, predicate.toString());
    }
}

package seedu.address.model.predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;

public class ContainsTagPredicateTest {

    @Test
    public void create() {
        Tag tag = Tag.create("CS2103T T02");
        ContainsTagPredicate predicate = ContainsTagPredicate.create(tag);

        ContainsTagPredicate expected = new ContainsTagPredicate(tag);
        assertEquals(expected, predicate);
    }

    @Test
    public void toStringMethod() {
        Tag tag = Tag.create("CS2103T T02");
        ContainsTagPredicate predicate = new ContainsTagPredicate(tag);

        assertEquals("Tag Filter: " + tag.getTagName(), predicate.toString());
    }

}

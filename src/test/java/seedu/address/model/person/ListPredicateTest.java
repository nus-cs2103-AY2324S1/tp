package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.FIELD_BOOKMARKED;
import static seedu.address.logic.parser.CliSyntax.FIELD_HIDDEN;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class ListPredicateTest {

    @Test
    public void test_filterHidden_returnsTrue() {
        ListPredicate listPredicate = new ListPredicate(FIELD_HIDDEN, true);
        assertTrue(listPredicate.test(new PersonBuilder().withHidden(true).build()));
    }

    @Test
    public void test_filterHidden_returnsFalse() {
        ListPredicate listPredicate = new ListPredicate(FIELD_HIDDEN, true);
        assertFalse(listPredicate.test(new PersonBuilder().withHidden(false).build()));
    }

    @Test
    public void test_filterBookmarked_returnsTrue() {
        ListPredicate listPredicate = new ListPredicate(FIELD_BOOKMARKED, true);
        assertTrue(listPredicate.test(new PersonBuilder().withBookmark(true).build()));
    }

    @Test
    public void test_filterBookmarked_returnsFalse() {
        ListPredicate listPredicate = new ListPredicate(FIELD_BOOKMARKED, true);
        assertFalse(listPredicate.test(new PersonBuilder().withBookmark(false).build()));
    }

    @Test
    public void test_filterUndefined_returnsFalse() {
        ListPredicate listPredicate = new ListPredicate("undefined", true);
        assertFalse(listPredicate.test(new PersonBuilder().withBookmark(false).build()));
    }
}

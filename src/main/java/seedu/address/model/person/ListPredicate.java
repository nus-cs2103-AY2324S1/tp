package seedu.address.model.person;

import static seedu.address.logic.parser.CliSyntax.FIELD_BOOKMARKED;
import static seedu.address.logic.parser.CliSyntax.FIELD_HIDDEN;

import java.util.function.Predicate;


/**
 * Tests that a {@code Person}'s specified field matches the boolean given.
 */
public class ListPredicate implements Predicate<Person> {
    private final String fieldName;
    private final boolean filter;

    /**
     * Constructor for ListPredicate.
     * @param fieldName
     * @param filter
     */
    public ListPredicate(String fieldName, boolean filter) {
        this.fieldName = fieldName;
        this.filter = filter;
    }
    @Override
    public boolean test(Person person) {
        switch (fieldName) {
        case FIELD_HIDDEN:
            return person.getIsHidden().value == filter;
        case FIELD_BOOKMARKED:
            return person.getIsBookmarked().value == filter;
        default:
            return false;
        }
    }
}

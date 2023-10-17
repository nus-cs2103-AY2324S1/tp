
package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class ContainsCourseTutorialPredicate implements Predicate<Person> {
    private final Tag tag;

    /**
     * Constructor a {@code ContainsCourseTutorialPredicate} with only keywords.
     *
     * @param tag Tutorial group to filter the contact list for.
     */
    public ContainsCourseTutorialPredicate(Tag tag) {
        this.tag = tag;
    }

    @Override
    public boolean test(Person person) {

        return person.getTags().stream()
                .anyMatch(personTag -> StringUtil.containsWordIgnoreCase(personTag.getTagName(), tag.getTagName()));
    }
}

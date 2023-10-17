
package seedu.address.model.person;

import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class ContainsTutorialGroupPredicate implements Predicate<Person> {
    private final TutorialGroup tutorialGroup;

    /**
     * Constructor a {@code ContainsKeywordsPredicate} with only keywords.
     *
     * @param tutorialGroup Tutorial group to filter the contact list for.
     */
    public ContainsTutorialGroupPredicate(TutorialGroup tutorialGroup) {
        this.tutorialGroup = tutorialGroup;
    }

    @Override
    public boolean test(Person person) {
        return person.getTutorialGroup().equals(tutorialGroup);
    }
}

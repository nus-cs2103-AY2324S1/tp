package networkbook.model.person.filter;

import java.util.List;
import java.util.function.Predicate;

import networkbook.commons.util.CollectionUtil;
import networkbook.model.person.Person;

/**
 * Tests that a Person's grad year equals one of the given years.
 */
public class GradEqualsOneOfPredicate implements Predicate<Person> {
    private final List<Integer> gradYears;

    /**
     * Creates a predicate that returns true for any Person object that has their grad year
     * match any of the years given.
     */
    public GradEqualsOneOfPredicate(List<Integer> gradYears) {
        assert gradYears != null : "List should not be null";
        CollectionUtil.requireAllNonNull(gradYears);
        this.gradYears = gradYears;
    }

    @Override
    public boolean test(Person person) {
        assert person != null : "Person should not be null";
        if (person.getGraduation().isEmpty()) {
            return false;
        }
        return gradYears.stream()
                .anyMatch(year -> year == person.getGraduation().get().getGradYear());
    }
}

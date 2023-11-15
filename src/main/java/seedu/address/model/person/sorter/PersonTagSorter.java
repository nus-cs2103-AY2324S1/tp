package seedu.address.model.person.sorter;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;

/**
 * A sorter for persons in the address book based on the tags.
 * Subclass of {@link PersonSorter}.
 */
public class PersonTagSorter extends PersonSorter {

    /**
     * Constructs a {@code PersonTagSorter}.
     */
    public PersonTagSorter() {
        criterion = "tags-friends";
    }

    @Override
    public int compare(Person person1, Person person2) {
        requireAllNonNull(person1, person2);

        int tagComparison = compareTags(person1.getTags(), person2.getTags());

        if (tagComparison != 0) {
            return tagComparison;
        } else {
            // If the highest priority tag for both persons are similar, sort by name
            PersonNameAscendingSorter nameAscendingSorter = new PersonNameAscendingSorter();
            return nameAscendingSorter.compare(person1, person2);
        }
    }

    /**
     * Compares two sets of tags based on their highest priority tag.
     *
     * @param person1Tags The set of tags for the first person.
     * @param person2Tags The set of tags for the second person.
     * @return A negative integer if person1 has a higher-priority tag,
     *         a positive integer if person2 has a higher-priority tag,
     *         or 0 if their tag priorities are equal.
     */
    public int compareTags(Set<Tag> person1Tags, Set<Tag> person2Tags) {
        int person1TagPriority = getMaxTagPriority(person1Tags);
        int person2TagPriority = getMaxTagPriority(person2Tags);

        return Integer.compare(person1TagPriority, person2TagPriority);
    }

    /**
     * Determines the highest priority tag from a set of tags.
     *
     * @param tags The set of tags to evaluate.
     * @return The highest priority tag, represented as an integer based on a predefined mapping.
     */
    public int getMaxTagPriority(Set<Tag> tags) {
        Map<String, Integer> tagPriority = new HashMap<>();

        tagPriority.put("Close Friend", 1);
        tagPriority.put("Friend", 2);
        tagPriority.put("Emergency", 3);

        int maxPriority = Integer.MAX_VALUE;

        for (Tag tag : tags) {
            String tagName = tag.getTagName();
            if (tagPriority.containsKey(tagName)) {
                maxPriority = Math.min(maxPriority, tagPriority.get(tagName));
            }
        }
        return maxPriority;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PersonTagSorter)) {
            return false;
        }

        PersonTagSorter otherPersonTagSorter = (PersonTagSorter) other;
        return this.getSortingCriterion().equals(otherPersonTagSorter.getSortingCriterion());
    }

    @Override
    public int hashCode() {
        return criterion.hashCode();
    }
}

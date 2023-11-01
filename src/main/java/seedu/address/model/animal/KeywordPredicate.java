package seedu.address.model.animal;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Animal}'s {@code Name} matches any of the keywords given.
 */
public class KeywordPredicate implements Predicate<Animal> {
    private final List<String> keywords;

    public KeywordPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Animal animal) {
        return keywords.stream().allMatch(keyword ->
                StringUtil.containsWordIgnoreCase(animal.getNameForSerialization(), keyword)
                        || StringUtil.containsWordIgnoreCase(animal.getPetIdForSerialization(), keyword)
                        || StringUtil.containsWordIgnoreCase(animal.getDateOfBirthForSerialization(), keyword)
                        || StringUtil.containsWordIgnoreCase(animal.getAdmissionDateForSerialization(), keyword)
                        || StringUtil.containsWordIgnoreCase(animal.getSpeciesForSerialization(), keyword)
                        || StringUtil.containsWordIgnoreCase(animal.getSexForSerialization(), keyword)
                        || StringUtil.containsWordIgnoreCase(animal.getBreedForSerialization(), keyword)
        );
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof KeywordPredicate // instanceof handles nulls
                && keywords.equals(((KeywordPredicate) other).keywords)); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}

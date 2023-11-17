package seedu.address.model.animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

        List<String> stringAttributes = new ArrayList<>(animal.attributes());

        assert(keywords.size() == stringAttributes.size()) : "keywords was not parsed properly";

        List<String> keywordsCopy = new ArrayList<>(keywords);
        keywordsCopy = keywordsCopy.stream()
                .filter(keyword -> !keyword.isEmpty()).collect(Collectors.toList());
        assert(!keywordsCopy.isEmpty()) : "keywords is not supposed to be empty";

        return keywords.stream().allMatch(keyword -> {
            if (keyword.isEmpty()) {
                stringAttributes.remove(0);
                return true;
            } else {
                String attribute = stringAttributes.remove(0);
                return StringUtil.containsPhraseIgnoreCase(attribute, keyword);
            }
        });
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

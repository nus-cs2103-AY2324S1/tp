package seedu.address.model.team;

import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Pattern;

/**
 * Tests that a {@code Team}'s name matches any of the keywords given.
 */
public class TeamContainsKeywordsPredicate implements Predicate<Team> {
    private final List<String> keywords;

    public TeamContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Team team) {
        return keywords.stream()
                .anyMatch(keyword -> containsWordIgnoreCase(team.getTeamName(), keyword));
    }

    private static boolean containsWordIgnoreCase(String source, String query) {
        String pattern = "\\b" + Pattern.quote(query) + "\\b"; // \b denotes a word boundary
        return Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(source).find();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TeamContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TeamContainsKeywordsPredicate) other).keywords)); // state check
    }

    @Override
    public String toString() {
        return String.join(", ", keywords);
    }
}

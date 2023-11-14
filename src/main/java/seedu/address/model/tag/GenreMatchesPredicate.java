package seedu.address.model.tag;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.musician.Musician;

/**
 * Tests that a {@code Musician}'s {@code Genre} tag matches any of the keywords given.
 */
public class GenreMatchesPredicate implements Predicate<Musician> {

    private final List<String> genres;

    public GenreMatchesPredicate(List<String> genres) {
        this.genres = genres;
    }

    @Override
    public boolean test(Musician musician) {
        return genres.stream()
                .anyMatch(genreToMatch -> musician.getGenres().stream().anyMatch(
                        musicianGenre -> StringUtil.containsWordIgnoreCase(musicianGenre.tagName, genreToMatch)
                ));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof GenreMatchesPredicate)) {
            return false;
        }

        GenreMatchesPredicate otherGenreMatchesPredicate = (GenreMatchesPredicate) other;
        return genres.equals(otherGenreMatchesPredicate.genres);
    }

    @Override
    public int hashCode() {
        return genres.hashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("genres", genres).toString();
    }

}

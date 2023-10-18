package seedu.address.model.tag;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.musician.Musician;

/**
 * Tests that a {@code Musician}'s {@code Genre} tag matches any of the keywords given.
 */
public class GenreMatchesPredicate extends TagMatchesPredicate {

    public GenreMatchesPredicate(List<String> genre) {
        super(genre);
    }

    @Override
    public boolean test(Musician musician) {
        List<String> genres = super.getTagNames();
        return genres.stream()
                .anyMatch(genreToMatch -> musician.getGenres().stream().anyMatch(
                        musicianGenre -> genreToMatch.equals(musicianGenre.tagName)
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

        return super.equals(other);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("genres", super.getTagNames()).toString();
    }

}

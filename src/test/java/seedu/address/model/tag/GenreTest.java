package seedu.address.model.tag;

import static seedu.address.model.tag.Genre.VALID_GENRES;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class GenreTest extends TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Genre(null));
    }

    @Test
    public void constructor_invalidGenreName_throwsIllegalArgumentException() {
        String invalidGenreName = "foo";
        if (!VALID_GENRES.contains(invalidGenreName)) {
            assertThrows(IllegalArgumentException.class, () -> new Genre(invalidGenreName));
        }
    }
}

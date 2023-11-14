package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
public class FavouriteTest {

    @Test
    public void isValidFavourite() {
        // null favourite
        assertThrows(NullPointerException.class, () -> Favourite.isValidFavourite(null));

        // invalid favourite
        assertFalse(Favourite.isValidFavourite("")); // empty string
        assertFalse(Favourite.isValidFavourite(" ")); // spaces only
        assertFalse(Favourite.isValidFavourite("truefalse")); // true and false
        assertFalse(Favourite.isValidFavourite("True")); // capital letters

        // valid favourite
        assertTrue(Favourite.isValidFavourite("true")); // true
        assertTrue(Favourite.isValidFavourite("false")); // false
    }

    @Test
    public void getFavourite() {
        Favourite favourite = new Favourite(true);
        assertTrue(favourite.getFavourite());
    }

    @Test
    public void setFavourite() {
        Favourite favourite = new Favourite(false);
        favourite.setFavourite();
        assertTrue(favourite.getFavourite());
    }

    @Test
    public void equals() {
        Favourite favourite = new Favourite(true);

        // same values -> returns true
        assertTrue(favourite.equals(new Favourite(true)));

        // same object -> returns true
        assertTrue(favourite.equals(favourite));

        // null -> returns false
        assertFalse(favourite.equals(null));

        // different types -> returns false
        assertFalse(favourite.equals(5.0f));

        // different values -> returns false
        assertFalse(favourite.equals(new Favourite(false)));
    }
}

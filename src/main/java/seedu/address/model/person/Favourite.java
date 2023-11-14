package seedu.address.model.person;

/**
 * Represents the boolean Favourite in a Person.
 * Guarantees: immutable
 */
public class Favourite {

    public static final String MESSAGE_CONSTRAINTS = "Favourite should be either true or false.";

    private boolean isFavourite;

    /**
    * Constructs a {@code Favourite}.
    */
    public Favourite(boolean isFavourite) {
        this.isFavourite = isFavourite;
    }

    public static boolean isValidFavourite(String test) {
        return test.equals("true") || test.equals("false");
    }

    /**
     * Returns the favourite status of the person.
     */
    public boolean getFavourite() {
        return this.isFavourite;
    }

    /**
     * Sets the favourite to true.
     */
    public void setFavourite() {
        this.isFavourite = true;
    }

    /**
     * Sets the favourite to false.
     */
    public void setUnfavourite() {
        this.isFavourite = false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Favourite)) {
            return false;
        }

        Favourite otherFavourite = (Favourite) other;
        return isFavourite == otherFavourite.isFavourite;
    }

    @Override
    public int hashCode() {
        return this.hashCode();
    }

}

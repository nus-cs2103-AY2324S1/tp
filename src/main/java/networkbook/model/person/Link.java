package networkbook.model.person;

import static java.util.Objects.requireNonNull;
import static networkbook.commons.util.AppUtil.checkArgument;

import networkbook.model.util.Identifiable;

/**
 * Represents a Person's web link in the network book.
 * Guarantees: immutable; is valid as declared in {@link #isValidLink(String)}
 */
public class Link implements Identifiable<Link> {

    public static final String MESSAGE_CONSTRAINTS = "Web links should follow the format: domain/path "
            + "and adhere to the following constraints:\n"
            + "1. The domain should adhere to these rules:\n"
            + "    - It must end with a domain label of at least 2 characters.\n"
            + "    - Each domain label should start and end with alphanumeric characters.\n"
            + "    - Domain labels may consist of alphanumeric characters, "
            + "separated by hyphens if needed.\n"
            + "2. The path, if included, should be a valid URI path.";
    private static final String SPECIAL_CHARACTERS = "+_.-";

    // alphanumeric and special characters

    private static final String DOMAIN_NAME_REGEX = "^(http://|https://)?(www\\.)?[a-zA-Z0-9-]+\\.[a-z]{2,}";
    private static final String PATH_REGEX = "(/[\\w-]+)*"; // Valid URI path

    public static final String VALIDATION_REGEX = DOMAIN_NAME_REGEX + "(" + PATH_REGEX + ")?$";

    private final String value;

    /**
     * Constructs an {@code Link}.
     *
     * @param link A valid web link.
     */
    public Link(String link) {
        requireNonNull(link);
        checkArgument(isValidLink(link), MESSAGE_CONSTRAINTS);
        value = link;
    }

    /**
     * Returns if a given string is a valid link.
     */
    public static boolean isValidLink(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean isSame(Link another) {
        return this.value.equals(another.value);
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Link)) {
            return false;
        }

        Link otherLink = (Link) other;
        return value.equals(otherLink.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}

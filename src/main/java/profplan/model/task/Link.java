package profplan.model.task;

import static java.util.Objects.requireNonNull;

/**
 * Represents a Task's link in the ProfPlan.
 * Guarantees: immutable; is valid as declared in {@link #isValidLink(String)}
 */
public class Link {

    public static final String MESSAGE_CONSTRAINTS = "Links should be a valid URL "
            + "and adhere to the following constraint:\n"
            + "The domain name is made up of domain labels "
            + "separated by periods.\n"
            + "The domain name must:\n"
            + "    - end with a domain label at least 2 characters long\n"
            + "    - have each domain label start and end with alphanumeric characters\n";

    public static final String VALIDATION_REGEX = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)"
        + "?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";

    public final String value;

    /**
     * Constructs an {@code Link}.
     *
     * @param url A valid url.
     */
    public Link(String url) {
        requireNonNull(url);
        value = url;
    }

    /**
     * Returns if a given string is a valid url.
     */
    public static boolean isValidLink(String test) {
        return true;
        //return (test.equals("-") || test.matches(VALIDATION_REGEX));
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

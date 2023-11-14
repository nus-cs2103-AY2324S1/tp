package seedu.address.model.socialmedialink;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents the Social Media Links in StudentConnect.
 * Guarantees: immutable; name is valid as declared in {@link #isValidSocialMediaLink(String)}
 */
public class SocialMediaLink {

    public static final String MESSAGE_CONSTRAINTS = "Social media links should start with \"http://\", or "
            + "\"https://\", followed by one or more alphanumeric characters, dots, or hyphens in the domain name";
    public static final String VALIDATION_REGEX = "^(https?|ftp)://[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}(:[0-9]+)?(/.*)?$";

    public final String socialMediaLink;

    /**
     * Constructs a {@code SocialMedia}.
     *
     * @param socialMediaLink A valid social media link.
     */
    public SocialMediaLink(String socialMediaLink) {
        requireNonNull(socialMediaLink);
        checkArgument(isValidSocialMediaLink(socialMediaLink), MESSAGE_CONSTRAINTS);
        this.socialMediaLink = socialMediaLink;
    }

    /**
     * Returns true if a given string is a valid social media link.
     */
    public static boolean isValidSocialMediaLink(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SocialMediaLink)) {
            return false;
        }

        SocialMediaLink otherSocialMedia = (SocialMediaLink) other;
        return socialMediaLink.equals(otherSocialMedia.socialMediaLink);
    }

    @Override
    public int hashCode() {
        return socialMediaLink.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return '[' + socialMediaLink + ']';
    }
}

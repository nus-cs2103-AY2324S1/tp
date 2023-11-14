package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.socialmedialink.SocialMediaLink;

/**
 * Jackson-friendly version of {@link SocialMediaLink}.
 */
public class JsonAdaptedSocialMedia {

    private final String socialMediaLink;

    /**
     * Constructs a {@code JsonAdaptedSocialMedia} with the given {@code socialMediaLink}.
     */
    @JsonCreator
    public JsonAdaptedSocialMedia(String socialMediaLink) {
        this.socialMediaLink = socialMediaLink;
    }

    /**
     * Converts a given {@code SocialMedia} into this class for Jackson use.
     */
    public JsonAdaptedSocialMedia(SocialMediaLink source) {
        socialMediaLink = source.socialMediaLink;
    }

    @JsonValue
    public String getSocialMediaLink() {
        return socialMediaLink;
    }

    /**
     * Converts this Jackson-friendly adapted social media object into the model's {@code SocialMedia} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted social media.
     */
    public SocialMediaLink toModelType() throws IllegalValueException {
        if (!SocialMediaLink.isValidSocialMediaLink(socialMediaLink)) {
            throw new IllegalValueException(SocialMediaLink.MESSAGE_CONSTRAINTS);
        }
        return new SocialMediaLink(socialMediaLink);
    }
}

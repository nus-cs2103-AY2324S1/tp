package seedu.address.model.socialmedialink;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SocialMediaLinkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new SocialMediaLink(null));
    }

    @Test
    public void constructor_invalidSocialMediaLink_throwsIllegalArgumentException() {
        String invalidSocialMediaLink = "";
        assertThrows(IllegalArgumentException.class, () -> new SocialMediaLink(invalidSocialMediaLink));
    }

    @Test
    public void isValidSocialMediaLink() {
        // null social media link
        assertThrows(NullPointerException.class, () -> SocialMediaLink.isValidSocialMediaLink(null));
    }
}

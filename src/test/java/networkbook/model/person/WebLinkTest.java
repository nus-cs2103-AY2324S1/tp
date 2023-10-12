package networkbook.model.person;

import static networkbook.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class WebLinkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new WebLink(null));
    }

    @Test
    public void constructor_invalidLink_throwsIllegalArgumentException() {
        String invalidLink = "";
        assertThrows(IllegalArgumentException.class, () -> new Email(invalidLink));
    }

    @Test
    public void isValidLink() {
        // null email
        assertThrows(NullPointerException.class, () -> WebLink.isValidLink(null));

        // blank email
        assertFalse(WebLink.isValidLink("")); // empty string
        assertFalse(WebLink.isValidLink(" ")); // spaces only

        // missing parts
        assertFalse(WebLink.isValidLink("example.")); // missing local part
        assertFalse(WebLink.isValidLink("peterjack@")); // missing domain label

        // invalid parts
        assertFalse(WebLink.isValidLink("peterjack@-.com")); // invalid domain name
        assertFalse(WebLink.isValidLink("exam_ple.com")); // underscore in domain name

        // valid link
        assertTrue(WebLink.isValidLink("example1.com"));
    }

    @Test
    public void equals() {
        WebLink webLink = new WebLink("linkedin.com/in/aaron");

        // same values -> returns true
        assertTrue(webLink.equals(new WebLink("linkedin.com/in/aaron")));

        // same object -> returns true
        assertTrue(webLink.equals(webLink));

        // null -> returns false
        assertFalse(webLink.equals(null));

        // different types -> returns false
        assertFalse(webLink.equals(5.0f));

        // different values -> returns false
        assertFalse(webLink.equals(new WebLink("linkedin.com/in/colin")));
    }

    @Test
    public void isSame_sameLinks_returnsTrue() {
        assertTrue(new WebLink("facebook.com/colin").isSame(new WebLink("facebook.com/colin")));
        assertTrue(new WebLink("www.google.com").isSame(new WebLink("www.google.com")));
    }

    @Test
    public void isSame_differentLinks_returnsFalse() {
        assertFalse(new WebLink("facebook.com/colin").isSame(new WebLink("facebook.com/aaron")));
        assertFalse(new WebLink("www.google.com").isSame(new WebLink("www.exam.com")));
    }
}

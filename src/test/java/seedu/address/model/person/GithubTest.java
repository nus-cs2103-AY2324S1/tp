package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GithubTest {

    @Test
    public void equals() {
        Github github = new Github("alexyeoh");
        assertTrue(github.equals(new Github("alexyeoh")));

        assertTrue(github.equals(github));

        assertFalse(github.equals(5.0f));

        assertFalse(github.equals(new Github("zhiwang")));
    }

    @Test
    public void toStringMethod() {
        Github github = new Github("alexyeoh");
        String s = "alexyeoh";
        assertEquals(github.toString(), s);
    }
}

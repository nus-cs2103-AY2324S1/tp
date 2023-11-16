package seedu.address.model.project;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ProjectTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Project(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Project(invalidTagName));
    }

    /*
    @Test
    public void isValidProjectName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Project.isValidProjectName(null));
    }
    */
}

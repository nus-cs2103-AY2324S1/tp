package seedu.address.model.localcourse;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class LocalCourseAttributeTest {
    @Test
    public void isValidLocalCourseAttributeForSearch() {
        // invalid localCourseAttribute
        assertFalse(LocalCourseAttribute.isValidAttributeForSearch(""));
        assertFalse(LocalCourseAttribute.isValidAttributeForSearch(" "));
        assertFalse(LocalCourseAttribute.isValidAttributeForSearch("a"));
        assertFalse(LocalCourseAttribute.isValidAttributeForSearch("123"));
        assertFalse(LocalCourseAttribute.isValidAttributeForSearch("LOCAL"));
        assertFalse(LocalCourseAttribute.isValidAttributeForSearch("localunit"));
        assertFalse(LocalCourseAttribute.isValidAttributeForSearch(null));
        assertFalse(LocalCourseAttribute.isValidAttributeForSearch("LOCALCODE"));
        assertFalse(LocalCourseAttribute.isValidAttributeForSearch("LOCALNAME"));
        assertFalse(LocalCourseAttribute.isValidAttributeForSearch("LOCALUNIT"));
        assertFalse(LocalCourseAttribute.isValidAttributeForSearch("LOCALDESCRIPTION"));

        // valid localCourseAttribute
        assertTrue(LocalCourseAttribute.isValidAttributeForSearch("localcode"));
        assertTrue(LocalCourseAttribute.isValidAttributeForSearch("localname"));
        assertTrue(LocalCourseAttribute.isValidAttributeForSearch("localdescription"));

        // Additional invalid cases
        assertFalse(LocalCourseAttribute.isValidAttributeForSearch("local code"));
        assertFalse(LocalCourseAttribute.isValidAttributeForSearch("LocalCode"));
        assertFalse(LocalCourseAttribute.isValidAttributeForSearch("LocalName"));
    }

    @Test
    public void isValidLocalCourseAttributeForSort() {
        // invalid localCourseAttribute
        assertFalse(LocalCourseAttribute.isValidAttributeForSort(""));
        assertFalse(LocalCourseAttribute.isValidAttributeForSort(" "));
        assertFalse(LocalCourseAttribute.isValidAttributeForSort("a"));
        assertFalse(LocalCourseAttribute.isValidAttributeForSort("123"));
        assertFalse(LocalCourseAttribute.isValidAttributeForSort("LOCAL"));
        assertFalse(LocalCourseAttribute.isValidAttributeForSort("localunit"));
        assertFalse(LocalCourseAttribute.isValidAttributeForSort(null));
        assertFalse(LocalCourseAttribute.isValidAttributeForSort("LOCALCODE"));
        assertFalse(LocalCourseAttribute.isValidAttributeForSort("LOCALNAME"));
        assertFalse(LocalCourseAttribute.isValidAttributeForSort("LOCALUNIT"));
        assertFalse(LocalCourseAttribute.isValidAttributeForSort("LOCALDESCRIPTION"));

        // valid localCourseAttribute
        assertTrue(LocalCourseAttribute.isValidAttributeForSort("localcode"));
        assertTrue(LocalCourseAttribute.isValidAttributeForSort("localname"));

        // Additional invalid cases
        assertFalse(LocalCourseAttribute.isValidAttributeForSort("local code"));
        assertFalse(LocalCourseAttribute.isValidAttributeForSort("LocalCode"));
        assertFalse(LocalCourseAttribute.isValidAttributeForSort("LocalName"));
        assertFalse(LocalCourseAttribute.isValidAttributeForSort("localunit"));
        assertFalse(LocalCourseAttribute.isValidAttributeForSort("localdescription"));
    }

    @Test
    public void isValidLocalCourseAttributeForUpdate() {
        // invalid localCourseAttribute
        assertFalse(LocalCourseAttribute.isValidAttributeForUpdate(""));
        assertFalse(LocalCourseAttribute.isValidAttributeForUpdate(" "));
        assertFalse(LocalCourseAttribute.isValidAttributeForUpdate("a"));
        assertFalse(LocalCourseAttribute.isValidAttributeForUpdate("123"));
        assertFalse(LocalCourseAttribute.isValidAttributeForUpdate("LOCAL"));
        assertFalse(LocalCourseAttribute.isValidAttributeForUpdate(null));
        assertFalse(LocalCourseAttribute.isValidAttributeForUpdate("LOCALCODE"));
        assertFalse(LocalCourseAttribute.isValidAttributeForUpdate("LOCALNAME"));
        assertFalse(LocalCourseAttribute.isValidAttributeForUpdate("LOCALUNIT"));
        assertFalse(LocalCourseAttribute.isValidAttributeForUpdate("LOCALDESCRIPTION"));

        // valid localCourseAttribute
        assertTrue(LocalCourseAttribute.isValidAttributeForUpdate("localcode"));
        assertTrue(LocalCourseAttribute.isValidAttributeForUpdate("localname"));
        assertTrue(LocalCourseAttribute.isValidAttributeForUpdate("localunit"));
        assertTrue(LocalCourseAttribute.isValidAttributeForUpdate("localdescription"));

        // Additional invalid cases
        assertFalse(LocalCourseAttribute.isValidAttributeForUpdate("local code"));
        assertFalse(LocalCourseAttribute.isValidAttributeForUpdate("LocalCode"));
        assertFalse(LocalCourseAttribute.isValidAttributeForUpdate("LocalName"));
    }
}

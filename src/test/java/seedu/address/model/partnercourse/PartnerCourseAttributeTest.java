package seedu.address.model.partnercourse;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PartnerCourseAttributeTest {
    @Test
    public void isValidPartnerCourseAttributeForSearch() {
        // invalid partnerCourseAttribute
        assertFalse(PartnerCourseAttribute.isValidAttributeForSearch(""));
        assertFalse(PartnerCourseAttribute.isValidAttributeForSearch(" "));
        assertFalse(PartnerCourseAttribute.isValidAttributeForSearch("a"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForSearch("123"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForSearch("PARTNER"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForSearch(null));
        assertFalse(PartnerCourseAttribute.isValidAttributeForSearch("PARTNERCODE"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForSearch("PARTNERNAME"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForSearch("UNIT"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForSearch("DESCRIPTION"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForSearch("UNIVERSITY"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForSearch("partner code"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForSearch("PartnerCode"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForSearch("PartnerName"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForSearch("unit"));

        // valid partnerCourseAttribute
        assertTrue(PartnerCourseAttribute.isValidAttributeForSearch("partnercode"));
        assertTrue(PartnerCourseAttribute.isValidAttributeForSearch("partnername"));
        assertTrue(PartnerCourseAttribute.isValidAttributeForSearch("description"));
        assertTrue(PartnerCourseAttribute.isValidAttributeForSearch("university"));
    }

    @Test
    public void isValidPartnerCourseAttributeForSort() {
        // invalid partnerCourseAttribute
        assertFalse(PartnerCourseAttribute.isValidAttributeForSort(""));
        assertFalse(PartnerCourseAttribute.isValidAttributeForSort(" "));
        assertFalse(PartnerCourseAttribute.isValidAttributeForSort("a"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForSort("123"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForSort("PARTNER"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForSort(null));
        assertFalse(PartnerCourseAttribute.isValidAttributeForSort("PARTNERCODE"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForSort("PARTNERNAME"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForSort("UNIT"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForSort("DESCRIPTION"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForSort("UNIVERSITY"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForSort("partner code"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForSort("PartnerCode"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForSort("PartnerName"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForSort("unit"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForSort("description"));

        // valid partnerCourseAttribute
        assertTrue(PartnerCourseAttribute.isValidAttributeForSort("partnercode"));
        assertTrue(PartnerCourseAttribute.isValidAttributeForSort("partnername"));
        assertTrue(PartnerCourseAttribute.isValidAttributeForSort("university"));
    }

    @Test
    public void isValidPartnerCourseAttributeForUpdate() {
        // invalid partnerCourseAttribute
        assertFalse(PartnerCourseAttribute.isValidAttributeForUpdate(""));
        assertFalse(PartnerCourseAttribute.isValidAttributeForUpdate(" "));
        assertFalse(PartnerCourseAttribute.isValidAttributeForUpdate("a"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForUpdate("123"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForUpdate("PARTNER"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForUpdate(null));
        assertFalse(PartnerCourseAttribute.isValidAttributeForUpdate("PARTNERCODE"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForUpdate("PARTNERNAME"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForUpdate("UNIT"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForUpdate("DESCRIPTION"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForUpdate("UNIVERSITY"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForUpdate("partner code"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForUpdate("PartnerCode"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForUpdate("PartnerName"));
        assertFalse(PartnerCourseAttribute.isValidAttributeForUpdate("university"));

        // valid partnerCourseAttribute
        assertTrue(PartnerCourseAttribute.isValidAttributeForUpdate("partnercode"));
        assertTrue(PartnerCourseAttribute.isValidAttributeForUpdate("partnername"));
        assertTrue(PartnerCourseAttribute.isValidAttributeForUpdate("unit"));
        assertTrue(PartnerCourseAttribute.isValidAttributeForUpdate("description"));
    }
}

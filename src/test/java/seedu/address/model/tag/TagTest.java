package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    void testIsValidPatientTagName() {
        assertTrue(Tag.isValidPatientTagName("LOW"));
        assertTrue(Tag.isValidPatientTagName("MEDIUM"));
        assertTrue(Tag.isValidPatientTagName("HIGH"));
        assertFalse(Tag.isValidPatientTagName("URGENT"));
    }

    @Test
    void testIsValidFullPatientTagName() {
        assertTrue(Tag.isValidFullPatientTagName("priority: LOW"));
        assertFalse(Tag.isValidFullPatientTagName("LOW"));
        assertFalse(Tag.isValidFullPatientTagName("priority: URGENT"));
    }

    @Test
    void testIsValidDoctorTagName() {
        assertTrue(Tag.isValidDoctorTagName("CARDIOLOGIST"));
        assertTrue(Tag.isValidDoctorTagName("SURGEON"));
        assertFalse(Tag.isValidDoctorTagName("NURSE"));
    }

    @Test
    void testIsValidPatientTag() {
        Tag validTag = new Tag("priority: HIGH");
        Tag invalidTag = new Tag("HIGH");
        assertTrue(validTag.isValidPatientTag());
        assertFalse(invalidTag.isValidPatientTag());
    }

    @Test
    void testIsValidDoctorTag() {
        Tag validTag = new Tag("CARDIOLOGIST");
        Tag invalidTag = new Tag("NURSE");
        assertTrue(validTag.isValidDoctorTag());
        assertFalse(invalidTag.isValidDoctorTag());
    }

    @Test
    void testEquals() {
        Tag tag1 = new Tag("CARDIOLOGIST");
        Tag tag2 = new Tag("CARDIOLOGIST");
        Tag tag3 = new Tag("SURGEON");
        assertEquals(tag1, tag2);
        assertNotEquals(tag1, tag3);
    }

    @Test
    void testHashCode() {
        Tag tag1 = new Tag("CARDIOLOGIST");
        Tag tag2 = new Tag("CARDIOLOGIST");
        assertEquals(tag1.hashCode(), tag2.hashCode());
    }

    @Test
    void testToString() {
        Tag tag = new Tag("CARDIOLOGIST");
        assertEquals("[CARDIOLOGIST]", tag.toString());
    }

}

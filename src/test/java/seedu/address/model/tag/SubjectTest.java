package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SubjectTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Subject(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Subject(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Subject.isValidSubjectName(null));
    }

    @Test
    public void equals() {
        Subject subject1 = new Subject(Subject.CHEMI);
        Subject subject2 = new Subject(Subject.CHEMI);
        Subject subject3 = new Subject(Subject.ENG);

        //same values -> equal
        assertEquals(subject1, subject2);

        //null -> not equal
        assertNotEquals(subject1, null);

        // different values -> not equal
        assertNotEquals(subject1, subject3);

        // different type -> return false
        assertFalse(subject3.equals(0.5f));
    }

    @Test
    public void constructor_validSubjectName_success() {
        Subject english = new Subject("enGLISH");
        Subject chemistry = new Subject("cHEmisTRY");
        Subject physics = new Subject("PHYSICS");
        Subject aMaths = new Subject("Additional Mathematics");
        Subject socialStudies = new Subject("soCIal STUdies");

        assertEquals(Subject.ENG, english.getSubjectName());
        assertEquals(Subject.CHEMI, chemistry.getSubjectName());
        assertEquals(Subject.PHY, physics.getSubjectName());
        assertEquals(Subject.AMATH, aMaths.getSubjectName());
        assertEquals(Subject.SOC, socialStudies.getSubjectName());
    }

    @Test
    public void getSubjectNameMethod() {
        Subject subject1 = new Subject(Subject.CHEMI);
        assertEquals(subject1.getSubjectName(), Subject.CHEMI);
        assertFalse(subject1.getSubjectName().equals(" "));
    }

    @Test
    public void getEnrolDateMethod() {
        Subject subject = new Subject(Subject.BIO, new EnrolDate("Jul 2023"));
        assertEquals(new EnrolDate("Jul 2023"), subject.getEnrolDate());

    }
}

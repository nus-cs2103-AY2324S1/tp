package networkbook.logic.commands.edit;

import static networkbook.testutil.Assert.assertThrows;
import static networkbook.testutil.TypicalPersons.JACK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import networkbook.logic.commands.exceptions.CommandException;
import networkbook.model.person.Course;
import networkbook.model.person.Person;
import networkbook.model.util.UniqueList;

public class EditCourseActionTest {
    private static final EditCourseAction SAMPLE_VALID_EDIT_COURSE_ACTION =
            new EditCourseAction(EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_COURSE);
    private static final EditCourseAction SAMPLE_INVALID_EDIT_COURSE_ACTION =
            new EditCourseAction(EditCommandUtil.INVALID_INDEX, EditCommandUtil.VALID_COURSE);

    @Test
    public void equalsTest() {
        assertFalse(new EditCourseAction(EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_COURSE).equals(1));
        assertFalse(new EditCourseAction(EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_COURSE).equals(null));
        assertFalse(new EditCourseAction(
                EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_COURSE).equals(new Object()));

        assertTrue(SAMPLE_VALID_EDIT_COURSE_ACTION.equals(SAMPLE_VALID_EDIT_COURSE_ACTION));
        assertTrue(new EditCourseAction(EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_COURSE)
                .equals(SAMPLE_VALID_EDIT_COURSE_ACTION));
    }

    @Test
    public void toStringTest() {
        String expected = EditCourseAction.class.getCanonicalName() + "{course=" + EditCommandUtil.VALID_COURSE
                + ", index=" + EditCommandUtil.VALID_INDEX.getOneBased() + "}";
        assertEquals(SAMPLE_VALID_EDIT_COURSE_ACTION.toString(), expected);
    }

    @Test
    public void edit_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> SAMPLE_VALID_EDIT_COURSE_ACTION.edit(null));
    }

    @Test
    public void edit_validEditAction_success() throws CommandException {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        SAMPLE_VALID_EDIT_COURSE_ACTION.edit(actualDescriptor);

        UniqueList<Course> newCourseList = JACK.getCourses();
        newCourseList.setItem(EditCommandUtil.VALID_INDEX.getZeroBased(), EditCommandUtil.VALID_COURSE);

        Person expectedPerson = new Person(
                JACK.getName(),
                JACK.getPhones(),
                JACK.getEmails(),
                JACK.getLinks(),
                JACK.getGraduation().orElse(null),
                newCourseList,
                JACK.getSpecialisations(),
                JACK.getTags(),
                JACK.getPriority().orElse(null)
        );
        EditPersonDescriptor expectedDescriptor = new EditPersonDescriptor(expectedPerson);
        assertEquals(expectedDescriptor, actualDescriptor);
    }

    @Test
    public void edit_invalidEditAction_throwsCommandException() {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        assertThrows(CommandException.class, () -> SAMPLE_INVALID_EDIT_COURSE_ACTION.edit(actualDescriptor));
    }
}

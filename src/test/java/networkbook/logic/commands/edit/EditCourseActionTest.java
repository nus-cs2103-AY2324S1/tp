package networkbook.logic.commands.edit;

import static networkbook.testutil.Assert.assertThrowsAssertionError;
import static networkbook.testutil.TypicalPersons.JACK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import networkbook.logic.Messages;
import networkbook.logic.commands.exceptions.CommandException;
import networkbook.model.person.Course;
import networkbook.model.person.Person;
import networkbook.model.util.UniqueList;
import networkbook.testutil.TypicalIndexes;

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
    public void edit_null_throwsAssertionError() {
        assertThrowsAssertionError(() -> SAMPLE_VALID_EDIT_COURSE_ACTION.edit(null,
                TypicalIndexes.INDEX_FIRST_PERSON));
    }

    @Test
    public void edit_validEditAction_success() throws CommandException {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        SAMPLE_VALID_EDIT_COURSE_ACTION.edit(actualDescriptor, TypicalIndexes.INDEX_FIRST_PERSON);

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
        assertThrows(CommandException.class, () -> SAMPLE_INVALID_EDIT_COURSE_ACTION.edit(actualDescriptor,
                TypicalIndexes.INDEX_FIRST_PERSON),
                String.format(Messages.MESSAGE_INVALID_MULTIVALUED_FIELD_ENTRY_INDEX,
                        TypicalIndexes.INDEX_FIRST_PERSON.getOneBased(), "a course",
                        EditCommandUtil.INVALID_INDEX.getOneBased()));
    }
}

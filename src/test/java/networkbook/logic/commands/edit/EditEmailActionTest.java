package networkbook.logic.commands.edit;

import static networkbook.testutil.Assert.assertThrows;
import static networkbook.testutil.TypicalPersons.JACK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import networkbook.logic.commands.exceptions.CommandException;
import networkbook.model.person.Email;
import networkbook.model.person.Person;
import networkbook.model.util.UniqueList;

public class EditEmailActionTest {
    private static final EditEmailAction SAMPLE_VALID_EDIT_COURSE_ACTION =
            new EditEmailAction(EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_EMAIL);
    private static final EditEmailAction SAMPLE_INVALID_EDIT_COURSE_ACTION =
            new EditEmailAction(EditCommandUtil.INVALID_INDEX, EditCommandUtil.VALID_EMAIL);

    @Test
    public void equalsTest() {
        assertFalse(new EditEmailAction(EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_EMAIL).equals(1));
        assertFalse(new EditEmailAction(EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_EMAIL).equals(null));
        assertFalse(new EditEmailAction(EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_EMAIL).equals(new Object()));

        assertTrue(SAMPLE_VALID_EDIT_COURSE_ACTION.equals(SAMPLE_VALID_EDIT_COURSE_ACTION));
        assertTrue(new EditEmailAction(EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_EMAIL)
                .equals(SAMPLE_VALID_EDIT_COURSE_ACTION));
    }

    @Test
    public void toStringTest() {
        String expected = EditEmailAction.class.getCanonicalName() + "{email=" + EditCommandUtil.VALID_EMAIL
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

        UniqueList<Email> newEmailList = JACK.getEmails();
        newEmailList.setItem(EditCommandUtil.VALID_INDEX.getZeroBased(), EditCommandUtil.VALID_EMAIL);

        Person expectedPerson = new Person(
                JACK.getName(),
                JACK.getPhones(),
                newEmailList,
                JACK.getLinks(),
                JACK.getGraduation().orElse(null),
                JACK.getCourses(),
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

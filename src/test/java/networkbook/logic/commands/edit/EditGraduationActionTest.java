package networkbook.logic.commands.edit;

import static networkbook.testutil.Assert.assertThrows;
import static networkbook.testutil.TypicalPersons.JACK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import networkbook.logic.commands.exceptions.CommandException;
import networkbook.model.person.Graduation;
import networkbook.model.person.Person;

public class EditGraduationActionTest {
    private static final EditGraduationAction SAMPLE_VALID_EDIT_GRADUATION_ACTION =
            new EditGraduationAction(EditCommandUtil.VALID_GRADUATION);

    @Test
    public void equalsTest() {
        assertFalse(new EditGraduationAction(EditCommandUtil.VALID_GRADUATION).equals(1));
        assertFalse(new EditGraduationAction(EditCommandUtil.VALID_GRADUATION).equals(null));
        assertFalse(new EditGraduationAction(EditCommandUtil.VALID_GRADUATION).equals(new Object()));

        assertTrue(SAMPLE_VALID_EDIT_GRADUATION_ACTION.equals(SAMPLE_VALID_EDIT_GRADUATION_ACTION));
        assertTrue(new EditGraduationAction(EditCommandUtil.VALID_GRADUATION).equals(SAMPLE_VALID_EDIT_GRADUATION_ACTION));
    }

    @Test
    public void toStringTest() {
        String expected = EditGraduationAction.class.getCanonicalName() + "{graduation=" + EditCommandUtil.VALID_GRADUATION + "}";
        assertEquals(SAMPLE_VALID_EDIT_GRADUATION_ACTION.toString(), expected);
    }

    @Test
    public void edit_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> SAMPLE_VALID_EDIT_GRADUATION_ACTION.edit(null));
    }

    @Test
    public void edit_validEditAction_success() throws CommandException {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        SAMPLE_VALID_EDIT_GRADUATION_ACTION.edit(actualDescriptor);

        Person expectedPerson = new Person(
                JACK.getName(),
                JACK.getPhones(),
                JACK.getEmails(),
                JACK.getLinks(),
                EditCommandUtil.VALID_GRADUATION,
                JACK.getCourses(),
                JACK.getSpecialisations(),
                JACK.getTags(),
                JACK.getPriority().orElse(null)
        );
        EditPersonDescriptor expectedDescriptor = new EditPersonDescriptor(expectedPerson);
        assertEquals(expectedDescriptor, actualDescriptor);
    }
}

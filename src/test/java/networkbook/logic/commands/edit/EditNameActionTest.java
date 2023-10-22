package networkbook.logic.commands.edit;

import static networkbook.testutil.Assert.assertThrows;
import static networkbook.testutil.TypicalPersons.JACK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import networkbook.logic.commands.exceptions.CommandException;
import networkbook.model.person.Person;

public class EditNameActionTest {
    private static final EditNameAction SAMPLE_VALID_EDIT_NAME_ACTION =
            new EditNameAction(EditCommandUtil.VALID_NAME);

    @Test
    public void equalsTest() {
        assertFalse(new EditNameAction(EditCommandUtil.VALID_NAME).equals(1));
        assertFalse(new EditNameAction(EditCommandUtil.VALID_NAME).equals(null));
        assertFalse(new EditNameAction(EditCommandUtil.VALID_NAME).equals(new Object()));

        assertTrue(SAMPLE_VALID_EDIT_NAME_ACTION.equals(SAMPLE_VALID_EDIT_NAME_ACTION));
        assertTrue(new EditNameAction(EditCommandUtil.VALID_NAME).equals(SAMPLE_VALID_EDIT_NAME_ACTION));
    }

    @Test
    public void toStringTest() {
        String expected = EditNameAction.class.getCanonicalName() + "{name=" + EditCommandUtil.VALID_NAME + "}";
        assertEquals(SAMPLE_VALID_EDIT_NAME_ACTION.toString(), expected);
    }

    @Test
    public void edit_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> SAMPLE_VALID_EDIT_NAME_ACTION.edit(null));
    }

    @Test
    public void edit_validEditAction_success() throws CommandException {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        SAMPLE_VALID_EDIT_NAME_ACTION.edit(actualDescriptor);

        Person expectedPerson = new Person(
                EditCommandUtil.VALID_NAME,
                JACK.getPhones(),
                JACK.getEmails(),
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
}

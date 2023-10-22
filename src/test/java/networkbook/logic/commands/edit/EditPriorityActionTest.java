package networkbook.logic.commands.edit;

import static networkbook.testutil.Assert.assertThrows;
import static networkbook.testutil.TypicalPersons.JACK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import networkbook.logic.commands.exceptions.CommandException;
import networkbook.model.person.Person;
import networkbook.model.person.Priority;

public class EditPriorityActionTest {
    private static final EditPriorityAction SAMPLE_VALID_EDIT_PRIORITY_ACTION =
            new EditPriorityAction(EditCommandUtil.VALID_PRIORITY);

    @Test
    public void equalsTest() {
        assertFalse(new EditPriorityAction(EditCommandUtil.VALID_PRIORITY).equals(1));
        assertFalse(new EditPriorityAction(EditCommandUtil.VALID_PRIORITY).equals(null));
        assertFalse(new EditPriorityAction(EditCommandUtil.VALID_PRIORITY).equals(new Object()));

        assertTrue(SAMPLE_VALID_EDIT_PRIORITY_ACTION.equals(SAMPLE_VALID_EDIT_PRIORITY_ACTION));
        assertTrue(new EditPriorityAction(EditCommandUtil.VALID_PRIORITY).equals(SAMPLE_VALID_EDIT_PRIORITY_ACTION));
    }

    @Test
    public void toStringTest() {
        String expected = EditPriorityAction.class.getCanonicalName() + "{priority=" + EditCommandUtil.VALID_PRIORITY + "}";
        assertEquals(SAMPLE_VALID_EDIT_PRIORITY_ACTION.toString(), expected);
    }

    @Test
    public void edit_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> SAMPLE_VALID_EDIT_PRIORITY_ACTION.edit(null));
    }

    @Test
    public void edit_validEditAction_success() throws CommandException {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        SAMPLE_VALID_EDIT_PRIORITY_ACTION.edit(actualDescriptor);

        Person expectedPerson = new Person(
                JACK.getName(),
                JACK.getPhones(),
                JACK.getEmails(),
                JACK.getLinks(),
                JACK.getGraduation().orElse(null),
                JACK.getCourses(),
                JACK.getSpecialisations(),
                JACK.getTags(),
                EditCommandUtil.VALID_PRIORITY
        );
        EditPersonDescriptor expectedDescriptor = new EditPersonDescriptor(expectedPerson);
        assertEquals(expectedDescriptor, actualDescriptor);
    }
}

package networkbook.logic.commands.edit;

import static networkbook.testutil.Assert.assertThrows;
import static networkbook.testutil.TypicalPersons.JACK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import networkbook.logic.commands.exceptions.CommandException;
import networkbook.model.person.Person;
import networkbook.model.person.Specialisation;
import networkbook.model.util.UniqueList;

public class EditSpecialisationActionTest {
    private static final EditSpecialisationAction SAMPLE_VALID_EDIT_SPECIALISATION_ACTION =
            new EditSpecialisationAction(EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_SPECIALISATION);
    private static final EditSpecialisationAction SAMPLE_INVALID_EDIT_SPECIALISATION_ACTION =
            new EditSpecialisationAction(EditCommandUtil.INVALID_INDEX, EditCommandUtil.VALID_SPECIALISATION);

    @Test
    public void equalsTest() {
        assertFalse(new EditSpecialisationAction(
                EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_SPECIALISATION).equals(1));
        assertFalse(new EditSpecialisationAction(
                EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_SPECIALISATION).equals(null));
        assertFalse(new EditSpecialisationAction(
                EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_SPECIALISATION).equals(new Object()));

        assertTrue(SAMPLE_VALID_EDIT_SPECIALISATION_ACTION.equals(SAMPLE_VALID_EDIT_SPECIALISATION_ACTION));
        assertTrue(new EditSpecialisationAction(EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_SPECIALISATION)
                .equals(SAMPLE_VALID_EDIT_SPECIALISATION_ACTION));
    }

    @Test
    public void toStringTest() {
        String expected = EditSpecialisationAction.class.getCanonicalName() + "{specialisation="
                + EditCommandUtil.VALID_SPECIALISATION + ", index=" + EditCommandUtil.VALID_INDEX.getOneBased() + "}";
        assertEquals(SAMPLE_VALID_EDIT_SPECIALISATION_ACTION.toString(), expected);
    }

    @Test
    public void edit_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, ()
                -> SAMPLE_VALID_EDIT_SPECIALISATION_ACTION.edit(null));
    }

    @Test
    public void edit_validEditAction_success() throws CommandException {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        SAMPLE_VALID_EDIT_SPECIALISATION_ACTION.edit(actualDescriptor);

        UniqueList<Specialisation> newSpecialisationList = JACK.getSpecialisations();
        newSpecialisationList.setItem(EditCommandUtil.VALID_INDEX.getZeroBased(),
                EditCommandUtil.VALID_SPECIALISATION);

        Person expectedPerson = new Person(
                JACK.getName(),
                JACK.getPhones(),
                JACK.getEmails(),
                JACK.getLinks(),
                JACK.getGraduation().orElse(null),
                JACK.getCourses(),
                newSpecialisationList,
                JACK.getTags(),
                JACK.getPriority().orElse(null)
        );
        EditPersonDescriptor expectedDescriptor = new EditPersonDescriptor(expectedPerson);
        assertEquals(expectedDescriptor, actualDescriptor);
    }

    @Test
    public void edit_invalidEditAction_throwsCommandException() {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        assertThrows(CommandException.class, ()
                -> SAMPLE_INVALID_EDIT_SPECIALISATION_ACTION.edit(actualDescriptor));
    }
}

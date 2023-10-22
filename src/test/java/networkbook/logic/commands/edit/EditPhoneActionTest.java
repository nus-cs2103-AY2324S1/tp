package networkbook.logic.commands.edit;

import static networkbook.testutil.Assert.assertThrows;
import static networkbook.testutil.TypicalPersons.JACK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import networkbook.commons.core.index.Index;
import networkbook.logic.commands.exceptions.CommandException;
import networkbook.model.person.Person;
import networkbook.model.person.Phone;
import networkbook.model.util.UniqueList;

public class EditPhoneActionTest {
    private static final EditPhoneAction SAMPLE_VALID_EDIT_PHONE_ACTION =
            new EditPhoneAction(EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_PHONE);
    private static final EditPhoneAction SAMPLE_INVALID_EDIT_PHONE_ACTION =
            new EditPhoneAction(EditCommandUtil.INVALID_INDEX, EditCommandUtil.VALID_PHONE);

    @Test
    public void equalsTest() {
        assertFalse(new EditPhoneAction(EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_PHONE).equals(1));
        assertFalse(new EditPhoneAction(EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_PHONE).equals(null));
        assertFalse(new EditPhoneAction(EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_PHONE).equals(new Object()));

        assertTrue(SAMPLE_VALID_EDIT_PHONE_ACTION.equals(SAMPLE_VALID_EDIT_PHONE_ACTION));
        assertTrue(new EditPhoneAction(EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_PHONE).equals(SAMPLE_VALID_EDIT_PHONE_ACTION));
    }

    @Test
    public void toStringTest() {
        String expected = EditPhoneAction.class.getCanonicalName() + "{phone=" + EditCommandUtil.VALID_PHONE
                + ", index=" + EditCommandUtil.VALID_INDEX.getOneBased() + "}";
        assertEquals(SAMPLE_VALID_EDIT_PHONE_ACTION.toString(), expected);
    }

    @Test
    public void edit_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> SAMPLE_VALID_EDIT_PHONE_ACTION.edit(null));
    }

    @Test
    public void edit_validEditAction_success() throws CommandException {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        SAMPLE_VALID_EDIT_PHONE_ACTION.edit(actualDescriptor);

        UniqueList<Phone> newPhoneList = JACK.getPhones();
        newPhoneList.setItem(EditCommandUtil.VALID_INDEX.getZeroBased(), EditCommandUtil.VALID_PHONE);

        Person expectedPerson = new Person(
                JACK.getName(),
                newPhoneList,
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

    @Test
    public void edit_invalidEditAction_throwsCommandException() {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        assertThrows(CommandException.class, () -> SAMPLE_INVALID_EDIT_PHONE_ACTION.edit(actualDescriptor));
    }
}

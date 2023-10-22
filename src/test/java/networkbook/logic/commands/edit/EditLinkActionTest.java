package networkbook.logic.commands.edit;

import static networkbook.testutil.Assert.assertThrows;
import static networkbook.testutil.TypicalPersons.JACK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import networkbook.commons.core.index.Index;
import networkbook.logic.commands.exceptions.CommandException;
import networkbook.model.person.Link;
import networkbook.model.person.Person;
import networkbook.model.util.UniqueList;

public class EditLinkActionTest {
    private static final EditLinkAction SAMPLE_VALID_EDIT_LINK_ACTION =
            new EditLinkAction(EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_LINK);
    private static final EditLinkAction SAMPLE_INVALID_EDIT_LINK_ACTION =
            new EditLinkAction(EditCommandUtil.INVALID_INDEX, EditCommandUtil.VALID_LINK);

    @Test
    public void equalsTest() {
        assertFalse(new EditLinkAction(EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_LINK).equals(1));
        assertFalse(new EditLinkAction(EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_LINK).equals(null));
        assertFalse(new EditLinkAction(EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_LINK).equals(new Object()));

        assertTrue(SAMPLE_VALID_EDIT_LINK_ACTION.equals(SAMPLE_VALID_EDIT_LINK_ACTION));
        assertTrue(new EditLinkAction(EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_LINK).equals(SAMPLE_VALID_EDIT_LINK_ACTION));
    }

    @Test
    public void toStringTest() {
        String expected = EditLinkAction.class.getCanonicalName() + "{link=" + EditCommandUtil.VALID_LINK
                + ", index=" + EditCommandUtil.VALID_INDEX.getOneBased() + "}";
        assertEquals(SAMPLE_VALID_EDIT_LINK_ACTION.toString(), expected);
    }

    @Test
    public void edit_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> SAMPLE_VALID_EDIT_LINK_ACTION.edit(null));
    }

    @Test
    public void edit_validEditAction_success() throws CommandException {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        SAMPLE_VALID_EDIT_LINK_ACTION.edit(actualDescriptor);

        UniqueList<Link> newLinkList = JACK.getLinks();
        newLinkList.setItem(EditCommandUtil.VALID_INDEX.getZeroBased(), EditCommandUtil.VALID_LINK);

        Person expectedPerson = new Person(
                JACK.getName(),
                JACK.getPhones(),
                JACK.getEmails(),
                newLinkList,
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
        assertThrows(CommandException.class, () -> SAMPLE_INVALID_EDIT_LINK_ACTION.edit(actualDescriptor));
    }
}

package networkbook.logic.commands.delete;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import networkbook.commons.core.index.Index;
import networkbook.logic.Messages;
import networkbook.logic.commands.exceptions.CommandException;
import networkbook.model.person.Link;
import networkbook.model.person.Person;
import networkbook.model.util.UniqueList;
import networkbook.testutil.TypicalIndexes;
import networkbook.testutil.TypicalPersons;

public class DeleteLinkActionTest {
    private static final Person JACK = TypicalPersons.JACK;
    private static final Index firstIndex = Index.fromOneBased(1);
    private static final Index tenthIndex = Index.fromOneBased(10);
    private static final DeletePersonDescriptor jackWithoutFirstLink = new DeletePersonDescriptor(new Person(
            JACK.getName(),
            JACK.getPhones(),
            JACK.getEmails(),
            new UniqueList<Link>().setItems(List.of(new Link("https://nknguyenhc.github.io"))),
            JACK.getGraduation().get(),
            JACK.getCourses(),
            JACK.getSpecialisations(),
            JACK.getTags(),
            JACK.getPriority().get()));

    private static final DeletePersonDescriptor jackWithoutAnyLink = new DeletePersonDescriptor(new Person(
            JACK.getName(),
            JACK.getPhones(),
            JACK.getEmails(),
            new UniqueList<Link>(),
            JACK.getGraduation().get(),
            JACK.getCourses(),
            JACK.getSpecialisations(),
            JACK.getTags(),
            JACK.getPriority().get()));

    @Test
    public void delete_deleteLinkValidIndex_success() throws CommandException {
        DeletePersonDescriptor descriptor = new DeletePersonDescriptor(JACK);
        DeleteLinkAction action = new DeleteLinkAction(firstIndex);
        action.delete(descriptor, TypicalIndexes.INDEX_FIRST_PERSON);
        assertEquals(descriptor, jackWithoutFirstLink);
        action.delete(descriptor, TypicalIndexes.INDEX_FIRST_PERSON);
        assertEquals(descriptor, jackWithoutAnyLink);
    }

    @Test
    public void delete_deleteLinkInvalidIndex_commandException() throws CommandException {
        DeleteLinkAction deleteFirstAction = new DeleteLinkAction(firstIndex);
        DeletePersonDescriptor descriptorWithoutLink = new DeletePersonDescriptor(new Person(
                JACK.getName(),
                JACK.getPhones(),
                JACK.getEmails(),
                new UniqueList<Link>(),
                JACK.getGraduation().get(),
                JACK.getCourses(),
                JACK.getSpecialisations(),
                JACK.getTags(),
                JACK.getPriority().get()));
        assertThrows(CommandException.class, () -> deleteFirstAction.delete(descriptorWithoutLink,
                TypicalIndexes.INDEX_FIRST_PERSON),
                String.format(Messages.MESSAGE_INVALID_MULTIVALUED_FIELD_ENTRY_INDEX,
                        TypicalIndexes.INDEX_FIRST_PERSON.getOneBased(), "a link", firstIndex.getOneBased()));

        DeleteLinkAction deleteTenthAction = new DeleteLinkAction(tenthIndex);
        DeletePersonDescriptor descriptorWithTwoLinks = new DeletePersonDescriptor(JACK);
        assertThrows(CommandException.class, () -> deleteTenthAction.delete(descriptorWithTwoLinks,
                TypicalIndexes.INDEX_SECOND_PERSON),
                String.format(Messages.MESSAGE_INVALID_MULTIVALUED_FIELD_ENTRY_INDEX,
                        TypicalIndexes.INDEX_SECOND_PERSON.getOneBased(), "a link", tenthIndex.getOneBased()));
    }

    @Test
    public void delete_deleteNull_nullPointerException() {
        DeleteLinkAction deleteFirstAction = new DeleteLinkAction(firstIndex);
        assertThrows(NullPointerException.class, () -> deleteFirstAction.delete(null,
                TypicalIndexes.INDEX_FIRST_PERSON));
    }

    @Test
    public void equalsTest() {
        DeleteLinkAction deleteFirstAction1 = new DeleteLinkAction(firstIndex);
        DeleteLinkAction deleteFirstAction2 = new DeleteLinkAction(firstIndex);
        DeleteLinkAction deleteTenthAction = new DeleteLinkAction(tenthIndex);
        assertEquals(deleteFirstAction1, deleteFirstAction1);
        assertEquals(deleteFirstAction1, deleteFirstAction2);
        assertNotEquals(deleteFirstAction1, null);
        assertNotEquals(null, deleteFirstAction1);
        assertNotEquals(deleteFirstAction1, new DeletePriorityAction());
        assertNotEquals(deleteFirstAction1, deleteTenthAction);
    }
}

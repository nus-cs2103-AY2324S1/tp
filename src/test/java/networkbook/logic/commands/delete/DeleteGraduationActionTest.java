package networkbook.logic.commands.delete;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import networkbook.commons.core.index.Index;
import networkbook.logic.Messages;
import networkbook.logic.commands.exceptions.CommandException;
import networkbook.model.person.Person;
import networkbook.testutil.TypicalIndexes;
import networkbook.testutil.TypicalPersons;

public class DeleteGraduationActionTest {
    private static final Person JACK = TypicalPersons.JACK;
    private static final DeletePersonDescriptor jackWithoutGraduation = new DeletePersonDescriptor(new Person(
            JACK.getName(),
            JACK.getPhones(),
            JACK.getEmails(),
            JACK.getLinks(),
            null,
            JACK.getCourses(),
            JACK.getSpecialisations(),
            JACK.getTags(),
            JACK.getPriority().get()));

    @Test
    public void delete_deleteGraduation_success() throws CommandException {
        DeletePersonDescriptor descriptor = new DeletePersonDescriptor(JACK);
        DeleteGraduationAction action = new DeleteGraduationAction();
        action.delete(descriptor, TypicalIndexes.INDEX_FIRST_PERSON);
        assertEquals(jackWithoutGraduation, descriptor);
    }

    @Test
    public void delete_deleteGraduationOfPersonWithoutGraduation_commandException() throws CommandException {
        DeleteGraduationAction action = new DeleteGraduationAction();
        DeletePersonDescriptor descriptorWithoutGraduation = new DeletePersonDescriptor(new Person(
                JACK.getName(),
                JACK.getPhones(),
                JACK.getEmails(),
                JACK.getLinks(),
                null,
                JACK.getCourses(),
                JACK.getSpecialisations(),
                JACK.getTags(),
                JACK.getPriority().get()));
        assertThrows(CommandException.class, () ->
                action.delete(descriptorWithoutGraduation, TypicalIndexes.INDEX_FIRST_PERSON),
                String.format(Messages.MESSAGE_DELETE_EMPTY_SINGLE_VALUED_FIELD,
                        TypicalIndexes.INDEX_FIRST_PERSON.getOneBased(), "a graduation semester"));
    }

    @Test
    public void delete_deleteNull_nullPointerException() {
        DeleteGraduationAction deleteFirstAction = new DeleteGraduationAction();
        assertThrows(NullPointerException.class, () -> deleteFirstAction.delete(null,
                TypicalIndexes.INDEX_FIRST_PERSON));
    }

    @Test
    public void equalsTest() {
        DeleteGraduationAction action1 = new DeleteGraduationAction();
        DeleteGraduationAction action2 = new DeleteGraduationAction();
        assertEquals(action1, action1);
        assertEquals(action1, action2);
        assertNotEquals(action1, null);
        assertNotEquals(null, action1);
        assertNotEquals(action1, new DeleteCourseAction(Index.fromOneBased(1)));
    }
}

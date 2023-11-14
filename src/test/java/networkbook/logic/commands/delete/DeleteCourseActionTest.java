package networkbook.logic.commands.delete;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import networkbook.commons.core.index.Index;
import networkbook.logic.Messages;
import networkbook.logic.commands.exceptions.CommandException;
import networkbook.model.person.Course;
import networkbook.model.person.Person;
import networkbook.model.util.UniqueList;
import networkbook.testutil.TypicalIndexes;
import networkbook.testutil.TypicalPersons;

public class DeleteCourseActionTest {
    private static final Person JACK = TypicalPersons.JACK;
    private static final Index firstIndex = Index.fromOneBased(1);
    private static final Index tenthIndex = Index.fromOneBased(10);
    private static final DeletePersonDescriptor jackWithoutFirstCourse = new DeletePersonDescriptor(new Person(
            JACK.getName(),
            JACK.getPhones(),
            JACK.getEmails(),
            JACK.getLinks(),
            JACK.getGraduation().get(),
            new UniqueList<Course>().setItems(List.of(new Course("CS2109S"))),
            JACK.getSpecialisations(),
            JACK.getTags(),
            JACK.getPriority().get()));

    private static final DeletePersonDescriptor jackWithoutAnyCourse = new DeletePersonDescriptor(new Person(
            JACK.getName(),
            JACK.getPhones(),
            JACK.getEmails(),
            JACK.getLinks(),
            JACK.getGraduation().get(),
            new UniqueList<Course>(),
            JACK.getSpecialisations(),
            JACK.getTags(),
            JACK.getPriority().get()));

    @Test
    public void delete_deleteCourseValidIndex_success() throws CommandException {
        DeletePersonDescriptor descriptor = new DeletePersonDescriptor(JACK);
        DeleteCourseAction action = new DeleteCourseAction(firstIndex);
        action.delete(descriptor, TypicalIndexes.INDEX_FIRST_PERSON);
        assertEquals(descriptor, jackWithoutFirstCourse);
        action.delete(descriptor, TypicalIndexes.INDEX_FIRST_PERSON);
        assertEquals(descriptor, jackWithoutAnyCourse);
    }

    @Test
    public void delete_deleteCourseInvalidIndex_commandException() throws CommandException {
        DeleteCourseAction deleteFirstAction = new DeleteCourseAction(firstIndex);
        DeletePersonDescriptor descriptorWithoutCourse = new DeletePersonDescriptor(new Person(
                JACK.getName(),
                JACK.getPhones(),
                JACK.getEmails(),
                JACK.getLinks(),
                JACK.getGraduation().get(),
                new UniqueList<Course>(),
                JACK.getSpecialisations(),
                JACK.getTags(),
                JACK.getPriority().get()));
        assertThrows(CommandException.class, () -> deleteFirstAction.delete(descriptorWithoutCourse,
                TypicalIndexes.INDEX_FIRST_PERSON),
                String.format(Messages.MESSAGE_INVALID_MULTIVALUED_FIELD_ENTRY_INDEX,
                        TypicalIndexes.INDEX_FIRST_PERSON.getOneBased(), "a course", firstIndex.getOneBased()));

        DeleteCourseAction deleteTenthAction = new DeleteCourseAction(tenthIndex);
        DeletePersonDescriptor descriptorWithTwoCourses = new DeletePersonDescriptor(JACK);
        assertThrows(CommandException.class, () -> deleteTenthAction.delete(descriptorWithTwoCourses,
                TypicalIndexes.INDEX_SECOND_PERSON),
                String.format(Messages.MESSAGE_INVALID_MULTIVALUED_FIELD_ENTRY_INDEX,
                        TypicalIndexes.INDEX_SECOND_PERSON.getOneBased(), "a course", tenthIndex.getOneBased()));
    }

    @Test
    public void delete_deleteNull_nullPointerException() {
        DeleteCourseAction deleteFirstAction = new DeleteCourseAction(firstIndex);
        assertThrows(NullPointerException.class, () -> deleteFirstAction.delete(null,
                TypicalIndexes.INDEX_FIRST_PERSON));
    }

    @Test
    public void equalsTest() {
        DeleteCourseAction deleteFirstAction1 = new DeleteCourseAction(firstIndex);
        DeleteCourseAction deleteFirstAction2 = new DeleteCourseAction(firstIndex);
        DeleteCourseAction deleteTenthAction = new DeleteCourseAction(tenthIndex);
        assertEquals(deleteFirstAction1, deleteFirstAction1);
        assertEquals(deleteFirstAction1, deleteFirstAction2);
        assertNotEquals(deleteFirstAction1, null);
        assertNotEquals(null, deleteFirstAction1);
        assertNotEquals(deleteFirstAction1, new DeletePriorityAction());
        assertNotEquals(deleteFirstAction1, deleteTenthAction);
    }
}

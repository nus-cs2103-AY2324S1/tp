package networkbook.logic.commands.delete;

import static networkbook.logic.commands.CommandTestUtil.assertCommandFailure;
import static networkbook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static networkbook.logic.commands.delete.DeleteFieldCommand.MESSAGE_DELETE_PERSON_FIELD_SUCCESS;
import static networkbook.testutil.Assert.assertThrowsAssertionError;

import org.junit.jupiter.api.Test;

import networkbook.commons.core.index.Index;
import networkbook.logic.Messages;
import networkbook.model.Model;
import networkbook.model.ModelManager;
import networkbook.model.NetworkBook;
import networkbook.model.UserPrefs;
import networkbook.model.person.Course;
import networkbook.model.person.Email;
import networkbook.model.person.Graduation;
import networkbook.model.person.Link;
import networkbook.model.person.Name;
import networkbook.model.person.Person;
import networkbook.model.person.Phone;
import networkbook.model.person.Priority;
import networkbook.model.person.Specialisation;
import networkbook.model.person.Tag;
import networkbook.model.util.UniqueList;
import networkbook.testutil.TypicalPersons;

public class DeleteFieldCommandTest {
    private static final Model model =
            new ModelManager(TypicalPersons.getTypicalNetworkBook(), new UserPrefs());
    private static final Index INDEX_ONE = Index.fromOneBased(1);
    private static final Index INVALID_INDEX = Index.fromOneBased(
            model.getDisplayedPersonList().size() + 1);
    private static final DeleteCourseAction DELETE_FIRST_COURSE_ACTION = new DeleteCourseAction(INDEX_ONE);
    private static final DeleteEmailAction DELETE_FIRST_EMAIL_ACTION = new DeleteEmailAction(INDEX_ONE);
    private static final DeleteLinkAction DELETE_FIRST_LINK_ACTION = new DeleteLinkAction(INDEX_ONE);
    private static final DeletePhoneAction DELETE_FIRST_PHONE_ACTION = new DeletePhoneAction(INDEX_ONE);
    private static final DeleteSpecialisationAction DELETE_FIRST_SPEC_ACTION =
            new DeleteSpecialisationAction(INDEX_ONE);
    private static final DeleteTagAction DELETE_FIRST_TAG_ACTION = new DeleteTagAction(INDEX_ONE);
    private static final DeletePriorityAction DELETE_PRIORITY_ACTION = new DeletePriorityAction();
    private static final DeleteGraduationAction DELETE_GRADUATION_ACTION = new DeleteGraduationAction();


    @Test
    public void constructor_null_nullPointerException() {
        assertThrowsAssertionError(() -> new DeleteFieldCommand(null, DELETE_PRIORITY_ACTION));
        assertThrowsAssertionError(() -> new DeleteFieldCommand(INDEX_ONE, null));
    }

    @Test
    public void execute_invalidIndexOfPerson_commandException() {
        DeleteFieldCommand command1 = new DeleteFieldCommand(INVALID_INDEX, DELETE_PRIORITY_ACTION);
        assertCommandFailure(command1, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);

        DeleteFieldCommand command2 = new DeleteFieldCommand(INVALID_INDEX, DELETE_FIRST_TAG_ACTION);
        assertCommandFailure(command2, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidIndexOfOldValue_commandException() {
        int numberOfCourses = model.getDisplayedPersonList().get(0).getCourses().size();
        DeleteFieldCommand deleteInvalidCourseCommand = new DeleteFieldCommand(INDEX_ONE,
                new DeleteCourseAction(Index.fromOneBased(numberOfCourses + 1)));
        assertCommandFailure(deleteInvalidCourseCommand, model,
                String.format(Messages.MESSAGE_INVALID_MULTIVALUED_FIELD_ENTRY_INDEX,
                        INDEX_ONE.getOneBased(), "a course", numberOfCourses + 1));

        int numberOfPhones = model.getDisplayedPersonList().get(0).getPhones().size();
        DeleteFieldCommand deleteInvalidPhoneCommand = new DeleteFieldCommand(INDEX_ONE,
                new DeletePhoneAction(Index.fromOneBased(numberOfPhones + 1)));
        assertCommandFailure(deleteInvalidPhoneCommand, model,
                String.format(Messages.MESSAGE_INVALID_MULTIVALUED_FIELD_ENTRY_INDEX,
                        INDEX_ONE.getOneBased(), "a phone", numberOfPhones + 1));

        int numberOfEmails = model.getDisplayedPersonList().get(0).getEmails().size();
        DeleteFieldCommand deleteInvalidEmailCommand = new DeleteFieldCommand(INDEX_ONE,
                new DeleteEmailAction(Index.fromOneBased(numberOfEmails + 1)));
        assertCommandFailure(deleteInvalidEmailCommand, model,
                String.format(Messages.MESSAGE_INVALID_MULTIVALUED_FIELD_ENTRY_INDEX,
                        INDEX_ONE.getOneBased(), "an email", numberOfEmails + 1));

        int numberOfLinks = model.getDisplayedPersonList().get(0).getLinks().size();
        DeleteFieldCommand deleteInvalidLinkCommand = new DeleteFieldCommand(INDEX_ONE,
                new DeleteLinkAction(Index.fromOneBased(numberOfLinks + 1)));
        assertCommandFailure(deleteInvalidLinkCommand, model,
                String.format(Messages.MESSAGE_INVALID_MULTIVALUED_FIELD_ENTRY_INDEX,
                        INDEX_ONE.getOneBased(), "a link", numberOfLinks + 1));

        int numberOfSpecs = model.getDisplayedPersonList().get(0).getSpecialisations().size();
        DeleteFieldCommand deleteInvalidSpecCommand = new DeleteFieldCommand(INDEX_ONE,
                new DeleteSpecialisationAction(Index.fromOneBased(numberOfSpecs + 1)));
        assertCommandFailure(deleteInvalidSpecCommand, model,
                String.format(Messages.MESSAGE_INVALID_MULTIVALUED_FIELD_ENTRY_INDEX,
                        INDEX_ONE.getOneBased(), "a specialisation", numberOfSpecs + 1));

        int numberOfTags = model.getDisplayedPersonList().get(0).getTags().size();
        DeleteFieldCommand deleteInvalidTagCommand = new DeleteFieldCommand(INDEX_ONE,
                new DeleteTagAction(Index.fromOneBased(numberOfTags + 1)));
        assertCommandFailure(deleteInvalidTagCommand, model,
                String.format(Messages.MESSAGE_INVALID_MULTIVALUED_FIELD_ENTRY_INDEX,
                        INDEX_ONE.getOneBased(), "a tag", numberOfTags + 1));
    }

    @Test
    public void execute_validCommand_success() {
        Model originalModel = new ModelManager(new NetworkBook(model.getNetworkBook()), new UserPrefs());
        originalModel.setItem(originalModel.getDisplayedPersonList().get(0),
                TypicalPersons.ADAM);
        Name adamName = TypicalPersons.ADAM.getName();
        UniqueList<Phone> adamPhones = TypicalPersons.ADAM.getPhones();
        UniqueList<Email> adamEmails = TypicalPersons.ADAM.getEmails();
        UniqueList<Link> adamLinks = TypicalPersons.ADAM.getLinks();
        UniqueList<Course> adamCourses = TypicalPersons.ADAM.getCourses();
        UniqueList<Specialisation> adamSpecialisations = TypicalPersons.ADAM.getSpecialisations();
        UniqueList<Tag> adamTags = TypicalPersons.ADAM.getTags();
        Graduation adamGraduation = TypicalPersons.ADAM.getGraduation().get();
        Priority adamPriority = TypicalPersons.ADAM.getPriority().get();

        UniqueList<Phone> adamPhonesWithoutFirst = TypicalPersons.ADAM.getPhones();
        UniqueList<Email> adamEmailsWithoutFirst = TypicalPersons.ADAM.getEmails();
        UniqueList<Link> adamLinksWithoutFirst = TypicalPersons.ADAM.getLinks();
        UniqueList<Course> adamCoursesWithoutFirst = TypicalPersons.ADAM.getCourses();
        UniqueList<Specialisation> adamSpecialisationsWithoutFirst = TypicalPersons.ADAM.getSpecialisations();
        UniqueList<Tag> adamTagsWithoutFirst = TypicalPersons.ADAM.getTags();

        adamPhonesWithoutFirst.removeAtIndex(0);
        adamEmailsWithoutFirst.removeAtIndex(0);
        adamLinksWithoutFirst.removeAtIndex(0);
        adamCoursesWithoutFirst.removeAtIndex(0);
        adamSpecialisationsWithoutFirst.removeAtIndex(0);
        adamTagsWithoutFirst.removeAtIndex(0);

        Person adamWithoutFirstPhone = new Person(
                adamName,
                adamPhonesWithoutFirst,
                adamEmails,
                adamLinks,
                adamGraduation,
                adamCourses,
                adamSpecialisations,
                adamTags,
                adamPriority
        );

        Person adamWithoutFirstEmail = new Person(
                adamName,
                adamPhones,
                adamEmailsWithoutFirst,
                adamLinks,
                adamGraduation,
                adamCourses,
                adamSpecialisations,
                adamTags,
                adamPriority
        );

        Person adamWithoutFirstLink = new Person(
                adamName,
                adamPhones,
                adamEmails,
                adamLinksWithoutFirst,
                adamGraduation,
                adamCourses,
                adamSpecialisations,
                adamTags,
                adamPriority
        );

        Person adamWithoutFirstCourse = new Person(
                adamName,
                adamPhones,
                adamEmails,
                adamLinks,
                adamGraduation,
                adamCoursesWithoutFirst,
                adamSpecialisations,
                adamTags,
                adamPriority
        );

        Person adamWithoutFirstSpec = new Person(
                adamName,
                adamPhones,
                adamEmails,
                adamLinks,
                adamGraduation,
                adamCourses,
                adamSpecialisationsWithoutFirst,
                adamTags,
                adamPriority
        );

        Person adamWithoutFirstTag = new Person(
                adamName,
                adamPhones,
                adamEmails,
                adamLinks,
                adamGraduation,
                adamCourses,
                adamSpecialisations,
                adamTagsWithoutFirst,
                adamPriority
        );

        Person adamWithoutGraduation = new Person(
                adamName,
                adamPhones,
                adamEmails,
                adamLinks,
                null,
                adamCourses,
                adamSpecialisations,
                adamTags,
                adamPriority
        );

        Person adamWithoutPriority = new Person(
                adamName,
                adamPhones,
                adamEmails,
                adamLinks,
                adamGraduation,
                adamCourses,
                adamSpecialisations,
                adamTags,
                null
        );

        DeleteFieldCommand deletePhoneCommand = new DeleteFieldCommand(INDEX_ONE, DELETE_FIRST_PHONE_ACTION);
        Model expectedModelAfterDeletingPhone =
                new ModelManager(new NetworkBook(model.getNetworkBook()), new UserPrefs());
        expectedModelAfterDeletingPhone.setItem(expectedModelAfterDeletingPhone.getDisplayedPersonList().get(0),
                                                adamWithoutFirstPhone);
        assertCommandSuccess(deletePhoneCommand, originalModel,
                String.format(MESSAGE_DELETE_PERSON_FIELD_SUCCESS, Messages.format(adamWithoutFirstPhone)),
                expectedModelAfterDeletingPhone);

        originalModel.setItem(originalModel.getDisplayedPersonList().get(0), TypicalPersons.ADAM);
        DeleteFieldCommand deleteEmailCommand = new DeleteFieldCommand(INDEX_ONE, DELETE_FIRST_EMAIL_ACTION);
        Model expectedModelAfterDeletingEmail =
                new ModelManager(new NetworkBook(model.getNetworkBook()), new UserPrefs());
        expectedModelAfterDeletingEmail.setItem(model.getDisplayedPersonList().get(0), adamWithoutFirstEmail);
        assertCommandSuccess(deleteEmailCommand, originalModel,
                String.format(MESSAGE_DELETE_PERSON_FIELD_SUCCESS, Messages.format(adamWithoutFirstEmail)),
                expectedModelAfterDeletingEmail);

        originalModel.setItem(originalModel.getDisplayedPersonList().get(0), TypicalPersons.ADAM);
        DeleteFieldCommand deleteLinkCommand = new DeleteFieldCommand(INDEX_ONE, DELETE_FIRST_LINK_ACTION);
        Model expectedModelAfterDeletingLink =
                new ModelManager(new NetworkBook(model.getNetworkBook()), new UserPrefs());
        expectedModelAfterDeletingLink.setItem(model.getDisplayedPersonList().get(0), adamWithoutFirstLink);
        assertCommandSuccess(deleteLinkCommand, originalModel,
                String.format(MESSAGE_DELETE_PERSON_FIELD_SUCCESS, Messages.format(adamWithoutFirstLink)),
                expectedModelAfterDeletingLink);

        originalModel.setItem(originalModel.getDisplayedPersonList().get(0), TypicalPersons.ADAM);
        DeleteFieldCommand deleteCourseCommand = new DeleteFieldCommand(INDEX_ONE, DELETE_FIRST_COURSE_ACTION);
        Model expectedModelAfterDeletingCourse =
                new ModelManager(new NetworkBook(model.getNetworkBook()), new UserPrefs());
        expectedModelAfterDeletingCourse.setItem(model.getDisplayedPersonList().get(0), adamWithoutFirstCourse);
        assertCommandSuccess(deleteCourseCommand, originalModel,
                String.format(MESSAGE_DELETE_PERSON_FIELD_SUCCESS, Messages.format(adamWithoutFirstCourse)),
                expectedModelAfterDeletingCourse);

        originalModel.setItem(originalModel.getDisplayedPersonList().get(0), TypicalPersons.ADAM);
        DeleteFieldCommand deleteSpecCommand = new DeleteFieldCommand(INDEX_ONE, DELETE_FIRST_SPEC_ACTION);
        Model expectedModelAfterDeletingSpec =
                new ModelManager(new NetworkBook(model.getNetworkBook()), new UserPrefs());
        expectedModelAfterDeletingSpec.setItem(model.getDisplayedPersonList().get(0), adamWithoutFirstSpec);
        assertCommandSuccess(deleteSpecCommand, originalModel,
                String.format(MESSAGE_DELETE_PERSON_FIELD_SUCCESS, Messages.format(adamWithoutFirstSpec)),
                expectedModelAfterDeletingSpec);

        originalModel.setItem(originalModel.getDisplayedPersonList().get(0), TypicalPersons.ADAM);
        DeleteFieldCommand deleteTagCommand = new DeleteFieldCommand(INDEX_ONE, DELETE_FIRST_TAG_ACTION);
        Model expectedModelAfterDeletingTag =
                new ModelManager(new NetworkBook(model.getNetworkBook()), new UserPrefs());
        expectedModelAfterDeletingTag.setItem(model.getDisplayedPersonList().get(0), adamWithoutFirstTag);
        assertCommandSuccess(deleteTagCommand, originalModel,
                String.format(MESSAGE_DELETE_PERSON_FIELD_SUCCESS, Messages.format(adamWithoutFirstTag)),
                expectedModelAfterDeletingTag);

        originalModel.setItem(originalModel.getDisplayedPersonList().get(0), TypicalPersons.ADAM);
        DeleteFieldCommand deleteGraduationCommand = new DeleteFieldCommand(INDEX_ONE, DELETE_GRADUATION_ACTION);
        Model expectedModelAfterDeletingGraduation =
                new ModelManager(new NetworkBook(model.getNetworkBook()), new UserPrefs());
        expectedModelAfterDeletingGraduation.setItem(model.getDisplayedPersonList().get(0), adamWithoutGraduation);
        assertCommandSuccess(deleteGraduationCommand, originalModel,
                String.format(MESSAGE_DELETE_PERSON_FIELD_SUCCESS, Messages.format(adamWithoutGraduation)),
                expectedModelAfterDeletingGraduation);

        originalModel.setItem(originalModel.getDisplayedPersonList().get(0), TypicalPersons.ADAM);
        DeleteFieldCommand deletePriorityCommand = new DeleteFieldCommand(INDEX_ONE, DELETE_PRIORITY_ACTION);
        Model expectedModelAfterDeletingPriority =
                new ModelManager(new NetworkBook(model.getNetworkBook()), new UserPrefs());
        expectedModelAfterDeletingPriority.setItem(model.getDisplayedPersonList().get(0), adamWithoutPriority);
        assertCommandSuccess(deletePriorityCommand, originalModel,
                String.format(MESSAGE_DELETE_PERSON_FIELD_SUCCESS, Messages.format(adamWithoutPriority)),
                expectedModelAfterDeletingPriority);
    }
}

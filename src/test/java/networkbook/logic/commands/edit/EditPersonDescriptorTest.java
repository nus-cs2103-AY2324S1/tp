package networkbook.logic.commands.edit;

import static networkbook.testutil.Assert.assertThrows;
import static networkbook.testutil.TypicalPersons.JACK;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import networkbook.logic.commands.exceptions.CommandException;
import networkbook.model.person.Course;
import networkbook.model.person.Email;
import networkbook.model.person.Link;
import networkbook.model.person.Person;
import networkbook.model.person.Phone;
import networkbook.model.person.Specialisation;
import networkbook.model.tag.Tag;
import networkbook.model.util.UniqueList;

public class EditPersonDescriptorTest {
    @Test
    public void setName_null_throwsNullPointerException() {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        assertThrows(NullPointerException.class, () -> actualDescriptor.setName(null));
    }

    @Test
    public void setName_validName_success() {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        actualDescriptor.setName(EditCommandUtil.VALID_NAME);

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

    @Test
    public void setPhone_null_throwsNullPointerException() {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        assertThrows(NullPointerException.class, ()
                -> actualDescriptor.setPhone(null, EditCommandUtil.VALID_PHONE));
        assertThrows(NullPointerException.class, ()
                -> actualDescriptor.setPhone(EditCommandUtil.VALID_INDEX, null));
        assertThrows(NullPointerException.class, () -> actualDescriptor.setPhone(null, null));
    }

    @Test
    public void setPhone_validPhoneAndIndex_success() throws Exception {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        actualDescriptor.setPhone(EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_PHONE);

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
    public void setPhone_invalidIndex_throwsCommandException() {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        assertThrows(CommandException.class, ()
                -> actualDescriptor.setPhone(EditCommandUtil.INVALID_INDEX, EditCommandUtil.VALID_PHONE));
    }

    @Test
    public void setEmail_null_throwsNullPointerException() {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        assertThrows(NullPointerException.class, ()
                -> actualDescriptor.setEmail(null, EditCommandUtil.VALID_EMAIL));
        assertThrows(NullPointerException.class, ()
                -> actualDescriptor.setEmail(EditCommandUtil.VALID_INDEX, null));
        assertThrows(NullPointerException.class, () -> actualDescriptor.setEmail(null, null));
    }

    @Test
    public void setEmail_validEmailAndIndex_success() throws Exception {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        actualDescriptor.setEmail(EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_EMAIL);

        UniqueList<Email> newEmailList = JACK.getEmails();
        newEmailList.setItem(EditCommandUtil.VALID_INDEX.getZeroBased(), EditCommandUtil.VALID_EMAIL);

        Person expectedPerson = new Person(
                JACK.getName(),
                JACK.getPhones(),
                newEmailList,
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
    public void setEmail_invalidIndex_throwsCommandException() {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        assertThrows(CommandException.class, ()
                -> actualDescriptor.setEmail(EditCommandUtil.INVALID_INDEX, EditCommandUtil.VALID_EMAIL));
    }

    @Test
    public void setLink_null_throwsNullPointerException() {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        assertThrows(NullPointerException.class, ()
                -> actualDescriptor.setLink(null, EditCommandUtil.VALID_LINK));
        assertThrows(NullPointerException.class, ()
                -> actualDescriptor.setLink(EditCommandUtil.VALID_INDEX, null));
        assertThrows(NullPointerException.class, () -> actualDescriptor.setLink(null, null));
    }

    @Test
    public void setLink_validLinkAndIndex_success() throws Exception {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        actualDescriptor.setLink(EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_LINK);

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
    public void setLink_invalidIndex_throwsCommandException() {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        assertThrows(CommandException.class, ()
                -> actualDescriptor.setLink(EditCommandUtil.INVALID_INDEX, EditCommandUtil.VALID_LINK));
    }

    @Test
    public void setGraduation_null_throwsNullPointerException() {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        assertThrows(NullPointerException.class, () -> actualDescriptor.setGraduation(null));
    }

    @Test
    public void setGraduation_validGraduation_success() {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        actualDescriptor.setGraduation(EditCommandUtil.VALID_GRADUATION);

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

    @Test
    public void setCourse_null_throwsNullPointerException() {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        assertThrows(NullPointerException.class, ()
                -> actualDescriptor.setCourse(null, EditCommandUtil.VALID_COURSE));
        assertThrows(NullPointerException.class, ()
                -> actualDescriptor.setCourse(EditCommandUtil.VALID_INDEX, null));
        assertThrows(NullPointerException.class, () -> actualDescriptor.setCourse(null, null));
    }

    @Test
    public void setCourse_validCourseAndIndex_success() throws Exception {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        actualDescriptor.setCourse(EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_COURSE);

        UniqueList<Course> newCourseList = JACK.getCourses();
        newCourseList.setItem(EditCommandUtil.VALID_INDEX.getZeroBased(), EditCommandUtil.VALID_COURSE);

        Person expectedPerson = new Person(
                JACK.getName(),
                JACK.getPhones(),
                JACK.getEmails(),
                JACK.getLinks(),
                JACK.getGraduation().orElse(null),
                newCourseList,
                JACK.getSpecialisations(),
                JACK.getTags(),
                JACK.getPriority().orElse(null)
        );
        EditPersonDescriptor expectedDescriptor = new EditPersonDescriptor(expectedPerson);
        assertEquals(expectedDescriptor, actualDescriptor);
    }

    @Test
    public void setCourse_invalidIndex_throwsCommandException() {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        assertThrows(CommandException.class, ()
                -> actualDescriptor.setCourse(EditCommandUtil.INVALID_INDEX, EditCommandUtil.VALID_COURSE));
    }

    @Test
    public void setSpecialisation_null_throwsNullPointerException() {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        assertThrows(NullPointerException.class, ()
                -> actualDescriptor.setSpecialisation(null, EditCommandUtil.VALID_SPECIALISATION));
        assertThrows(NullPointerException.class, ()
                -> actualDescriptor.setSpecialisation(EditCommandUtil.VALID_INDEX, null));
        assertThrows(NullPointerException.class, ()
                -> actualDescriptor.setSpecialisation(null, null));
    }

    @Test
    public void setSpecialisation_validCourseAndIndex_success() throws Exception {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        actualDescriptor.setSpecialisation(EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_SPECIALISATION);

        UniqueList<Specialisation> newSpecialisationList = JACK.getSpecialisations();
        newSpecialisationList.setItem(
                EditCommandUtil.VALID_INDEX.getZeroBased(), EditCommandUtil.VALID_SPECIALISATION);

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
    public void setSpecialisation_invalidIndex_throwsCommandException() {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        assertThrows(CommandException.class, ()
                -> actualDescriptor.setSpecialisation(
                        EditCommandUtil.INVALID_INDEX, EditCommandUtil.VALID_SPECIALISATION));
    }

    @Test
    public void setTag_null_throwsNullPointerException() {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        assertThrows(NullPointerException.class, ()
                -> actualDescriptor.setTag(null, EditCommandUtil.VALID_TAG));
        assertThrows(NullPointerException.class, ()
                -> actualDescriptor.setTag(EditCommandUtil.VALID_INDEX, null));
        assertThrows(NullPointerException.class, () -> actualDescriptor.setTag(null, null));
    }

    @Test
    public void setTag_validTagAndIndex_success() throws Exception {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        actualDescriptor.setTag(EditCommandUtil.VALID_INDEX, EditCommandUtil.VALID_TAG);

        UniqueList<Tag> newTagList = JACK.getTags();
        newTagList.setItem(EditCommandUtil.VALID_INDEX.getZeroBased(), EditCommandUtil.VALID_TAG);

        Person expectedPerson = new Person(
                JACK.getName(),
                JACK.getPhones(),
                JACK.getEmails(),
                JACK.getLinks(),
                JACK.getGraduation().orElse(null),
                JACK.getCourses(),
                JACK.getSpecialisations(),
                newTagList,
                JACK.getPriority().orElse(null)
        );
        EditPersonDescriptor expectedDescriptor = new EditPersonDescriptor(expectedPerson);
        assertEquals(expectedDescriptor, actualDescriptor);
    }

    @Test
    public void setTag_invalidIndex_throwsCommandException() {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        assertThrows(CommandException.class, ()
                -> actualDescriptor.setTag(EditCommandUtil.INVALID_INDEX, EditCommandUtil.VALID_TAG));
    }

    @Test
    public void setPriority_null_throwsNullPointerException() {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        assertThrows(NullPointerException.class, () -> actualDescriptor.setPriority(null));
    }

    @Test
    public void setPriority_validPriority_success() {
        EditPersonDescriptor actualDescriptor = new EditPersonDescriptor(JACK);
        actualDescriptor.setPriority(EditCommandUtil.VALID_PRIORITY);

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

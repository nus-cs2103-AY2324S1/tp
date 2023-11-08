package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.TypicalLessons.getTypicalScheduleList;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.lessons.Day;
import seedu.address.model.lessons.Lesson;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Subject;
import seedu.address.model.person.Subjects;
import seedu.address.model.person.Tags;
import seedu.address.model.state.State;

class FilterCommandParserTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalScheduleList());
    private FilterCommandParser PersonFilter = new FilterCommandParser(State.STUDENT);
    private FilterCommandParser LessonFilter = new FilterCommandParser(State.SCHEDULE);
    // phone, address, remark, tags, email
    @Test
    void test_personFilter_Name() throws ParseException, CommandException {
        Person person = Person.getDefaultPerson();
        person.setName(Name.of("test 123456"));
        Person person2 = Person.getDefaultPerson();
        person2.setName(Name.of("test 12345678"));
        model.addPerson(person);
        model.addPerson(person2);
        PersonFilter.parse(" -name test 123456").execute(model);
        assertTrue(model.getFilteredPersonList().contains(person));
        assertFalse(model.getFilteredPersonList().contains(person2));
    }

    @Test
    void test_personFilter_Phone() throws ParseException, CommandException {
        Person person = Person.getDefaultPerson();
        person.setPhone(Phone.of("12345678"));
        person.setName(Name.of("person 1"));
        Person person2 = Person.getDefaultPerson();
        person2.setPhone(Phone.of("123456789"));
        person2.setName(Name.of("person 2"));
        model.addPerson(person);
        model.addPerson(person2);
        PersonFilter.parse(" -phone 12345678").execute(model);
        assertTrue(model.getFilteredPersonList().contains(person));
        assertFalse(model.getFilteredPersonList().contains(person2));
    }

    @Test
    void test_personFilter_Email() throws ParseException, CommandException {
        Person person = Person.getDefaultPerson();
        person.setEmail(Email.of("fake123@domain.com"));
        person.setName(Name.of("person 1"));
        Person person2 = Person.getDefaultPerson();
        person2.setEmail(Email.of("fake1234@domain.com"));
        person2.setName(Name.of("person 2"));
        model.addPerson(person);
        model.addPerson(person2);
        PersonFilter.parse(" -email fake123@domain.com").execute(model);
        assertTrue(model.getFilteredPersonList().contains(person));
        assertFalse(model.getFilteredPersonList().contains(person2));
    }

    @Test
    void test_personFilter_Tags() throws ParseException, CommandException {
        Person person = Person.getDefaultPerson();
        person.setTags(Tags.of("tag1,tag2"));
        person.setName(Name.of("person 1"));
        Person person2 = Person.getDefaultPerson();
        person2.setTags(Tags.of("tag1,tag3"));
        person2.setName(Name.of("person 2"));
        model.addPerson(person);
        model.addPerson(person2);
        PersonFilter.parse(" -tag tag1").execute(model);
        assertTrue(model.getFilteredPersonList().contains(person));
        assertTrue(model.getFilteredPersonList().contains(person2));
        PersonFilter.parse(" -tag tag2").execute(model);
        assertTrue(model.getFilteredPersonList().contains(person));
        assertFalse(model.getFilteredPersonList().contains(person2));
        PersonFilter.parse(" -tag tag3").execute(model);
        assertFalse(model.getFilteredPersonList().contains(person));
        assertTrue(model.getFilteredPersonList().contains(person2));
    }
    // todo properly document the behaviour of the filter command.
    @Test
void test_personFilter_subjects() throws ParseException, CommandException {
        Person person = Person.getDefaultPerson();
        person.setSubjects(Subjects.of("physics,chemistry"));
        person.setName(Name.of("person 1"));
        Person person2 = Person.getDefaultPerson();
        person2.setSubjects(Subjects.of("physics,English"));
        person2.setName(Name.of("person 2"));
        model.addPerson(person);
        model.addPerson(person2);
        PersonFilter.parse(" -subject physics").execute(model);
        assertTrue(model.getFilteredPersonList().contains(person));
        assertTrue(model.getFilteredPersonList().contains(person2));
        PersonFilter.parse(" -subject physics,chemistry").execute(model);
        assertTrue(model.getFilteredPersonList().contains(person));
        assertFalse(model.getFilteredPersonList().contains(person2));
        PersonFilter.parse(" -subject English").execute(model);
        assertFalse(model.getFilteredPersonList().contains(person));
        assertTrue(model.getFilteredPersonList().contains(person2));
    }

    @Test
    void test_personFilter_Remark() throws ParseException, CommandException {
        Person person = Person.getDefaultPerson();
        person.setRemark(Remark.of("remark1"));
        person.setName(Name.of("person 1"));
        Person person2 = Person.getDefaultPerson();
        person2.setRemark(Remark.of("remark2"));
        person2.setName(Name.of("person 2"));
        model.addPerson(person);
        model.addPerson(person2);
        PersonFilter.parse(" -remark remark1").execute(model);
        assertTrue(model.getFilteredPersonList().contains(person));
        assertFalse(model.getFilteredPersonList().contains(person2));
        PersonFilter.parse(" -remark remark").execute(model);
        assertTrue(model.getFilteredPersonList().contains(person));
        assertTrue(model.getFilteredPersonList().contains(person2));
    }

    @Test
    void test_personFilter_Multiple() throws ParseException, CommandException {
        Person person = Person.getDefaultPerson();
        person.setTags(Tags.of("tag1,tag2"));
        person.setName(Name.of("person 1"));
        person.setPhone(Phone.of("12345678"));
        Person person2 = Person.getDefaultPerson();
        person2.setTags(Tags.of("tag1,tag3"));
        person2.setName(Name.of("person 2"));
        person2.setPhone(Phone.of("123456789"));
        model.addPerson(person);
        model.addPerson(person2);
        PersonFilter.parse(" -tag tag1 -phone 12345678").execute(model);
        assertTrue(model.getFilteredPersonList().contains(person));
        assertFalse(model.getFilteredPersonList().contains(person2));
        PersonFilter.parse(" -tag tag1 -phone 123456789").execute(model);
        assertFalse(model.getFilteredPersonList().contains(person));
        assertTrue(model.getFilteredPersonList().contains(person2));
    }

    @Test
    void test_lessonFilter_Name() throws ParseException, CommandException {
        Lesson lesson = Lesson.getDefaultLesson();
        lesson.setName(Name.of("test lesson 1"));
        Lesson lesson2 = Lesson.getDefaultLesson();
        lesson2.setName(Name.of("test lesson 2"));
        model.addLesson(lesson);
        model.addLesson(lesson2);
        LessonFilter.parse(" -name test lesson 1").execute(model);
        assertTrue(model.getFilteredScheduleList().contains(lesson));
        assertFalse(model.getFilteredScheduleList().contains(lesson2));
    }

    @Test
    void test_lessonFilter_beforeAfterAndOn() throws ParseException, CommandException {
        Lesson lesson = Lesson.getDefaultLesson();
        lesson.setDay(Day.of("1"));
        Lesson lesson2 = Lesson.getDefaultLesson();
        lesson2.setDay(Day.of("3"));
        model.addLesson(lesson);
        model.addLesson(lesson2);
        LessonFilter.parse(" -before 2").execute(model);
        assertTrue(model.getFilteredScheduleList().contains(lesson));
        assertFalse(model.getFilteredScheduleList().contains(lesson2));
        LessonFilter.parse(" -after 2").execute(model);
        assertFalse(model.getFilteredScheduleList().contains(lesson));
        assertTrue(model.getFilteredScheduleList().contains(lesson2));
        LessonFilter.parse(" -on 1").execute(model);
        assertTrue(model.getFilteredScheduleList().contains(lesson));
        assertFalse(model.getFilteredScheduleList().contains(lesson2));
    }

    @Test
    void test_lessonFilter_subjects() throws ParseException, CommandException {
        Lesson lesson = Lesson.getDefaultLesson();
        lesson.setSubject(Subject.of("physics"));
        Lesson lesson2 = Lesson.getDefaultLesson();
        lesson2.setSubject(Subject.of("chemistry"));
        model.addLesson(lesson);
        model.addLesson(lesson2);
        LessonFilter.parse(" -subject physics").execute(model);
        assertTrue(model.getFilteredScheduleList().contains(lesson));
        assertFalse(model.getFilteredScheduleList().contains(lesson2));
        LessonFilter.parse(" -subject physics,chemistry").execute(model);
        assertTrue(model.getFilteredScheduleList().contains(lesson));
        assertTrue(model.getFilteredScheduleList().contains(lesson2));
        LessonFilter.parse(" -subject English").execute(model);
        assertFalse(model.getFilteredScheduleList().contains(lesson));
        assertFalse(model.getFilteredScheduleList().contains(lesson2));
    }

    @Test
    void test_lessonFilter_Multiple() throws ParseException, CommandException {
        Lesson lesson = Lesson.getDefaultLesson();
        lesson.setSubject(Subject.of("physics"));
        lesson.setDay(Day.of("1"));
        Lesson lesson2 = Lesson.getDefaultLesson();
        lesson2.setSubject(Subject.of("chemistry"));
        lesson2.setDay(Day.of("3"));
        model.addLesson(lesson);
        model.addLesson(lesson2);
        LessonFilter.parse(" -subject physics -before 2").execute(model);
        assertTrue(model.getFilteredScheduleList().contains(lesson));
        assertFalse(model.getFilteredScheduleList().contains(lesson2));
        LessonFilter.parse(" -subject physics -after 2").execute(model);
        assertFalse(model.getFilteredScheduleList().contains(lesson));
        assertFalse(model.getFilteredScheduleList().contains(lesson2));
        LessonFilter.parse(" -subject physics -on 1").execute(model);
        assertTrue(model.getFilteredScheduleList().contains(lesson));
        assertFalse(model.getFilteredScheduleList().contains(lesson2));
    }

}

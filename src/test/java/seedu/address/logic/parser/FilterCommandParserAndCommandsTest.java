package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Subject;
import seedu.address.model.person.Subjects;
import seedu.address.model.person.Tags;
import seedu.address.model.state.State;

class FilterCommandParserAndCommandsTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalScheduleList());
    private FilterCommandParser personFilter = new FilterCommandParser(State.STUDENT);
    private FilterCommandParser lessonFilter = new FilterCommandParser(State.SCHEDULE);
    // phone, address, remark, tags, email
    // todo, make it clear what the filter command does for name.
    @Test
    void test_personFilter_name() throws ParseException, CommandException {
        Person person = Person.getDefaultPerson();
        person.setName(Name.of("test 123456"));
        Person person2 = Person.getDefaultPerson();
        person2.setName(Name.of("test 12345678"));
        model.addPerson(person);
        model.addPerson(person2);
        personFilter.parse(" -name test 123456").execute(model);
        assertTrue(model.getFilteredPersonList().contains(person));
        assertTrue(model.getFilteredPersonList().contains(person2));
        personFilter.parse(" -name test 12345678").execute(model);
        assertFalse(model.getFilteredPersonList().contains(person));
        assertTrue(model.getFilteredPersonList().contains(person2));
    }

    @Test
    void test_personFilter_tags() throws ParseException, CommandException {
        Person person = Person.getDefaultPerson();
        person.setTags(Tags.of("tag1,tag2"));
        person.setName(Name.of("person 1"));
        Person person2 = Person.getDefaultPerson();
        person2.setTags(Tags.of("tag1,tag3"));
        person2.setName(Name.of("person 2"));
        model.addPerson(person);
        model.addPerson(person2);
        personFilter.parse(" -tag tag1").execute(model);
        assertTrue(model.getFilteredPersonList().contains(person));
        assertTrue(model.getFilteredPersonList().contains(person2));
        personFilter.parse(" -tag tag2").execute(model);
        assertTrue(model.getFilteredPersonList().contains(person));
        assertFalse(model.getFilteredPersonList().contains(person2));
        personFilter.parse(" -tag tag3").execute(model);
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
        personFilter.parse(" -subject physics").execute(model);
        assertTrue(model.getFilteredPersonList().contains(person));
        assertTrue(model.getFilteredPersonList().contains(person2));
        personFilter.parse(" -subject physics,chemistry").execute(model);
        assertTrue(model.getFilteredPersonList().contains(person));
        assertFalse(model.getFilteredPersonList().contains(person2));
        personFilter.parse(" -subject English").execute(model);
        assertFalse(model.getFilteredPersonList().contains(person));
        assertTrue(model.getFilteredPersonList().contains(person2));
    }

    @Test
    void test_personFilter_remark() throws ParseException, CommandException {
        Person person = Person.getDefaultPerson();
        person.setRemark(Remark.of("remark1"));
        person.setName(Name.of("person 1"));
        Person person2 = Person.getDefaultPerson();
        person2.setRemark(Remark.of("remark2"));
        person2.setName(Name.of("person 2"));
        model.addPerson(person);
        model.addPerson(person2);
        personFilter.parse(" -remark remark1").execute(model);
        assertTrue(model.getFilteredPersonList().contains(person));
        assertFalse(model.getFilteredPersonList().contains(person2));
        personFilter.parse(" -remark remark").execute(model);
        assertTrue(model.getFilteredPersonList().contains(person));
        assertTrue(model.getFilteredPersonList().contains(person2));
    }

    @Test
    void test_personFilter_multiple() throws ParseException, CommandException {
        Person person = Person.getDefaultPerson();
        person.setTags(Tags.of("tag1,tag2"));
        person.setName(Name.of("person 1"));
        person.setRemark(Remark.of("remark1"));
        Person person2 = Person.getDefaultPerson();
        person2.setTags(Tags.of("tag1,tag3"));
        person2.setName(Name.of("person 2"));
        person2.setRemark(Remark.of("remark2"));
        model.addPerson(person);
        model.addPerson(person2);
        personFilter.parse(" -tag tag1 -remark remark1").execute(model);
        assertTrue(model.getFilteredPersonList().contains(person));
        assertFalse(model.getFilteredPersonList().contains(person2));
        personFilter.parse(" -tag tag1 -remark remark2").execute(model);
        assertFalse(model.getFilteredPersonList().contains(person));
        assertTrue(model.getFilteredPersonList().contains(person2));
    }

    @Test
    void test_lessonFilter_name() throws ParseException, CommandException {
        Lesson lesson = Lesson.getDefaultLesson();
        lesson.setName(Name.of("test lesson 1"));
        Lesson lesson2 = Lesson.getDefaultLesson();
        lesson2.setName(Name.of("test lesson 2"));
        model.addLesson(lesson);
        model.addLesson(lesson2);
        lessonFilter.parse(" -name test lesson 1").execute(model);
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
        lessonFilter.parse(" -before 2").execute(model);
        assertTrue(model.getFilteredScheduleList().contains(lesson));
        assertFalse(model.getFilteredScheduleList().contains(lesson2));
        lessonFilter.parse(" -after 2").execute(model);
        assertFalse(model.getFilteredScheduleList().contains(lesson));
        assertTrue(model.getFilteredScheduleList().contains(lesson2));
        lessonFilter.parse(" -on 1").execute(model);
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
        lessonFilter.parse(" -subject physics").execute(model);
        assertTrue(model.getFilteredScheduleList().contains(lesson));
        assertFalse(model.getFilteredScheduleList().contains(lesson2));
        lessonFilter.parse(" -subject physics,chemistry").execute(model);
        assertTrue(model.getFilteredScheduleList().contains(lesson));
        assertTrue(model.getFilteredScheduleList().contains(lesson2));
        lessonFilter.parse(" -subject English").execute(model);
        assertFalse(model.getFilteredScheduleList().contains(lesson));
        assertFalse(model.getFilteredScheduleList().contains(lesson2));
    }

    @Test
    void test_lessonFilter_multiple() throws ParseException, CommandException {
        Lesson lesson = Lesson.getDefaultLesson();
        lesson.setSubject(Subject.of("physics"));
        lesson.setDay(Day.of("1"));
        Lesson lesson2 = Lesson.getDefaultLesson();
        lesson2.setSubject(Subject.of("chemistry"));
        lesson2.setDay(Day.of("3"));
        model.addLesson(lesson);
        model.addLesson(lesson2);
        lessonFilter.parse(" -subject physics -before 2").execute(model);
        assertTrue(model.getFilteredScheduleList().contains(lesson));
        assertFalse(model.getFilteredScheduleList().contains(lesson2));
        lessonFilter.parse(" -subject physics -after 2").execute(model);
        assertFalse(model.getFilteredScheduleList().contains(lesson));
        assertFalse(model.getFilteredScheduleList().contains(lesson2));
        lessonFilter.parse(" -subject physics -on 1").execute(model);
        assertTrue(model.getFilteredScheduleList().contains(lesson));
        assertFalse(model.getFilteredScheduleList().contains(lesson2));
    }

    @Test
    void test_multipleBeforeOnAfter() {
        assertThrows(ParseException.class, () -> lessonFilter.parse(" -before 1 -on 1"));
    }
}

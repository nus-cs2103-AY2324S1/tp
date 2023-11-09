package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static seedu.address.testutil.TypicalLessons.getTypicalScheduleList;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Subjects;
import seedu.address.model.person.Tags;

public class EditPersonCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs(),
            getTypicalScheduleList());
    @Test
    void test_name() throws ParseException, CommandException {
        Person person = model.getFilteredPersonList().get(0);
        Person expected = person.clone();
        expected.setName(new Name("Test Name 123"));
        Person editDescriptor = Person.getDefaultPerson();
        editDescriptor.setName(new Name("Test Name 123"));
        EditPersonCommand editPersonCommand = new EditPersonCommand(1, editDescriptor);
        editPersonCommand.execute(model);
        assertFalse(model.getPersonsFulfill(p -> p.equals(expected)).isEmpty());
        model.showPerson(expected.clone());
        expected.setName(new Name("Test Name 1234"));
        assertFalse(model.hasPerson(expected));
        editDescriptor.setName(new Name("Test Name 1234"));
        new EditPersonCommand(null, editDescriptor).execute(model);
        assertTrue(model.hasPerson(expected));
    }

    @Test
    void test_phone() throws ParseException, CommandException {
        Person person = model.getFilteredPersonList().get(0);
        Person expected = person.clone();
        expected.setPhone(Phone.of("12345678"));
        Person editDescriptor = Person.getDefaultPerson();
        editDescriptor.setPhone(Phone.of("12345678"));
        EditPersonCommand editPersonCommand = new EditPersonCommand(1, editDescriptor);
        editPersonCommand.execute(model);
        assertFalse(model.getPersonsFulfill(p -> p.equals(expected)).isEmpty());
        model.showPerson(expected.clone());
        expected.setPhone(Phone.of("12345679"));
        assertTrue(model.getPersonsFulfill(p -> p.equals(expected)).isEmpty());
        editDescriptor.setPhone(Phone.of("12345679"));
        new EditPersonCommand(null, editDescriptor).execute(model);
        assertFalse(model.getPersonsFulfill(p -> p.equals(expected)).isEmpty());
    }

    @Test
    void test_address() throws ParseException, CommandException {
        Person person = model.getFilteredPersonList().get(0);
        Person expected = person.clone();
        expected.setAddress(Address.of("Test Address 12-3"));
        Person editDescriptor = Person.getDefaultPerson();
        editDescriptor.setAddress(Address.of("Test Address 12-3"));
        EditPersonCommand editPersonCommand = new EditPersonCommand(1, editDescriptor);
        editPersonCommand.execute(model);
        assertFalse(model.getPersonsFulfill(p -> p.equals(expected)).isEmpty());
        model.showPerson(expected.clone());
        expected.setAddress(Address.of("Test Address 12-34"));
        assertTrue(model.getPersonsFulfill(p -> p.equals(expected)).isEmpty());
        editDescriptor.setAddress(Address.of("Test Address 12-34"));
        new EditPersonCommand(null, editDescriptor).execute(model);
        assertFalse(model.getPersonsFulfill(p -> p.equals(expected)).isEmpty());
    }

    @Test
    void test_email() throws ParseException, CommandException {
        Person person = model.getFilteredPersonList().get(0);
        Person expected = person.clone();
        expected.setEmail(Email.of("fake123@domain.com"));
        Person editDescriptor = Person.getDefaultPerson();
        editDescriptor.setEmail(Email.of("fake123@domain.com"));
        EditPersonCommand editPersonCommand = new EditPersonCommand(1, editDescriptor);
        editPersonCommand.execute(model);
        assertFalse(model.getPersonsFulfill(p -> p.equals(expected)).isEmpty());
        model.showPerson(expected.clone());
        expected.setEmail(Email.of("fake1234@domain.com"));
        assertTrue(model.getPersonsFulfill(p -> p.equals(expected)).isEmpty());
        editDescriptor.setEmail(Email.of("fake1234@domain.com"));
        new EditPersonCommand(null, editDescriptor).execute(model);
        assertFalse(model.getPersonsFulfill(p -> p.equals(expected)).isEmpty());
    }

    @Test
    void test_remark() throws ParseException, CommandException {
        Person person = model.getFilteredPersonList().get(0);
        Person expected = person.clone();
        expected.setRemark(Remark.of("Test Remark 123"));
        Person editDescriptor = Person.getDefaultPerson();
        editDescriptor.setRemark(Remark.of("Test Remark 123"));
        EditPersonCommand editPersonCommand = new EditPersonCommand(1, editDescriptor);
        editPersonCommand.execute(model);
        assertFalse(model.getPersonsFulfill(p -> p.equals(expected)).isEmpty());
        model.showPerson(expected.clone());
        expected.setRemark(Remark.of("Test Remark 1234"));
        assertTrue(model.getPersonsFulfill(p -> p.equals(expected)).isEmpty());
        editDescriptor.setRemark(Remark.of("Test Remark 1234"));
        new EditPersonCommand(null, editDescriptor).execute(model);
        assertFalse(model.getPersonsFulfill(p -> p.equals(expected)).isEmpty());
    }

    @Test
    void test_tags() throws ParseException, CommandException {
        Person person = model.getFilteredPersonList().get(0);
        Person expected = person.clone();
        expected.setTags(Tags.of("123, 456, 789"));
        Person editDescriptor = Person.getDefaultPerson();
        editDescriptor.setTags(Tags.of("123, 456, 789"));
        EditPersonCommand editPersonCommand = new EditPersonCommand(1, editDescriptor);
        editPersonCommand.execute(model);
        assertFalse(model.getPersonsFulfill(p -> p.equals(expected)).isEmpty());
        model.showPerson(expected.clone());
        expected.setTags(Tags.of("1234, 4567, 7890"));
        assertTrue(model.getPersonsFulfill(p -> p.equals(expected)).isEmpty());
        editDescriptor.setTags(Tags.of("1234, 4567, 7890"));
        new EditPersonCommand(null, editDescriptor).execute(model);
        assertFalse(model.getPersonsFulfill(p -> p.equals(expected)).isEmpty());
    }
    @Test
    void test_subjects() throws ParseException, CommandException {
        Person person = model.getFilteredPersonList().get(0);
        Person expected = person.clone();
        expected.setSubjects(Subjects.of("Physics, English"));
        Person editDescriptor = Person.getDefaultPerson();
        editDescriptor.setSubjects(Subjects.of("Physics, English"));
        EditPersonCommand editPersonCommand = new EditPersonCommand(1, editDescriptor);
        editPersonCommand.execute(model);
        assertFalse(model.getPersonsFulfill(p -> p.equals(expected)).isEmpty());
        model.showPerson(expected.clone());
        expected.setSubjects(Subjects.of("Physics, chemistry"));
        assertTrue(model.getPersonsFulfill(p -> p.equals(expected)).isEmpty());
        editDescriptor.setSubjects(Subjects.of("Physics, chemistry"));
        new EditPersonCommand(null, editDescriptor).execute(model);
        assertFalse(model.getPersonsFulfill(p -> p.equals(expected)).isEmpty());
    }

    @Test
    void test_combine() throws ParseException, CommandException {
        Person person = model.getFilteredPersonList().get(0);
        Person expected = person.clone();
        expected.setSubjects(Subjects.of("Physics, English"));
        expected.setTags(Tags.of("123, 456, 789"));
        expected.setRemark(Remark.of("Test Remark 123"));
        Person editDescriptor = Person.getDefaultPerson();
        editDescriptor.setSubjects(Subjects.of("Physics, English"));
        editDescriptor.setTags(Tags.of("123, 456, 789"));
        editDescriptor.setRemark(Remark.of("Test Remark 123"));
        EditPersonCommand editPersonCommand = new EditPersonCommand(1, editDescriptor);
        editPersonCommand.execute(model);
        assertFalse(model.getPersonsFulfill(p -> p.equals(expected)).isEmpty());
        model.showPerson(expected.clone());
        expected.setSubjects(Subjects.of("Physics, chemistry"));
        expected.setTags(Tags.of("1234, 4567, 7890"));
        expected.setRemark(Remark.of("Test Remark 1234"));
        assertTrue(model.getPersonsFulfill(p -> p.equals(expected)).isEmpty());
        editDescriptor.setSubjects(Subjects.of("Physics, chemistry"));
        editDescriptor.setTags(Tags.of("1234, 4567, 7890"));
        editDescriptor.setRemark(Remark.of("Test Remark 1234"));
        new EditPersonCommand(null, editDescriptor).execute(model);
        assertFalse(model.getPersonsFulfill(p -> p.equals(expected)).isEmpty());
    }

    @Test
    void test_nameCollision_noModification() {
        try {
            Person person = Person.getDefaultPerson();
            person.setName(new Name("test name 1 no collision"));
            assertFalse(model.hasPerson(person));
            new EditPersonCommand(1, person).execute(model);
            assertTrue(model.hasPerson(person));
            assertThrows(CommandException.class, () -> new EditPersonCommand(1, person).execute(model));
            assertThrows(CommandException.class, () -> new EditPersonCommand(2, person).execute(model));
            model.showPerson(model.getFilteredPersonList().get(0));
            assertThrows(CommandException.class, () -> new EditPersonCommand(null, person).execute(model));
            assertThrows(CommandException.class, () -> new EditPersonCommand(2, person).execute(model));
        } catch (Exception e) {
            fail();
        }
    }
    @Test
    void test_indexOutOfBound() {
        try {
            Person person = Person.getDefaultPerson();
            person.setName(new Name("test name 1 no collision"));
            assertFalse(model.hasPerson(person));
            new EditPersonCommand(1, person).execute(model);
            assertTrue(model.hasPerson(person));
            assertThrows(CommandException.class, () -> new EditPersonCommand(0, person).execute(model));
            assertThrows(CommandException.class, () -> new EditPersonCommand(3, person).execute(model));
            model.showPerson(model.getFilteredPersonList().get(0));
            assertThrows(CommandException.class, () -> new EditPersonCommand(null, person).execute(model));
            assertThrows(CommandException.class, () -> new EditPersonCommand(2, person).execute(model));
        } catch (Exception e) {
            fail();
        }
    }
}


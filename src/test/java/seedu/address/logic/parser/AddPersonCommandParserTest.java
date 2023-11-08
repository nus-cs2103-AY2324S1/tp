package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Subject;
import seedu.address.model.person.Subjects;
import seedu.address.model.person.Tags;
import seedu.address.model.tag.Tag;



public class AddPersonCommandParserTest {
    private AddPersonCommandParser p = new AddPersonCommandParser();

    @Test
    void test_parseName_parseWithoutName() throws ParseException {
        Person person = Person.getDefaultPerson();
        person.setNameIfNotDefault(Name.of("name"));
        assertEquals(person, p.parse(" -name name").getPerson());
        assertThrows(ParseException.class, () -> p.parse(" -name"));
        assertThrows(ParseException.class, () -> p.parse(" -phone 12345678"));
    }

    @Test
    void test_parsePhone_parseEmail() throws ParseException {
        Person person = Person.getDefaultPerson();
        person.setName(Name.of("name"));
        person.setPhoneIfNotDefault(Phone.of("12345678"));
        assertEquals(person, p.parse("-name name -phone 12345678").getPerson());
        person.setEmailIfNotDefault(Email.of("fake@domain.com"));
        assertEquals(person, p.parse("-name name -phone 12345678 -email fake@domain.com").getPerson());
    }

    @Test
    void test_parseRemark_parseAddress() throws ParseException {
        Person person = Person.getDefaultPerson();
        person.setName(Name.of("name"));
        person.setRemarkIfNotDefault(Remark.of("remark"));
        assertEquals(person, p.parse("-name name -remark remark").getPerson());
        person.setAddressIfNotDefault(Address.of("address 3/4-1"));
        assertEquals(person, p.parse("-name name -remark remark -address address 3/4-1 ").getPerson());
    }
    @Test
    void test_parseTags_parseSubjects() throws ParseException {
        Person person = Person.getDefaultPerson();
        person.setName(Name.of("name"));
        person.setTags(Tags.of("tag1,tag2"));
        assertEquals(person, p.parse("-name name -tag tag1,tag2").getPerson());
        person.setSubjects(Subjects.of("physics, chemistry"));
        assertEquals(person, p.parse("-name name -tag tag1,tag2 -subject physics, chemistry").getPerson());
    }

    @Test
    void parse_combine() {
        try {
            Person actualPerson = AddPersonCommandParser.parsePerson("add -name Yiwen"
                    + " -phone 12345678 -email email@u.com -address Blk 123, Clementi Ave 3, #12,34 "
                    + "-tag friends -subject English", false);
            Person expectedPerson = new Person(new Name("Yiwen"));
            expectedPerson.setPhone(new Phone("12345678"));
            expectedPerson.setEmail(new Email("email@u.com"));
            expectedPerson.setAddress(new Address("Blk 123, Clementi Ave 3, #12,34"));
            expectedPerson.setTagsIfNotDefault(Set.of(new Tag("friends")));
            expectedPerson.setSubjectsIfNotDefault(Set.of(new Subject("English")));
            assertEquals(expectedPerson, actualPerson);
        } catch (ParseException e) {
            fail();
        }
    }
    //todo, in us, make clear of the behaviour of duplicate flags
    @Test
    void test_duplicateFlag_unrecognisedFlags() throws ParseException {
        assertThrows(ParseException.class, () -> p.parse("-name name -name name"));
        assertThrows(ParseException.class, () -> p.parse(""));
        p.parse("this is alright -name name -subject physics -day 1 -start 14:00 -end 15:00 -flag flag");
    }

}

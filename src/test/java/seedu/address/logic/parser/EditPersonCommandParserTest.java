package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

public class EditPersonCommandParserTest {

    private EditPersonCommandParser p = new EditPersonCommandParser();

    @Test
    void test_parseName() throws ParseException {
        Person person = Person.getDefaultPerson();
        person.setNameIfNotDefault(Name.of("name"));
        assertEquals(person, p.parse(" -name name").getEditDescriptor());
        assertEquals(person, p.parse("1 -name name").getEditDescriptor());
        assertNull(p.parse(" -name name").getIndex());
        assertEquals(1, p.parse("1 -name name").getIndex());
        assertThrows(ParseException.class, () -> p.parse(" -name"));
        assertThrows(ParseException.class, () -> p.parse("1 -name"));
    }

    @Test
    void test_parsePhone_parseEmail() throws ParseException {
        Person person = Person.getDefaultPerson();
        person.setName(Name.of("name"));
        person.setPhoneIfNotDefault(Phone.of("12345678"));
        assertEquals(person, p.parse("-name name -phone 12345678").getEditDescriptor());
        assertEquals(person, p.parse("1 -name name -phone 12345678").getEditDescriptor());
        assertNull(p.parse("-name name -phone 12345678").getIndex());
        assertEquals(1, p.parse("1 -name name -phone 12345678").getIndex());

        person.setEmailIfNotDefault(Email.of("fake@domain.com"));
        assertEquals(person, p.parse("-name name -phone 12345678 -email fake@domain.com").getEditDescriptor());
        assertEquals(person, p.parse("1 -name name -phone 12345678 -email fake@domain.com").getEditDescriptor());
        assertNull(p.parse("-name name -phone 12345678 -email fake@domain.com").getIndex());
        assertEquals(1, p.parse("1 -name name -phone 12345678 -email fake@domain.com").getIndex());
    }

    @Test
    void test_parseRemark_parseAddress() throws ParseException {
        Person person = Person.getDefaultPerson();
        person.setName(Name.of("name"));
        person.setRemarkIfNotDefault(Remark.of("remark"));
        assertEquals(person, p.parse("-name name -remark remark").getEditDescriptor());
        assertEquals(person, p.parse("1 -name name -remark remark").getEditDescriptor());
        assertNull(p.parse("-name name -remark remark").getIndex());
        assertEquals(1, p.parse("1 -name name -remark remark").getIndex());

        person.setAddressIfNotDefault(Address.of("address 3/4-1"));
        assertEquals(person, p.parse("-name name -remark remark -address address 3/4-1 ").getEditDescriptor());
        assertEquals(person, p.parse("1 -name name -remark remark -address address 3/4-1 ").getEditDescriptor());
        assertNull(p.parse("-name name -remark remark -address address 3/4-1 ").getIndex());
        assertEquals(1, p.parse("1 -name name -remark remark -address address 3/4-1 ").getIndex());
    }
    @Test
    void test_parseTags_parseSubjects() throws ParseException {
        Person person = Person.getDefaultPerson();
        person.setName(Name.of("name"));
        person.setTags(Tags.of("tag1,tag2"));
        assertEquals(person, p.parse("-name name -tag tag1,tag2").getEditDescriptor());
        assertEquals(person, p.parse("1 -name name -tag tag1,tag2").getEditDescriptor());
        assertNull(p.parse("-name name -tag tag1,tag2").getIndex());
        assertEquals(1, p.parse("1 -name name -tag tag1,tag2").getIndex());

        person.setSubjects(Subjects.of("physics, chemistry"));
        assertEquals(person, p.parse("-name name -tag tag1,tag2 -subject physics, chemistry").getEditDescriptor());
        assertEquals(person, p.parse("1 -name name -tag tag1,tag2 -subject physics, chemistry").getEditDescriptor());
        assertNull(p.parse("-name name -tag tag1,tag2 -subject physics, chemistry").getIndex());
        assertEquals(1, p.parse("1 -name name -tag tag1,tag2 -subject physics, chemistry").getIndex());
    }

    @Test
    void parse_combine() throws ParseException {
        String input = "add -name Yiwen"
                + " -phone 12345678 -email email@u.com -address Blk 123, Clementi Ave 3, #12,34 "
                + "-tag friends -subject English";
        Person expectedPerson = new Person(new Name("Yiwen"));
        expectedPerson.setPhone(new Phone("12345678"));
        expectedPerson.setEmail(new Email("email@u.com"));
        expectedPerson.setAddress(new Address("Blk 123, Clementi Ave 3, #12,34"));
        expectedPerson.setTagsIfNotDefault(Set.of(new Tag("friends")));
        expectedPerson.setSubjectsIfNotDefault(Set.of(new Subject("English")));
        assertEquals(expectedPerson, p.parse(input).getEditDescriptor());
        assertNull(p.parse(input).getIndex());
        assertEquals(expectedPerson, p.parse("1 " + input).getEditDescriptor());
        assertEquals(1, p.parse("1 " + input).getIndex());
    }
    //todo, in us, make clear of the behaviour of duplicate flags
    @Test
    void test_duplicateFlag_unrecognisedFlags() throws ParseException {
        assertThrows(ParseException.class, () -> p.parse("-name name -name name"));
        assertThrows(ParseException.class, () -> p.parse("1 -name name -name name"));
        p.parse("this is alright -name name -subject physics -day 1 -start 14:00 -end 15:00 -flag flag");
    }

    //todo, specify this behaviour in the doc "edit index -name yiwen " would not trigger this exception
}

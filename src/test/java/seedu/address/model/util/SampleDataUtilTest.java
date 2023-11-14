package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CONTACT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COURSE_3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_STUDENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_TA;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TUTORIAL_3;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Contact;
import seedu.address.model.person.Course;
import seedu.address.model.person.Person;
import seedu.address.model.person.Role;
import seedu.address.model.person.Tutorial;

public class SampleDataUtilTest {

    @Test
    public void getSamplePersons() {
        Person[] samplePersons = SampleDataUtil.getSamplePersons();

        assertNotNull(samplePersons);

        assertEquals(6, samplePersons.length);

        // Alex Yeoh
        assertEquals("Alex Yeoh", samplePersons[0].getName().fullName);
        assertEquals("[Student]", samplePersons[0].getRoles().toString());
        assertEquals("[[alexyeoh@example.com]]", samplePersons[0].getContacts().toString());
        assertEquals("[CS1101]", samplePersons[0].getCourses().toString());
        assertEquals("[CS1101/T03E]", samplePersons[0].getTutorials().toString());
        assertTrue(samplePersons[0].getFavourite().getFavourite());

        // Bernice Yu
        assertEquals("Bernice Yu", samplePersons[1].getName().fullName);
        assertEquals("[TA]", samplePersons[1].getRoles().toString());
        assertEquals("[[berniceyu@example.com]]", samplePersons[1].getContacts().toString());
        assertEquals("[CS2100]", samplePersons[1].getCourses().toString());
        assertEquals("[CS2100/B32]", samplePersons[1].getTutorials().toString());
        assertFalse(samplePersons[1].getFavourite().getFavourite());

        // Charlotte Oliveiro
        assertEquals("Charlotte Oliveiro", samplePersons[2].getName().fullName);
        assertEquals("[Professor]", samplePersons[2].getRoles().toString());
        assertEquals("[[charlotte@example.com]]", samplePersons[2].getContacts().toString());
        assertEquals("[CS2106]", samplePersons[2].getCourses().toString());
        assertEquals("[CS2106/T04]", samplePersons[2].getTutorials().toString());
        assertFalse(samplePersons[2].getFavourite().getFavourite());

        // David Li
        assertEquals("David Li", samplePersons[3].getName().fullName);
        assertEquals("[Student]", samplePersons[3].getRoles().toString());
        assertEquals("[[lidavid@example.com]]", samplePersons[3].getContacts().toString());
        assertEquals("[CS2101]", samplePersons[3].getCourses().toString());
        assertEquals("[CS2101/G06]", samplePersons[3].getTutorials().toString());
        assertFalse(samplePersons[3].getFavourite().getFavourite());

        // Irfan Ibrahim
        assertEquals("Irfan Ibrahim", samplePersons[4].getName().fullName);
        assertEquals("[TA]", samplePersons[4].getRoles().toString());
        assertEquals("[[irfan@example.com]]", samplePersons[4].getContacts().toString());
        assertEquals("[CS2103T]", samplePersons[4].getCourses().toString());
        assertEquals("[CS2103T/F08]", samplePersons[4].getTutorials().toString());
        assertFalse(samplePersons[4].getFavourite().getFavourite());

        // Roy Balakrishnan
        assertEquals("Roy Balakrishnan", samplePersons[5].getName().fullName);
        assertEquals("[Professor]", samplePersons[5].getRoles().toString());
        assertEquals("[[royb@example.com]]", samplePersons[5].getContacts().toString());
        assertEquals("[CS2109S]", samplePersons[5].getCourses().toString());
        assertEquals("[CS2109S/T31]", samplePersons[5].getTutorials().toString());
        assertTrue(samplePersons[5].getFavourite().getFavourite());
    }

    @Test
    public void getSampleAddressBook() {
        assertNotNull(SampleDataUtil.getSampleAddressBook());
    }

    @Test
    public void getRolesSet() {
        Set<Role> expectedRoles = new HashSet<>();
        expectedRoles.add(new Role(VALID_ROLE_STUDENT));
        expectedRoles.add(new Role(VALID_ROLE_TA));
        Set<Role> actualRoles = SampleDataUtil.getRolesSet(VALID_ROLE_STUDENT, VALID_ROLE_TA);
        assertEquals(expectedRoles, actualRoles);
    }

    @Test
    public void getContactSet() {
        Set<Contact> expectedContacts = new HashSet<>();
        expectedContacts.add(new Contact(VALID_CONTACT_AMY));
        expectedContacts.add(new Contact(VALID_CONTACT_BOB));
        Set<Contact> actualContacts = SampleDataUtil.getContactSet(VALID_CONTACT_AMY, VALID_CONTACT_BOB);
        assertEquals(expectedContacts, actualContacts);
    }

    @Test
    public void getCoursesSet() {
        Set<Course> expectedCourses = new HashSet<>();
        expectedCourses.add(VALID_COURSE_1);
        expectedCourses.add(VALID_COURSE_2);
        expectedCourses.add(VALID_COURSE_3);
        Set<Course> actualCourses = SampleDataUtil.getCourseSet(VALID_COURSE_1, VALID_COURSE_2, VALID_COURSE_3);
        assertEquals(expectedCourses, actualCourses);
    }

    @Test
    public void getTutorialsSet() {
        Set<Tutorial> expectedTutorials = new HashSet<>();
        expectedTutorials.add(VALID_TUTORIAL_1);
        expectedTutorials.add(VALID_TUTORIAL_2);
        expectedTutorials.add(VALID_TUTORIAL_3);
        Set<Tutorial> actualTutorials = SampleDataUtil.getTutorialSet(VALID_TUTORIAL_1, VALID_TUTORIAL_2,
                VALID_TUTORIAL_3);
        assertEquals(expectedTutorials, actualTutorials);
    }

}

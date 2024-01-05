package seedu.address.model.session;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.model.person.StudentSet;
import seedu.address.testutil.PersonBuilder;

public class StudentSetTest {

    @Test
    public void addStudent_validStudent_success() {
        StudentSet studentSet = new StudentSet();
        Person student = new PersonBuilder().withName("Alice").build();

        studentSet.add(student);
        assertTrue(studentSet.contains(student));
        assertEquals(1, studentSet.size());
    }

    @Test
    public void removeStudent_validStudent_success() {
        StudentSet studentSet = new StudentSet();
        Person student = new PersonBuilder().withName("Alice").build();

        studentSet.add(student);
        assertTrue(studentSet.contains(student));

        studentSet.remove(student);
        assertFalse(studentSet.contains(student));
        assertEquals(0, studentSet.size());
    }

    @Test
    public void toStudentNames_validSessionStudents_success() {
        StudentSet studentSet = new StudentSet(
                new PersonBuilder().withName("Zoe").build(),
                new PersonBuilder().withName("Bob").build(),
                new PersonBuilder().withName("Alice").build()
        );

        String expected = "Alice, Bob, Zoe";
        assertEquals(expected, studentSet.toStudentNames());
    }

    @Test
    public void equals() {
        StudentSet studentSet1 = new StudentSet(new PersonBuilder().withName("Alice").build());
        StudentSet studentSet2 = new StudentSet(new PersonBuilder().withName("Bob").build());
        StudentSet studentSet1Copy = new StudentSet(new PersonBuilder().withName("Alice").build());

        // Testing equality with itself
        assertTrue(studentSet1.equals(studentSet1));

        // Testing equality with an equivalent object
        assertTrue(studentSet1.equals(studentSet1Copy));

        // Testing equality with a different object
        assertFalse(studentSet1.equals(studentSet2));

        // Testing equality with null
        assertFalse(studentSet1.equals(null));
    }
}

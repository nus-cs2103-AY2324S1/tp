package seedu.address.logic.commands;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SUBJECT_BIOLOGY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.UpdateSecLevelCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Name;
import seedu.address.model.person.Student;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
/**
 * Contains integration tests (interaction with the Model) and unit tests for UpdateSecLevelCommand.
 */
public class UpdateSecLevelCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void executeWithAllSec4() throws CommandException {
        Model model1 = new ModelManager();
        Student student1 = getSec4Student("Alice");
        Student student2 = getSec4Student("Bob");
        Student student3 = getSec4Student("Jack");
        model1.addPerson(student1);
        model1.addPerson(student2);
        model1.addPerson(student3);

        UpdateSecLevelCommand updateSecLevelCommand = new UpdateSecLevelCommand();
        updateSecLevelCommand.execute(model1);
        //All sec level 4 students have been removed from database.
        assertEquals(0, model1.getFilteredPersonList().size());
    }

    @Test
    public void executeWithAllNonSec4() throws CommandException {
        Model model1 = new ModelManager();
        Student[] nonSec4Students = getNonSec4Students();
        for (Student student : nonSec4Students) {
            model1.addPerson(student);
        }
        UpdateSecLevelCommand updateSecLevelCommand = new UpdateSecLevelCommand();
        updateSecLevelCommand.execute(model1);
        // No student is removed since there is no sec 4 student
        assertEquals(nonSec4Students.length, model1.getFilteredPersonList().size());
        model.getFilteredPersonList().stream().forEach(student -> {
            for (Student originalStudent : nonSec4Students) {
                if (student.getName().equals(originalStudent.getName())) {
                    assertEquals(originalStudent.getSecLevel().getValue() + 1, student.getSecLevel().getValue());
                }
            }
        });
    }

    @Test
    public void executewithMixedSec4AndNonSec4() throws CommandException {
        Model model1 = new ModelManager();
        Student[] nonSec4Students = getNonSec4Students();
        for (Student student : nonSec4Students) {
            model1.addPerson(student);
        }
        model1.addPerson(getSec4Student("Alice"));
        model1.addPerson(getSec4Student("Bob"));

        UpdateSecLevelCommand updateSecLevelCommand = new UpdateSecLevelCommand();
        updateSecLevelCommand.execute(model1);

        // Updated student list must have same number of students as nonSec4Students since all sec4 students are removed from database.
        assertEquals(nonSec4Students.length, model1.getFilteredPersonList().size());
        model.getFilteredPersonList().stream().forEach(student -> {
            for (Student originalStudent : nonSec4Students) {
                if (student.getName().equals(originalStudent.getName())) {
                    assertEquals(originalStudent.getSecLevel().getValue() + 1, student.getSecLevel().getValue());
                }
            }
        });
    }

    @Test
    public void EqualsMethod() {
        UpdateSecLevelCommand updateSecLevelCommand1 = new UpdateSecLevelCommand();
        UpdateSecLevelCommand updateSecLevelCommand2 = new UpdateSecLevelCommand();
        Command otherCommand = new ListCommand();
        // any two updateSecLevelCommand are equals
        assertEquals(updateSecLevelCommand1, updateSecLevelCommand2);

        // null -> not equal
        assertFalse(updateSecLevelCommand1.equals(null));

        // different type -> not equal
        assertFalse(updateSecLevelCommand1.equals(2.3));
        assertFalse(updateSecLevelCommand1.equals(otherCommand));
    }

    public Student getSec4Student(String name) {
        return new PersonBuilder().withName(name)
                .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                .withPhone("94351253").withGender("F").withSecLevel("4")
                .withNearestMrtStation("Jurong East")
                .withSubjects("Chinese").build();
    }

    public Student[] getNonSec4Students() {
        return new Student[] {
                new PersonBuilder().withName("A")
                        .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                        .withPhone("94351253").withGender("F").withSecLevel("1")
                        .withNearestMrtStation("Jurong East")
                        .withSubjects("Chinese").build(),
                new PersonBuilder().withName("B")
                        .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                        .withPhone("94351253").withGender("F").withSecLevel("2")
                        .withNearestMrtStation("Jurong East")
                        .withSubjects("Chinese").build(),
                new PersonBuilder().withName("C")
                        .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                        .withPhone("94351253").withGender("F").withSecLevel("3")
                        .withNearestMrtStation("Jurong East")
                        .withSubjects("Chinese").build()
        };
    }
}

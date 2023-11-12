package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.LinkedList;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Gender;
import seedu.address.model.person.MrtStation;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.SecLevel;
import seedu.address.model.person.Student;
import seedu.address.model.tag.Subject;

/**
 * Command for update the sec level for all students, remove sec 4 student.
 */
public class UpdateSecLevelCommand extends Command {

    public static final String UPDATE_COMMAND_WORD = "uplevel";
    public static final String UNDO_COMMAND_WORD = "undolevel";

    public static final String MESSAGE_UPDATE_SUCCESS = "Sec Levels for all students have been updated.\n"
                                                        + "Sec Levels for students with sec level 1-3 have been "
                                                        + "increased by 1\n"
                                                        + "Students with sec level 4 have been deleted\n"
                                                        + "Note: If you didn't intend to do so, enter 'undolevel' to "
                                                        + "revert back to previous student records";

    public static final String MESSAGE_UNDO_SUCCESS = "Students records before previous sec level update"
                                                        + " since you last opened this application have been restored";
    public static final String MESSAGE_UNDO_FAILURE = "There has not been any sec level update left to be undone since"
                                                        + " you last opened this application.";

    public static final String MESSAGE_NO_STUDENT_LEFT = "There isn't any student left";

    // To keep track of students records state before each uplevel, for the purpose of undolevel.
    private static LinkedList<Student[]> beforeLastUpdateStudents = new LinkedList<>();
    private boolean isUndo;
    public UpdateSecLevelCommand(boolean isUndo) {
        this.isUndo = isUndo;
    }

    public UpdateSecLevelCommand() {
        this(false);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (isUndo) {
            if (beforeLastUpdateStudents.size() == 0) {
                return new CommandResult(MESSAGE_UNDO_FAILURE);
            }
            Student[] lastUpdateStudents = beforeLastUpdateStudents.pollLast();
            model.setAddressBook(new AddressBook());
            performAddStudents(model, lastUpdateStudents);
            return new CommandResult(MESSAGE_UNDO_SUCCESS);
        } else if (model.getFilteredPersonList().size() == 0) {
            return new CommandResult(MESSAGE_NO_STUDENT_LEFT);
        } else {
            Student[] students = model.getFilteredPersonList().toArray(new Student[0]);
            // record the students before performing the update for undo purpose.
            beforeLastUpdateStudents.add(students);

            performUpdateStudents(model, students);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(MESSAGE_UPDATE_SUCCESS);
        }
    }


    private Student createUpdatedSecStudent(Student studentToUpdate) {
        assert studentToUpdate != null;
        assert studentToUpdate.getSecLevelValue() <= 3;
        assert studentToUpdate.getSecLevelValue() >= 1;

        Name updatedName = studentToUpdate.getName();
        Phone updatedPhone = studentToUpdate.getPhone();
        Email updatedEmail = studentToUpdate.getEmail();
        Address updatedAddress = studentToUpdate.getAddress();
        Gender updatedGender = studentToUpdate.getGender();
        MrtStation updatedNearestMrtStation = studentToUpdate.getNearestMrtStation();
        Set<Subject> updatedSubjects = studentToUpdate.getSubjects();
        SecLevel updatedSecLevel = studentToUpdate.getSecLevel().getUpSecLevel();
        return new Student(updatedName, updatedPhone, updatedEmail,
                updatedAddress, updatedGender, updatedSecLevel,
                updatedNearestMrtStation, updatedSubjects);
    }

    /**
     * Add all the students in the student array into the argument model.
     * @param model instance of Model type, e.g. ModelManager instance
     * @param students array of students to be added into model.
     */
    private void performAddStudents(Model model, Student[] students) {
        for (Student student: students) {
            model.addPerson(student);
        }
    }

    /**
     * Update model for the sec level of the students specified in the student array, delete sec level 4 students.
     * @param model instance of Model type, e.g. ModelManager instance
     * @param students array of students to be added into model.
     */
    private void performUpdateStudents(Model model, Student[] students) {
        for (Student student : students) {
            if (student.getSecLevelValue() > 3) {
                model.deletePerson(student);
            } else {
                Student updatedStudent = createUpdatedSecStudent(student);
                model.setPerson(student, updatedStudent);
            }
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        //instanceof handles nulls
        if (!(other instanceof UpdateSecLevelCommand)) {
            return false;
        }

        return true;
    }
}

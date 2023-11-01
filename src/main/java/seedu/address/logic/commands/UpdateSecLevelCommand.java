package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import static seedu.address.logic.commands.EditCommand.MESSAGE_DUPLICATE_PERSON;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ENROL_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEAREST_MRT_STATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEC_LEVEL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUBJECT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
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
public class UpdateSecLevelCommand extends Command {

    public static final String UPDATE_COMMAND_WORD = "uplevel";
    public static final String UNDO_COMMAND_WORD = "undolevel";

    public static final String MESSAGE_UPDATE_SUCCESS = "Sec Levels for all students have been updated.\n"
                                                        + "Sec Levels for students with sec level 1-3 have been increased by 1\n"
                                                        + "Sec Levels for students with sec level 4 have been deleted";

    public static final String MESSAGE_UNDO_SUCCESS = "All of the students records before last sec level update have been restored";
    public static final String MESSAGE_UNDO_FAILURE = "There is no sec level update record since you last open this application.";
    private static Student[] beforeLastUpdateStudents = null;
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
            if (beforeLastUpdateStudents == null) {
                return new CommandResult(MESSAGE_UNDO_FAILURE);
            }
            model.setAddressBook(new AddressBook());
            for (Student student: beforeLastUpdateStudents) {
                model.addPerson(student);
            }
            return new CommandResult(MESSAGE_UNDO_SUCCESS);
        } else {
            Student[] students = model.getFilteredPersonList().toArray(new Student[0]);
            // record the students before performing the update for undo purpose.
            beforeLastUpdateStudents = students;
            for (Student student : students) {
                if (student.getSecLevel().getValue() > 3) {
                    model.deletePerson(student);
                } else {
                    Student updatedStudent = createUpdatedSecStudent(student);
                    model.setPerson(student, updatedStudent);
                }
            }
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
            return new CommandResult(MESSAGE_UPDATE_SUCCESS);
        }
    }


    private Student createUpdatedSecStudent(Student studentToUpdate) {
        assert studentToUpdate != null;
        assert studentToUpdate.getSecLevel().getValue() <=3;
        assert studentToUpdate.getSecLevel().getValue() >= 1;

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

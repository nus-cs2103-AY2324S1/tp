package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.internship.role.InternshipRole;
import seedu.address.model.internship.task.InternshipTask;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_ASSIGNMENTS = "Assignments list contains duplicate assignment(s).";

    public static final String MESSAGE_DUPLICATE_INTERN_ROLES = "InternshipRole list contains duplicate role(s).";

    public static final String MESSAGE_DUPLICATE_INTERN_TASKS = "InternshipRole list contains duplicate task(s).";

    private static final Logger logger = LogsCenter.getLogger(JsonSerializableAddressBook.class);

    private final List<JsonAdaptedAssignment> assignments = new ArrayList<>();

    private final List<JsonAdaptedInternshipRole> roles = new ArrayList<>();

    private final List<JsonAdaptedInternshipTask> internshipTasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given assignments.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("assignments") List<JsonAdaptedAssignment> assignments,
                                       @JsonProperty("roles") List<JsonAdaptedInternshipRole> roles,
                                       @JsonProperty("internshipTasks")
                                           List<JsonAdaptedInternshipTask> internshipTasks) {
        this.assignments.addAll(assignments);
        this.roles.addAll(roles);
        this.internshipTasks.addAll(internshipTasks);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        assignments.addAll(source.getAssignmentList().stream()
                .map(JsonAdaptedAssignment::new).collect(Collectors.toList()));
        roles.addAll(source.getInternshipRoleList().stream()
                .map(JsonAdaptedInternshipRole::new).collect(Collectors.toList()));
        internshipTasks.addAll(source.getInternshipTaskList().stream()
                .map(JsonAdaptedInternshipTask::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedAssignment jsonAdaptedAssignment : assignments) {
            try {
                Assignment assignment = jsonAdaptedAssignment.toModelType();
                if (addressBook.hasAssignment(assignment)) {
                    throw new IllegalValueException(MESSAGE_DUPLICATE_ASSIGNMENTS);
                }
                addressBook.addAssignment(assignment);
            } catch (IllegalValueException e) {
                logger.warning("Invalid assignment, will not be added.");
            }
        }

        for (JsonAdaptedInternshipRole jsonAdaptedInternshipRole : roles) {
            try {
                InternshipRole role = jsonAdaptedInternshipRole.toModelType();
                if (addressBook.hasInternshipRole(role)) {
                    throw new IllegalValueException(MESSAGE_DUPLICATE_INTERN_ROLES);
                }
                addressBook.addInternshipRole(role);
            } catch (IllegalValueException e) {
                logger.warning("Invalid role, will not be added");
            }
        }

        for (JsonAdaptedInternshipTask jsonAdaptedInternshipTask : internshipTasks) {
            try {
                InternshipTask internshipTask = jsonAdaptedInternshipTask.toModelType();
                if (addressBook.hasInternshipTask(internshipTask)) {
                    throw new IllegalValueException(MESSAGE_DUPLICATE_INTERN_TASKS);
                }
                if (!addressBook.getInternshipRoleList().contains(internshipTask.getInternshipRole())) {
                    throw new IllegalValueException("Task does not belong to a valid internship role");
                }
                addressBook.addInternshipTask(internshipTask);
            } catch (IllegalValueException e) {
                logger.warning("Invalid task, will not be added");
            }
        }
        return addressBook;
    }

}

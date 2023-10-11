package seedu.staffsnap.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_JOB_TITLE;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.staffsnap.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.staffsnap.model.Model.PREDICATE_SHOW_ALL_EMPLOYEES;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.staffsnap.commons.core.index.Index;
import seedu.staffsnap.commons.util.CollectionUtil;
import seedu.staffsnap.commons.util.ToStringBuilder;
import seedu.staffsnap.logic.Messages;
import seedu.staffsnap.logic.commands.exceptions.CommandException;
import seedu.staffsnap.model.Model;
import seedu.staffsnap.model.employee.Department;
import seedu.staffsnap.model.employee.Employee;
import seedu.staffsnap.model.employee.JobTitle;
import seedu.staffsnap.model.employee.Name;
import seedu.staffsnap.model.employee.Phone;
import seedu.staffsnap.model.tag.Tag;

/**
 * Edits the details of an existing employee in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the employee identified "
            + "by the index number used in the displayed employee list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_DEPARTMENT + "DEPARTMENT] "
            + "[" + PREFIX_JOB_TITLE + "JOBTITLE] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_DEPARTMENT + "johndoe@example.com";

    public static final String MESSAGE_EDIT_EMPLOYEE_SUCCESS = "Edited Employee: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_EMPLOYEE = "This employee already exists in the address book.";

    private final Index index;
    private final EditEmployeeDescriptor editEmployeeDescriptor;

    /**
     * @param index of the employee in the filtered employee list to edit
     * @param editEmployeeDescriptor details to edit the employee with
     */
    public EditCommand(Index index, EditEmployeeDescriptor editEmployeeDescriptor) {
        requireNonNull(index);
        requireNonNull(editEmployeeDescriptor);

        this.index = index;
        this.editEmployeeDescriptor = new EditEmployeeDescriptor(editEmployeeDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Employee> lastShownList = model.getFilteredEmployeeList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_EMPLOYEE_DISPLAYED_INDEX);
        }

        Employee employeeToEdit = lastShownList.get(index.getZeroBased());
        Employee editedEmployee = createEditedEmployee(employeeToEdit, editEmployeeDescriptor);

        if (!employeeToEdit.isSameEmployee(editedEmployee) && model.hasEmployee(editedEmployee)) {
            throw new CommandException(MESSAGE_DUPLICATE_EMPLOYEE);
        }

        model.setEmployee(employeeToEdit, editedEmployee);
        model.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);
        return new CommandResult(String.format(MESSAGE_EDIT_EMPLOYEE_SUCCESS, Messages.format(editedEmployee)));
    }

    /**
     * Creates and returns a {@code Employee} with the details of {@code employeeToEdit}
     * edited with {@code editEmployeeDescriptor}.
     */
    private static Employee createEditedEmployee(
            Employee employeeToEdit, EditEmployeeDescriptor editEmployeeDescriptor) {
        assert employeeToEdit != null;

        Name updatedName = editEmployeeDescriptor.getName().orElse(employeeToEdit.getName());
        Phone updatedPhone = editEmployeeDescriptor.getPhone().orElse(employeeToEdit.getPhone());
        Department updatedDepartment = editEmployeeDescriptor.getDepartment().orElse(employeeToEdit.getDepartment());
        JobTitle updatedJobTitle = editEmployeeDescriptor.getJobTitle().orElse(employeeToEdit.getJobTitle());
        Set<Tag> updatedTags = editEmployeeDescriptor.getTags().orElse(employeeToEdit.getTags());

        return new Employee(updatedName, updatedPhone, updatedDepartment, updatedJobTitle, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        EditCommand otherEditCommand = (EditCommand) other;
        return index.equals(otherEditCommand.index)
                && editEmployeeDescriptor.equals(otherEditCommand.editEmployeeDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editEmployeeDescriptor", editEmployeeDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the employee with. Each non-empty field value will replace the
     * corresponding field value of the employee.
     */
    public static class EditEmployeeDescriptor {
        private Name name;
        private Phone phone;
        private Department department;
        private JobTitle jobTitle;
        private Set<Tag> tags;

        public EditEmployeeDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditEmployeeDescriptor(EditEmployeeDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setDepartment(toCopy.department);
            setJobTitle(toCopy.jobTitle);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, department, jobTitle, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setDepartment(Department department) {
            this.department = department;
        }

        public Optional<Department> getDepartment() {
            return Optional.ofNullable(department);
        }

        public void setJobTitle(JobTitle jobTitle) {
            this.jobTitle = jobTitle;
        }

        public Optional<JobTitle> getJobTitle() {
            return Optional.ofNullable(jobTitle);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditEmployeeDescriptor)) {
                return false;
            }

            EditEmployeeDescriptor otherEditEmployeeDescriptor = (EditEmployeeDescriptor) other;
            return Objects.equals(name, otherEditEmployeeDescriptor.name)
                    && Objects.equals(phone, otherEditEmployeeDescriptor.phone)
                    && Objects.equals(department, otherEditEmployeeDescriptor.department)
                    && Objects.equals(jobTitle, otherEditEmployeeDescriptor.jobTitle)
                    && Objects.equals(tags, otherEditEmployeeDescriptor.tags);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("department", department)
                    .add("jobTitle", jobTitle)
                    .add("tags", tags)
                    .toString();
        }
    }
}

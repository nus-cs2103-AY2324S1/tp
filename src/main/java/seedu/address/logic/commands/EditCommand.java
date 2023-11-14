package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LEAVE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MANAGER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_EMPLOYEES;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.employee.Address;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Leave;
import seedu.address.model.employee.Phone;
import seedu.address.model.employee.Role;
import seedu.address.model.employee.Salary;
import seedu.address.model.name.DepartmentName;
import seedu.address.model.name.EmployeeName;

/**
 * Edits the details of an existing employee in the ManageHR app.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";
    public static final String MESSAGE_EXAMPLE = COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the employee identified "
            + "by the index number used in the displayed employee list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_SALARY + "SALARY] "
            + "[" + PREFIX_LEAVE + "LEAVE] "
            + "[" + PREFIX_ROLE + "ROLE] "
            + "[" + PREFIX_MANAGER + "MANAGER]...\n"
            + "[" + PREFIX_DEPARTMENT + "DEPARTMENT]...\n"
            + "\n"
            + "Example: \n" + MESSAGE_EXAMPLE;

    public static final String MESSAGE_EDIT_EMPLOYEE_SUCCESS = "Edited Employee: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_EMPLOYEE = "This employee already exists in the ManageHR app.";
    public static final String MESSAGE_UNDEFINED_DEPARTMENT = "The department(s) currently do not exist in ManageHR.";

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

        for (DepartmentName departmentName : editedEmployee.getDepartments()) {
            if (!model.hasDepartmentWithName(departmentName)) {
                throw new CommandException(MESSAGE_UNDEFINED_DEPARTMENT);
            }
        }

        model.setEmployee(employeeToEdit, editedEmployee);
        model.updateFilteredEmployeeList(PREDICATE_SHOW_ALL_EMPLOYEES);
        return new CommandResult(String.format(MESSAGE_EDIT_EMPLOYEE_SUCCESS, Messages.format(editedEmployee)));
    }

    /**
     * Creates and returns a {@code Employee} with the details of {@code employeeToEdit}
     * edited with {@code editEmployeeDescriptor}.
     */
    private static Employee createEditedEmployee(Employee employeeToEdit,
                                                 EditEmployeeDescriptor editEmployeeDescriptor) {
        assert employeeToEdit != null;

        EmployeeName updatedName = editEmployeeDescriptor.getName().orElse(employeeToEdit.getName());
        Phone updatedPhone = editEmployeeDescriptor.getPhone().orElse(employeeToEdit.getPhone());
        Email updatedEmail = editEmployeeDescriptor.getEmail().orElse(employeeToEdit.getEmail());
        Address updatedAddress = editEmployeeDescriptor.getAddress().orElse(employeeToEdit.getAddress());
        Salary updatedSalary = editEmployeeDescriptor.getSalary().orElse(employeeToEdit.getSalary());
        Leave updatedLeave = editEmployeeDescriptor.getLeave().orElse(employeeToEdit.getLeave());
        Role updatedRole = editEmployeeDescriptor.getRole().orElse(employeeToEdit.getRole());
        Set<EmployeeName> updatedSupervisors = editEmployeeDescriptor
                .getSupervisors().orElse(employeeToEdit.getSupervisors());
        Set<DepartmentName> updatedDepartments = editEmployeeDescriptor
                .getDepartments().orElse(employeeToEdit.getDepartments());

        return new Employee(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedSalary, updatedLeave, updatedRole, updatedSupervisors, updatedDepartments);
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
        private EmployeeName name;
        private Phone phone;
        private Email email;
        private Address address;
        private Salary salary;
        private Leave leave;
        private Role role;
        private Set<EmployeeName> supervisors;
        private Set<DepartmentName> departments;

        public EditEmployeeDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code departments} is used internally.
         */
        public EditEmployeeDescriptor(EditEmployeeDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setSalary(toCopy.salary);
            setLeave(toCopy.leave);
            setRole(toCopy.role);
            setSupervisors(toCopy.supervisors);
            setDepartments(toCopy.departments);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, salary,
                    leave, role, supervisors, departments);
        }

        public void setName(EmployeeName name) {
            this.name = name;
        }

        public Optional<EmployeeName> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        public void setSalary(Salary salary) {
            this.salary = salary;
        }

        public Optional<Salary> getSalary() {
            return Optional.ofNullable(salary);
        }

        public void setLeave(Leave leave) {
            this.leave = leave;
        }

        public Optional<Leave> getLeave() {
            return Optional.ofNullable(leave);
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public Optional<Role> getRole() {
            return Optional.ofNullable(role);
        }

        /**
         * Sets {@code supervisors} to this object's {@code supervisors}.
         * A defensive copy of {@code supervisors} is used internally.
         */
        public void setSupervisors(Set<EmployeeName> supervisors) {
            this.supervisors = (supervisors != null) ? new HashSet<>(supervisors) : null;
        }

        /**
         * Returns an unmodifiable name set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code supervisors} is null.
         */
        public Optional<Set<EmployeeName>> getSupervisors() {
            return (supervisors != null)
                    ? Optional.of(Collections.unmodifiableSet(supervisors)) : Optional.empty();
        }

        /**
         * Sets {@code departments} to this object's {@code departments}.
         * A defensive copy of {@code departments} is used internally.
         */
        public void setDepartments(Set<DepartmentName> departments) {
            this.departments = (departments != null) ? new HashSet<>(departments) : null;
        }

        /**
         * Returns an unmodifiable department set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code departments} is null.
         */
        public Optional<Set<DepartmentName>> getDepartments() {
            return (departments != null) ? Optional.of(Collections.unmodifiableSet(departments)) : Optional.empty();
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
                    && Objects.equals(email, otherEditEmployeeDescriptor.email)
                    && Objects.equals(address, otherEditEmployeeDescriptor.address)
                    && Objects.equals(salary, otherEditEmployeeDescriptor.salary)
                    && Objects.equals(leave, otherEditEmployeeDescriptor.leave)
                    && Objects.equals(role, otherEditEmployeeDescriptor.role)
                    && Objects.equals(supervisors, otherEditEmployeeDescriptor.supervisors)
                    && Objects.equals(departments, otherEditEmployeeDescriptor.departments);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("salary", salary)
                    .add("leave", leave)
                    .add("role", role)
                    .add("supervisors", supervisors)
                    .add("departments", departments)
                    .toString();
        }
    }
}

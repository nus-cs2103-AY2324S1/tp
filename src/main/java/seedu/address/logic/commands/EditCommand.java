package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ANNUAL_LEAVE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BANK_ACCOUNT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_JOIN_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SALARY;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.AnnualLeave;
import seedu.address.model.person.BankAccount;
import seedu.address.model.person.Email;
import seedu.address.model.person.JoinDate;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Salary;

/**
 * Edits the details of an existing employee in the list.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the employee identified "
            + "by the index number used in the displayed employee list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + " NAME] "
            + "[" + PREFIX_PHONE + " PHONE] "
            + "[" + PREFIX_EMAIL + " EMAIL] "
            + "[" + PREFIX_ADDRESS + " ADDRESS] "
            + "[" + PREFIX_BANK_ACCOUNT + " BANK_ACCOUNT] "
            + "[" + PREFIX_JOIN_DATE + " JOIN_DATE] "
            + "[" + PREFIX_SALARY + " SALARY] "
            + "[" + PREFIX_ANNUAL_LEAVE + " ANNUAL_LEAVE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + " 91234568 "
            + PREFIX_EMAIL + " johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Employee: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This employee already exists in the list.";

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
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person employeeToEdit = lastShownList.get(index.getZeroBased());
        Person editedEmployee = createEditedEmployee(employeeToEdit, editEmployeeDescriptor);

        if (!employeeToEdit.isSamePerson(editedEmployee) && model.hasPerson(editedEmployee)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(employeeToEdit, editedEmployee);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedEmployee)));
    }

    /**
     * Creates and returns an {@code Employee} with the details of {@code employeeToEdit}
     * edited with {@code editEmployeeDescriptor}.
     */
    private static Person createEditedEmployee(Person employeeToEdit, EditEmployeeDescriptor editEmployeeDescriptor) {
        assert employeeToEdit != null;

        Name updatedName = editEmployeeDescriptor.getName().orElse(employeeToEdit.getName());
        Phone updatedPhone = editEmployeeDescriptor.getPhone().orElse(employeeToEdit.getPhone());
        Email updatedEmail = editEmployeeDescriptor.getEmail().orElse(employeeToEdit.getEmail());
        Address updatedAddress = editEmployeeDescriptor.getAddress().orElse(employeeToEdit.getAddress());
        BankAccount updatedBankAccount = editEmployeeDescriptor.getBankAccount()
            .orElse(employeeToEdit.getBankAccount());
        JoinDate updatedJoinDate = editEmployeeDescriptor.getJoinDate()
            .orElse(employeeToEdit.getJoinDate());
        Salary updatedSalary = editEmployeeDescriptor.getSalary()
            .orElse(employeeToEdit.getSalary());
        AnnualLeave updatedAnnualLeave = editEmployeeDescriptor.getAnnualLeave()
            .orElse(employeeToEdit.getAnnualLeave());

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress, updatedBankAccount, updatedJoinDate,
            updatedSalary, updatedAnnualLeave);
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
        private Email email;
        private Address address;
        private AnnualLeave annualLeave;
        private BankAccount bankAccount;
        private JoinDate joinDate;
        private Salary salary;

        public EditEmployeeDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditEmployeeDescriptor(EditEmployeeDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
            setAnnualLeave(toCopy.annualLeave);
            setBankAccount(toCopy.bankAccount);
            setJoinDate(toCopy.joinDate);
            setSalary(toCopy.salary);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address, annualLeave, bankAccount, joinDate, salary);
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

        public void setBankAccount(BankAccount bankAccount) {
            this.bankAccount = bankAccount;
        }

        public Optional<BankAccount> getBankAccount() {
            return Optional.ofNullable(bankAccount);
        }

        public void setJoinDate(JoinDate joinDate) {
            this.joinDate = joinDate;
        }

        public Optional<JoinDate> getJoinDate() {
            return Optional.ofNullable(joinDate);
        }

        public void setSalary(Salary salary) {
            this.salary = salary;
        }

        public Optional<Salary> getSalary() {
            return Optional.ofNullable(salary);
        }

        public void setAnnualLeave(AnnualLeave annualLeave) {
            this.annualLeave = annualLeave;
        }

        public Optional<AnnualLeave> getAnnualLeave() {
            return Optional.ofNullable(annualLeave);
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
                    && Objects.equals(bankAccount, otherEditEmployeeDescriptor.bankAccount)
                    && Objects.equals(joinDate, otherEditEmployeeDescriptor.joinDate)
                    && Objects.equals(salary, otherEditEmployeeDescriptor.salary)
                    && Objects.equals(annualLeave, otherEditEmployeeDescriptor.annualLeave);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("address", address)
                    .add("bankAccount", bankAccount)
                    .add("joinDate", joinDate)
                    .add("salary", salary)
                    .add("annualLeave", annualLeave)
                    .toString();
        }
    }
}

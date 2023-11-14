package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.attendance.AttendanceStorage;
import seedu.address.model.person.attendance.AttendanceType;
import seedu.address.model.person.payroll.PayrollStorage;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated,
 * immutable.
 */
public class Person {

    // Identity fields
    private final Email email;
    private final Name name;
    private final Phone phone;

    // Data fields
    private final Address address;
    private final AnnualLeave annualLeave;
    private final BankAccount bankAccount;
    private final JoinDate joinDate;
    private final Salary salary;
    private final AttendanceStorage attendanceStorage;
    private final PayrollStorage payrollStorage;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, BankAccount bankAccount, JoinDate joinDate,
            Salary salary, AnnualLeave annualLeave,
                  AttendanceStorage attendanceStorage, PayrollStorage payrollStorage) {
        requireAllNonNull(name, phone, email, address, bankAccount, joinDate, salary, annualLeave);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.bankAccount = bankAccount;
        this.joinDate = joinDate;
        this.salary = salary;
        this.annualLeave = annualLeave;
        this.attendanceStorage = attendanceStorage;
        this.payrollStorage = payrollStorage;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public JoinDate getJoinDate() {
        return joinDate;
    }

    public Salary getSalary() {
        return salary;
    }

    public AnnualLeave getAnnualLeave() {
        return annualLeave;
    }

    public List<LocalDate> getLeaveList() {
        return annualLeave.getLeaveList();
    }

    public PayrollStorage getPayrollStorage() {
        return this.payrollStorage;
    }

    public AttendanceStorage getAttendanceStorage() {
        return attendanceStorage;
    }

    private AttendanceType getAttendanceToday() {
        return this.attendanceStorage.getType(LocalDate.now());
    }

    /**
     * @return the working status of this employee.
     */
    public AttendanceType getWorkingStatusToday() {
        if (this.annualLeave.getLeaveStatus().equals("On Leave")) {
            return AttendanceType.ON_LEAVE;
        }
        return this.getAttendanceToday();
    }

    /**
     * Adds a payroll to the payroll list of this person.
     * @param payroll Payroll to be added.
     */
    public void addPayroll(Payroll payroll) {
        this.payrollStorage.add(payroll);
    }

    /**
     * Returns the latest payroll of this person.
     * @return Latest payroll.
     */
    public Payroll getLatestPayroll() {
        return this.payrollStorage.getLatestPayroll();
    }

    /**
     * Returns a payroll based on a specific date.
     * @param date Start date of the payroll you want to retrieve, in MM/YY.
     * @return payroll of a specific start date.
     */
    public Payroll getPayrollWithStartDate(LocalDate date) {
        return this.payrollStorage.getPayrollWithStartDate(date);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
            && otherPerson.equals(this);
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && bankAccount.equals(otherPerson.bankAccount)
                && joinDate.equals(otherPerson.joinDate)
                && salary.equals(otherPerson.salary)
                && annualLeave.equals(otherPerson.annualLeave);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, bankAccount, joinDate, salary, annualLeave);
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
                .add("annualLeave", annualLeave.value)
                .toString();
    }
}

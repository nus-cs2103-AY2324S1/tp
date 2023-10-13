package seedu.address.model.employee;

import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.*;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

import static seedu.address.commons.util.CollectionUtil.isAnyNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Employee {

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

    /**
     * Every field must be present and not null.
     */
    public Employee(Name name, Phone phone, Email email, Address address, BankAccount bankAccount, JoinDate joinDate, Salary salary, AnnualLeave annualLeave) {
        requireAllNonNull(name, phone, email, address, bankAccount, joinDate, salary, annualLeave);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.bankAccount = bankAccount;
        this.joinDate = joinDate;
        this.salary = salary;
        this.annualLeave = annualLeave;
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
                .add("bank account", bankAccount)
                .add("join date", joinDate)
                .add("salary", salary)
                .add("annual leave", annualLeave)
                .toString();
    }

    public Address getAddress() {
        return this.address;
    }

    public Email getEmail() {
        return this.email;
    }

    public Name getName() {
        return this.name;
    }

    public Phone getPhone() {
        return this.phone;
    }

    /**
     * Returns true if both employees have the same identity and data fields.
     * This defines a stronger notion of equality between two employees.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Employee)) {
            return false;
        }

        Employee otherEmployee = (Employee) other;
        return name.equals(otherEmployee.name)
                && phone.equals(otherEmployee.phone)
                && email.equals(otherEmployee.email)
                && address.equals(otherEmployee.address)
                && bankAccount.equals(otherEmployee.bankAccount)
                && joinDate.equals(otherEmployee.joinDate)
                && salary.equals(otherEmployee.salary)
                && annualLeave.equals(otherEmployee.annualLeave);
    }

    /**
     * Returns true if both employees have the same name.
     * This defines a weaker notion of equality between two employees.
     */
    public boolean isSameEmployee(Employee otherEmployee) {
        if (otherEmployee == this) {
            return true;
        }

        return otherEmployee != null
                && otherEmployee.getName().equals(getName());
    }
}

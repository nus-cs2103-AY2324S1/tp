package seedu.address.model.employee;

import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

import static seedu.address.commons.util.CollectionUtil.isAnyNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class Employee {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final BankAccount bankAccount;
    private final JoinDate joinDate;
    private final Salary salary;
    private final AnnualLeave annualLeave;

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
}

package seedu.address.model.employee;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

import java.util.Objects;

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

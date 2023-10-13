package seedu.address.model.employee;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;

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
}

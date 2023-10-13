package seedu.address.model.employee;

import java.util.Set;

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

}

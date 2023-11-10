package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;

import seedu.address.model.person.Address;
import seedu.address.model.person.AnnualLeave;
import seedu.address.model.person.BankAccount;
import seedu.address.model.person.Email;
import seedu.address.model.person.JoinDate;
import seedu.address.model.person.Name;
import seedu.address.model.person.Payroll;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Salary;
import seedu.address.model.person.attendance.AttendanceStorage;
import seedu.address.model.person.payroll.PayrollStorage;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_BANKACCOUNT = "12345678";
    public static final String DEFAULT_JOINDATE = "12/02/2023";
    public static final String DEFAULT_SALARY = "2000.00";
    public static final String DEFAULT_ANNUALLEAVE = "14";
    public static final ArrayList<String> DEFAULT_ATTENDANCE_STORAGE = new ArrayList<>(
        Arrays.asList("27/10/2023//late"));
    public static final ArrayList<Payroll> DEFAULT_PAYROLL_STORAGE = new ArrayList<>(
        Arrays.asList(new Payroll(new Salary(DEFAULT_SALARY))));

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private BankAccount bankAccount;
    private JoinDate joinDate;
    private Salary salary;
    private AnnualLeave annualLeave;
    private AttendanceStorage attendanceStorage;
    private PayrollStorage payrollStorage;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        bankAccount = new BankAccount(DEFAULT_BANKACCOUNT);
        joinDate = new JoinDate(DEFAULT_JOINDATE);
        salary = new Salary(DEFAULT_SALARY);
        annualLeave = new AnnualLeave(DEFAULT_ANNUALLEAVE);
        attendanceStorage = new AttendanceStorage(DEFAULT_ATTENDANCE_STORAGE);
        payrollStorage = new PayrollStorage(DEFAULT_PAYROLL_STORAGE);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        address = personToCopy.getAddress();
        bankAccount = personToCopy.getBankAccount();
        joinDate = personToCopy.getJoinDate();
        salary = personToCopy.getSalary();
        annualLeave = personToCopy.getAnnualLeave();
        attendanceStorage = personToCopy.getAttendanceStorage();
        payrollStorage = personToCopy.getPayrollStorage();
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code BankAccount} of the {@code Person} that we are building.
     */
    public PersonBuilder withBankAccount(String bankAccount) {
        this.bankAccount = new BankAccount(bankAccount);
        return this;
    }

    /**
     * Sets the {@code JoinDate} of the {@code Person} that we are building.
     */
    public PersonBuilder withJoinDate(String joinDate) {
        this.joinDate = new JoinDate(joinDate);
        return this;
    }

    /**
     * Sets the {@code Salary} of the {@code Person} that we are building.
     */
    public PersonBuilder withSalary(String salary) {
        this.salary = new Salary(salary);
        return this;
    }

    /**
     * Sets the {@code AnnualLeave} of the {@code Person} that we are building.
     */
    public PersonBuilder withAnnualLeave(String annualLeave) {
        this.annualLeave = new AnnualLeave(annualLeave);
        return this;
    }

    /**
     * Sets the {@code AttendanceStorage} of the {@code Person} that we are building.
     */
    public PersonBuilder withAttendanceStorage(ArrayList<String> attendanceStorage) {
        this.attendanceStorage = new AttendanceStorage(attendanceStorage);
        return this;
    }

    /**
     * Sets the {@code payrollStorage} of the {@code Person} that we are building
     */
    public PersonBuilder withPayrollStorage(ArrayList<Payroll> payrollStorage) {
        this.payrollStorage = new PayrollStorage(payrollStorage);
        this.payrollStorage.add(new Payroll(this.salary));
        return this;
    }

    /**
     * Turn the {@code PersonBuilder} object into {@code Person} object
     * @return person object
     */
    public Person build() {
        return new Person(name, phone, email, address, bankAccount,
                joinDate, salary, annualLeave, attendanceStorage, payrollStorage);
    }

}

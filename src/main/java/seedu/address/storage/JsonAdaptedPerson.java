package seedu.address.storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.person.Address;
import seedu.address.model.person.AnnualLeave;
import seedu.address.model.person.BankAccount;
import seedu.address.model.person.Email;
import seedu.address.model.person.JoinDate;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Salary;
import seedu.address.model.person.attendance.AttendanceStorage;
import seedu.address.model.person.payroll.PayrollStorage;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String bankAccount;
    private final String joinDate;
    private final String salary;
    private final String annualLeave;
    private final ArrayList<String> attendanceStorage;
    private final ArrayList<String> payrollStorage;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address,
            @JsonProperty("bankAccount") String bankAccount, @JsonProperty("joinDate") String joinDate,
            @JsonProperty("salary") String salary, @JsonProperty("annualLeave") String annualLeave,
            @JsonProperty("attendanceStorage") ArrayList<String> attendanceStorage,
            @JsonProperty("payrollStorage") ArrayList<String> payrollStorage) {
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

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        bankAccount = source.getBankAccount().value;
        joinDate = source.getJoinDate().value;
        salary = source.getSalary().value;
        attendanceStorage = source.getAttendanceStorage().getValue();
        annualLeave = source.getAnnualLeave().toString();
        payrollStorage = source.getPayrollStorage().getValue();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's
     * {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in
     *                               the adapted person.
     */
    public Person toModelType() throws IllegalValueException {

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (!BankAccount.isValidBankAccount(bankAccount)) {
            throw new IllegalValueException(BankAccount.MESSAGE_CONSTRAINTS);
        }
        final BankAccount modelBankAccount = new BankAccount(bankAccount);

        if (!JoinDate.isValidJoinDate(joinDate)) {
            throw new IllegalValueException(JoinDate.MESSAGE_CONSTRAINTS);
        }
        final JoinDate modelJoinDate = new JoinDate(joinDate);

        if (!Salary.isValid(salary)) {
            throw new IllegalValueException(Salary.MESSAGE_CONSTRAINTS);
        }
        final Salary modelSalary = new Salary(salary);

        if (!AnnualLeave.isValidAnnualLeave(getTotalNumOfLeaves(annualLeave))) {
            throw new IllegalValueException(AnnualLeave.MESSAGE_CONSTRAINTS);
        }
        final AnnualLeave modelAnnualLeave = new AnnualLeave(getTotalNumOfLeaves(annualLeave));
        modelAnnualLeave.setLeaveList(stringToLeaveListConverter(annualLeave));

        final AttendanceStorage modelAttendanceStorage = new AttendanceStorage(attendanceStorage);

        final PayrollStorage modelPayrollStorage = new PayrollStorage(payrollStorage);

        return new Person(modelName, modelPhone, modelEmail, modelAddress, modelBankAccount, modelJoinDate, modelSalary,
                modelAnnualLeave, modelAttendanceStorage, modelPayrollStorage);
    }

    public List<LocalDate> stringToLeaveListConverter(String annualLeave) {
        List<LocalDate> leaveList = new ArrayList<>();
        String args = annualLeave.trim();
        String[] dates = args.split(" \\| ");

        for (int i = 1; i < dates.length; i++) {
            System.out.println(ParserUtil.stringToDate(dates[i]));
            leaveList.add(ParserUtil.stringToDate(dates[i]));
        }
        return leaveList;
    }

    public String getTotalNumOfLeaves(String annualLeave) {
        String args = annualLeave.trim();
        String[] dates = args.split(" \\| ");
        return dates[0];
    }

}

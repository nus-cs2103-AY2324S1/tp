package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ManageHr;
import seedu.address.model.ReadOnlyManageHr;
import seedu.address.model.employee.Address;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Leave;
import seedu.address.model.employee.Phone;
import seedu.address.model.employee.Role;
import seedu.address.model.employee.Salary;
import seedu.address.model.name.Name;

/**
 * Contains utility methods for populating {@code ManageHr} with sample data.
 */
public class SampleDataUtil {
    public static Employee[] getSampleEmployees() {
        return new Employee[] {
            new Employee(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), new Salary("2000"), new Leave("21"),
                new Role("manager"), getNameSet(), getNameSet("investment")),
            new Employee(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Salary("345400"),
                new Leave("35"), new Role("manager"), getNameSet("Alex Yeoh"),
                getNameSet("R&D", "investment")),
            new Employee(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), new Salary("123200"), new Leave("14"),
                new Role("subordinate"), getNameSet(), getNameSet("logistics")),
            new Employee(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), new Salary("800"), new Leave("14"),
                new Role("subordinate"), getNameSet("Alex Yeoh"), getNameSet("Production")),
            new Employee(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), new Salary("1800"), new Leave("14"),
                new Role("subordinate"), getNameSet(), getNameSet("Sales")),
            new Employee(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), new Salary("0"), new Leave("0"),
                new Role("subordinate"), getNameSet(), getNameSet("Not-confirmed"))
        };
    }

    public static ReadOnlyManageHr getSampleManageHr() {
        ManageHr sampleAb = new ManageHr();
        for (Employee sampleEmployee : getSampleEmployees()) {
            sampleAb.addEmployee(sampleEmployee);
        }
        return sampleAb;
    }

    /**
     * Returns a name set containing the list of strings given.
     */
    public static Set<Name> getNameSet(String... strings) {
        return Arrays.stream(strings)
                .map(Name::new)
                .collect(Collectors.toSet());
    }
}

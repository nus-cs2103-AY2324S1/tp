package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.department.Department;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.Id;
import seedu.address.model.employee.Name;
import seedu.address.model.employee.Phone;
import seedu.address.model.employee.Position;
import seedu.address.model.employee.Salary;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Employee[] getSampleEmployees() {
        return new Employee[] {
            new Employee(new Name("Alex Yeoh"), new Position("Software engineer"),
                new Id("EID1234-5678"),
                new Phone("87438807"),
                new Email("alexyeoh@example.com"),
            getDepartmentSet("IT"),
                new Salary("$8,500")),

            new Employee(new Name("Bernice Yu"), new Position("Systems analyst"),
                new Id("EID5678-1234"),
                new Phone("99272758"),
                new Email("berniceyu@example.com"),
            getDepartmentSet("IT"),
                new Salary("$6,800")),

            new Employee(new Name("Charlotte Oliveiro"), new Position("Marketing executive"),
                new Id("EID2023-1234"),
                new Phone("93210283"),
                new Email("charlotte@example.com"),
            getDepartmentSet("Marketing"),
                new Salary("$8,000")),

            new Employee(new Name("David Li"), new Position("Operations manager"),
                new Id("EID2023-5678"),
                new Phone("91031282"),
                new Email("lidavid@example.com"),
            getDepartmentSet("Finance"),
                new Salary("$7,900")),

            new Employee(new Name("Irfan Ibrahim"), new Position("Software engineer"),
                new Id("EID2024-1234"),
                new Phone("92492021"),
                new Email("irfan@example.com"),
            getDepartmentSet("IT"),
                new Salary("$8,500")),

            new Employee(new Name("Roy Balakrishnan"), new Position("Graphic design intern"),
                new Id("EID2024-5678"),
                new Phone("92624417"),
                new Email("royb@example.com"),
            getDepartmentSet("Marketing"),
                new Salary("$1,000"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Employee sampleEmployee : getSampleEmployees()) {
            sampleAb.addEmployee(sampleEmployee);
        }
        return sampleAb;
    }

    /**
     * Returns a department set containing the list of strings given.
     */
    public static Set<Department> getDepartmentSet(String... strings) {
        return Arrays.stream(strings)
            .map(Department::new)
            .collect(Collectors.toSet());
    }
}

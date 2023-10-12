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

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Employee[] getSampleEmployees() {
        return new Employee[] {
            new Employee(new Name("Alex Yeoh"), new Position("software engineer"),
                new Id("EID-1234-5678"),
                new Phone("87438807"),
                new Email("alexyeoh@example.com"),
            getDepartmentSet("friends")),
            new Employee(new Name("Bernice Yu"), new Position("software engineer"),
                new Id("EID-5678-1234"),
                new Phone("99272758"),
                new Email("berniceyu@example.com"),
            getDepartmentSet("colleagues", "friends")),
            new Employee(new Name("Charlotte Oliveiro"), new Position("software engineer"),
                new Id("EID-2023-1234"),
                new Phone("93210283"),
                new Email("charlotte@example.com"),
            getDepartmentSet("neighbours")),
            new Employee(new Name("David Li"), new Position("software engineer"),
                new Id("EID-2023-5678"),
                new Phone("91031282"),
                new Email("lidavid@example.com"),
            getDepartmentSet("family")),
            new Employee(new Name("Irfan Ibrahim"), new Position("software engineer"),
                new Id("EID-2024-1234"),
                new Phone("92492021"),
                new Email("irfan@example.com"),
            getDepartmentSet("classmates")),
            new Employee(new Name("Roy Balakrishnan"), new Position("software engineer"),
                new Id("EID-2024-5678"),
                new Phone("92624417"),
                new Email("royb@example.com"),
            getDepartmentSet("colleagues"))
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

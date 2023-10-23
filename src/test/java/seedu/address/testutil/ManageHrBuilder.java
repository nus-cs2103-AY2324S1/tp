package seedu.address.testutil;

import seedu.address.model.ManageHr;
import seedu.address.model.employee.Employee;

/**
 * A utility class to help with building ManageHR objects.
 * Example usage: <br>
 *     {@code ManageHr ab = new ManageHrBuilder().withEmployee("John", "Doe").build();}
 */
public class ManageHrBuilder {

    private ManageHr manageHr;

    public ManageHrBuilder() {
        manageHr = new ManageHr();
    }

    public ManageHrBuilder(ManageHr manageHr) {
        this.manageHr = manageHr;
    }

    /**
     * Adds a new {@code Employee} to the {@code ManageHr} that we are building.
     */
    public ManageHrBuilder withEmployee(Employee employee) {
        manageHr.addEmployee(employee);
        return this;
    }

    public ManageHr build() {
        return manageHr;
    }
}

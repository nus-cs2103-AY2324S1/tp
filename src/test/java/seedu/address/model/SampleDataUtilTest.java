package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.department.Department;
import seedu.address.model.employee.Employee;
import seedu.address.model.util.SampleDataUtil;

public class SampleDataUtilTest {
    @Test
    public void getSampleManageHr_verifyInput_success() {
        Employee[] sampleEmployees = SampleDataUtil.getSampleEmployees();
        Department[] sampleDepartments = SampleDataUtil.getSampleDepartments();
        ReadOnlyManageHr readOnlyManageHr = SampleDataUtil.getSampleManageHr();
        ManageHr manageHr1 = new ManageHr();

        for (Department sampleDepartment : sampleDepartments) {
            manageHr1.addDepartment(sampleDepartment);
        }
        assertEquals(manageHr1.getDepartmentList(), readOnlyManageHr.getDepartmentList());

        for (Employee sampleEmployee : sampleEmployees) {
            manageHr1.addEmployee(sampleEmployee);
        }
        assertEquals(manageHr1.getEmployeeList(), readOnlyManageHr.getEmployeeList());
    }
}

package seedu.address.model.department;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.name.DepartmentName;

public class DepartmentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Department(new DepartmentName(null)));
    }

    @Test
    public void constructor_invalidDepartmentName_throwsIllegalArgumentException() {
        String invalidDepartmentName = "";
        assertThrows(IllegalArgumentException.class, () -> new Department(invalidDepartmentName));
    }
}

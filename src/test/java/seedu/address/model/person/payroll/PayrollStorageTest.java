package seedu.address.model.person.payroll;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Payroll;
import seedu.address.testutil.TypicalPersons;

public class PayrollStorageTest {
    @Test
    public void getPayrollWithStartDate_sameDate_returnPayroll() {
        PayrollStorage payrollStorage = TypicalPersons.PAYROLL.getPayrollStorage();
        Payroll payroll = payrollStorage.getLatestPayroll();

        assertEquals(payroll, payrollStorage.getPayrollWithStartDate(LocalDate.parse("2023-11-01")));
    }

}

package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Benefit;
import seedu.address.model.person.Deduction;
import seedu.address.model.person.Reason;
import seedu.address.model.person.Salary;

public class JsonAdaptedPayrollTest {

    private static final String INVALID_SALARY = "200";
    private static final String INVALID_STARTDATE = "11/2023";
    private static final String INVALID_ENDDATE = "38/11/2023";
    private static final String INVALID_PAYMENTDATE = "1234";
    private static final String INVALID_BENEFIT = "200";
    private static final String INVALID_DEDUCTION = "200";

    private static final String VALID_SALARY = "200.00";
    private static final String VALID_STARTDATE = BENSON.getLatestPayroll().getStartDateString();
    private static final String VALID_ENDDATE = BENSON.getLatestPayroll().getEndDateString();
    private static final String VALID_PAYMENTDATE = BENSON.getLatestPayroll().getPaymentDateString();
    private static final Benefit VALID_BENEFIT = new Benefit("20.00", Reason.ANNUAL_BONUS);
    private static final Deduction VALID_DEDUCTION = new Deduction("20.00", Reason.ABSENCE);
    private static final ArrayList<JsonAdaptedBenefit> VALID_BENEFITS =
            new ArrayList<>(Arrays.asList(new JsonAdaptedBenefit(VALID_BENEFIT)));
    private static final ArrayList<JsonAdaptedDeduction> VALID_DEDUCTIONS =
            new ArrayList<>(Arrays.asList(new JsonAdaptedDeduction(VALID_DEDUCTION)));

    @Test
    public void toModelType_validPayrollDetails_returnsPayroll() {
        JsonAdaptedPayroll payroll = new JsonAdaptedPayroll(BENSON.getLatestPayroll());
        assertEquals(BENSON.getLatestPayroll(), payroll.toModelType());
    }

    @Test
    public void toModelType_invalidSalary_throwsIllegalArgumentException() {
        JsonAdaptedPayroll payroll = new JsonAdaptedPayroll(INVALID_SALARY, VALID_STARTDATE,
                VALID_ENDDATE, VALID_PAYMENTDATE, VALID_BENEFITS, VALID_DEDUCTIONS);
        String expectedMessage = Salary.MESSAGE_CONSTRAINTS;

        assertThrows(IllegalArgumentException.class, expectedMessage, payroll::toModelType);
    }

    @Test
    public void toModelType_nullSalary_throwsNullPointerException() {
        JsonAdaptedPayroll payroll = new JsonAdaptedPayroll(null, VALID_STARTDATE,
                VALID_ENDDATE, VALID_PAYMENTDATE, VALID_BENEFITS, VALID_DEDUCTIONS);
        assertThrows(NullPointerException.class, payroll::toModelType);
    }

    @Test
    public void toModelType_invalidStartDate_throwsDateTimeParseException() {
        JsonAdaptedPayroll payroll = new JsonAdaptedPayroll(VALID_SALARY, INVALID_STARTDATE,
                VALID_ENDDATE, VALID_PAYMENTDATE, VALID_BENEFITS, VALID_DEDUCTIONS);
        assertThrows(DateTimeParseException.class, payroll::toModelType);
    }

    @Test
    public void toModelType_nullStartDate_throwsNullPointerException() {
        JsonAdaptedPayroll payroll = new JsonAdaptedPayroll(VALID_SALARY, null,
                VALID_ENDDATE, VALID_PAYMENTDATE, VALID_BENEFITS, VALID_DEDUCTIONS);
        assertThrows(NullPointerException.class, payroll::toModelType);
    }

    @Test
    public void toModelType_invalidEndDate_throwsDateTimeParseException() {
        JsonAdaptedPayroll payroll = new JsonAdaptedPayroll(VALID_SALARY, VALID_STARTDATE,
                INVALID_ENDDATE, VALID_PAYMENTDATE, VALID_BENEFITS, VALID_DEDUCTIONS);
        assertThrows(DateTimeParseException.class, payroll::toModelType);
    }

    @Test
    public void toModelType_nullEndDate_throwsNullPointerException() {
        JsonAdaptedPayroll payroll = new JsonAdaptedPayroll(VALID_SALARY, VALID_STARTDATE,
                null, VALID_PAYMENTDATE, VALID_BENEFITS, VALID_DEDUCTIONS);
        assertThrows(NullPointerException.class, payroll::toModelType);
    }

    @Test
    public void toModelType_invalidPaymentDate_throwsDateTimeParseException() {
        JsonAdaptedPayroll payroll = new JsonAdaptedPayroll(VALID_SALARY, VALID_STARTDATE,
                VALID_ENDDATE, INVALID_PAYMENTDATE, VALID_BENEFITS, VALID_DEDUCTIONS);
        assertThrows(DateTimeParseException.class, payroll::toModelType);
    }

    @Test
    public void toModelType_nullPaymentDate_throwsNullPointerException() {
        JsonAdaptedPayroll payroll = new JsonAdaptedPayroll(VALID_SALARY, VALID_STARTDATE,
                VALID_ENDDATE, null, VALID_BENEFITS, VALID_DEDUCTIONS);
        assertThrows(NullPointerException.class, payroll::toModelType);
    }

    @Test
    public void toModelType_invalidBenefits_throwsIllegalArgumentException() {
        List<JsonAdaptedBenefit> invalidBenefits = new ArrayList<>(VALID_BENEFITS);
        invalidBenefits.add(new JsonAdaptedBenefit(INVALID_BENEFIT, "ANNUAL_BONUS"));
        JsonAdaptedPayroll payroll =
                new JsonAdaptedPayroll(VALID_SALARY, VALID_STARTDATE,
                        VALID_ENDDATE, VALID_ENDDATE, invalidBenefits, VALID_DEDUCTIONS);
        assertThrows(IllegalArgumentException.class, payroll::toModelType);
    }

    @Test
    public void toModelType_invalidDeductions_throwsIllegalArgumentException() {
        List<JsonAdaptedDeduction> invalidDeductions = new ArrayList<>(VALID_DEDUCTIONS);
        invalidDeductions.add(new JsonAdaptedDeduction(INVALID_DEDUCTION, "ABSENCE"));
        JsonAdaptedPayroll payroll =
                new JsonAdaptedPayroll(VALID_SALARY, VALID_STARTDATE,
                        VALID_ENDDATE, VALID_ENDDATE, VALID_BENEFITS, invalidDeductions);
        assertThrows(IllegalArgumentException.class, payroll::toModelType);
    }
}

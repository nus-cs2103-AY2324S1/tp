package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.Map;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Payroll;

class PaySlipGeneratorTest {

    @Test
    void getFileName() {
        assertTrue("alice_pauline.pdf".equals(PaySlipGenerator.getFileName(ALICE)));
        assertTrue("benson_meier.pdf".equals(PaySlipGenerator.getFileName(BENSON)));

        assertFalse("Alice-Pauline.pdf".equals(PaySlipGenerator.getFileName(ALICE)));
        assertFalse("BensonMeier.pdf".equals(PaySlipGenerator.getFileName(BENSON)));
    }

    @Test
    void getFieldMap() {
        ALICE.addPayroll(new Payroll(ALICE.getSalary()));
        Payroll payroll = ALICE.getLatestPayroll();
        Map<String, String> fieldMap = PaySlipGenerator.getFieldMap(ALICE, payroll);

        assertTrue(fieldMap.containsKey("employerName"));
        assertTrue(fieldMap.containsKey("employeeName"));
        assertTrue(fieldMap.containsKey("basicPay"));
        assertTrue(fieldMap.containsKey("totalDeductions"));
        assertTrue(fieldMap.containsKey("netPay"));
        assertTrue(fieldMap.containsKey("totalAllowances"));
        assertTrue(fieldMap.containsKey("grossPay"));
        assertTrue(fieldMap.containsKey("CPFDeduction"));
        assertTrue(fieldMap.containsKey("otherAdditionalPayments"));
        assertFalse(fieldMap.containsKey("additionalPaymentReason1"));
        assertFalse(fieldMap.containsKey("additionalPayment1"));
        assertFalse(fieldMap.containsKey("deductionReason1"));
        assertFalse(fieldMap.containsKey("deduction1"));
        assertFalse(fieldMap.containsKey("allowanceReason1"));
        assertFalse(fieldMap.containsKey("allowance1"));
        assertTrue(fieldMap.containsKey("paymentStartDate"));
        assertTrue(fieldMap.containsKey("paymentEndDate"));
        assertTrue(fieldMap.containsKey("dateOfPayment"));

        assertTrue(fieldMap.get("employerName").equals("XXX Limited"));
        assertTrue(fieldMap.get("employeeName").equals("Alice Pauline"));
        assertTrue(fieldMap.get("basicPay").equals("1500.00"));
        assertTrue(fieldMap.get("totalDeductions").equals("0.00"));
        assertTrue(fieldMap.get("netPay").equals("1500.00"));
        assertTrue(fieldMap.get("totalAllowances").equals("0.00"));
        assertTrue(fieldMap.get("grossPay").equals("1500.00"));
        assertTrue(fieldMap.get("CPFDeduction").equals("0.00"));
        assertTrue(fieldMap.get("otherAdditionalPayments").equals("0.00"));
        assertTrue(fieldMap.get("paymentStartDate").equals(payroll.getStartDateString()));
        assertTrue(fieldMap.get("paymentEndDate").equals(payroll.getEndDateString()));
        assertTrue(fieldMap.get("dateOfPayment").equals(payroll.getPaymentDateString()));
    }
}

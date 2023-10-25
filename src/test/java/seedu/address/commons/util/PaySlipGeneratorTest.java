package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.Map;

import org.junit.jupiter.api.Test;

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
        Map<String, String> fieldMap = PaySlipGenerator.getFieldMap(ALICE);

        assertTrue(fieldMap.containsKey("employerName"));
        assertTrue(fieldMap.containsKey("employeeName"));
        assertTrue(fieldMap.containsKey("basicPay"));
        assertTrue(fieldMap.containsKey("totalDeductions"));
        assertTrue(fieldMap.containsKey("netPay"));

        assertTrue(fieldMap.get("employerName").equals("XXX Limited"));
        assertTrue(fieldMap.get("employeeName").equals("Alice Pauline"));
        assertTrue(fieldMap.get("basicPay").equals("1500.00"));
        assertTrue(fieldMap.get("totalDeductions").equals("0.00"));
        assertTrue(fieldMap.get("netPay").equals("1500.00"));
    }
}

package seedu.address.model.employee;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalEmployees.ALICE;
import static seedu.address.testutil.TypicalEmployees.BOB;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ReportTest {


    @Test
    public void constructor_nullEmployee_throwsNullPointerException() {
        // Employee field null
        assertThrows(NullPointerException.class, () -> new Report(
                null,
                ALICE.getOvertimeHours().value,
                ALICE.getOvertimePay(),
                ALICE.getNumOfLeaves(),
                ALICE.getRemarkList()
        ));
    }

    @Test
    public void toStringMethod() {
        Report report = new Report(
                ALICE,
                ALICE.getOvertimeHours().value,
                ALICE.getOvertimePay(),
                ALICE.getNumOfLeaves(),
                ALICE.getRemarkList()
        );
        String expectedString = "EMPLOYEE REPORT\n"
                + "\nDate: " + LocalDate.now()
                + "\n====================================\n"
                + "Employee Name: " + report.employee.getName().fullName
                + "\nOvertime Hours: " + report.overtimeHours
                + "\nOvertime Pay: " + report.overtimePay
                + "\nNumber of Leaves: " + report.numOfLeaves
                + "\nRemarks:\n" + report.remarkList;
        assertTrue(report.toString().equals(expectedString));
    }

    @Test
    public void equals() {
        Report report = new Report(
                ALICE,
                ALICE.getOvertimeHours().value,
                ALICE.getOvertimePay(),
                ALICE.getNumOfLeaves(),
                ALICE.getRemarkList()
        );

        // same values -> returns true
        Report reportCopy = new Report(
                ALICE,
                ALICE.getOvertimeHours().value,
                ALICE.getOvertimePay(),
                ALICE.getNumOfLeaves(),
                ALICE.getRemarkList()
        );
        assertTrue(report.equals(reportCopy));

        // same object -> returns true
        assertTrue(report.equals(report));

        // null -> returns false
        assertFalse(report.equals(null));

        // different type -> returns false
        assertFalse(report.equals(5));

        // different report -> returns false
        assertFalse(report.equals(new Report(
                BOB,
                BOB.getOvertimeHours().value,
                BOB.getOvertimePay(),
                BOB.getNumOfLeaves(),
                BOB.getRemarkList()
        )));

        // different employee -> returns false
        assertFalse(report.equals(new Report(
                ALICE,
                BOB.getOvertimeHours().value,
                BOB.getOvertimePay(),
                BOB.getNumOfLeaves(),
                BOB.getRemarkList()
        )));

        // different overtime hours -> returns false
        assertFalse(report.equals(new Report(
                ALICE,
                BOB.getOvertimeHours().value,
                ALICE.getOvertimePay(),
                ALICE.getNumOfLeaves(),
                ALICE.getRemarkList()
        )));

        // different overtime pay -> returns false
        assertFalse(report.equals(new Report(
                ALICE,
                ALICE.getOvertimeHours().value,
                BOB.getOvertimePay(),
                ALICE.getNumOfLeaves(),
                ALICE.getRemarkList()
        )));

        // different number of leaves -> returns false
        assertFalse(report.equals(new Report(
                ALICE,
                ALICE.getOvertimeHours().value,
                ALICE.getOvertimePay(),
                BOB.getNumOfLeaves(),
                ALICE.getRemarkList()
        )));

        // different remark list -> returns false
        assertFalse(report.equals(new Report(
                ALICE,
                ALICE.getOvertimeHours().value,
                ALICE.getOvertimePay(),
                ALICE.getNumOfLeaves(),
                BOB.getRemarkList()
        )));
    }
}

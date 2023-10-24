package seedu.address.model.person;

import seedu.address.logic.commands.PayrollCommand;

public class Payroll {
    private Salary salary;

    public Payroll(Salary salary) {
        this.salary = salary;
    }

    public void calculatePayroll() {

    }
}

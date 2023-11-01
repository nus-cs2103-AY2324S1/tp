package seedu.address.model.person.payroll;

import java.time.LocalDate;
import java.util.ArrayList;

import seedu.address.model.person.Payroll;

public class PayrollStorage {
    private ArrayList<Payroll> payrolls;

    public PayrollStorage() {
        this.payrolls = new ArrayList<>();
    }

    public PayrollStorage(ArrayList<Payroll> payrolls) {
        this.payrolls = payrolls;
    }

    public void add(Payroll payroll) {
        this.payrolls.add(payroll);
    }

    public ArrayList<Payroll> getPayrolls() {
        return this.payrolls;
    }

    public Payroll getLatestPayroll() {
        return this.payrolls.get(this.payrolls.size() - 1);
    }

    public Payroll getPayrollWithStartDate(LocalDate date) {
        for (Payroll payroll: payrolls) {
            if (payroll.getStartDate().getMonth().equals(date.getMonth())
                    && payroll.getStartDate().getYear() == date.getYear()) {
                return payroll;
            }
        }
        return null;
    }

    public ArrayList<String> getValue() {
        ArrayList<String> value = new ArrayList<>();
        return value;
    }
}

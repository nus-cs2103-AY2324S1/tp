package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.FinancesBook;
import seedu.address.model.finance.Commission;
import seedu.address.model.finance.Expense;
import seedu.address.model.finance.Finance;

/**
 * A utility class containing a list of {@code Finance} objects to be used in tests.
 */
public class TypicalFinances {
    public static final Commission COMMISSION_TEN_FROM_T = new CommissionBuilder()
            .withAmount("10")
            .withPerson("T")
            .withDescription("Test commission")
            .build();
    public static final Commission COMMISSION_TWENTY_FROM_H = new CommissionBuilder()
            .withAmount("20")
            .withPerson("H")
            .withDescription("Payday")
            .build();

    public static final Expense EXPENSE_TWENTY_TO_G = new ExpenseBuilder()
            .withAmount("20")
            .withPerson("G")
            .withDescription("Test commission")
            .build();
    public static final Expense EXPENSE_THIRTY_TO_K = new ExpenseBuilder()
            .withAmount("30")
            .withPerson("K")
            .withDescription("Extra")
            .build();
    public static final Commission COMMISSION_FROM_ALICE = new CommissionBuilder().withPerson("ALICE")
            .withAmount("80")
            .withDescription("Ninjavan UI payment").build();
    public static final Commission COMMISSION_FROM_BOB = new CommissionBuilder().withPerson("BOB")
            .withAmount("1000")
            .withDescription("Wedding photoshoot").build();

    /**
     * Returns an {@code FinancesBook} with all the typical finances.
     */
    public static FinancesBook getTypicalFinancesBook() {
        FinancesBook fb = new FinancesBook();
        for (Finance finance : getTypicalFinances()) {
            fb.addFinance(finance);
        }
        return fb;
    }

    /**
     * Returns an {@code AddressBook} with all the typical expenses.
     */
    public static FinancesBook getTypicalExpenseOnlyBook() {
        FinancesBook fb = new FinancesBook();
        for (Finance finance : getTypicalExpensesOnly()) {
            fb.addFinance(finance);
        }
        return fb;
    }

    /**
     * Returns an {@code AddressBook} with all the typical commissions.
     */
    public static FinancesBook getTypicalCommissionOnlyBook() {
        FinancesBook fb = new FinancesBook();
        for (Finance finance : getTypicalCommissionsOnly()) {
            fb.addFinance(finance);
        }
        return fb;
    }

    public static List<Finance> getTypicalFinances() {
        return new ArrayList<>(Arrays.asList(COMMISSION_TEN_FROM_T,
                COMMISSION_TWENTY_FROM_H, EXPENSE_TWENTY_TO_G, EXPENSE_THIRTY_TO_K));
    }

    public static List<Finance> getTypicalExpensesOnly() {
        return new ArrayList<>(Arrays.asList(EXPENSE_TWENTY_TO_G, EXPENSE_THIRTY_TO_K));
    }

    public static List<Finance> getTypicalCommissionsOnly() {
        return new ArrayList<>(Arrays.asList(COMMISSION_TEN_FROM_T, COMMISSION_TWENTY_FROM_H));
    }
}

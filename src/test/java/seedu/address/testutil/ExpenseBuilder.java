package seedu.address.testutil;

import static seedu.address.testutil.CommissionBuilder.DEFAULT_EXPENSE_TIME_DUE;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.model.finance.Amount;
import seedu.address.model.finance.Description;
import seedu.address.model.finance.Expense;
import seedu.address.model.finance.TimeDue;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TelegramName;

/**
 * A utility class to help with building Expense objects.
 */
public class ExpenseBuilder {
    public static final String DEFAULT_AMOUNT = "900";
    public static final String DEFAULT_DESCRIPTION = "Test Description";
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_TIME_DUE = "27-10-2023 01:57";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private Person client;
    private Amount amount;
    private Description description;
    private TimeDue timeDue;

    /**
     * Creates a {@code ExpenseBuilder} with the default details.
     */
    public ExpenseBuilder() {
        this.client = new Person(new Name(DEFAULT_NAME), new Phone("00000"), new Email("filler@email.com"),
                new Address(""), new Company(""), new TelegramName(""));
        this.amount = new Amount(DEFAULT_AMOUNT);
        this.description = new Description(DEFAULT_DESCRIPTION);
        this.timeDue = new TimeDue(LocalDateTime.parse(DEFAULT_EXPENSE_TIME_DUE, DATE_TIME_FORMATTER));
    }

    /**
     * creates a {@code ExpenseBuilder} with given expense object.
     */

    public ExpenseBuilder(Expense expense) {
        this.client = expense.getClient();
        this.amount = expense.getAmount();
        this.description = expense.getDescription();
        this.timeDue = expense.getTimeDue();
    }

    /**
     * Sets the {@code Amount} of the {@code Expense} that we are building.
     */
    public ExpenseBuilder withAmount(String amount) {
        this.amount = new Amount(amount);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Expense} that we are building.
     */
    public ExpenseBuilder withDescription(String description) {
        if (!description.trim().equals("")) {
            this.description = new Description(description);
        }
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Person} of the {@code Expense} that we are building.
     */
    public ExpenseBuilder withPerson(String clientName) {
        if (clientName == null) {
            return this;
        }
        this.client = new Person(new Name(clientName), new Phone("00000"), new Email("filler@email.com"),
                new Address(""), new Company(""), new TelegramName(""));
        return this;
    }

    /**
     * Sets the {@code TimeDue} of the {@code Expense} that we are building.
     */
    public ExpenseBuilder withTimeDue(String timeDue) {
        this.timeDue = new TimeDue(LocalDateTime.parse(timeDue, DATE_TIME_FORMATTER));
        return this;
    }

    public Expense build() {
        return new Expense(amount, client, description, timeDue);
    }
}

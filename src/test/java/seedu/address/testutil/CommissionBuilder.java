package seedu.address.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.model.finance.Amount;
import seedu.address.model.finance.Commission;
import seedu.address.model.finance.Description;
import seedu.address.model.finance.TimeDue;
import seedu.address.model.person.Address;
import seedu.address.model.person.Company;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TelegramName;

/**
 * A utility class to help with building Commission objects.
 */
public class CommissionBuilder {
    public static final String DEFAULT_AMOUNT = "900";
    public static final String DEFAULT_DESCRIPTION = "Test Description";
    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_COMMISSION_TIME_DUE = "27-10-2023 01:57";
    public static final String DEFAULT_EXPENSE_TIME_DUE = "30-10-2023 01:57";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private Person client;
    private Amount amount;
    private Description description;
    private TimeDue timeDue;

    /**
     * Creates a {@code CommissionBuilder} with the default details.
     */
    public CommissionBuilder() {
        this.client = Person.makeDummyWithName(DEFAULT_NAME);
        this.amount = new Amount(DEFAULT_AMOUNT);
        this.description = new Description(DEFAULT_DESCRIPTION);
        this.timeDue = new TimeDue(LocalDateTime.parse(DEFAULT_COMMISSION_TIME_DUE, DATE_TIME_FORMATTER));
    }

    /**
     * Creates a {@code CommissionBuilder} with given commission object.
     */
    public CommissionBuilder(Commission commission) {
        this.client = commission.getClient();
        this.amount = commission.getAmount();
        this.description = commission.getDescription();
        this.timeDue = commission.getTimeDue();
    }

    /**
     * Sets the {@code Amount} of the {@code Commission} that we are building.
     */
    public CommissionBuilder withAmount(String amount) {
        this.amount = new Amount(amount);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Commission} that we are building.
     * No changes if the description is an empty string.
     */
    public CommissionBuilder withDescription(String description) {
        if (!description.trim().equals("")) {
            this.description = new Description(description);
        }
        return this;
    }

    /**
     * Sets the {@code Person} of the {@code Commission} that we are building.
     */
    public CommissionBuilder withPerson(String clientName) {
        this.client = new Person(new Name(clientName), new Phone("00000"), new Email("filler@email.com"),
                new Address(""), new Company(""), new TelegramName(""));
        return this;
    }

    /**
     * Sets the {@code TimeDue} of the {@code Commission} that we are building.
     */
    public CommissionBuilder withTimeDue(String timeDue) {
        this.timeDue = new TimeDue(LocalDateTime.parse(timeDue, DATE_TIME_FORMATTER));
        return this;
    }

    /**
     * Sets the {@code TimeDue} of the {@code Commission} that we are building using a LocalDateTime.
     */
    public CommissionBuilder withTimeDue(LocalDateTime time) {
        this.timeDue = new TimeDue(time);
        return this;
    }

    public Commission build() {
        return new Commission(amount, client, description, timeDue);
    }
}

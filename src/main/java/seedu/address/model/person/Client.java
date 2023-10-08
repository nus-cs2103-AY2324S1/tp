package seedu.address.model.person;

import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Client in the address book.
 * A client is a person with a name, phone, email, telegram, profession, income, and details.
 */
public class Client extends Person {
    // Additional fields on top of Person
    private final String telegram;
    private final String profession;
    private final Number income;
    private final String details;

    /**
     * Person-related fields must be present and not null.
     */
    public Client(Name name, Phone phone, Email email, Address address,
                  Set<Tag> tags, String telegram, String profession,
                  Number income, String details) {
        super(name, phone, email, address, tags);
        this.telegram = telegram;
        this.profession = profession;
        this.income = income;
        this.details = details;
    }

    public String getTelegram() {
        return telegram;
    }

    public String getProfession() {
        return profession;
    }

    public Number getIncome() {
        return income;
    }

    public String getDetails() {
        return details;
    }
}

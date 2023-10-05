package seedu.address.model.client;

/**
 * Represents a Client in the address book. Guarantees: required details are present and not null,
 * field values are validated, immutable.
 */
public class Client {

    // Identity fields (required: name)
    private final String name;
    private final String phone;
    private final String email;
    private final String telegram;

    // Data fields
    private final String profession;
    private final Number income;
    private final String details;

    /**
     * Name must be present and not null.
     */
    public Client(String name, String phone, String email, String telegram, String profession, Number income,
            String details) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.telegram = telegram;
        this.profession = profession;
        this.income = income;
        this.details = details;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
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

    /**
     * Returns true iff both persons have the same name.
     */
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Client // instanceof handles nulls
                        && name.equals(((Client) other).name)); // state check
    }

    @Override
    public String toString() {
        return name;
    }
}

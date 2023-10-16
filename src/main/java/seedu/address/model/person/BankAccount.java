package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's banAccount in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidBankAccount(String)}
 */
public class BankAccount {
    public static final String MESSAGE_CONSTRAINTS =
            "Bank account should only contain numerical digits. It should not contain dashes or spaces.";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "\\d{5,17}";

    public final String value;

    /**
     * Constructs a {@code BankAccount}.
     *
     * @param bankAccount A valid bank account.
     */
    public BankAccount(String bankAccount) {
        requireNonNull(bankAccount);
        checkArgument(isValidBankAccount(bankAccount), MESSAGE_CONSTRAINTS);
        value = bankAccount;
    }

    /**
     * Returns true if a given string is a valid bank account.
     */
    public static boolean isValidBankAccount(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof BankAccount)) {
            return false;
        }

        BankAccount otherBankAccount = (BankAccount) other;
        return value.equals(otherBankAccount.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

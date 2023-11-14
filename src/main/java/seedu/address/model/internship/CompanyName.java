package seedu.address.model.internship;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Internship's company name in Flagship.
 * Guarantees: immutable; is valid as declared in {@link #isValidCompanyName(String)}
 */
public class CompanyName implements Comparable<CompanyName> {

    public static final String MESSAGE_CONSTRAINTS = "The company name should fulfill all the following conditions:\n"
            + "1. Contains only english alphanumeric characters and spaces\n"
            + "2. Should not be blank\n"
            + "3. Cannot be longer than 200 characters";

    public static final String VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    private final String companyName;

    /**
     * Constructs a {@code CompanyName}.
     *
     * @param companyName A valid company name.
     */
    public CompanyName(String companyName) {
        requireNonNull(companyName);
        checkArgument(isValidCompanyName(companyName), MESSAGE_CONSTRAINTS);
        this.companyName = companyName;
    }

    /**
     * Verifies whether the given string constitutes a valid company name. Company names longer than 200 characters
     * are rejected to prevent overflows in the UI. The given string is also stripped to defensively guard against
     * instances where leading or trailing spaces are inserted when user directly modifies the internship.json file.
     * This is important so that the given string does not fail the regex check.
     *
     * @param test The given string to be tested.
     * @return A boolean representing whether the string input is valid.
     */
    public static boolean isValidCompanyName(String test) {
        String strippedTest = test.strip();
        if (strippedTest.length() > 200) {
            return false;
        }
        return strippedTest.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.companyName;
    }

    /**
     * Verifies whether this company name is equals to the given object. This block of code defensively guard against
     * duplicate company names that only differ in terms of the number of leading, trailing or internal spaces. It is
     * especially important in cases where users edit the internship.json file directly.
     * @param other The given object to check for equality against.
     * @return A boolean representing whether this company name is equals to the object.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CompanyName)) {
            return false;
        }

        CompanyName otherCompanyName = (CompanyName) other;


        String thisStrippedCompanyNameString = this.companyName
                .strip()
                .replaceAll("\\s+", " ");
        String otherStrippedCompanyNameString = otherCompanyName.companyName
                .strip()
                .replaceAll("\\s+", " ");

        return thisStrippedCompanyNameString.equalsIgnoreCase(otherStrippedCompanyNameString);
    }

    /**
     * Returns the raw string value of the Internship's company name. This method is used exclusively by the filter
     * command. We have both a toString() method and a getCompanyName() method to guard against any potential changes to
     * the toString() string output that will regress the functionalities of filter.
     *
     * @return The raw string value of the Internship's company name.
     */
    public String getCompanyName() {
        return this.companyName;
    }

    @Override
    public int hashCode() {
        return this.companyName.hashCode();
    }

    @Override
    public int compareTo(CompanyName other) {
        return this.companyName.compareToIgnoreCase(other.companyName);
    }
}

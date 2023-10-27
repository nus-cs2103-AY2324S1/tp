package seedu.staffsnap.model.applicant;

import static seedu.staffsnap.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import com.opencsv.bean.CsvBindByName;

import seedu.staffsnap.commons.util.ToStringBuilder;
import seedu.staffsnap.model.interview.Interview;

/**
 * Represents a CSV version of Applicant in the applicant book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class CsvApplicant {
    @CsvBindByName(required = true)
    private final String name;
    @CsvBindByName(required = true)
    private final String phone;
    // Data fields
    @CsvBindByName(required = true)
    private final String email;
    @CsvBindByName(required = true)
    private final String position;

    /**
     * Every field must be present and not null.
     */
    public CsvApplicant(String name, String phone, String email, String position) {
        requireAllNonNull(name, phone, email, position);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.position = position;
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

    public String getPosition() {
        return position;
    }

    /**
     * Returns true if both applicants have the same email or phone number.
     * This defines a weaker notion of equality between two applicants.
     */
    public boolean isSameApplicant(CsvApplicant otherCsvApplicant) {
        if (otherCsvApplicant == this) {
            return true;
        }

        return otherCsvApplicant != null
                && (otherCsvApplicant.getEmail().equals(getEmail())
                || otherCsvApplicant.getPhone().equals(getPhone()));
    }

    /**
     * Returns true if both applicants have the same identity and data fields.
     * This defines a stronger notion of equality between two applicants.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CsvApplicant)) {
            return false;
        }

        CsvApplicant otherCsvApplicant = (CsvApplicant) other;
        return name.equals(otherCsvApplicant.name)
                && phone.equals(otherCsvApplicant.phone)
                && email.equals(otherCsvApplicant.email)
                && position.equals(otherCsvApplicant.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, email, position);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("position", position)
                .toString();
    }
}

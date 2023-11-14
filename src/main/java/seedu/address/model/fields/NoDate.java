package seedu.address.model.fields;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * Should be instantiated in the add-date parser
 * if no date is given
 */
public class NoDate extends Date {
    @Override
    public Optional<LocalDateTime> getDate() {
        return Optional.ofNullable(null);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof NoDate);
    }

    @Override
    public String toSaveData() {
        return null;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public int compareTo(Date o) {
        return 0;
    }
}
